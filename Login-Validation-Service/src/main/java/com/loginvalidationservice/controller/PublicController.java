package com.loginvalidationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loginvalidationservice.admin.UserService;
import com.loginvalidationservice.dto.AppUserRequestDTO;
import com.loginvalidationservice.dto.JWTResponseDTO;
import com.loginvalidationservice.security.JWTUtil;
import com.loginvalidationservice.admin.User;


@RestController
@CrossOrigin(origins= {"http://localhost:8081", "http://localhost:4200"},allowedHeaders = "*")
@RequestMapping("/abc-university/public")
public class PublicController {

	@Autowired
	private UserService appUserService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	//--------------------------------------------------------------------------------LOGIN----------------------------------------------------------------------------------------------------
	@PostMapping("/login")
	public ResponseEntity<JWTResponseDTO> doLogin(@RequestBody AppUserRequestDTO userEntry)throws Exception
	{
		System.out.println("----->> inside public/login "+userEntry);
		
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEntry.getUsername(), userEntry.getPassword()));
			System.out.println();
			
		} catch (Exception e) {
			throw new Exception("Bad credentials ");
		}

		UserDetails userDetails =  appUserService.loadUserByUsername(userEntry.getUsername());
		
		String token = jwtUtil.generateToken(userDetails);
		
		boolean isValid = token!=null?true:false;
		
		JWTResponseDTO jwtResponseDTO = new JWTResponseDTO(token, userEntry.getUsername(), isValid);
		
		return new ResponseEntity<JWTResponseDTO>(jwtResponseDTO, HttpStatus.OK);
	}
	
	//--------------------------------------------------------------------------------REGISTER----------------------------------------------------------------------------------------------------
	@PostMapping("/register")
	public ResponseEntity<User> adduser(@RequestBody User a){
		User registeredUser= appUserService.addUser(a);
		return new ResponseEntity<User>(registeredUser, HttpStatus.OK);
		
	}
	
	
}

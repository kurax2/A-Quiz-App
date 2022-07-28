package com.loginvalidationservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loginvalidationservice.security.JWTUtil;

@RestController
@CrossOrigin(origins= {"http://localhost:8081", "http://localhost:4200"},allowedHeaders = "*")
@RequestMapping("/abc-university/public")
public class TokenValidatorController {
	
	String orgKey="ncs-";
	
	@Autowired
	com.loginvalidationservice.admin.UserService AppUserService;
	
	@Autowired
	JWTUtil jwtUtil;
	

	@GetMapping("/validate")
	public boolean validateToken(HttpServletRequest request)
	{
		String requestedTokenHeader = "ncs-"+request.getHeader("Authorization");
		String userType= "admin"; //request.getHeader("userType")
		
		
		//String requestedTokenHeader = token;
		String username = null;
		String jwtToken = null;
		 
		//System.out.println("\n\n ---->> 1 orgKey "+orgKey+" requestTokenHeader "+requestedTokenHeader);
		if(requestedTokenHeader !=null && requestedTokenHeader.startsWith(orgKey))
		{
			System.out.println("\n\n  ***---->> 2 code Inside filter "+requestedTokenHeader+"\n");
			int cutToken = orgKey.length();	
			jwtToken = requestedTokenHeader.substring(cutToken);
			System.out.println("\n\n  ***---->> 3 JWT Token is  "+jwtToken+"\n");
			
				
				try {
					
					username = jwtUtil.extractUsername(jwtToken);
					System.out.println(" 4 username from token is :- "+username);
					
				} catch (Exception e) {
					System.out.println(" --->> 4.b exception inside extract usernmae "+e);
					return false;
					// ideally should throw Custom Exception
				}
				
				// --- code execute if no exception
				
				
				 if (username != null)
				 {

			            UserDetails userDetails = this.AppUserService.loadUserByUsername(username);
			            System.out.println(" --->> 5 Inside if Username not null "+userDetails);
			            
			            System.out.println(userDetails.getAuthorities());
			            
			            List<GrantedAuthority> allAuthorities = (List<GrantedAuthority>)userDetails.getAuthorities(); 
			            
			            for (GrantedAuthority grantedAuthority : allAuthorities) {
							String authority = grantedAuthority.getAuthority();
							System.out.println("--->> 5.0 authority :- "+authority+" userType "+userType );
							if(authority.equals(userType))
							{
								System.out.println("----->> 5.a user and auth are same ");
								return true;
							}
						}
			            /*
			            if (jwtUtil.validateToken(jwtToken, userDetails)) {
			            	System.out.println(" --->> 6 Inside if jwtToken Validate ");
			                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
			                		new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			               
			                usernamePasswordAuthenticationToken
			                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			                return true;
			            }
			            */
			           
			            	System.out.println("--->> 5.b Inside else ");
			            	return false;
			            
			           
			            
			      }//end if username != null
		}//end if
		else
		{
			System.out.println("2.b Token is not validated ...");
			return false;
			
		}
		
		return false;
	}//end of get mapping

}
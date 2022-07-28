package com.loginvalidationservice.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.loginvalidationservice.admin.UserService;
import com.loginvalidationservice.security.JWTUtil;



/*
 * class configurations are used to filter non login request 
 * and extract JWT token from it.
 * 
 * */

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{

	String orgKey = "ncs-";
	
	@Autowired
	UserService AppUserService;
	
	@Autowired
	JWTUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		/*
		 *Method used to extract the JWT Token 
		 *check starts from our key or not like bearer
		 * then validate token also
		 * 
		 * */
		
		String requestedTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		 
		if(requestedTokenHeader !=null && requestedTokenHeader.startsWith(orgKey))
		{
				jwtToken = requestedTokenHeader.substring(4);
				
				try {
					
					username = jwtUtil.extractUsername(jwtToken);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				// --- code execute if no exception
				
				 if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			            UserDetails userDetails = this.AppUserService.loadUserByUsername(username);

			            if (jwtUtil.validateToken(jwtToken, userDetails)) {

			                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
			                		new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			               
			                usernamePasswordAuthenticationToken
			                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			            }
			        }//end if username != null
				 
				
				
		}//end if
		else
		{
			System.out.println("Token is not validated ...");
		}
		
		filterChain.doFilter(request, response);
		// NOTE :- always put outside the if ... otherwise leads exception in first login request
		
		
	}

	
	
	
}

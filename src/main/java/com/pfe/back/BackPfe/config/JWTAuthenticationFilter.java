package com.pfe.back.BackPfe.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pfe.back.BackPfe.entities.User;
import com.pfe.back.BackPfe.services.UserService;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	UserService userService;
	
	
	private UserDetailsService userDetailsService;
	private JWTTokenHelper jwtTokenHelper;
	
	public JWTAuthenticationFilter(UserDetailsService userDetailsService,JWTTokenHelper jwtTokenHelper) {
		this.userDetailsService=userDetailsService;
		this.jwtTokenHelper=jwtTokenHelper;
		
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("Filter");

		
		String authToken=jwtTokenHelper.getToken(request);
		
		if(null!=authToken) {
			System.out.println("authToken");

			System.out.println(authToken);

			String userName=jwtTokenHelper.getUsernameFromToken(authToken);
			
			if(null!=userName) {            
				UserDetails userDetails=userDetailsService.loadUserByUsername(userName);
				System.out.println(userName);
				if(jwtTokenHelper.validateToken(authToken, userDetails)) {
					
					UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetails(request));
					
					SecurityContextHolder.getContext().setAuthentication(authentication);				
					
				}
				
			}
			
		}
		
		filterChain.doFilter(request, response);
		
		
		
	}

}

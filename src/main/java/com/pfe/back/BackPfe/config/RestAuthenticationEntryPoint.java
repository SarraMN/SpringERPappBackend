package  com.pfe.back.BackPfe.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		System.out.println("RestAuthenticationEntryPoint");

		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.getMessage());
		System.out.println(authException.getMessage());
		System.out.println(HttpServletResponse.SC_UNAUTHORIZED);

	
	}

}

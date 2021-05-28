package rs.ac.ni.pmf.web.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.pmf.web.exception.ErrorInfo;
import rs.ac.ni.pmf.web.exception.ErrorInfo.ErrorType;
import rs.ac.ni.pmf.web.exception.ErrorInfo.ResourceType;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private static final String[] SWAGGER_WHITELIST = {
			"/swagger-ui.html",
			"/swagger-ui-bundle.js",
			"/swagger-ui-standalone-preset.js",
			"/springfox.js",
	        "/v2/api-docs",
	        "/webjars/**",
	        "/swagger-resources/**"
	    };
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Bean(name = "passwordEncoder")
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//@formatter:off
		http
				.csrf().disable()
				.authorizeRequests()
					.antMatchers("/services/rest/workers/*/**")
						.hasRole("ADMIN")
					.antMatchers(HttpMethod.GET, SWAGGER_WHITELIST)
						.permitAll()
					.antMatchers(HttpMethod.GET, "/services/rest/*/**")
						.permitAll()
					.anyRequest()
						.authenticated()
//						.permitAll()
				.and()
				.httpBasic()
				.authenticationEntryPoint((request, response, e) -> {
					log.error("Authentication error: {}", e.getMessage());
					final ErrorInfo errorInfo = ErrorInfo.builder()
							.errorType(ErrorType.AUTHENTICATION_ERROR)
							.resourceType(ResourceType.ACCESS)
							.message("Failed to authorize the user. Bad username and/or password")
							.build();
					
					response.setContentType("application/json;charset=UTF-8");
					response.setHeader("WWW-Authenticate", "Basic");
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					response.getWriter().write(objectMapper.writeValueAsString(errorInfo));
				})
				.and()
				.exceptionHandling()
					.accessDeniedHandler((reqest, response, e) -> {
						log.error("Authorization error: {}", e.getMessage());
						final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
						final String message = "User" + (auth != null ? " '" + auth.getName() + "'" : "") 
								+ " attempted to access the protected URL: " + reqest.getRequestURI();
						final ErrorInfo errorInfo = ErrorInfo.builder()
								.errorType(ErrorType.UNAUTHORIZED)
								.resourceType(ResourceType.ACCESS)
								.message(message)
								.build();
						
						response.setContentType("application/json;charset=UTF-8");
						response.getWriter().write(objectMapper.writeValueAsString(errorInfo));
					});
		//@formatter:on
	}
}

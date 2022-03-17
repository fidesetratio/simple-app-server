package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.app.customs.CustomAccessDenied;
import com.app.customs.CustomJwtTokenAuthenticationFilter;
import com.app.customs.RestAuthenticationEntryPoint;
import com.app.filters.CustomBasicFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter  {
	
	@Autowired
    private RestAuthenticationEntryPoint entryPoint;
	
	@Autowired
	private CustomJwtTokenAuthenticationFilter customJwtTokenAuthenticationFilter;
	
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		 http.cors().disable();
         http.csrf().disable();
         http.authorizeRequests().
         
         antMatchers("/listnews").hasRole("USER").
         antMatchers("/login").permitAll().
         antMatchers("/logout").permitAll().
         antMatchers("/registration").permitAll().
         
         anyRequest().authenticated().and()
         .exceptionHandling().accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(entryPoint).and()   
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      //   http.addFilterAfter(new CustomBasicFilter(), BasicAuthenticationFilter.class);
	     //http.addFilterBefore(new CustomJwtTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
			
	     http.addFilterBefore(customJwtTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	 	


	}
	
		
	/**	@Bean
	     public AccessDeniedHandler accessDeniedHandler(){
	         return new CustomAccessDenied();
	     }
	**/
}

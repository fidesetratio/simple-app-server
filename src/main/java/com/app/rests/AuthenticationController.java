package com.app.rests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Login;
import com.app.model.LoginAuthenticated;
import com.app.model.Logout;
import com.app.model.ResponseServices;
import com.app.model.UserPerson;

@RestController
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
	
    @Value("${username.token}")
    private String token;
    
	
	@PostMapping("/login")
	public LoginAuthenticated login(Login login) {
			LoginAuthenticated loginAuthenticated = new LoginAuthenticated();
			logger.info("Username & Login = "+login);
			loginAuthenticated.setToken(token);
			return loginAuthenticated;
	}
	
	
	@PostMapping("/logout")
	public LoginAuthenticated logout(Logout logout) {
			LoginAuthenticated loginAuthenticated = new LoginAuthenticated();
			logger.info("Logout = "+logout);
			return loginAuthenticated;
	}
	
	
	@PostMapping("/registration")
	public ResponseServices registration(UserPerson user) {
		ResponseServices response = new ResponseServices();
		logger.info("registration = "+user);
		return response;
		
	}
	
	@PostMapping("/showme")
	public ResponseServices showme(UserPerson user) {
		ResponseServices response = new ResponseServices();
		logger.info("registration = "+user);
		return response;
		
	}
}

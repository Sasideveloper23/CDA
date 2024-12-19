package org.jsp.cda.service;

import org.jsp.cda.entity.AuthUser;
import org.jsp.cda.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
	ResponseEntity<?> saveUser(User user);

	ResponseEntity<?> findUserById(int id);

	ResponseEntity<?> findAllUsers();
	
	ResponseEntity<?> findByUsernameAndPassword(AuthUser authUser);

	ResponseEntity<?> verifyOtp(int id, int otp);						
}

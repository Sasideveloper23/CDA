package org.jsp.cda.service;

import org.jsp.cda.entity.Administrator;
import org.springframework.http.ResponseEntity;

public interface AdministratorService {

	ResponseEntity<?> saveAdministrator(Administrator administrator);

	ResponseEntity<?> findAdministratorById(int id);

}

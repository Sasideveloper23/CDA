package org.jsp.cda.controller;

import org.jsp.cda.entity.Administrator;
import org.jsp.cda.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
@RequestMapping(value="/administrator")
public class AdministratorController {
	@Autowired
	private AdministratorService service;
	
	@PostMapping
	public ResponseEntity<?> saveAdministrator(@RequestBody Administrator administrator){
		return service.saveAdministrator(administrator);
	}
}

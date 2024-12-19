package org.jsp.cda.controller;

import org.jsp.cda.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
@RequestMapping(value="/enrollments")
public class EnrollmentController {
	@Autowired
	private EnrollmentService enrollmentService;
	@PostMapping("/{uid}/{cid}")
	public ResponseEntity<?> saveEnrollment(@PathVariable int cid,@PathVariable int sid){
		return enrollmentService.saveEnrollment(cid,sid);
	}
	@GetMapping(value="/user/{uid}")
	public ResponseEntity<?> findEnrollmentByStudentId(@PathVariable int sid){
		return enrollmentService.findEnrollmentByUserId(sid);
	}
	@GetMapping(value = "/faculty/{fid}")
	public ResponseEntity<?> findAllEnrollmentsByFacultyProfileId(@PathVariable(name = "fid") int fid){
		return enrollmentService.findAllEnrollmentsByFacultyProfileId(fid);
	}
	
}

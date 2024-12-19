package org.jsp.cda.controller;

import java.time.LocalTime;

import org.jsp.cda.entity.Faculty;
import org.jsp.cda.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@CrossOrigin
@RestController
@RequestMapping("/faculty")
public class FacultyController {
	@Autowired
	private FacultyService service;
	
	@PostMapping
	public ResponseEntity<?> saveFaculty(@RequestParam int uid,@RequestParam MultipartFile file){
		return service.saveFaculty(uid,file);
	}
	
	@PatchMapping(value="/{uid}/{did}")	
	public ResponseEntity<?> assignDepartmentToFaculty(@PathVariable(name="uid") int uid,@PathVariable(name="did")int did)
	{
		return service.assignDepartmentToFaculty(uid, did);
	}
	@PatchMapping("/update")
	public ResponseEntity<?> updateInfo(@RequestParam int id, @RequestParam String email, @RequestParam String phone,
			@RequestParam LocalTime officeHours) {
		return service.updateInfo(id, email, phone, officeHours);
	}

	@PatchMapping
	public ResponseEntity<?> updatePhoto(@RequestParam int id, @RequestParam MultipartFile file) {
		return service.updatePhoto(id, file);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findFacultyProfileById(@PathVariable int id) {
		return service.findFacultyById(id);
	}

	@GetMapping
	public ResponseEntity<?> findAllFacultyProfile() {
		return service.findAllFaculty();
	}


}

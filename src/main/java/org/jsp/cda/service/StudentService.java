package org.jsp.cda.service;

import org.jsp.cda.entity.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService {
	ResponseEntity<?> saveStudent(int uid, MultipartFile file);

	ResponseEntity<?> findStudentById(int id);

	ResponseEntity<?> assignDepartmentToStudent(int uid, int did);

	ResponseEntity<?> findAllStudent();

	ResponseEntity<?> setYearToStudent(int id, String year);

	
	
}

package org.jsp.cda.controller;
import org.jsp.cda.service.StudentService;
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
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentService studentService;	
	@PostMapping
	public ResponseEntity<?> saveStudent(@RequestParam int uid,@RequestParam MultipartFile file){
		return studentService.saveStudent(uid,file);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> findStudentProfileById(@PathVariable int id){
		return studentService.findStudentById(id);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllStudentProfiles(){
		return studentService.findAllStudent();
	}
	
	@PatchMapping(value = "/{uid}/{did}")
	public ResponseEntity<?> assignDepartmentToStudentProfile(@PathVariable(name = "uid") int uid,@PathVariable(name = "did") int did){
		return studentService.assignDepartmentToStudent(uid,did);
	}

	@PatchMapping(value = "/year/{uid}/{year}")
	public ResponseEntity<?> setYearToStudentProfile(@PathVariable(name = "uid") int id, @PathVariable(name = "year") String year){
		return studentService.setYearToStudent(id,year); 
	}
}

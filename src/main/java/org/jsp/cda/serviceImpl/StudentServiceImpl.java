package org.jsp.cda.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jsp.cda.dao.DepartmentDao;
import org.jsp.cda.dao.StudentDao;
import org.jsp.cda.dao.UserDao;
import org.jsp.cda.entity.Department;
import org.jsp.cda.entity.Student;
import org.jsp.cda.entity.User;
import org.jsp.cda.exceptionclasses.UserNotFoundException;
import org.jsp.cda.service.StudentService;
import org.jsp.cda.structure.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public  class StudentServiceImpl implements StudentService{
	private static final String FOLDER_PATH="C://Users//sasi/pictures";
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	public ResponseEntity<?> saveStudent(int uid,MultipartFile file) {
		Optional<User> optional=userDao.findUserById(uid);
		if(optional.isEmpty())
			throw UserNotFoundException.builder().message("Invalid User Id: "+uid).build();
		User user=optional.get();
		String photo=FOLDER_PATH + UUID.randomUUID()+file.getOriginalFilename();
		try {
			file.transferTo(new File(photo));
		}catch(IOException e) {
			e.printStackTrace();
		}
		Student student=Student.builder().id(uid).photo(photo).user(user).build();
		studentDao.saveStudent(student);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Student Saved Successfully...").body(student).build());
	}
	
	@Override
	public ResponseEntity<?> findStudentById(int id){
		Optional<Student> optional=studentDao.findStudentById(id);
		if(optional.isEmpty())
			throw new RuntimeException();
		Student student=optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Stydent Profile Saved Successfully...").body(student).build());
	}

	@Override
	public ResponseEntity<?> assignDepartmentToStudent(int uid,int did){
		Optional<Department> optional1=departmentDao.findDepartmentById(did);
		if(optional1.isEmpty())
			throw new RuntimeException("Invalid Department Id: "+did);
		Optional<Student> optional2=studentDao.findStudentById(uid);
		if(optional2.isEmpty())
			throw new RuntimeException("Invalid Student Profile Id: "+uid);
		Department department=optional1.get();
		Student student=optional2.get();
		student.setDepartment(department);
		student=studentDao.saveStudent(student);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Department Assigned To Student Successfully..").build());
	}
	
	@Override
	public ResponseEntity<?> setYearToStudent(int id, String year) {
		
		Optional<Student> optional = studentDao.findStudentById(id);
		if (optional.isEmpty())
			throw new RuntimeException("Invalid Student Profile Id : " + id);
		Student studentProfile = optional.get();
		studentProfile.setYear(year);
		studentProfile = studentDao.saveStudent(studentProfile);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Year Updated Successfully...").body(studentProfile).build());
	}

	@Override
	public ResponseEntity<?> findAllStudent() {

		List<Student> studentProfiles = studentDao.findAllStudent();

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("All Student Profiles Found Successfully...").body(studentProfiles).build());
	}

	
}

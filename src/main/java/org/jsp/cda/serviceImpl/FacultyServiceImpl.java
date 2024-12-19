package org.jsp.cda.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jsp.cda.dao.DepartmentDao;
import org.jsp.cda.dao.FacultyDao;
import org.jsp.cda.dao.UserDao;
import org.jsp.cda.entity.Department;
import org.jsp.cda.entity.Faculty;
import org.jsp.cda.entity.User;
import org.jsp.cda.exceptionclasses.UserNotFoundException;
import org.jsp.cda.service.FacultyService;
import org.jsp.cda.structure.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FacultyServiceImpl implements FacultyService{
	private static final String FOLDER_PATH = "C:\\Users\\gagan\\Documents\\My-React\\cda";
	@Autowired
	private FacultyDao facultyDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Override
	public ResponseEntity<?> saveFaculty(int uid, MultipartFile file) {
		Optional<User> optional = userDao.findUserById(uid);
		if (optional.isEmpty())
			throw UserNotFoundException.builder().message("Inavlid User Id : " + uid).build();
		User user = optional.get();
		String photo = FOLDER_PATH + UUID.randomUUID() + file.getOriginalFilename();
		try {
			file.transferTo(new File(photo));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Faculty facultyProfile = Faculty.builder().photo(photo).id(uid).user(user).build();

		facultyProfile = facultyDao.saveFaculty(facultyProfile);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("Faculty Profile Saved Successfully...").body(facultyProfile).build());
	}

	@Override
	public ResponseEntity<?> findAllFaculty() {
		List<Faculty> facultyProfiles = facultyDao.findAllFaculty();
		if (facultyProfiles.isEmpty())
			throw new RuntimeException("No Faculty Profiles Present in Databse Table...");
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("All Faculty Profiles Found Succesfully...").body(facultyProfiles).build());
	}

	@Override
	public ResponseEntity<?> findFacultyById(int id) {
		Optional<Faculty> optional = facultyDao.findFacultyById(id);
		if (optional.isEmpty())
			throw new RuntimeException("Invalid Faculty Profile Id : " + id);
		Faculty facultyProfile = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("Faculty Found Successfully...").body(facultyProfile).build());
	}

	@Override
	public ResponseEntity<?> updatePhoto(int id, MultipartFile file) {
		Optional<Faculty> optional = facultyDao.findFacultyById(id);
		if (optional.isEmpty())
			throw new RuntimeException("Invalid Faculty Profile Id : " + id);
		Faculty facultyProfile = optional.get();
		String photo = FOLDER_PATH + UUID.randomUUID() + file.getOriginalFilename();
		try {
			file.transferTo(new File(photo));
		} catch (IOException e) {
			e.printStackTrace();
		}
		facultyProfile.setPhoto(photo);
		facultyProfile = facultyDao.saveFaculty(facultyProfile);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("Photo Uploaded Successfully...").body(facultyProfile).build());
	}

	@Override
	public ResponseEntity<?> updateInfo(int id, String email, String phone, LocalTime officeHours) {
		Optional<Faculty> optional = facultyDao.findFacultyProfileById(id);
		if (optional.isEmpty())
			throw new RuntimeException("Invalid Faculty Profile Id : " + id);
		Optional<User> optional2 = userDao.findUserById(id);
		if (optional2.isEmpty())
			throw new RuntimeException("Invalid User Id : " + id);
		User user = optional2.get();
		Faculty facultyProfile = optional.get();
		facultyProfile.setOfficeHours(officeHours);
		facultyProfile = facultyDao.saveFaculty(facultyProfile);
		user.setEmail(email);
		user.setPhone(phone);
		user = userDao.saveUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("Faculty Information Updated Successfully...").body(facultyProfile).build());
	}

	@Override
	public ResponseEntity<?> assignDepartmentToFaculty(int uid, int did) {
		Optional<Department> optional1 = departmentDao.findDepartmentById(did);
		if (optional1.isEmpty())
			throw new RuntimeException("Invalid Department Id : " + did);
		Optional<Faculty> optional2 = facultyDao.findFacultyProfileById(uid);
		if (optional2.isEmpty())
			throw new RuntimeException("Invalid Faculty Profile Id : " + uid);
		Department department = optional1.get();
		Faculty  facultyProfile = optional2.get();
		facultyProfile.setDepartment(department);
		facultyProfile=facultyDao.saveFaculty(facultyProfile);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Department Assigned Successfully To Faculty Profile").body(facultyProfile).build());
	}

	
}

package org.jsp.cda.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.DepartmentDao;
import org.jsp.cda.entity.Department;
import org.jsp.cda.service.DepartmentService;
import org.jsp.cda.structure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	private DepartmentDao departmentDao;
	
	@Override
	public ResponseEntity<?> saveDepartment(Department department) {
		department = departmentDao.saveDepartment(department);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("Department Saved Successfully...").body(department).build());
	}

	@Override
	public ResponseEntity<?> findAllDepartments() {
		List<Department> departments = departmentDao.findAllDepartments();
		if (departments.isEmpty())
			throw new RuntimeException("No Departments Found In Database Table...");
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("All Departments Fetched Successfully...").body(departments).build());
	}

	@Override
	public ResponseEntity<?> findDepartmentById(int id) {
		Optional<Department> optional = departmentDao.findDepartmentById(id);
		if (optional.isEmpty())
			throw new RuntimeException("Invalid Department Id : " + id);
		Department department = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("Department Found Successfully...").body(department).build());
	}

	@Override
	public ResponseEntity<?> deleteDepartmentById(int id) {
		Optional<Department> optional = departmentDao.findDepartmentById(id);
		if (optional.isEmpty())
			throw new RuntimeException("Invalid Department Id : " + id);
		departmentDao.deleteDepartmentById(id);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("Department Deleted Successfully").body("Department Deleted Successfully").build());
	}

}

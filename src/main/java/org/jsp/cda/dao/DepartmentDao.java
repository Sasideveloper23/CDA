package org.jsp.cda.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.entity.Department;


public interface DepartmentDao {

	Optional<Department> findDepartmentById(int did);

	Department saveDepartment(Department department);

	List<Department> findAllDepartments();

	void deleteDepartmentById(int id);

}

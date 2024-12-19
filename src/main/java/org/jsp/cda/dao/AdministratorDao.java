package org.jsp.cda.dao;

import java.util.Optional;

import org.jsp.cda.entity.Administrator;


public interface AdministratorDao
{

	Optional<Administrator> findAdministratorById(int id);

	Administrator saveAdministrator(Administrator administrator);
}

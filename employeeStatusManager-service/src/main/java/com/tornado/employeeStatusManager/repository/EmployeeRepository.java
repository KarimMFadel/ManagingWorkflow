package com.tornado.employeeStatusManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tornado.employeeStatusManager.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}

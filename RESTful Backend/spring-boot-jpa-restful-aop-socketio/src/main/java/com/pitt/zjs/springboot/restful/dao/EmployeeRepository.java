package com.pitt.zjs.springboot.restful.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.pitt.zjs.springboot.restful.entity.Employee;

@CrossOrigin()
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}

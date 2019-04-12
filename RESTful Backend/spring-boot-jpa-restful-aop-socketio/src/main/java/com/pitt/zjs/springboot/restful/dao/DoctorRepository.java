package com.pitt.zjs.springboot.restful.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.pitt.zjs.springboot.restful.entity.Doctor;

@CrossOrigin()
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	@Query(value = "select * from Doctor d where d.email=?1", nativeQuery = true)
	Doctor findByEmail(String email);

}

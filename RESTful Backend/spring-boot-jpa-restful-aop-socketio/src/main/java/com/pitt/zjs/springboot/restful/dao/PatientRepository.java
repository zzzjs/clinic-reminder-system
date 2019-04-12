package com.pitt.zjs.springboot.restful.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.pitt.zjs.springboot.restful.entity.Patient;

@CrossOrigin()
public interface PatientRepository extends JpaRepository<Patient, Integer> {
	@Query(value = "select * from Patient p where p.doctor_id=?1", nativeQuery = true)
	List<Patient> findByDoctorId(int doctor_id);
}

package com.pitt.zjs.springboot.restful.service;

import java.util.List;

import com.pitt.zjs.springboot.restful.entity.Doctor;


public interface DoctorService {

	public List<Doctor> getDoctors();

	public void saveDoctor(Doctor theDoctor);

	public Doctor getDoctor(int theId);
	
	public Doctor getDoctorByEmail(String theEmail);

	public void deleteDoctor(int theId);
	
}

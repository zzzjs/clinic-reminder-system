package com.pitt.zjs.springboot.restful.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pitt.zjs.springboot.restful.dao.DoctorRepository;
import com.pitt.zjs.springboot.restful.entity.Doctor;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;
	
	@Override
	public List<Doctor> getDoctors() {
		return doctorRepository.findAll();
	}

	@Override
	public void saveDoctor(Doctor theDoctor) {

		doctorRepository.save(theDoctor);
	}

	@Override
	public Doctor getDoctor(int theId) {
		
		Optional<Doctor> result = doctorRepository.findById(theId);
		Doctor theDoctor = null;
		if (result.isPresent()) {
			theDoctor = result.get(); 
		} else {
			// didn't find the employee
			throw new RuntimeException("Did not find the doctor id - " + theId);
		}
		return theDoctor;
	}
	

	@Override
	public Doctor getDoctorByEmail(String theEmail) {
		// TODO Auto-generated method stub
		return doctorRepository.findByEmail(theEmail);
	}

	@Override
	public void deleteDoctor(int theId) {
		
		doctorRepository.deleteById(theId);
	}
}






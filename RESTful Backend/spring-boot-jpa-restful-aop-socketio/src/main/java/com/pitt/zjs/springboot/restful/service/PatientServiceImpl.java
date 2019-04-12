package com.pitt.zjs.springboot.restful.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pitt.zjs.springboot.restful.dao.PatientRepository;
import com.pitt.zjs.springboot.restful.entity.Patient;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	@Override
	public List<Patient> getPatients() {
		return patientRepository.findAll();
	}

	@Override
	public void savePatient(Patient thePatient) {

		patientRepository.save(thePatient);
	}

	@Override
	public Patient getPatient(int theId) {
		Optional<Patient> result = patientRepository.findById(theId);
		Patient thePatient = null;
		if (result.isPresent()) {
			thePatient = result.get(); 
		} else {
			// didn't find the employee
			throw new RuntimeException("Did not find the patient id - " + theId);
		}
		return thePatient;
	}

	@Override
	public void deletePatient(int theId) {
		
		patientRepository.deleteById(theId);
	}

	@Override
	public List<Patient> getPatientsByDoctorId(int theDoctorId) {
		// TODO Auto-generated method stub
		return patientRepository.findByDoctorId(theDoctorId);
	}
}






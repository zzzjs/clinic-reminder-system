package com.pitt.zjs.springboot.restful.service;

import java.util.List;

import com.pitt.zjs.springboot.restful.entity.Patient;

public interface PatientService {

	public List<Patient> getPatients();

	public void savePatient(Patient thePatient);

	public Patient getPatient(int theId);

	public void deletePatient(int theId);

	public List<Patient> getPatientsByDoctorId(int theDoctorId);
	
}

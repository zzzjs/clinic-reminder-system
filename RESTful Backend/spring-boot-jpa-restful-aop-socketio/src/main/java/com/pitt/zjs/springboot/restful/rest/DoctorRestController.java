package com.pitt.zjs.springboot.restful.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.pitt.zjs.springboot.restful.entity.Account;
import com.pitt.zjs.springboot.restful.entity.Doctor;
import com.pitt.zjs.springboot.restful.entity.Patient;
import com.pitt.zjs.springboot.restful.entity.Reminder;
import com.pitt.zjs.springboot.restful.exception.NotFoundException;
import com.pitt.zjs.springboot.restful.net.ReminderListItem;
import com.pitt.zjs.springboot.restful.net.PushResponse;
import com.pitt.zjs.springboot.restful.net.Request;
import com.pitt.zjs.springboot.restful.service.AccountService;
import com.pitt.zjs.springboot.restful.service.DoctorService;
import com.pitt.zjs.springboot.restful.service.PatientService;
import com.pitt.zjs.springboot.restful.service.ReminderService;
import com.pitt.zjs.springboot.restful.socketio.MessageEventHandler;
import com.pitt.zjs.springboot.restful.util.Codec;

@RestController
@RequestMapping("api")
@CrossOrigin()
public class DoctorRestController {

	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private ReminderService reminderService;
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/list")
	public String listDoctors(Model theModel) {
		
		List<Doctor> theDoctors = doctorService.getDoctors();
		
		theModel.addAttribute("doctors", theDoctors);
		
		return "list-customers";
	}

	@GetMapping("/doctors")
	public List<Doctor> getDoctors() {
		return doctorService.getDoctors();
	}
	@GetMapping("/patients")
	public List<Patient> getPatients() {
		return patientService.getPatients();
	}
	@GetMapping("/reminders/{patientId}")
	public List<Reminder> getReminders(@PathVariable int patientId) {
		return reminderService.getRemindersNotFinished(patientId);
	}
	@GetMapping("/reminders/doctor/{doctorId}")
	public List<ReminderListItem> getRemindersByDoctorId(@PathVariable int doctorId) {
		
		List<Patient> patients = patientService.getPatientsByDoctorId(doctorId);
		
		List<ReminderListItem> list = new ArrayList<>();
		for (Patient patient : patients) {
			List<Reminder> reminders = reminderService.getReminders(patient.getId());
			list.add(new ReminderListItem(patient.getId(), patient.getFirstName(), patient.getLastName(), reminders));
		}
		System.out.println(list);
		
		return list;
	}

	@GetMapping("/doctors/{doctorId}")
	public Doctor getDoctor(@PathVariable int doctorId) {
		
		Doctor theDoctor = doctorService.getDoctor(doctorId);
		
		if (theDoctor == null) {
			throw new NotFoundException("Doctor id not found - " + doctorId);
		}
		return theDoctor;
	}
	@GetMapping("/patients/{patientId}")
	public Patient getPatient(@PathVariable int patientId) {
		
		Patient thePatient = patientService.getPatient(patientId);
		if (thePatient == null) {
			throw new NotFoundException("Patient id not found - " + patientId);
		}
		return thePatient;
	}
	@PostMapping("/doctors")
	public Doctor addDoctor(@RequestBody Doctor theDoctor) {
		
		theDoctor.setId(0);
		
		doctorService.saveDoctor(theDoctor);
		
		return theDoctor;
	}
	@PostMapping("/patients/{theId}")
	public Patient addPatient(@PathVariable int theId, @RequestBody Patient thePatient) {
		
		thePatient.setId(0);
		patientService.savePatient(thePatient);
		
		return thePatient;
	}
	@PostMapping("/reminders")
	public String addReminder(@RequestBody String theReminder) {
		Gson gson = new Gson();
		Reminder reminder = Codec.decoderChildReminderResp(theReminder);
		reminder.setId(0);
		reminderService.saveReminder(reminder);
		MessageEventHandler.sendMessage(reminder.getPatientId()+"", "2", theReminder);
		return gson.toJson(reminder);
	}
	@PostMapping("/register")
	public Account addAccount(@RequestBody Account theAccount) {
		
		theAccount.setId(0);
		
		accountService.saveAccount(theAccount);
		
		return theAccount;
	}
	@PostMapping("/patient/login")
	public String patientLogin(@RequestBody String reqAccount) {
		Gson gson = new Gson();
		Account theAccount = Codec.decoderChildAccountResp(reqAccount);
		String theEmail = theAccount.getEmail();
		Account account = accountService.getAccountByEmail(theEmail); 
//		if (account == null) {
//			throw new NotFoundException("Patient not found - " + theEmail);
//		}
		if (account == null || !account.getPassword().equals(theAccount.getPassword())) {
			return null;
		}
		return gson.toJson(account);
	}
	@PostMapping("/doctor/login")
	public String doctorLogin(@RequestBody String reqAccount) {
		Gson gson = new Gson();
		Doctor reqDoctorAccount = Codec.decoderChildDoctorAccountResp(reqAccount);
		String theEmail = reqDoctorAccount.getEmail();
		Doctor theDoctorAccount = doctorService.getDoctorByEmail(theEmail);
//		if (theDoctorAccount == null) {
//			throw new NotFoundException("Patient not found - " + theEmail);
//		}
		if (theDoctorAccount == null || !theDoctorAccount.getPassword().equals(reqDoctorAccount.getPassword())) {
			return null;
		}
		return gson.toJson(theDoctorAccount);
	}
	@PutMapping("/doctors")
	public Doctor upDataDoctor(@RequestBody Doctor theDoctor) {
		
		doctorService.saveDoctor(theDoctor);
		
		return theDoctor;
	}
	@PutMapping("/reminders")
	public String upDataReminder(@RequestBody String theReminder) {
		Gson gson = new Gson();
		Reminder reminder = Codec.decoderChildReminderResp(theReminder);
		reminderService.saveReminder(reminder);
		MessageEventHandler.sendMessage(reminder.getDoctorId()+"", "1", new Gson().toJson(reminder));
		return gson.toJson(reminder);
	}
	@PutMapping("/patients")
	public Patient upDataPatient(@RequestBody Patient thePatient) {
		
		patientService.savePatient(thePatient);
		
		return thePatient;
	}
	@DeleteMapping("/doctors/{doctorId}")
	public String deleteDoctor(@PathVariable int doctorId) {
		
		Doctor tempDoctor = doctorService.getDoctor(doctorId);
		
		if (tempDoctor == null) {
			throw new NotFoundException("Doctor id not found - " + doctorId);
		}
		
		doctorService.deleteDoctor(doctorId);;
		
		return "Delete doctor id - " + doctorId;
	}
	
}

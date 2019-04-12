package com.pitt.zjs.springboot.restful.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="doctor_id")
	private String doctorId;
	
//	@OneToOne(mappedBy="patient",
//			cascade= {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//	private Account account;
	
//	@OneToMany(mappedBy="patient",cascade= {CascadeType.DETACH, CascadeType.PERSIST, 
//			CascadeType.MERGE, CascadeType.REFRESH})
//	private List<Reminder> reminders;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

//	public Account getAccount() {
//		return account;
//	}
//
//	public void setAccount(Account account) {
//		this.account = account;
//	}

//	public List<Reminder> getReminders() {
//		return reminders;
//	}
//
//	public void setReminders(List<Reminder> reminders) {
//		this.reminders = reminders;
//	}
//	
//	public void	add(Reminder tempReminder) {
//		if (reminders == null) {
//			reminders = new ArrayList<>();
//		}
//		reminders.add(tempReminder);
//		tempReminder.setPatient(this);
//	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", doctorId=" + doctorId;
	}
	
	public Patient() {
		// TODO Auto-generated constructor stub
	}

	public Patient(int id, String firstName, String lastName, String email, String password, String reminder,
			String doctorId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.doctorId = doctorId;
	}
	
}

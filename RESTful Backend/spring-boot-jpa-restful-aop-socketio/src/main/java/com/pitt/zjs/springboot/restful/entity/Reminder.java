package com.pitt.zjs.springboot.restful.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reminder")
public class Reminder {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="priority")
	private String priority;
	
	@Column(name="year")
	private String year;
	
	@Column(name="month")
	private String month;
	
	@Column(name="date")
	private String date;
	
	@Column(name="hours")
	private String hours;
	
	@Column(name="minutes")
	private String minutes;
	
	@Column(name="description")
	private String description;
	
	@Column(name="note")
	private String note;
	
	@Column(name="patient_id")
	private int patientId;
	
	@Column(name="finished")
	private String finished;
	
	@Column(name="finished_ontime")
	private String finishedOntime;
	
	@Column(name="doctor_id")
	private int doctorId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getMinutes() {
		return minutes;
	}

	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getFinished() {
		return finished;
	}

	public void setFinished(String finished) {
		this.finished = finished;
	}

	public String getFinishedOntime() {
		return finishedOntime;
	}

	public void setFinishedOntime(String finishedOntime) {
		this.finishedOntime = finishedOntime;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	@Override
	public String toString() {
		return "Reminder [id=" + id + ", priority=" + priority + ", year=" + year + ", month=" + month + ", date="
				+ date + ", hours=" + hours + ", minutes=" + minutes + ", description=" + description + ", note=" + note
				+ ", patientId=" + patientId + ", finished=" + finished + ", finishedOntime=" + finishedOntime
				+ ", doctorId=" + doctorId + "]";
	}

	
//	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.PERSIST, 
//			CascadeType.MERGE, CascadeType.REFRESH})
//	@JoinColumn(name="patient_id")
//	private Patient patient;

//	public Patient getPatient() {
//		return patient;
//	}
//
//	public void setPatient(Patient patient) {
//		this.patient = patient;
//	}


	
}

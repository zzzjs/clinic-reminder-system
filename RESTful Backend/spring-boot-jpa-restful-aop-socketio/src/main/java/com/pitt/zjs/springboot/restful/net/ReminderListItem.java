package com.pitt.zjs.springboot.restful.net;

import java.util.List;

import com.pitt.zjs.springboot.restful.entity.Reminder;

public class ReminderListItem {
	
	private int patientId;
	private String firstName;
	private String lastName;
	private List<Reminder> patientReminders;
	/**
	 * @param patientId
	 * @param firstName
	 * @param lastName
	 * @param patientReminders
	 */
	public ReminderListItem(int patientId, String firstName, String lastName, List<Reminder> patientReminders) {
		super();
		this.patientId = patientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.patientReminders = patientReminders;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
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
	public List<Reminder> getPatientReminders() {
		return patientReminders;
	}
	public void setPatientReminders(List<Reminder> patientReminders) {
		this.patientReminders = patientReminders;
	}
		
	
}

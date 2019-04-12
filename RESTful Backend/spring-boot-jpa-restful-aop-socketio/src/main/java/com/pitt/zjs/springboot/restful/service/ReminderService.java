package com.pitt.zjs.springboot.restful.service;

import java.util.List;

import com.pitt.zjs.springboot.restful.entity.Reminder;


public interface ReminderService {
	public List<Reminder> getReminders(int thePatientId);
	
	public List<Reminder> getRemindersNotFinished(int thePatientId);

	public void saveReminder(Reminder theReminder);

	public Reminder getReminder(int theId);

	public void deleteReminder(int theId);
}

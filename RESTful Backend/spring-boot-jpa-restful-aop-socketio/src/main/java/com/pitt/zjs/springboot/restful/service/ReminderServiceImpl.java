package com.pitt.zjs.springboot.restful.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pitt.zjs.springboot.restful.dao.ReminderRepository;
import com.pitt.zjs.springboot.restful.entity.Reminder;

@Service
public class ReminderServiceImpl implements ReminderService {

	@Autowired
	private ReminderRepository reminderRepository;

	@Override
	public List<Reminder> getReminders(int thePatientId) {
		
		return reminderRepository.findByPatientId(thePatientId);
	}

	@Override
	public void saveReminder(Reminder theReminder) {
		reminderRepository.save(theReminder);
	}

	@Override
	public Reminder getReminder(int theId) {
		Optional<Reminder> result = reminderRepository.findById(theId);
		Reminder theReminder = null;
		if (result.isPresent()) {
			theReminder = result.get(); 
		} else {
			// didn't find the employee
			throw new RuntimeException("Did not find the reminder id - " + theId);
		}
		return theReminder;
	}

	@Override
	public void deleteReminder(int theId) {
		reminderRepository.deleteById(theId);
	}

	@Override
	public List<Reminder> getRemindersNotFinished(int thePatientId) {
		return 	reminderRepository.findByPatientIdwithNotFinished(thePatientId);
	}

}

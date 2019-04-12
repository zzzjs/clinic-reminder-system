package com.pitt.zjs.springboot.restful.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.pitt.zjs.springboot.restful.entity.Reminder;

@CrossOrigin()
public interface ReminderRepository extends JpaRepository<Reminder, Integer> {
	@Query(value = "select * from Reminder r where r.patient_id=?1", nativeQuery = true)
	List<Reminder> findByPatientId(int patient_id);

	@Query(value = "select * from Reminder r where r.patient_id=?1 and r.finished='N'", nativeQuery = true)
	List<Reminder> findByPatientIdwithNotFinished(int patient_id);
}

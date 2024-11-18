package com.pfe.back.BackPfe.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.back.BackPfe.entities.TimeSheet;
import com.pfe.back.BackPfe.entities.User;

@Repository
public interface TimeSheetRepo extends JpaRepository<TimeSheet, Long> {

	// Find timesheets by employee
	List<TimeSheet> findByEmployee(User employee);

	List<TimeSheet> findByDate(LocalDate date);

}

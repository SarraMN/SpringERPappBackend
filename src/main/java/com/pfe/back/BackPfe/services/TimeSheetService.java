package com.pfe.back.BackPfe.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.back.BackPfe.entities.State;
import com.pfe.back.BackPfe.entities.TimeSheet;
import com.pfe.back.BackPfe.entities.User;
import com.pfe.back.BackPfe.repository.TimeSheetRepo;

@Service
public class TimeSheetService {

	@Autowired
	private TimeSheetRepo timeSheetRepository;

	// Retrieve all timesheets
	public List<TimeSheet> getAllTimeSheets() {
		return timeSheetRepository.findAll();
	}

	// Retrieve a single timesheet by ID
	public TimeSheet getTimeSheetById(Long id) {
		return timeSheetRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("TimeSheet with ID " + id + " not found."));
	}

	// Create a new timesheet
	public TimeSheet createTimeSheet(TimeSheet timeSheet) {
		timeSheet.setState(State.PENDING); // Default state
		return timeSheetRepository.save(timeSheet);
	}

	// Approve a timesheet
	public TimeSheet approveTimeSheet(Long id, User approvedBy) {
		TimeSheet timeSheet = getTimeSheetById(id);
		timeSheet.setState(State.APPROVED);
		timeSheet.setApprovedBy(approvedBy);
		return timeSheetRepository.save(timeSheet);
	}

	// Reject a timesheet
	public TimeSheet rejectTimeSheet(Long id, User rejectedBy) {
		TimeSheet timeSheet = getTimeSheetById(id);
		timeSheet.setState(State.REJECTED);
		timeSheet.setApprovedBy(rejectedBy); // Optionally set the approver
		return timeSheetRepository.save(timeSheet);
	}

	// Delete a timesheet by ID
	public void deleteTimeSheet(Long id) {
		timeSheetRepository.deleteById(id);
	}

	// Retrieve timesheets by employee
	public List<TimeSheet> getTimeSheetsByEmployee(User employee) {
		return timeSheetRepository.findByEmployee(employee); // Assuming `employee` is a field in `TimeSheet`
	}

	// Retrieve timesheets by specific date
	public List<TimeSheet> getTimeSheetsByDate(LocalDate date) {
		return timeSheetRepository.findByDate(date); // Assuming `date` is a field in `TimeSheet`
	}

	// Update an existing timesheet
	public TimeSheet updateTimeSheet(Long id, TimeSheet updatedTimeSheet) {
		// Find the existing timesheet by ID
		TimeSheet existingTimeSheet = getTimeSheetById(id);

		// Update the fields of the existing timesheet with values from the updated one
		existingTimeSheet.setEmployee(updatedTimeSheet.getEmployee());
		existingTimeSheet.setApprovedBy(updatedTimeSheet.getApprovedBy());
		existingTimeSheet.setDate(updatedTimeSheet.getDate());
		existingTimeSheet.setHoursWorked(updatedTimeSheet.getHoursWorked());
		existingTimeSheet.setTaskTitle(updatedTimeSheet.getTaskTitle());
		existingTimeSheet.setState(updatedTimeSheet.getState());
		existingTimeSheet.setDescription(updatedTimeSheet.getDescription());

		// Save the updated timesheet and return it
		return timeSheetRepository.save(existingTimeSheet);
	}

}
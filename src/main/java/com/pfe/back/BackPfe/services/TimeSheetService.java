package com.pfe.back.BackPfe.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.back.BackPfe.entities.State;
import com.pfe.back.BackPfe.entities.TimeSheet;
import com.pfe.back.BackPfe.entities.User;
import com.pfe.back.BackPfe.repository.TimeSheetRepo;
import com.pfe.back.BackPfe.repository.UserDetailsRepository;

@Service
public class TimeSheetService {

	@Autowired
	private TimeSheetRepo timeSheetRepository;
	@Autowired
	private UserDetailsRepository UserDetailsRepository;

	// Retrieve all timesheets
	public List<TimeSheet> getAllTimeSheets() {
		return timeSheetRepository.findAll();
	}

	public List<TimeSheet> getAllTraitedTimeSheets() {
		// TODO Auto-generated method stub
		return getAllTimeSheets().stream().filter(t->!t.getState().equals(State.PENDING)).collect(Collectors.toList());
	}
	public List<TimeSheet> getAllNotTraitedTimeSheets() {
		// TODO Auto-generated method stub
		return getAllTimeSheets().stream().filter(t->t.getState().equals(State.PENDING)).collect(Collectors.toList());
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
	public TimeSheet approveTimeSheet(Long id, Long approvedBy) {
		System.out.println("srour");
		TimeSheet timeSheet = getTimeSheetById(id);
		timeSheet.setState(State.APPROVED);
		Optional<User> byId = UserDetailsRepository.findById(approvedBy);
		timeSheet.setApprovedBy(byId.get());
		return timeSheetRepository.save(timeSheet);
	}

	// Reject a timesheet
	public TimeSheet rejectTimeSheet(Long id, Long rejectedBy) {
		TimeSheet timeSheet = getTimeSheetById(id);
		timeSheet.setState(State.REJECTED);
		Optional<User> byId = UserDetailsRepository.findById(rejectedBy);
		timeSheet.setApprovedBy(byId.get()); // Optionally set the approver
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

package com.pfe.back.BackPfe.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.back.BackPfe.entities.TimeSheet;
import com.pfe.back.BackPfe.entities.User;
import com.pfe.back.BackPfe.services.TimeSheetService;

@RestController
@RequestMapping("/api/timesheets")
public class TimeSheetController {

	@Autowired
	private TimeSheetService timeSheetService;

	// Endpoint to get all timesheets
	@GetMapping
	public List<TimeSheet> getAllTimeSheets() {
		return timeSheetService.getAllTimeSheets();
	}

	// Endpoint to get a timesheet by its ID
	@GetMapping("/{id}")
	public TimeSheet getTimeSheetById(@PathVariable Long id) {
		return timeSheetService.getTimeSheetById(id);
	}

	// Endpoint to get timesheets by employee (User)
	@GetMapping("/employee/{employeeId}")
	public List<TimeSheet> getTimeSheetsByEmployee(@PathVariable Long employeeId) {
		User employee = new User(); // Assuming User exists in the system and can be retrieved by ID
		employee.setId(employeeId);
		return timeSheetService.getTimeSheetsByEmployee(employee); // Modify service method accordingly
	}

	// Endpoint to get timesheets by specific date
	@GetMapping("/date/{date}")
	public List<TimeSheet> getTimeSheetsByDate(@PathVariable String date) {
		LocalDate parsedDate = LocalDate.parse(date);
		return timeSheetService.getTimeSheetsByDate(parsedDate);
	}

	// Endpoint to create a new timesheet
	@PostMapping
	public TimeSheet createTimeSheet(@RequestBody TimeSheet timeSheet) {
		return timeSheetService.createTimeSheet(timeSheet);
	}

	// Endpoint to approve a timesheet
	@PutMapping("/approve/{id}")
	public TimeSheet approveTimeSheet(@PathVariable Long id, @RequestBody User approvedBy) {
		return timeSheetService.approveTimeSheet(id, approvedBy);
	}

	// Endpoint to reject a timesheet
	@PutMapping("/reject/{id}")
	public TimeSheet rejectTimeSheet(@PathVariable Long id, @RequestBody User rejectedBy) {
		return timeSheetService.rejectTimeSheet(id, rejectedBy);
	}

	// Endpoint to delete a timesheet by ID
	@DeleteMapping("/{id}")
	public void deleteTimeSheet(@PathVariable Long id) {
		timeSheetService.deleteTimeSheet(id);
	}

	// Endpoint to update an existing timesheet (for example, changing state or
	// details)
	@PutMapping("/{id}")
	public TimeSheet updateTimeSheet(@PathVariable Long id, @RequestBody TimeSheet timeSheet) {
		timeSheet.setId(id); // Ensure we are updating the existing timesheet
		return timeSheetService.updateTimeSheet(id, timeSheet);
	}
}

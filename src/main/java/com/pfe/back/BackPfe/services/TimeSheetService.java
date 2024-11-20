package com.pfe.back.BackPfe.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.pfe.back.BackPfe.entities.Leave;
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
	@Autowired
	private JavaMailSender javaMailSender;
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
		sendEmail(timeSheet);
		return timeSheetRepository.save(timeSheet);
	}

	// Reject a timesheet
	public TimeSheet rejectTimeSheet(Long id, Long rejectedBy) {
		TimeSheet timeSheet = getTimeSheetById(id);
		timeSheet.setState(State.REJECTED);
		Optional<User> byId = UserDetailsRepository.findById(rejectedBy);
		timeSheet.setApprovedBy(byId.get()); // Optionally set the approver
		sendEmail(timeSheet);
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

	private void sendEmail(TimeSheet timeSheet) {
		// Prepare the email details
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom("amdounisirrine90@gmail.com");
		email.setTo(timeSheet.getEmployee().getEmail());
		email.setSubject("votre feuille de temps est traitée");

		// Compose a professional email body
		String emailBody = String.format(
			    "Votre feuille de temps est traitée par %s,\n\n" +
			    "Le résultat est : %s.\n\n" +
			    "Si vous avez des questions, n'hésitez pas à nous contacter.\n\n" +
			    "Cordialement,\n" +
			    "L'équipe RH",
			    timeSheet.getApprovedBy().getNom(), // Approver's name
			    timeSheet.getState() // Result state
			);

		email.setText(emailBody);

		// Send the email
		try {
			javaMailSender.send(email);
		} catch (Exception e) {
			System.out.println("Failed to send approval email: " + e.getMessage());
		}
	}
}

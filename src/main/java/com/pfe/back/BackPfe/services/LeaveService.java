package com.pfe.back.BackPfe.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.pfe.back.BackPfe.entities.Leave;
import com.pfe.back.BackPfe.entities.State;
import com.pfe.back.BackPfe.entities.User;
import com.pfe.back.BackPfe.repository.LeaveRepo;

@Service
public class LeaveService {

	@Autowired
	private LeaveRepo leaveRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private UserService userService;

	public List<Leave> getAllLeaves() {
		return leaveRepository.findAll();
	}

	public Leave createLeave(Leave leave) {
		leave.setStatus(State.PENDING);
		return leaveRepository.save(leave);
	}

	public void deleteLeave(Long id) {
		leaveRepository.deleteById(id);
	}

	public Leave approveLeave(Long id, User approvedBy) {
		Optional<Leave> leaveOptional = leaveRepository.findById(id);

		if (leaveOptional.isPresent()) {
			Leave leaveToUpdate = leaveOptional.get();

			// Calculate the number of days requested
			long daysRequested = java.time.temporal.ChronoUnit.DAYS.between(leaveToUpdate.getStartDate(),
					leaveToUpdate.getEndDate()) + 1;

			// Update leave status and approver
			leaveToUpdate.setStatus(State.APPROVED);
			leaveToUpdate.setApprovedBy(approvedBy);

			// Update user's leave balance
			User requestedBy = leaveToUpdate.getRequestedBy();
			int currentSolde = requestedBy.getSoldeLeaves();

			if (currentSolde < daysRequested) {
				System.out.println("Insufficient leave balance for approval.");
			}

			requestedBy.setSoldeLeaves(currentSolde - (int) daysRequested);

			// Save the updated user
			userService.updateUser(requestedBy.getId(), requestedBy);

			Leave updatedLeave = leaveRepository.save(leaveToUpdate);

			// Send approval email
			sendApprovalEmail(leaveToUpdate);

			return updatedLeave;
		}

		return null;
	}

	private void sendApprovalEmail(Leave leaveToUpdate) {
		// Prepare the email details
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom("amdounisirrine90@gmail.com");
		email.setTo(leaveToUpdate.getRequestedBy().getEmail());
		email.setSubject("Leave Approved: " + leaveToUpdate.getType());

		// Compose a professional email body
		String emailBody = String.format(
				"Dear %s,\n\n" + "Your leave request has been approved.\n\n" + "Leave Details:\n" + "Type: %s\n"
						+ "Start Date: %s\n" + "End Date: %s\n\n"
						+ "If you have any questions, feel free to reach out.\n\n" + "Best regards,\n" + "HR Team",
				leaveToUpdate.getRequestedBy().getNom(), // Use the employee's name
				leaveToUpdate.getType(), leaveToUpdate.getStartDate(), leaveToUpdate.getEndDate());

		email.setText(emailBody);

		// Send the email
		try {
			javaMailSender.send(email);
		} catch (Exception e) {
			System.out.println("Failed to send approval email: " + e.getMessage());
		}
	}

	public Leave rejectLeave(Long id, User disapprovedBy) {
		Optional<Leave> leaveOptional = leaveRepository.findById(id);

		if (leaveOptional.isPresent()) {
			Leave leaveToUpdate = leaveOptional.get();

			// Update leave status and approver
			leaveToUpdate.setStatus(State.REJECTED);
			leaveToUpdate.setApprovedBy(disapprovedBy);
			Leave updatedLeave = leaveRepository.save(leaveToUpdate);

			// Send rejection email
			sendRejectionEmail(leaveToUpdate);

			return updatedLeave;
		}
		return null;
	}

	private void sendRejectionEmail(Leave leaveToUpdate) {
		// Prepare the email details
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom("amdounisirrine90@gmail.com");
		;
		email.setTo(leaveToUpdate.getRequestedBy().getEmail());
		email.setSubject("Leave Rejected: " + leaveToUpdate.getType());

		// Compose a professional email body
		String emailBody = String.format(
				"Dear %s,\n\n" + "We regret to inform you that your leave request has been rejected.\n\n"
						+ "Leave Details:\n" + "Type: %s\n" + "Start Date: %s\n" + "End Date: %s\n\n"
						+ "If you have any questions or need clarification, please reach out to the HR department.\n\n"
						+ "Best regards,\n" + "HR Team",
				leaveToUpdate.getRequestedBy().getNom(), // Use the employee's name
				leaveToUpdate.getType(), leaveToUpdate.getStartDate(), leaveToUpdate.getEndDate());

		email.setText(emailBody);

		// Send the email
		try {
			javaMailSender.send(email);
		} catch (Exception e) {
			throw new RuntimeException("Failed to send rejection email: " + e.getMessage(), e);
		}
	}

}
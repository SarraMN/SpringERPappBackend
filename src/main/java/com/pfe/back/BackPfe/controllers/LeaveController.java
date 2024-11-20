package com.pfe.back.BackPfe.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.back.BackPfe.entities.Leave;
import com.pfe.back.BackPfe.entities.User;
import com.pfe.back.BackPfe.services.LeaveService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

	@Autowired
	private LeaveService leaveService;

	// Retrieve all leaves
	@GetMapping
	public ResponseEntity<List<Leave>> getAllLeaves() {
		List<Leave> leaves = leaveService.getAllLeaves();
		return ResponseEntity.ok(leaves);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<Leave> getLeaveById(@PathVariable Long id) {
		Optional<Leave> leave = leaveService.getLeaveById(id);
		if (leave.isPresent()) {
			return ResponseEntity.ok(leave.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping("/pending")
    public ResponseEntity<List<Leave>> getPendingLeaves() {
        List<Leave> pendingLeaves = leaveService.getPendingLeaves();
        return ResponseEntity.ok(pendingLeaves);
    }

    @GetMapping("/non-pending")
    public ResponseEntity<List<Leave>> getNonPendingLeaves() {
        List<Leave> nonPendingLeaves = leaveService.getNonPendingLeaves();
        return ResponseEntity.ok(nonPendingLeaves);
    }

	@GetMapping("/GetLeavesByUser/{id}")
	public List<Leave> getlisteLeaves(@PathVariable(name = "id") Long id) {
		return leaveService.GetLeavesByUser(id);

	}

	// Create a new leave request
	@PostMapping
	public ResponseEntity<Leave> createLeave(@RequestBody Leave leave) {
		Leave createdLeave = leaveService.createLeave(leave);
		return ResponseEntity.ok(createdLeave);
	}

	// Delete a leave request by ID
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteLeave(@PathVariable Long id) {
		leaveService.deleteLeave(id);
		return ResponseEntity.noContent().build();
	}

	// Approve a leave request
	@PutMapping("/{id}/approve")
	public ResponseEntity<Leave> approveLeave(@PathVariable Long id, @RequestBody User approvedBy) {
		Leave approvedLeave = leaveService.approveLeave(id, approvedBy);
		if (approvedLeave == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(approvedLeave);
	}

	// Reject a leave request
	@PutMapping("/{id}/reject")
	public ResponseEntity<Leave> rejectLeave(@PathVariable Long id, @RequestBody User disapprovedBy) {
		Leave rejectedLeave = leaveService.rejectLeave(id, disapprovedBy);
		if (rejectedLeave == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(rejectedLeave);
	}
}

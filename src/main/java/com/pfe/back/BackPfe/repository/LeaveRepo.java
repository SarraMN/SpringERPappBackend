package com.pfe.back.BackPfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.back.BackPfe.entities.Leave;

@Repository
public interface LeaveRepo extends JpaRepository<Leave, Long> {

}

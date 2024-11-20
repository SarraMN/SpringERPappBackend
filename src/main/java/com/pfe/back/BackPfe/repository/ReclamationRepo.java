package com.pfe.back.BackPfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.back.BackPfe.entities.Reclamation;

@Repository
public interface ReclamationRepo  extends JpaRepository<Reclamation, Long>{

}

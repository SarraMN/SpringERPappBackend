package com.pfe.back.BackPfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.back.BackPfe.entities.actualite;

@Repository
public interface actualiteRepo extends JpaRepository<actualite, Long>{

}

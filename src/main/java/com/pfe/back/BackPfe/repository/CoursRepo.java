package  com.pfe.back.BackPfe.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import  com.pfe.back.BackPfe.entities.Cours;


@Repository
public interface CoursRepo extends JpaRepository<Cours, Long>{
	/*
	 * @Query("select d from Cours d where d.formation=idFormation") public
	 * List<Cours> getCoursByIdFromation(@Param("x") Long idFormation);
	 */
}

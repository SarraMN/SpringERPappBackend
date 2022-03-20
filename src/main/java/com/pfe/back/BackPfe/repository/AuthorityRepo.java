package  com.pfe.back.BackPfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.back.BackPfe.entities.Authority;


@Repository
public interface AuthorityRepo extends JpaRepository<Authority, Long> {
	Authority findByRoleName(String roleName);


}

package ppa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ppa.model.Dobavljac;

// This will be AUTO IMPLEMENTED by Spring into a Bean called DobavljacRepository
@Repository
public interface DobavljacRepository extends JpaRepository<Dobavljac, Long>{
	
	@Query("SELECT d FROM Dobavljac d WHERE "
			+ "(:naziv IS NULL or d.naziv LIKE :naziv) AND"
			+ "(:maticniBroj IS NULL or d.maticniBroj LIKE :maticniBroj)")
	Page<Dobavljac> findAll(
			@Param(value = "naziv") String naziv,
			@Param(value = "maticniBroj") String maticniBroj, 
			Pageable pageRequest);
	
	Dobavljac findByMaticniBroj(String maticniBroj);

}

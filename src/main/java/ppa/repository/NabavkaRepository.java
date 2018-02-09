package ppa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ppa.model.Nabavka;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
@Repository
public interface NabavkaRepository extends JpaRepository<Nabavka, Long>{

	@Query("SELECT n FROM Nabavka n WHERE "
			+ "(:oznaka IS NULL or n.oznaka LIKE :oznaka) AND"
			+ "(:procenjenaVrednostMin IS NULL or n.procenjenaVrednost >= :procenjenaVrednostMin) AND"
			+ "(:procenjenaVrednostMax IS NULL or n.procenjenaVrednost <= :procenjenaVrednostMax) AND"
			+ "(:vrstaPostupkaId IS NULL or n.vrstaPostupka.id = :vrstaPostupkaId) AND"
			+ "(:vrstaPredmetaId IS NULL or n.vrstaPredmeta.id = :vrstaPredmetaId)")
	Page<Nabavka> findAll(
			@Param(value = "oznaka") String oznaka, 
			@Param(value = "procenjenaVrednostMin") Integer procenjenaVrednostMin, 
			@Param(value = "procenjenaVrednostMax") Integer procenjenaVrednostMax,
			@Param(value = "vrstaPostupkaId") Long vrstaPostupkaId, 
			@Param(value = "vrstaPredmetaId") Long vrstaPredmetaId,
			//Probati sa PageRequest
			Pageable pageRequest);

}

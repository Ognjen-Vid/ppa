package ppa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ppa.model.Ugovor;

public interface UgovorRepository extends JpaRepository<Ugovor, Long> {

	@Query("SELECT u FROM Ugovor u WHERE "
			+ "(:interniBroj IS NULL or u.interniBroj LIKE :interniBroj) AND"
			+ "(:dobavljacNaziv IS NULL or u.dobavljac.naziv LIKE :dobavljacNaziv) AND"
			+ "(:dobavljacMaticniBroj IS NULL or u.dobavljac.maticniBroj LIKE :dobavljacMaticniBroj) AND"
			+ "(:ugovorenaVrednostMin IS NULL or u.ugovorenaVrednost >= :ugovorenaVrednostMin) AND"
			+ "(:ugovorenaVrednostMax IS NULL or u.ugovorenaVrednost <= :ugovorenaVrednostMax) AND"
			+ "(:nabavkaId IS NULL or u.nabavka.id = :nabavkaId) AND"
			+ "(:vrstaPostupkaId IS NULL or u.nabavka.vrstaPostupka.id = :vrstaPostupkaId) AND"
			+ "(:vrstaPredmetaId iS NULL or u.nabavka.vrstaPredmeta.id = :vrstaPredmetaId)")
	Page<Ugovor> findAll(
			@Param(value = "interniBroj") String interniBroj, 
			@Param(value = "dobavljacNaziv") String dobavljacNaziv, 
			@Param(value = "dobavljacMaticniBroj") String dobavljacMaticniBroj,
			@Param(value = "ugovorenaVrednostMin") Integer ugovorenaVrednostMin, 
			@Param(value = "ugovorenaVrednostMax") Integer ugovorenaVrednostMax, 
			@Param(value = "nabavkaId") Long nabavkaId, 
			@Param(value = "vrstaPostupkaId") Long vrstaPostupkaId,
			@Param(value = "vrstaPredmetaId") Long vrstaPredmetaId, 
			Pageable pageRequest);

	Page<Ugovor> findByNabavkaId(Long id, Pageable pageRequest);

	Page<Ugovor> findByDobavljacId(Long id, Pageable pageRequest);

}

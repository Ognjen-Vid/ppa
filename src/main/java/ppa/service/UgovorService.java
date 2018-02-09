package ppa.service;

import org.springframework.data.domain.Page;

import ppa.model.Ugovor;

public interface UgovorService {

	Ugovor findOne(Long id);
	Page<Ugovor> findAll(int pageNum);
	Page<Ugovor> findAll(
			String interniBroj, 
			String dobavljacNaziv, 
			String dobavljacMaticniBroj,
			Integer ugovorenaVrednostMin, 
			Integer ugovorenaVrednostMax, 
			Long nabavkaId, 
			Long vrstaPostupkaId,
			Long vrstaPredmetaId,
			int pageNum);
	Page<Ugovor> findByNabavkaId(Long id,int pageNum);
	Page<Ugovor> findByDobavljacId(Long id, int pageNum);
	void save(Ugovor ugovor);
	void delete(Long id);
	
}

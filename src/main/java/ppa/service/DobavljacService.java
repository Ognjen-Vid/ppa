package ppa.service;

import org.springframework.data.domain.Page;

import ppa.model.Dobavljac;

public interface DobavljacService {
	
	Dobavljac findOne(Long id);
	Page<Dobavljac> findAll(int pageNum);
	Page<Dobavljac> findAll(
			String naziv,
			String maticniBroj,
			int pageNum);
	Dobavljac findByMaticniBroj(String maticniBroj);
	void save(Dobavljac dobavljac);
	void delete(Long id);

}

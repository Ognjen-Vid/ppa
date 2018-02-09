package ppa.service;

import java.util.List;

import org.springframework.data.domain.Page;

import ppa.model.Nabavka;

public interface NabavkaService {
	
	Nabavka findOne(Long id);
	List<Nabavka> findAll();
	Page<Nabavka> findAll(int pageNum);
	Page<Nabavka> findAll(
			String oznaka, 
			Integer procenjenaVrednostMin, 
			Integer procenjenaVrednostMax,
			Long vrstaPostupkaId, 
			Long vrstaPredmetaId, 
			int pageNum);
	void save(Nabavka nabavka);
	void delete(Long id);
	

}

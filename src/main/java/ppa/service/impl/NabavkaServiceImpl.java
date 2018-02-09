package ppa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ppa.model.Nabavka;
import ppa.repository.NabavkaRepository;
import ppa.service.NabavkaService;

@Service
public class NabavkaServiceImpl implements NabavkaService {

	NabavkaRepository nabavkaRepo;
	
	@Autowired
	public NabavkaServiceImpl(NabavkaRepository nabavkaRepo) {
		this.nabavkaRepo = nabavkaRepo;
	}
	
	@Override
	public Nabavka findOne(Long id) {
		return nabavkaRepo.findOne(id);
	}

	@Override
	public Page<Nabavka> findAll(int pageNum) {
		return nabavkaRepo.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public Page<Nabavka> findAll(String oznaka, Integer procenjenaVrednostMin, Integer procenjenaVrednostMax,
			Long vrstaPostupkaId, Long vrstaPredmetaId, int pageNum) {
		if(oznaka != null) 
			oznaka = "%" + oznaka + "%";
		return nabavkaRepo.findAll(oznaka, procenjenaVrednostMin, procenjenaVrednostMax, vrstaPostupkaId, vrstaPredmetaId, new PageRequest(pageNum, 5));
	}

	@Override
	public void save(Nabavka nabavka) {
		nabavkaRepo.save(nabavka);
	}

	@Override
	public void delete(Long id) {
		nabavkaRepo.delete(id);
	}

	@Override
	public List<Nabavka> findAll() {
		return nabavkaRepo.findAll();
	}

}

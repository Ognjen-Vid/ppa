package ppa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ppa.model.VrstaPredmeta;
import ppa.repository.VrstaPredmetaRepository;
import ppa.service.VrstaPredmetaService;

@Service
public class VrstaPredmetaImpl implements VrstaPredmetaService {
	
	VrstaPredmetaRepository vrstaPredmetaRepo;
	
	@Autowired
	public VrstaPredmetaImpl(VrstaPredmetaRepository vrstaPredmetaRepo) {
		this.vrstaPredmetaRepo = vrstaPredmetaRepo;
	}
	
	@Override
	public VrstaPredmeta findOne(Long id) {
		return vrstaPredmetaRepo.findOne(id);
	}

	@Override
	public List<VrstaPredmeta> findAll() {
		return vrstaPredmetaRepo.findAll();
	}

}

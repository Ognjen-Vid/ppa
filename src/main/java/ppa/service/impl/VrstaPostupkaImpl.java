package ppa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ppa.model.VrstaPostupka;
import ppa.repository.VrstaPostupkaRepository;
import ppa.service.VrstaPostupkaService;

@Service
public class VrstaPostupkaImpl implements VrstaPostupkaService {

	VrstaPostupkaRepository vrstaPostupkaRepo;
	
	@Autowired
	public VrstaPostupkaImpl(VrstaPostupkaRepository vrstaPostupkaRepo) {
		this.vrstaPostupkaRepo = vrstaPostupkaRepo;
	}
	
	@Override
	public VrstaPostupka findOne(Long id) {
		return vrstaPostupkaRepo.findOne(id);
	}

	@Override
	public List<VrstaPostupka> findAll() {
		return vrstaPostupkaRepo.findAll();
	}

}

package ppa.service;

import java.util.List;

import ppa.model.VrstaPostupka;

public interface VrstaPostupkaService {

	VrstaPostupka findOne(Long id);
	List<VrstaPostupka> findAll();
	

}

package ppa.service;

import java.util.List;

import ppa.model.VrstaPredmeta;

public interface VrstaPredmetaService {

	VrstaPredmeta findOne(Long id);
	List<VrstaPredmeta> findAll();

}

package ppa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ppa.model.Ugovor;
import ppa.repository.UgovorRepository;
import ppa.service.UgovorService;

@Service
public class UgovorServiceImpl implements UgovorService {

	UgovorRepository ugovorRepo;
	
	@Autowired
	UgovorServiceImpl(UgovorRepository ugovorRepo){
		this.ugovorRepo = ugovorRepo;
	}
	
	@Override
	public Ugovor findOne(Long id) {
		return ugovorRepo.findOne(id);
	}

	@Override
	public Page<Ugovor> findAll(int pageNum) {
		return ugovorRepo.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public void save(Ugovor ugovor) {
		ugovorRepo.save(ugovor);
	}

	@Override
	public void delete(Long id) {
		ugovorRepo.delete(id);
	}

	@Override
	public Page<Ugovor> findByNabavkaId(Long id, int pageNum) {
		return ugovorRepo.findByNabavkaId(id, new PageRequest(pageNum, 5));
	}

	@Override
	public Page<Ugovor> findByDobavljacId(Long id, int pageNum) {
		return ugovorRepo.findByDobavljacId(id, new PageRequest(pageNum, 5));
	}

	@Override
	public Page<Ugovor> findAll(String interniBroj, String dobavljacNaziv, String dobavljacMaticniBroj,
			Integer ugovorenaVrednostMin, Integer ugovorenaVrednostMax, 
			Long nabavkaId, Long vrstaPostupkaId, Long vrstaPredmetaId, int pageNum) {
		
		if(interniBroj != null)
			interniBroj = "%" + interniBroj + "%";
		if(dobavljacNaziv != null)
			dobavljacNaziv = "%" + dobavljacNaziv + "%";
		if(dobavljacMaticniBroj != null)
			dobavljacMaticniBroj = "%" + dobavljacMaticniBroj + "%";
		
		return ugovorRepo.findAll(interniBroj, dobavljacNaziv, dobavljacMaticniBroj,
				ugovorenaVrednostMin, ugovorenaVrednostMax, nabavkaId, vrstaPostupkaId,
				vrstaPredmetaId, new PageRequest(pageNum, 5));
	}

}

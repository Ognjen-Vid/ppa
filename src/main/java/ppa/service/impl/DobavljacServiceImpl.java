package ppa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ppa.model.Dobavljac;
import ppa.repository.DobavljacRepository;
import ppa.service.DobavljacService;

@Service
public class DobavljacServiceImpl implements DobavljacService {
	
	DobavljacRepository dobavljacRepo;

	@Autowired
	public DobavljacServiceImpl(DobavljacRepository dobavljacRepo) {
		this.dobavljacRepo = dobavljacRepo;
	}
	
	@Override
	public Dobavljac findOne(Long id) {
		return dobavljacRepo.findOne(id);
	}

	@Override
	public Page<Dobavljac> findAll(int pageNum) {
		return dobavljacRepo.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public Page<Dobavljac> findAll(String naziv, String maticniBroj, int pageNum) {
		if(naziv != null)
			naziv = "%" + naziv + "%";
		if(maticniBroj != null)
			maticniBroj = "%" + maticniBroj + "%";
		return dobavljacRepo.findAll(naziv, maticniBroj, new PageRequest(pageNum, 5));
	}
	
	//S obzirom da se ova metoda poziva jedino prilikom unosa novog ugovora
	//u slucaju da dobavljac ne postoji, dodace se u bazu
	@Override
	public Dobavljac findByMaticniBroj(String maticniBroj, String naziv) {
		Dobavljac dobavljac = dobavljacRepo.findByMaticniBroj(maticniBroj);
		if(dobavljac == null) {
			dobavljac = new Dobavljac();
			dobavljac.setNaziv(naziv);
			dobavljac.setMaticniBroj(maticniBroj);
			dobavljacRepo.save(dobavljac);
			dobavljac = dobavljacRepo.findByMaticniBroj(maticniBroj);
		}
		return dobavljac;
	}
	
	@Override
	public void save(Dobavljac dobavljac) {
		dobavljacRepo.save(dobavljac);
	}

	@Override
	public void delete(Long id) {
		dobavljacRepo.delete(id);
	}

}

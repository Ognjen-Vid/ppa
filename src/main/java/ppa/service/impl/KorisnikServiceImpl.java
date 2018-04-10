package ppa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ppa.model.Korisnik;
import ppa.repository.KorisnikRepository;
import ppa.service.KorisnikService;

@Service
public class KorisnikServiceImpl implements KorisnikService {

	KorisnikRepository korisnikRepo;
	
	@Autowired
	public KorisnikServiceImpl(KorisnikRepository korisnikRepo) {
		this.korisnikRepo = korisnikRepo;
	}

	@Override
	public Korisnik findByKorisnickoIme(String korisnickoIme) {
		return korisnikRepo.findByKorisnickoIme(korisnickoIme);
	}

}

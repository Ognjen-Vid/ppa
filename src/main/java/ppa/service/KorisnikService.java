package ppa.service;

import ppa.model.Korisnik;

public interface KorisnikService {

	Korisnik findByKorisnickoIme(String korisnickoIme);
}

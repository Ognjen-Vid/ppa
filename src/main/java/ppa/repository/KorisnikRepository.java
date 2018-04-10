package ppa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ppa.model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	Korisnik findByKorisnickoIme(String korisnickoIme);
}

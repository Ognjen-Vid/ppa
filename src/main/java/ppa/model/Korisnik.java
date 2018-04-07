package ppa.model;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class Korisnik {
	
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String korisnickoIme;
	@Column
	private String lozinka;
	private String lozinkaProvera;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	@Transient
	public String getLozinkaProvera() {
		return lozinkaProvera;
	}
	public void setLozinkaProvera(String lozinkaProvera) {
		this.lozinkaProvera = lozinkaProvera;
	}
}

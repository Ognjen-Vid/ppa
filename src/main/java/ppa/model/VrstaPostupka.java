package ppa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class VrstaPostupka {

	@Id
	@GeneratedValue
	@Column
	Long id;
	@Column
	String naziv;
	
	@OneToMany(mappedBy = "vrstaPostupka", cascade = CascadeType.ALL)
	List<Nabavka> nabavke = new ArrayList<Nabavka>();
	
	//Odrzavanje veze izmedju entiteta, JPA ne radi sam, moramo mi
	public void add(Nabavka nabavka) {
		this.nabavke.add(nabavka);
		if(!nabavka.getVrstaPostupka().equals(this)) //Da nema ove provere, doslo bi to infinite loop-a
			nabavka.setVrstaPostupka(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Nabavka> getNabavke() {
		return nabavke;
	}

	public void setNabavke(List<Nabavka> nabavke) {
		this.nabavke = nabavke;
	}
	
	
}

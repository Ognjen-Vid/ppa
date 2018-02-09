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

@Table
@Entity
public class Dobavljac {

	@Id
	@GeneratedValue
	@Column
	Long id;
	@Column
	String naziv;
	@Column
	String maticniBroj;
	
	//Kada obrisemo Dobavljaca, obrisace se svi Ugovori vezani za njega
	@OneToMany(mappedBy="dobavljac", cascade = CascadeType.ALL)
	List<Ugovor> ugovori = new ArrayList<Ugovor>();
	
	//Odrzavanje veze izmedju entiteta, JPA ne radi sam, moramo mi
	public void add(Ugovor ugovor) {
		this.ugovori.add(ugovor);
		if(!this.equals(ugovor.getDobavljac())) //Da nema ove provere, doslo bi to infinite loop-a
			ugovor.setDobavljac(this);
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

	public String getMaticniBroj() {
		return maticniBroj;
	}

	public void setMaticniBroj(String maticniBroj) {
		this.maticniBroj = maticniBroj;
	}

	public List<Ugovor> getUgovori() {
		return ugovori;
	}

	public void setUgovori(List<Ugovor> ugovori) {
		this.ugovori = ugovori;
	}
	
	
}

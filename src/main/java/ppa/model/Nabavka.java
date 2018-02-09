package ppa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Nabavka {
	
	@Id
	@Column
	@GeneratedValue
	Long id;
	@Column
	String oznaka;
	@Column
	int procenjenaVrednost;
	
	@ManyToOne(fetch = FetchType.LAZY)
	VrstaPostupka vrstaPostupka;
	
	@ManyToOne(fetch = FetchType.LAZY)
	VrstaPredmeta vrstaPredmeta;
	
	//Kada obrisemo Nabavku, obrisace se svi Ugovori vezani za nju
	@OneToMany(mappedBy="nabavka", cascade = CascadeType.ALL)
	List<Ugovor> ugovori = new ArrayList<Ugovor>();
	
	//Odrzavanje veze izmedju entiteta, JPA ne radi sam, moramo mi
	public void add(Ugovor ugovor) {
		this.ugovori.add(ugovor);
		if(!ugovor.getNabavka().equals(this)) //Da nema ove provere, doslo bi to infinite loop-a
			ugovor.setNabavka(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public int getProcenjenaVrednost() {
		return procenjenaVrednost;
	}

	public void setProcenjenaVrednost(int procenjenaVrednost) {
		this.procenjenaVrednost = procenjenaVrednost;
	}

	public VrstaPostupka getVrstaPostupka() {
		return vrstaPostupka;
	}

	//Odrzavanje veze izmedju entiteta, JPA ne radi sam, moramo mi
	public void setVrstaPostupka(VrstaPostupka vrstaPostupka) {
		this.vrstaPostupka = vrstaPostupka;
		if(vrstaPostupka != null && !vrstaPostupka.getNabavke().contains(this))
			vrstaPostupka.getNabavke().add(this); // Ovde bi trebalo da stoji vrstaPostupka.add(this);
	}

	public VrstaPredmeta getVrstaPredmeta() {
		return vrstaPredmeta;
	}
	
	//Odrzavanje veze izmedju entiteta, JPA ne radi sam, moramo mi
	public void setVrstaPredmeta(VrstaPredmeta vrstaPredmeta) {
		this.vrstaPredmeta = vrstaPredmeta;
		if(vrstaPredmeta != null && !vrstaPredmeta.getNabavke().contains(this))
			vrstaPredmeta.getNabavke().add(this); // Ovde bi trebalo da stoji vrstaPredmeta.add(this);
	}

	public List<Ugovor> getUgovori() {
		return ugovori;
	}

	public void setUgovori(List<Ugovor> ugovori) {
		this.ugovori = ugovori;
	}

}

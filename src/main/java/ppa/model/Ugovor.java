package ppa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.Id;

@Entity
@Table
public class Ugovor {

	@Id
	@GeneratedValue
	@Column
	Long id;
	@Column
	String interniBroj;
	@Column
	int ugovorenaVrednost;
	
	@ManyToOne(fetch = FetchType.LAZY)
	Nabavka nabavka;
	
	@ManyToOne(fetch = FetchType.LAZY)
	Dobavljac dobavljac;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInterniBroj() {
		return interniBroj;
	}

	public void setInterniBroj(String interniBroj) {
		this.interniBroj = interniBroj;
	}

	public int getUgovorenaVrednost() {
		return ugovorenaVrednost;
	}

	public void setUgovorenaVrednost(int ugovorenaVrednost) {
		this.ugovorenaVrednost = ugovorenaVrednost;
	}

	public Nabavka getNabavka() {
		return nabavka;
	}

	//Odrzavanje veze izmedju entiteta, JPA ne radi sam, moramo mi
	public void setNabavka(Nabavka nabavka) {
		this.nabavka = nabavka;
		if(!nabavka.getUgovori().contains(this))
			nabavka.getUgovori().add(this); // Ovde bi trebalo da stoji nabavka.add(this);
	}

	public Dobavljac getDobavljac() {
		return dobavljac;
	}

	//Odrzavanje veze izmedju entiteta, JPA ne radi sam, moramo mi
	public void setDobavljac(Dobavljac dobavljac) {
		this.dobavljac = dobavljac;
		if(!dobavljac.getUgovori().contains(this))
			dobavljac.getUgovori().add(this); // Ovde bi trebalo da stoji dobavljac.add(this);
	}

}

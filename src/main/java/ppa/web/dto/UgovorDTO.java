package ppa.web.dto;

public class UgovorDTO {

	Long id;
	String interniBroj;
	int ugovorenaVrednost;
	String datumZakljucenja;
	
	Long nabavkaId;
	String nabavkaOznaka;
	
	Long dobavljacId;
	String dobavljacNaziv;
	String dobavljacMaticniBroj;
	
	public String getDobavljacMaticniBroj() {
		return dobavljacMaticniBroj;
	}

	public void setDobavljacMaticniBroj(String dobavljacMaticniBroj) {
		this.dobavljacMaticniBroj = dobavljacMaticniBroj;
	}

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
	
	public String getDatumZakljucenja() {
		return datumZakljucenja;
	}

	public void setDatumZakljucenja(String datumZakljucenja) {
		this.datumZakljucenja = datumZakljucenja;
	}

	public Long getNabavkaId() {
		return nabavkaId;
	}

	public void setNabavkaId(Long nabavkaId) {
		this.nabavkaId = nabavkaId;
	}

	public String getNabavkaOznaka() {
		return nabavkaOznaka;
	}

	public void setNabavkaOznaka(String nabavkaOznaka) {
		this.nabavkaOznaka = nabavkaOznaka;
	}

	public Long getDobavljacId() {
		return dobavljacId;
	}

	public void setDobavljacId(Long dobavljacId) {
		this.dobavljacId = dobavljacId;
	}

	public String getDobavljacNaziv() {
		return dobavljacNaziv;
	}

	public void setDobavljacNaziv(String dobavljacNaziv) {
		this.dobavljacNaziv = dobavljacNaziv;
	}

}

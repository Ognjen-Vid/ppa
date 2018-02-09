package ppa.web.dto;

public class NabavkaDTO {
	
	Long id;
	String oznaka;
	int procenjenaVrednost;
	
	Long vrstaPostupkaId;
	String vrstaPostupkaNaziv;
	
	Long vrstaPredmetaId;
	String vrstaPredmetaNaziv;
	
	public String getVrstaPostupkaNaziv() {
		return vrstaPostupkaNaziv;
	}
	public void setVrstaPostupkaNaziv(String vrstaPostupkaNaziv) {
		this.vrstaPostupkaNaziv = vrstaPostupkaNaziv;
	}
	public String getVrstaPredmetaNaziv() {
		return vrstaPredmetaNaziv;
	}
	public void setVrstaPredmetaNaziv(String vrstaPredmetaNaziv) {
		this.vrstaPredmetaNaziv = vrstaPredmetaNaziv;
	}
	public void setVrstaPostupkaId(Long vrstaPostupkaId) {
		this.vrstaPostupkaId = vrstaPostupkaId;
	}
	public void setVrstaPredmetaId(Long vrstaPredmetaId) {
		this.vrstaPredmetaId = vrstaPredmetaId;
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
	public Long getVrstaPostupkaId() {
		return vrstaPostupkaId;
	}
	public Long getVrstaPredmetaId() {
		return vrstaPredmetaId;
	}
	
}

package ppa.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ppa.model.Nabavka;
import ppa.service.NabavkaService;
import ppa.service.VrstaPostupkaService;
import ppa.service.VrstaPredmetaService;
import ppa.web.dto.NabavkaDTO;

@Component
public class DTOtoNabavka implements Converter<NabavkaDTO, Nabavka>{

	NabavkaService nabavkaService;
	VrstaPostupkaService vrstaPostupkaService;
	VrstaPredmetaService vrstaPredmetaService;
	
	@Autowired
	DTOtoNabavka (NabavkaService nabavkaService, VrstaPostupkaService vrstaPostupkaService, VrstaPredmetaService vrstaPredmetaService) {
		this.nabavkaService = nabavkaService;
		this.vrstaPostupkaService = vrstaPostupkaService;
		this.vrstaPredmetaService = vrstaPredmetaService;
	}
	
	//Ovde se definise akcija za PUT i POST, one class to rule them all
	@Override
	public Nabavka convert(NabavkaDTO source) {
		Nabavka nabavka = new Nabavka();
		//1. Ako != null, pretpostavlja se da je u pitanju PUT
		//2. Ako == null, pretpostavlja se da je u pitanju POST i code ispod se preskace
		if(source.getId() != null) {
			nabavka = nabavkaService.findOne(source.getId());
			if(nabavka == null) {
				throw new IllegalArgumentException("Nepostojeca nabavka");
			}
		}
		//1. U kom slucaju ce doci do izmene atributa postojceg entiteta
			//koji se vraca RestControlleru da jpa izvrsi save-merge
			//nema potrebe da se radi nabavka.setId, jer vec postoji
		//2. U kom slucaju ce doci do kreiranja novog entiteta
			//koji se vraca RestControlleru da jpa izvrsi save-persist
		nabavka.setOznaka(source.getOznaka());
		nabavka.setProcenjenaVrednost(source.getProcenjenaVrednost());
		nabavka.setVrstaPostupka(vrstaPostupkaService.findOne(source.getVrstaPostupkaId()));
		nabavka.setVrstaPredmeta(vrstaPredmetaService.findOne(source.getVrstaPredmetaId()));
		return nabavka;
	}
}

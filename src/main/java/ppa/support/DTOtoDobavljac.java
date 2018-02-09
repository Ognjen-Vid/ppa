package ppa.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ppa.model.Dobavljac;
import ppa.service.DobavljacService;
import ppa.web.dto.DobavljacDTO;

@Component
public class DTOtoDobavljac implements Converter<DobavljacDTO, Dobavljac>{
	
	DobavljacService dobavljacService;
	
	@Autowired
	DTOtoDobavljac (DobavljacService dobavljacService) {
		this.dobavljacService = dobavljacService;
	}
	
	//Ovde se definise akcija za PUT i POST, one class to rule them all
	@Override
	public Dobavljac convert(DobavljacDTO dto) {
		Dobavljac retVal = new Dobavljac();
		//1. Ako != null, pretpostavlja se da je u pitanju PUT
		//2. Ako == null, pretpostavlja se da je u pitanju POST i code ispod se preskace
		if(dto.getId() != null) {
			retVal = dobavljacService.findOne(dto.getId());
			if(retVal == null)
				throw new IllegalArgumentException("Non-existent entity");
		}
		
		//1. U kom slucaju ce doci do izmene atributa postojceg entiteta
			//koji se vraca RestControlleru da jpa izvrsi save-merge
			//nema potrebe da se radi nabavka.setId, jer vec postoji
		//2. U kom slucaju ce doci do kreiranja novog entiteta
			//koji se vraca RestControlleru da jpa izvrsi save-pe
		retVal.setMaticniBroj(dto.getMaticniBroj());
		retVal.setNaziv(dto.getNaziv());
		return retVal;
	}
		
		
		

}

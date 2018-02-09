package ppa.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ppa.model.Dobavljac;
import ppa.model.Ugovor;
import ppa.service.DobavljacService;
import ppa.service.NabavkaService;
import ppa.service.UgovorService;
import ppa.web.dto.UgovorDTO;

@Component
public class DTOtoUgovor implements Converter<UgovorDTO, Ugovor>{

	UgovorService ugovorService;
	NabavkaService nabavkaService;
	DobavljacService dobavljacService;
	
	@Autowired
	DTOtoUgovor(UgovorService ugovorService, NabavkaService nabavkaService, DobavljacService dobavljacService){
		this.ugovorService = ugovorService;
		this.nabavkaService = nabavkaService;
		this.dobavljacService = dobavljacService;
	}
	
	@Override
	public Ugovor convert(UgovorDTO dto) {
		Ugovor ugovor = new Ugovor();
		if(dto.getId() != null) {
			ugovor = ugovorService.findOne(dto.getId());
			if(ugovor == null) {
				throw new IllegalArgumentException("Izmena nepostojceg entitea");
			}
		}
		Dobavljac dobavljac = dobavljacService.findByMaticniBroj(dto.getDobavljacMaticniBroj());
		if(dobavljac == null) {
			dobavljac = new Dobavljac();
			dobavljac.setNaziv(dto.getDobavljacNaziv());
			dobavljac.setMaticniBroj(dto.getDobavljacMaticniBroj());
			dobavljacService.save(dobavljac);
			dobavljac = dobavljacService.findByMaticniBroj(dto.getDobavljacMaticniBroj());
		}
		ugovor.setDobavljac(dobavljac);
		ugovor.setInterniBroj(dto.getInterniBroj());
		ugovor.setNabavka(nabavkaService.findOne(dto.getNabavkaId()));
		ugovor.setUgovorenaVrednost(dto.getUgovorenaVrednost());
		return ugovor;
	}

}

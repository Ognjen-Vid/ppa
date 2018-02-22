package ppa.support;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

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
		ugovor.setDobavljac(dobavljacService.findByMaticniBroj(dto.getDobavljacMaticniBroj(), dto.getDobavljacNaziv()));
		ugovor.setInterniBroj(dto.getInterniBroj());
		try {
			ugovor.setDatumZakljucenja(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDatumZakljucenja()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ugovor.setNabavka(nabavkaService.findOne(dto.getNabavkaId()));
		ugovor.setUgovorenaVrednost(Integer.parseInt(dto.getUgovorenaVrednost()));
		return ugovor;
	}

}

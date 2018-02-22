package ppa.support;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ppa.model.Ugovor;
import ppa.web.dto.UgovorDTO;

@Component
public class UgovorToDTO implements Converter<Ugovor, UgovorDTO>{

	@Override
	public UgovorDTO convert(Ugovor persisted) {
		UgovorDTO dto = new UgovorDTO();
		dto.setDobavljacId(persisted.getDobavljac().getId());
		dto.setDobavljacMaticniBroj(persisted.getDobavljac().getMaticniBroj());
		dto.setDobavljacNaziv(persisted.getDobavljac().getNaziv());
		dto.setId(persisted.getId());
		dto.setInterniBroj(persisted.getInterniBroj());
		dto.setNabavkaId(persisted.getNabavka().getId());
		dto.setNabavkaOznaka(persisted.getNabavka().getOznaka());
		dto.setUgovorenaVrednost(persisted.getUgovorenaVrednost());
		dto.setDatumZakljucenja(new SimpleDateFormat("dd-MM-yyyy").format(persisted.getDatumZakljucenja()));
		return dto;
	}
	
	public List<UgovorDTO> convert(List<Ugovor> ugovori) {
		List<UgovorDTO> dtos = new ArrayList<>();
		for(Ugovor u : ugovori) {
			dtos.add(convert(u));
		}
		return dtos;
	}

}

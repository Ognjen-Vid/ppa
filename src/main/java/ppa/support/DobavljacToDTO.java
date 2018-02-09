package ppa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ppa.model.Dobavljac;
import ppa.web.dto.DobavljacDTO;

@Component
public class DobavljacToDTO implements Converter<Dobavljac, DobavljacDTO> {

	@Override
	public DobavljacDTO convert(Dobavljac source) {
		DobavljacDTO dto = new DobavljacDTO();
		
		dto.setId(source.getId());
		dto.setMaticniBroj(source.getMaticniBroj());
		dto.setNaziv(source.getNaziv());
		
		return dto;
	}
	
	public List<DobavljacDTO> convert(List<Dobavljac> dobavljaci) {
		List<DobavljacDTO> retVal = new ArrayList<DobavljacDTO>();
		
		for(Dobavljac d : dobavljaci) {
			retVal.add(convert(d));
		}
		
		return retVal;
	}
}

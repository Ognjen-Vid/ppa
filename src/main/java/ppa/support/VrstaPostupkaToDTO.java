package ppa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ppa.model.VrstaPostupka;
import ppa.web.dto.VrstaPostupkaDTO;

@Component
public class VrstaPostupkaToDTO implements Converter<VrstaPostupka, VrstaPostupkaDTO>{

	@Override
	public VrstaPostupkaDTO convert(VrstaPostupka source) {
		VrstaPostupkaDTO dto = new VrstaPostupkaDTO();
		dto.setId(source.getId());
		dto.setNaziv(source.getNaziv());
		return dto;
	}
	
	public List<VrstaPostupkaDTO> convert(List<VrstaPostupka> list) {
		List<VrstaPostupkaDTO> dtos = new ArrayList<>();
		for(VrstaPostupka vp : list) {
			dtos.add(convert(vp));
		}
		return dtos;
	}

}

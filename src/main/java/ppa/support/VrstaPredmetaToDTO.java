package ppa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ppa.model.VrstaPredmeta;
import ppa.web.dto.VrstaPredmetaDTO;

@Component
public class VrstaPredmetaToDTO implements Converter<VrstaPredmeta, VrstaPredmetaDTO>{

	@Override
	public VrstaPredmetaDTO convert(VrstaPredmeta source) {
		VrstaPredmetaDTO dto = new VrstaPredmetaDTO();
		dto.setId(source.getId());
		dto.setNaziv(source.getNaziv());
		return dto;
	}
	
	public List<VrstaPredmetaDTO> convert(List<VrstaPredmeta> list) {
		List<VrstaPredmetaDTO> dtos = new ArrayList<>();
		for(VrstaPredmeta vp : list) {
			dtos.add(convert(vp));
		}
		return dtos;
	}

}

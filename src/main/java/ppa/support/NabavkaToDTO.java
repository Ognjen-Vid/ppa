package ppa.support;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ppa.model.Nabavka;
import ppa.web.dto.NabavkaDTO;

@Component
public class NabavkaToDTO implements Converter<Nabavka, NabavkaDTO>{

	@Override
	public NabavkaDTO convert(Nabavka persisted) {
		NabavkaDTO dto = new NabavkaDTO();
		
		dto.setId(persisted.getId());
		dto.setOznaka(persisted.getOznaka());
		dto.setProcenjenaVrednost(persisted.getProcenjenaVrednost());
		dto.setVrstaPostupkaId(persisted.getVrstaPostupka().getId());
		dto.setVrstaPostupkaNaziv(persisted.getVrstaPostupka().getNaziv());
		dto.setVrstaPredmetaId(persisted.getVrstaPredmeta().getId());
		dto.setVrstaPredmetaNaziv(persisted.getVrstaPredmeta().getNaziv());
		dto.setDatumOtvaranja(new SimpleDateFormat("dd-MM-yyyy").format(persisted.getDatumOtvaranja()));
		return dto;
	}
	
	public List<NabavkaDTO> convert(List<Nabavka> lista) {
		List<NabavkaDTO> dtos = new ArrayList<NabavkaDTO>();
		
		for(Nabavka n : lista) {
			dtos.add(convert(n));
		}
		
		return dtos;
	}

}

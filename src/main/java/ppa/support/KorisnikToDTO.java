package ppa.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ppa.model.Korisnik;
import ppa.web.dto.KorisnikDTO;

@Component
public class KorisnikToDTO implements Converter<Korisnik, KorisnikDTO> {

	@Override
	public KorisnikDTO convert(Korisnik source) {
		KorisnikDTO dto = new KorisnikDTO();
		
		dto.setId(source.getId());
		dto.setKorisnickoIme(source.getKorisnickoIme());
		
		return dto;
	}
	
}

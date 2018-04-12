package ppa.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ppa.model.Korisnik;
import ppa.service.KorisnikService;
import ppa.support.KorisnikToDTO;
import ppa.web.dto.KorisnikDTO;

@RestController
@RequestMapping(value = "/api/login")
public class LoginController {
	
	KorisnikService korisnikService;
	KorisnikToDTO toDTO;
	
	@Autowired
	LoginController(KorisnikService korisnikService, KorisnikToDTO toDTO) {
		this.korisnikService = korisnikService;
		this.toDTO = toDTO;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<KorisnikDTO> getAll(
			@RequestParam(required = false, value="korisnickoIme") String korisnickoIme, 
			@RequestParam(required = false, value="lozinka")String lozinka) {
		
		Korisnik retVal = null;
		
		if((korisnickoIme != null && !korisnickoIme.trim().isEmpty() && korisnickoIme != "") || (lozinka != null && !lozinka.trim().isEmpty() && lozinka != ""))
			retVal = korisnikService.findByKorisnickoIme(korisnickoIme);

		return new ResponseEntity<KorisnikDTO>(toDTO.convert(retVal), HttpStatus.OK);
	}
	
	

}

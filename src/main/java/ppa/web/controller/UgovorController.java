package ppa.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ppa.model.Ugovor;
import ppa.service.UgovorService;
import ppa.support.DTOtoUgovor;
import ppa.support.UgovorToDTO;
import ppa.web.dto.UgovorDTO;

@RestController
@RequestMapping(value = "/api/ugovori")
public class UgovorController {

	UgovorService ugovorService;
	UgovorToDTO toDTO;
	DTOtoUgovor toUgovor;

	@Autowired
	UgovorController(UgovorService ugovorService, UgovorToDTO toDTO, DTOtoUgovor toUgovor) {
		this.ugovorService = ugovorService;
		this.toDTO = toDTO;
		this.toUgovor = toUgovor;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<UgovorDTO>> getAll(
			@RequestParam(required = false) String interniBroj,
			@RequestParam(required = false) String dobavljacNaziv,
			@RequestParam(required = false) String dobavljacMaticniBroj,
			@RequestParam(required = false) Integer ugovorenaVrednostMin, 
			@RequestParam(required = false) Integer ugovorenaVrednostMax,
			@RequestParam(required = false) Date datumZakljucenjaOd,
			@RequestParam(required = false) Date datumZakljucenjaDo,
			@RequestParam(required = false) Long nabavkaId,
			@RequestParam(required = false) Long vrstaPostupkaId,
			@RequestParam(required = false) Long vrstaPredmetaId,
			@RequestParam(value="pageNum") int pageNum) {
		
		Page<Ugovor> retVal;
		
		if((interniBroj != null && !interniBroj.trim().isEmpty() && interniBroj != "") 
				|| (dobavljacNaziv != null && !dobavljacNaziv.trim().isEmpty() && dobavljacNaziv != "")
				|| (dobavljacMaticniBroj != null && !dobavljacMaticniBroj.trim().isEmpty() && dobavljacMaticniBroj != "")
				|| ugovorenaVrednostMin != null
				|| ugovorenaVrednostMax != null
				|| nabavkaId != null
				|| vrstaPostupkaId != null
				|| vrstaPredmetaId != null) {
			
			retVal = ugovorService.findAll(
					interniBroj, 
					dobavljacNaziv, 
					dobavljacMaticniBroj, 
					ugovorenaVrednostMin, 
					ugovorenaVrednostMax,
					nabavkaId,
					vrstaPostupkaId,
					vrstaPredmetaId,
					pageNum);
		} else {
			 retVal = ugovorService.findAll(pageNum);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(retVal.getTotalPages()));
		headers.add("totalUgovori", Long.toString(retVal.getTotalElements()));
		
		return new ResponseEntity<List<UgovorDTO>>(toDTO.convert(retVal.getContent()), headers, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	ResponseEntity<UgovorDTO> getOne(@PathVariable Long id) {
		Ugovor retVal = ugovorService.findOne(id);
		if (retVal == null)
			return new ResponseEntity<UgovorDTO>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<UgovorDTO>(toDTO.convert(retVal), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	ResponseEntity<UgovorDTO> put(@RequestBody UgovorDTO dto, @PathVariable Long id) {
		if (!id.equals(dto.getId())) {
			return new ResponseEntity<UgovorDTO>(HttpStatus.BAD_REQUEST);
		}
		Ugovor persisted = toUgovor.convert(dto);
		ugovorService.save(persisted);
		return new ResponseEntity<UgovorDTO>(toDTO.convert(persisted), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<UgovorDTO> post(@RequestBody UgovorDTO dto) {
		Ugovor persisting = toUgovor.convert(dto);
		ugovorService.save(persisting);
		return new ResponseEntity<UgovorDTO>(toDTO.convert(persisting), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	ResponseEntity<UgovorDTO> delete(@PathVariable Long id) {
		ugovorService.delete(id);
		return new ResponseEntity<UgovorDTO>(HttpStatus.NO_CONTENT);
	}

}

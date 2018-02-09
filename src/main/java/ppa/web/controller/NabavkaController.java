package ppa.web.controller;

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

import ppa.model.Nabavka;
import ppa.model.Ugovor;
import ppa.service.NabavkaService;
import ppa.service.UgovorService;
import ppa.support.DTOtoNabavka;
import ppa.support.NabavkaToDTO;
import ppa.support.UgovorToDTO;
import ppa.web.dto.NabavkaDTO;
import ppa.web.dto.UgovorDTO;

@RestController
@RequestMapping(value="/api/nabavke")
public class NabavkaController {

	NabavkaService nabavkaService;
	UgovorService ugovorService;
	NabavkaToDTO toDTO;
	DTOtoNabavka toNabavka;
	UgovorToDTO ugovorToDTO;
	
	@Autowired
	public NabavkaController(NabavkaService nabavkaService, UgovorService ugovorService, NabavkaToDTO toDto, DTOtoNabavka toNabavka, UgovorToDTO ugovorToDTO) {
		this.nabavkaService = nabavkaService;
		this.ugovorService = ugovorService;
		this.toDTO = toDto;
		this.toNabavka = toNabavka;
		this.ugovorToDTO = ugovorToDTO;
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<NabavkaDTO>> getAll(
			//Ako je value isto kao i aargument name, ne mora value
			@RequestParam(value="oznaka", required=false) String oznaka,
			@RequestParam(value="procenjenaVrednostMin", required=false) Integer procenjenaVrednostMin,
			@RequestParam(value="procenjenaVrednostMax", required=false) Integer procenjenaVrednostMax,
			@RequestParam(value="vrstaPostupkaId", required=false) Long vrstaPostupkaId,
			@RequestParam(value="vrstaPredmetaId", required=false) Long vrstaPredmetaId,
			@RequestParam(value="pageNum") int pageNum){

		Page<Nabavka> retVal = null;

		if(oznaka != null 
			|| procenjenaVrednostMin != null 
			|| procenjenaVrednostMax != null 
			|| vrstaPostupkaId != null 
			|| vrstaPredmetaId != null) {
			retVal = nabavkaService.findAll(oznaka, procenjenaVrednostMin, procenjenaVrednostMax, vrstaPostupkaId, vrstaPredmetaId, pageNum);
		} else {
			retVal = nabavkaService.findAll(pageNum);
		}

		List<Nabavka> lista = retVal.getContent();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(retVal.getTotalPages()));
		headers.add("totalNabavke", Long.toString(retVal.getTotalElements()));
		
		return new ResponseEntity<>(toDTO.convert(lista), headers, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{nabavkaId}/ugovori")
	ResponseEntity<List<UgovorDTO>> getUgovoriByNabavkaId(@PathVariable Long nabavkaId, @RequestParam(value="pageNum") int pageNum) {
		Page<Ugovor> retVal = ugovorService.findByNabavkaId(nabavkaId, pageNum);
		
		if (pageNum > retVal.getTotalPages() - 1)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(retVal.getTotalPages()));
		headers.add("totalUgovori", Long.toString(retVal.getTotalElements()));
		
		return new ResponseEntity<List<UgovorDTO>>(ugovorToDTO.convert(retVal.getContent()), headers, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/zaUgovore")
	public ResponseEntity<List<NabavkaDTO>> getAll(){
		return new ResponseEntity<>(toDTO.convert(nabavkaService.findAll()), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	ResponseEntity<NabavkaDTO> getOne(@PathVariable Long id) {
		Nabavka retVal = nabavkaService.findOne(id);
		if (retVal == null)
			return new ResponseEntity<NabavkaDTO>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<NabavkaDTO>(toDTO.convert(retVal), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	ResponseEntity<NabavkaDTO> put(@RequestBody NabavkaDTO dto, @PathVariable Long id) {
		if (!id.equals(dto.getId())) {
			return new ResponseEntity<NabavkaDTO>(HttpStatus.BAD_REQUEST);
		}
		Nabavka persisted = toNabavka.convert(dto);
		nabavkaService.save(persisted);
		return new ResponseEntity<NabavkaDTO>(toDTO.convert(persisted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<NabavkaDTO> post(@RequestBody NabavkaDTO dto) {
		Nabavka persisting = toNabavka.convert(dto);
		nabavkaService.save(persisting);
		return new ResponseEntity<NabavkaDTO>(toDTO.convert(persisting), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	ResponseEntity<NabavkaDTO> delete(@PathVariable Long id) {
		nabavkaService.delete(id);
		return new ResponseEntity<NabavkaDTO>(HttpStatus.NO_CONTENT);
	}
}
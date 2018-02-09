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

import ppa.model.Dobavljac;
import ppa.model.Ugovor;
import ppa.service.DobavljacService;
import ppa.service.UgovorService;
import ppa.support.DTOtoDobavljac;
import ppa.support.DobavljacToDTO;
import ppa.support.UgovorToDTO;
import ppa.web.dto.DobavljacDTO;
import ppa.web.dto.UgovorDTO;

@RestController
@RequestMapping(value = "/api/dobavljaci")
public class DobavljacController {

	DobavljacService dobavljacService;
	UgovorService ugovorService;
	DobavljacToDTO dobavljacToDTO;
	DTOtoDobavljac DTOtoDobavljac;
	UgovorToDTO ugovorToDTO;

	@Autowired
	DobavljacController(DobavljacService dobavljacService, UgovorService ugovorService, DobavljacToDTO toDTO, DTOtoDobavljac toDobavljac, UgovorToDTO ugovorToDTO) {
		this.dobavljacService = dobavljacService;
		this.ugovorService = ugovorService;
		this.dobavljacToDTO = toDTO;
		this.DTOtoDobavljac = toDobavljac;
		this.ugovorToDTO = ugovorToDTO;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<DobavljacDTO>> getAll(
			@RequestParam(required = false, value="naziv") String naziv, 
			@RequestParam(required = false, value="maticniBroj")String maticniBroj,
			@RequestParam(value="pageNum") int pageNum) {
		
		Page<Dobavljac> retVal;
		
		if((naziv != null && !naziv.trim().isEmpty() && naziv != "") || (maticniBroj != null && !maticniBroj.trim().isEmpty() && maticniBroj != ""))
			retVal = dobavljacService.findAll(naziv, maticniBroj, pageNum);
		else
			 retVal = dobavljacService.findAll(pageNum);

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(retVal.getTotalPages()));
		headers.add("totalDobavljaci", Long.toString(retVal.getTotalElements()));
		
		return new ResponseEntity<List<DobavljacDTO>>(dobavljacToDTO.convert(retVal.getContent()), headers, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{dobavljacId}/ugovori")
	ResponseEntity<List<UgovorDTO>> getUgovoriByDobavljacId(@PathVariable Long dobavljacId, @RequestParam(value="pageNum") int pageNum) {
		Page<Ugovor> retVal = ugovorService.findByDobavljacId(dobavljacId, pageNum);
		
		if (pageNum > retVal.getTotalPages() - 1)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(retVal.getTotalPages()));
		headers.add("totalUgovori", Long.toString(retVal.getTotalElements()));
		
		return new ResponseEntity<List<UgovorDTO>>(ugovorToDTO.convert(retVal.getContent()), headers, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	ResponseEntity<DobavljacDTO> getOne(@PathVariable Long id) {
		Dobavljac retVal = dobavljacService.findOne(id);
		if (retVal == null)
			return new ResponseEntity<DobavljacDTO>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<DobavljacDTO>(dobavljacToDTO.convert(retVal), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	ResponseEntity<DobavljacDTO> put(@RequestBody DobavljacDTO dto, @PathVariable Long id) {
		if (!id.equals(dto.getId())) {
			return new ResponseEntity<DobavljacDTO>(HttpStatus.BAD_REQUEST);
		}
		Dobavljac persisted = DTOtoDobavljac.convert(dto);
		dobavljacService.save(persisted);
		return new ResponseEntity<DobavljacDTO>(dobavljacToDTO.convert(persisted), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<DobavljacDTO> post(@RequestBody DobavljacDTO dto) {
		Dobavljac persisting = DTOtoDobavljac.convert(dto);
		dobavljacService.save(persisting);
		return new ResponseEntity<DobavljacDTO>(dobavljacToDTO.convert(persisting), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	ResponseEntity<DobavljacDTO> delete(@PathVariable Long id) {
		dobavljacService.delete(id);
		return new ResponseEntity<DobavljacDTO>(HttpStatus.NO_CONTENT);
	}

}

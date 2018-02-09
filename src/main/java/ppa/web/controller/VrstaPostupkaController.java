package ppa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ppa.model.VrstaPostupka;
import ppa.service.VrstaPostupkaService;
import ppa.support.VrstaPostupkaToDTO;
import ppa.web.dto.VrstaPostupkaDTO;

@RestController
@RequestMapping(value = "/api/vrstePostupka")
public class VrstaPostupkaController {

	VrstaPostupkaService vrstaPostupkaService;
	VrstaPostupkaToDTO toDTO;

	@Autowired
	VrstaPostupkaController(VrstaPostupkaService vrstaPostupkaService, VrstaPostupkaToDTO toDTO) {
		this.vrstaPostupkaService = vrstaPostupkaService;
		this.toDTO = toDTO;
	}

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<VrstaPostupkaDTO>> getAll() {
		List<VrstaPostupka> retVal = vrstaPostupkaService.findAll();
		return new ResponseEntity<List<VrstaPostupkaDTO>>(toDTO.convert(retVal), HttpStatus.OK);
	}

}

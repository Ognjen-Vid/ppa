package ppa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ppa.model.VrstaPredmeta;
import ppa.service.VrstaPredmetaService;
import ppa.support.VrstaPredmetaToDTO;
import ppa.web.dto.VrstaPredmetaDTO;

@RestController
@RequestMapping(value = "/api/vrstePredmeta")
public class VrstaPredmetaController {

	VrstaPredmetaService vrstaPredmetaService;
	VrstaPredmetaToDTO toDTO;

	@Autowired
	VrstaPredmetaController(VrstaPredmetaService vrstaPredmetaService, VrstaPredmetaToDTO toDTO) {
		this.vrstaPredmetaService = vrstaPredmetaService;
		this.toDTO = toDTO;
	}

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<VrstaPredmetaDTO>> getAll() {
		List<VrstaPredmeta> retVal = vrstaPredmetaService.findAll();
		return new ResponseEntity<>(toDTO.convert(retVal), HttpStatus.OK);
	}

}

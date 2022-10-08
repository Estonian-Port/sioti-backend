package com.estonianport.siotibackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estonianport.siotibackend.model.Mcu;
import com.estonianport.siotibackend.service.McuService;

@RestController
@RequestMapping(value = "/rest")
public class McuController {
	
	@Autowired
	private McuService personaService;
	
	@GetMapping(value = "/findPersona/{id}")
	public Mcu find(@PathVariable Long id){
		return personaService.get(id);
	}

	@GetMapping(value = "/allPersonas")
	public List<Mcu> getAll(){
		return personaService.getAll();
	}

	@PostMapping(value = "/savePersona")
	public ResponseEntity<Mcu> save(@RequestBody Mcu persona) {
		Mcu personaReturn = personaService.save(persona);
		return new ResponseEntity<Mcu>(personaReturn, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deletePersona/{id}")
	public ResponseEntity<Mcu> delete(@PathVariable Long id){
		Mcu persona = personaService.get(id);
		if(persona != null) {
			personaService.delete(id);
		}else {
			return new ResponseEntity<Mcu>(persona, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Mcu>(persona, HttpStatus.OK);
	}
}

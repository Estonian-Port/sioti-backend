package com.estonianport.siotibackend.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.estonianport.siotibackend.model.OnOff;
import com.estonianport.siotibackend.service.McuService;
import com.estonianport.siotibackend.service.OnOffService;

@RestController
@CrossOrigin("*")
public class OnOffController {
	
	@Autowired
	private OnOffService onOffService;
	
	@Autowired
	private McuService mcuService;
	
	@GetMapping(value = "/findOnOff/{id}")
	public OnOff find(@PathVariable Long id){
		return onOffService.get(id);
	}

	@GetMapping(value = "/allOnOff")
	public List<OnOff> getAll(){
		return onOffService.getAll();
	}

	@PostMapping(value = "/saveOnOff")
	public ResponseEntity<OnOff> save(@RequestBody OnOff onOff) {
		// Obtiene el mcu por nombre y se lo inserta a la accion onOff
		onOff.setMcu(mcuService.getMcuByNombre(onOff.getMcu().getNombre()));
		
		// Le coloca la hora actual de la accion OnOff
		onOff.setLocalDateTime(LocalDateTime.now());
		
		return new ResponseEntity<OnOff>(onOffService.save(onOff), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deleteOnOff/{id}")
	public ResponseEntity<OnOff> delete(@PathVariable Long id){
		OnOff onOff = onOffService.get(id);
		if(onOff != null) {
			onOffService.delete(id);
		}else {
			return new ResponseEntity<OnOff>(onOff, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<OnOff>(onOff, HttpStatus.OK);
	}
}

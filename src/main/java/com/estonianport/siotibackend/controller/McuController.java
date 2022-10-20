package com.estonianport.siotibackend.controller;

import java.time.Duration;
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

import com.estonianport.siotibackend.commons.dateUtil.DateUtil;
import com.estonianport.siotibackend.model.Mcu;
import com.estonianport.siotibackend.model.OnOff;
import com.estonianport.siotibackend.service.McuService;
import com.estonianport.siotibackend.service.OnOffService;

@RestController
@CrossOrigin("*")
public class McuController {
	
	@Autowired
	private McuService mcuService;
	
	@Autowired
	private OnOffService onOffService;
	
	@GetMapping(value = "/findMcu/{id}")
	public Mcu find(@PathVariable Long id){
		return mcuService.get(id);
	}

	@GetMapping(value = "/getAllMcu")
	public List<Mcu> getAll(){
		return mcuService.getAll();
	}

	@PostMapping(value = "/saveMcu")
	public ResponseEntity<Mcu> save(@RequestBody Mcu mcu) {
		return new ResponseEntity<Mcu>(mcuService.save(mcu), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deleteMcu/{id}")
	public ResponseEntity<Mcu> delete(@PathVariable Long id){
		Mcu mcu = mcuService.get(id);
		if(mcu != null) {
			mcuService.delete(id);
		}else {
			return new ResponseEntity<Mcu>(mcu, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Mcu>(mcu, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getTiempoEncendido/{id}")
	public LocalDateTime getTiempoEncendido(@PathVariable Long id){
		List<OnOff> onOffList = onOffService.getAllOnOffByMcu(mcuService.get(id));
		OnOff last = onOffList.get(onOffList.size() - 1);

		Duration diference = Duration.between(last.getLocalDateTime(), LocalDateTime.now());
		if(diference.toMinutes() < 1) {
			return DateUtil.createFechaConHora("01-01-2000", "00:00");
		}
		if(diference.toMinutes() <= 9) {
			return DateUtil.createFechaConHora("01-01-2000", "00:0" + Long.toString(diference.toMinutes()));
		}
		return DateUtil.createFechaConHora("01-01-2000", "00:" + Long.toString(diference.toMinutes()));
	}
}

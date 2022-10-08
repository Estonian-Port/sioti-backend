package com.estonianport.siotibackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.estonianport.siotibackend.commons.genericService.GenericServiceImpl;
import com.estonianport.siotibackend.dao.McuDao;
import com.estonianport.siotibackend.model.Mcu;

@Service
public class McuService extends GenericServiceImpl<Mcu, Long> {
	
	@Autowired
	private McuDao personaDao;

	@Override
	public CrudRepository<Mcu, Long> getDao() {
		return personaDao;
	}

}

package com.estonianport.siotibackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.estonianport.siotibackend.commons.genericService.GenericServiceImpl;
import com.estonianport.siotibackend.dao.OnOffDao;
import com.estonianport.siotibackend.model.OnOff;

@Service
public class OnOffService extends GenericServiceImpl<OnOff, Long> {
	
	@Autowired
	private OnOffDao onOffDao;

	@Override
	public CrudRepository<OnOff, Long> getDao() {
		return onOffDao;
	}

}

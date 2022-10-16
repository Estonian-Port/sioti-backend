package com.estonianport.siotibackend.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.estonianport.siotibackend.model.Mcu;
import com.estonianport.siotibackend.model.OnOff;

public interface OnOffDao extends CrudRepository<OnOff, Long> {

	List<OnOff> getAllOnOffByMcu(Mcu muc);

	OnOff findTopByOrderByIdDesc();

}

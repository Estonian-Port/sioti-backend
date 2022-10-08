package com.estonianport.siotibackend.dao;

import org.springframework.data.repository.CrudRepository;

import com.estonianport.siotibackend.model.OnOff;

public interface OnOffDao extends CrudRepository<OnOff, Long> {

}

package com.kevinvolanski.hotelaria.repository;

import org.springframework.data.repository.CrudRepository;

import com.kevinvolanski.hotelaria.models.Hospede;

public interface HospedeRepository extends CrudRepository<Hospede, String>{

	Hospede findById(int id);
	
	
}

package com.kevinvolanski.hotelaria.repository;

import org.springframework.data.repository.CrudRepository;

import com.kevinvolanski.hotelaria.models.Checkin;
import com.kevinvolanski.hotelaria.models.Hospede;

public interface CheckinRepository extends CrudRepository<Checkin, String>{

	Iterable<Checkin>findByHospede(Hospede hospede);
	
}

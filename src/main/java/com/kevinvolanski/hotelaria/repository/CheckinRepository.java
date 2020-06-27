package com.kevinvolanski.hotelaria.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kevinvolanski.hotelaria.models.Checkin;
import com.kevinvolanski.hotelaria.models.Hospede;

@Transactional
@Repository
public interface CheckinRepository extends CrudRepository<Checkin, String>{

	Iterable<Checkin>findByHospede(Hospede hospede);
	
	Checkin findById(int i);
	
//	@Query("SELECT c from Checkin c where c.dataEntrada not null and c.dataSaida = null")
//	List<Checkin> findCheckinByDataEntrada();
//	
//	@Query("SELECT c from Checkin c where c.dataEntrada not null and c.dataSaida not null")
//	List<Checkin> findCheckinByDataSaida();
	
	
}

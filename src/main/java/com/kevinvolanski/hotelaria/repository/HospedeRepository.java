package com.kevinvolanski.hotelaria.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kevinvolanski.hotelaria.models.Hospede;

@Repository
@Transactional
public interface HospedeRepository extends CrudRepository<Hospede, String>{

	Hospede findById(int id);

	@Query("SELECT h from Hospede h where h.nome like %?1%")
	List<Hospede> findHospedeByName(String nome);
	
	@Query("SELECT h from Hospede h where h.telefone like %?1%")
	List<Hospede> findHospedeByTelefone(String telefone);
	
	@Query("SELECT h from Hospede h where h.documento like %?1%")
	List<Hospede> findHospedeByDocumento(String documento);
}

package com.kevinvolanski.hotelaria.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
public class Hospede implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotEmpty
	private String nome;
	
	@NotEmpty
	private String documento;
	
	private String telefone;
	
	@OneToMany(mappedBy="hospede", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Checkin> checkin;
}

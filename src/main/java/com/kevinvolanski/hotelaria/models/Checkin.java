package com.kevinvolanski.hotelaria.models;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class Checkin implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar dataEntrada;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar dataSaida;
	private Boolean adicionalVeiculo = false;
	
	@ManyToOne
	private Hospede hospede;
	

}

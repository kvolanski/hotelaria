package com.kevinvolanski.hotelaria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kevinvolanski.hotelaria.models.Checkin;
import com.kevinvolanski.hotelaria.models.Hospede;
import com.kevinvolanski.hotelaria.repository.CheckinRepository;
import com.kevinvolanski.hotelaria.repository.HospedeRepository;


@Controller
public class HospedeController {
	
	
	@Autowired
	private HospedeRepository hr;
	
	@Autowired
	private CheckinRepository cr;
	
	@RequestMapping(value="/cadastrarHospede",method = RequestMethod.GET)
	public String form() {
		return "hospede/formHospede";
	}
	
	@RequestMapping(value="/cadastrarHospede",method = RequestMethod.POST)
	public String form(Hospede hospede) {
		hr.save(hospede);		
		return "redirect:/cadastrarHospede";
	}
	
	@RequestMapping("/hospedes")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("/index");
		Iterable<Hospede> hospedes = hr.findAll();
		mv.addObject("hospedes", hospedes);
		return mv;
	}

	@RequestMapping("/{id}")
	public ModelAndView detalhesHospede(@PathVariable("id") int id) {
		Hospede hospede = hr.findById(id);
		ModelAndView mv = new ModelAndView("hospede/detalhesHospede");
		mv.addObject(hospede);
		Iterable<Checkin> checkins = cr.findByHospede(hospede);
		mv.addObject("checkins", checkins);		
		return mv;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.POST)
	public String detalhesHospedePost(@PathVariable("id") int id, Checkin checkin) {
		Hospede hospede = hr.findById(id);
		checkin.setHospede(hospede);
		cr.save(checkin);
		return "redirect:/{id}";
	}
	
}

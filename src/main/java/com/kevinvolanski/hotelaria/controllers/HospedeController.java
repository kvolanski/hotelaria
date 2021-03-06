package com.kevinvolanski.hotelaria.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String form(@Valid Hospede hospede, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{id}";
		}
		hr.save(hospede);	
		attributes.addFlashAttribute("mensagem", "Hospede adicionado com sucesso!");
		return "redirect:/cadastrarHospede";
	}
	
	@RequestMapping("/hospedes")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("/index");
		Iterable<Hospede> hospedes = hr.findAll();
		mv.addObject("hospedes", hospedes);
		
		Iterable<Checkin> checkins = cr.findAll();
		mv.addObject("checkins", checkins);	
		
		return mv;
	}
	
	//BUSCAS
	@PostMapping("**/pesquisaNome")
	public ModelAndView listByName(@RequestParam("nomePesquisa") String nome) {
		ModelAndView mv = new ModelAndView("/index");
		Iterable<Hospede> hospedes = hr.findHospedeByName(nome);
		mv.addObject("hospedes", hospedes);
		return mv;
	}
	
	@PostMapping("**/pesquisaTelefone")
	public ModelAndView listByTelefone(@RequestParam("telefonePesquisa") String telefone) {
		ModelAndView mv = new ModelAndView("/index");
		Iterable<Hospede> hospedes = hr.findHospedeByTelefone(telefone);
		mv.addObject("hospedes", hospedes);
		return mv;
	}
	
	@PostMapping("**/pesquisaDocumento")
	public ModelAndView listByDocumento(@RequestParam("documentoPesquisa") String documento) {
		ModelAndView mv = new ModelAndView("/index");
		Iterable<Hospede> hospedes = hr.findHospedeByDocumento(documento);
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
		
	@RequestMapping("/deletarHospede")
	public String deleteHospede(int id) {
		Hospede hospede = hr.findById(id);
		hr.delete(hospede);
		return "redirect:/hospedes";
	}
	
	//EDITAR
	
	@RequestMapping("/editHospede")
	public ModelAndView editHospede(int id) {
		Hospede hospede = hr.findById(id);
		ModelAndView mv = new ModelAndView("hospede/editHospede");
		mv.addObject(hospede);
		return mv;
	}
	
	@RequestMapping(value="/editHospede",method = RequestMethod.POST)
	public String formEdit(@Valid Hospede hospede, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/editHospede{id}";
		}
		
		Hospede hospedeAntigo = hr.findById(hospede.getId());
		hospedeAntigo.setNome(hospede.getNome());
		hospedeAntigo.setDocumento(hospede.getDocumento());
		hospedeAntigo.setTelefone(hospede.getTelefone());		
		hr.save(hospedeAntigo);
		
		attributes.addFlashAttribute("mensagem", "Hospede editado com sucesso!");
		return "redirect:/hospedes";
	}
	
	//FIM EDITAR
	
	@RequestMapping("/deletarCheckin")
	public String deleteCheckin(int id) {
		Checkin checkin = cr.findById(id);
		cr.delete(checkin);
		Hospede hospede = checkin.getHospede();
		int idHospede = hospede.getId();
		String idHospedeString = "" + idHospede;
		return "redirect:/" + idHospedeString;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.POST)
	public String detalhesHospedePost(@PathVariable("id") int id,Checkin checkin, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{id}";
		}
		Hospede hospede = hr.findById(id);
		checkin.setHospede(hospede);
		cr.save(checkin);
		attributes.addFlashAttribute("mensagem", "Check-in adicionado com sucesso!");
		return "redirect:/{id}";
	}
	
}

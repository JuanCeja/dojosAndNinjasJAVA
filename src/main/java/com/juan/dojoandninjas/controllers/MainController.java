package com.juan.dojoandninjas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juan.dojoandninjas.models.Dojo;
import com.juan.dojoandninjas.models.Ninja;
import com.juan.dojoandninjas.services.DojoService;
import com.juan.dojoandninjas.services.NinjaService;

// ================ DOJO CONTROLLERS START========================

// INDEX AND FORM

@Controller
public class MainController {

	@Autowired
	private DojoService dojoService;
	@Autowired
	NinjaService ninjaService;

	@RequestMapping("/")
	public String index(@ModelAttribute("newDojo") Dojo dojo) {
		return "index.jsp";
	}

//POST METHOD FOR CREATING DOJO
	@PostMapping("/create/dojo")
	public String create(@Valid @ModelAttribute("newDojo") Dojo dojo, BindingResult result) {
		if (result.hasErrors()) {
			return "index.jsp";
		} else {
			dojoService.createDojo(dojo);
			return "redirect:/";
		}
	}

// ================ DOJO CONTROLLERS END========================
// ================ NINJA CONTROLLERS START========================

// RENDER CREATE NINJA PAGE
	@RequestMapping("/create/ninja")
	public String ninjaForm(@ModelAttribute("newNinja") Ninja ninja, Model model) {
		List<Dojo> allDojos = dojoService.allDojos();
		model.addAttribute("dojos", allDojos);
		return "createNinja.jsp";
	}

//	POST METHOD FOR CREATING NINJA
	@PostMapping("/create/ninja")
	public String createNinja(@Valid @ModelAttribute("newNinja") Ninja ninja, BindingResult result) {
		if (result.hasErrors()) {
			return "createNinja.jsp";
		} else {
			ninjaService.createNinja(ninja);
			return "redirect:/create/ninja";
		}
	}
	
//	RENDER DOJO AND ITS NINJAS
	@RequestMapping("/dojo/{id}")
	public String showDojoAndNinjas (@PathVariable("id") Long dojoId, Model model) {
		Dojo dojo = dojoService.findDojo(dojoId);
		model.addAttribute("dojo", dojo);
		return "show.jsp";
	}

// ================ NINJA CONTROLLERS END========================
}






package com.juan.dojoandninjas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juan.dojoandninjas.models.Dojo;
import com.juan.dojoandninjas.repositories.DojoRepo;

@Service
public class DojoService {
	@Autowired
	private DojoRepo dojoRepo;
	
//	READ ALL
	public List<Dojo> allDojos(){
		return dojoRepo.findAll();
	}
	
//	CREATE
	public Dojo createDojo(Dojo d) {
		return dojoRepo.save(d);
	}
	
//	READ ONE
	public Dojo findDojo(Long id) {
		Optional<Dojo> optionalDojo = dojoRepo.findById(id);
		if(optionalDojo.isPresent()) {
			return optionalDojo.get();
		} else {
			return null;
		}
	}
	
//	UPDATE
	public Dojo updateDojo(Dojo d) {
		return dojoRepo.save(d);
	}
	
//	DELETE
	public void deleteDojo (Long id) {
		dojoRepo.deleteById(id);
	}
}

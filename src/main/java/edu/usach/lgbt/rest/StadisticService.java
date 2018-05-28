package edu.usach.lgbt.rest;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;

import edu.usach.lgbt.entities.Stadistic;


import edu.usach.lgbt.repository.StadisticRepository;

@CrossOrigin
@RestController  
@RequestMapping("/stadistics")
public class StadisticService {
	
	@Autowired
	private StadisticRepository stadisticRepository;
	

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
	public Iterable<Stadistic> getAllStadistic() {
		System.out.println("PASEPORACA");
		return stadisticRepository.findAll();
	}
	
	@RequestMapping(value = "/gay",method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Stadistic> getGayStadistics() {
		ArrayList<Stadistic> gayStadistics = new ArrayList<Stadistic>();
		
		Iterable<Stadistic> allStadistic =stadisticRepository.findAll();

		for(Stadistic gayStadistic: allStadistic) {
			if(gayStadistic.getName_stadistic().equals("gay")) {
				gayStadistics.add(gayStadistic);
				System.out.println("agregue!!!");
			}
		}
		
		return gayStadistics;
	}

	@RequestMapping(value = "/lesbiana",method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Stadistic> getLesbianaStadistics() {
		ArrayList<Stadistic> lesbianaStadistics = new ArrayList<Stadistic>();
		
		Iterable<Stadistic> allStadistic =stadisticRepository.findAll();

		for(Stadistic lesbianaStadistic: allStadistic) {
			if(lesbianaStadistic.getName_stadistic().equals("lesbiana")) {
				lesbianaStadistics.add(lesbianaStadistic);
				System.out.println("agregue!!!");
			}
		}
		
		return lesbianaStadistics;
	}
	
	@RequestMapping(value = "/transgenero",method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Stadistic> getTransgeneroStadistics() {
		ArrayList<Stadistic> transgeneroStadistics = new ArrayList<Stadistic>();
		
		Iterable<Stadistic> allStadistic =stadisticRepository.findAll();

		for(Stadistic transgeneroStadistic: allStadistic) {
			if(transgeneroStadistic.getName_stadistic().equals("transgenero")) {
				transgeneroStadistics.add(transgeneroStadistic);
				System.out.println("agregue!!!");
			}
		}
		
		return transgeneroStadistics;
	}

	@RequestMapping(value = "/lgbt",method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Stadistic> getLgbtStadistics() {
		ArrayList<Stadistic> lgbtStadistics = new ArrayList<Stadistic>();
		
		Iterable<Stadistic> allStadistic =stadisticRepository.findAll();

		for(Stadistic lgbtStadistic: allStadistic) {
			if(lgbtStadistic.getName_stadistic().equals("lgbt")) {
				lgbtStadistics.add(lgbtStadistic);
				System.out.println("agregue!!!");
			}
		}
		
		return lgbtStadistics;
	}



	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public  Optional<Stadistic> findOne(@PathVariable("id") Integer id) {
		return stadisticRepository.findById(id);
	}
	

	/*
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Stadistic> create(@RequestBody Stadistic resource) {
		if (resource.getName_stadistic().isEmpty() || resource.getApproval_stadistic() < 0 || resource.getApproval_stadistic() >= 100){
			return new ResponseEntity<Stadistic>(HttpStatus.BAD_REQUEST);
		}else{
			
			return new ResponseEntity<Stadistic>(stadisticRepository.save(resource), HttpStatus.CREATED);
		}
	}
	*/
	

	/*
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("id") Integer id) { 
		stadisticRepository.delete(id);
	}*/
	
}
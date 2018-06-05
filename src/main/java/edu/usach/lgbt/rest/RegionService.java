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
import edu.usach.lgbt.entities.Region;
import edu.usach.lgbt.entities.Stadistic;
import edu.usach.lgbt.repository.RegionRepository;
import edu.usach.lgbt.repository.StadisticRepository;

@CrossOrigin
@RestController  
@RequestMapping("/regions")
public class RegionService {
	
	@Autowired
	private RegionRepository regionRepository;
	

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
	public Iterable<Stadistic> getAllRegion() {
		
		ArrayList<Stadistic> regionStadistics = new ArrayList<Stadistic>();
		
		Iterable<Region> allRegions = regionRepository.findAll();
		
		
		for(Region region: allRegions) {
			ArrayList<Stadistic> stadistics = (ArrayList<Stadistic>) region.getStadistics();
				Stadistic lastStadistic = stadistics.get(stadistics.size()-1);
				regionStadistics.add(lastStadistic);
		}
		
		return regionStadistics;
	}
	
	
	
}
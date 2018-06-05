package edu.usach.lgbt.rest;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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

import edu.usach.lgbt.entities.Tuser;


import edu.usach.lgbt.repository.TuserRepository;

@CrossOrigin
@RestController  
@RequestMapping("/twitterUser")
public class TuserService {
	
	@Autowired
	private TuserRepository tuserRepository;
	

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
	public Iterable<Tuser> getAllStadistic() {
		System.out.println("PASEPORACA");
		Iterable<Tuser> allTuser = tuserRepository.findAll();
		ArrayList listUser = (ArrayList) allTuser;
		
		
		Collections.sort(listUser, new Comparator<Tuser>() {
		    @Override
		    public int compare(Tuser z1, Tuser z2) {
		        if (z1.getRelevanceTuser() < z2.getRelevanceTuser())
		            return 1;
		        if (z1.getRelevanceTuser() > z2.getRelevanceTuser())
		            return -1;
		        return 0;
		    }
		});
		
		allTuser = (Iterable<Tuser>)listUser;
		System.out.println("SALIENDOMA");
		return allTuser;
		
		
	}


    
    Comparator<Integer> cmp = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 < o2) {
                return 1;
            } else {
                return -1;
            }
        }
    };

	

}
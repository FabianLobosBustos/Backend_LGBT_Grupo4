package edu.usach.lgbt.rest;



import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

import org.apache.lucene.document.Document;
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

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import antlr.collections.List;

import edu.usach.lgbt.entities.Stadistic;


import edu.usach.lgbt.repository.StadisticRepository;
import edu.usach.lgbt.tweet.database.MongoConnection;
import edu.usach.lgbt.tweet.objTweet.Tweet;

@CrossOrigin
@RestController  
@RequestMapping("/stadistics")
public class StadisticService {
	
	@Autowired
	private StadisticRepository stadisticRepository;
	
	private MongoConnection connection = new MongoConnection("127.0.0.1", "27017", "twitter", "statusJSONImpl");

	private ArrayList <String> tweetsImportantes;
	
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
			if(gayStadistic.getNameStadistic().equals("gay")) {
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
			if(lesbianaStadistic.getNameStadistic().equals("lesbiana")) {
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
			if(transgeneroStadistic.getNameStadistic().equals("transgenero")) {
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
			if(lgbtStadistic.getNameStadistic().equals("lgbt")) {
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
	
	
	
	@RequestMapping(value = "/importantTweets",method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<String> getImportantTweetsStadistics() {

		
		int noOfDays = -7; //i.e two weeks
		Calendar calendar = Calendar.getInstance();
		 Date dateOfOrder = new Date();
		calendar.setTime(dateOfOrder);            
		calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
		Date date = calendar.getTime();
		
		tweetsImportantes = new ArrayList<String>();
		MongoCollection<org.bson.Document> collection = this.connection.getCollection();
		MongoCursor<org.bson.Document> tweetsDocs = collection.find().iterator();
		while(tweetsDocs.hasNext()){
			org.bson.Document tweetDoc = tweetsDocs.next();
			Tweet tweet = new Tweet(tweetDoc);
			
		
			
			if(tweet.getCreatedAt().after(date)) {
				if(6*tweet.getTwitterUser().getFriendsCount()+8*tweet.getTwitterUser().getFollowersCount()>100000) {
					//if(tweet.getRetweetCount()>500) {
					System.out.println(tweet.getId().toString());
					tweetsImportantes.add(tweet.getId().toString());
					//}
				}				
			}

			
		}
		return tweetsImportantes;
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
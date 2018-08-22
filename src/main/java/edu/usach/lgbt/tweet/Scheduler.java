package edu.usach.lgbt.tweet;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import edu.usach.lgbt.tweet.objTweet.Tweet;
import edu.usach.lgbt.entities.Region;
import edu.usach.lgbt.entities.Stadistic;
import edu.usach.lgbt.entities.Tuser;
import edu.usach.lgbt.tweet.database.MongoConnection;
import edu.usach.lgbt.tweet.sentimentAnalyzer.SentimentAnalyzer;
import edu.usach.lgbt.tweet.sentimentAnalyzer.TweetIndexer;

import edu.usach.lgbt.graph.TwitterUserNode;
import edu.usach.lgbt.graphrepository.TwitterUserNodeRepository;
import edu.usach.lgbt.repository.StadisticRepository;
import edu.usach.lgbt.repository.TuserRepository;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class Scheduler {

	@Autowired
	private StadisticRepository stadisticRepository;
	
	@Autowired
	private TuserRepository tuserRepository;
	
	@Autowired
	private TwitterUserNodeRepository twitterUserNodeRepository;
	
	private MongoConnection connection = new MongoConnection("127.0.0.1", "27017", "twitter", "statusJSONImpl");

	



	/**
	 * Método que elimina toda la información de la tabla Valoraciones
	 */
	/*public void delete(){
        String statement = "DELETE FROM valoracion";
        template.update(statement);
    }*/

	/**
	 * Método encargado de realizar un llamado al creador de índices de Indexer
	 * para obtener los tweets , para posteriormente
	 * almacenar su valoración en la base de datos MySQL.
	 * @return void
	 */
	@Scheduled(cron = "*/100000 * * * * *")
	public void dailyTask(){

		//delete();

		List<Document> tweets;
		List<Tweet> tweetsFromMongo = new ArrayList<>();
		MongoCollection<org.bson.Document> collection = this.connection.getCollection();
		MongoCursor<org.bson.Document> tweetsDocs = collection.find().iterator();
		while(tweetsDocs.hasNext()){
		
			org.bson.Document tweetDoc = tweetsDocs.next();
			Tweet tweet = new Tweet(tweetDoc);
			tweetsFromMongo.add(tweet);
			
			
			
			Tuser tuser = new Tuser();
			tuser.setIdTuser(tweet.getTwitterUser().getId());
			tuser.setNameTuser(tweet.getTwitterUser().getName());
			tuser.setScreennameTuser(tweet.getTwitterUser().getScreenName());
			tuser.setRelevanceTuser(tweet.getTwitterUser().getFollowersCount() * 8 + tweet.getTwitterUser().getFriendsCount()* 2);
			tuser.setImageTuser(tweet.getTwitterUser().getProfileUrl());
			
			//if(!tuserRepository.existsById((int)tweet.getTwitterUser().getId())) {
				//tuserRepository.save(tuser);	
			//}
			
			
		}
		int[] sentiment;

		SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
		TweetIndexer indexer = new TweetIndexer(tweetsFromMongo);

		
		
		
		String[] temas = {"lesbiana","gay","transgenero","lgbt"};  
		
		
		String keyWords[][] = {
		{"lesbiana",
		"lela",
		"camiona",
		"maraca",
		"fleta",
		"tortillera",
		"marimacho",
		"lesbi",
		"lesbianas",
		"lelas",
		"lelitas",
		"lesbico",
		"tijeras",
		"maraka",
		"lesbiana",
		"marakas",
		"fletx",
		"tortilleras",
		"feminazi",
		"camionas",
		"Camiona"
		},
		{
		"gay",
		"fleto",
		"maraco",
		"Gays",
		"gays",
		"homosexual",
		"homo",
		"fletos",
		"maracos",
		"maricones",
		"marako",
		"Gay",
		"weco",
		"hueco",
		"fletito",
		"gay",
		"marakos",
		"comunidadGay",
		"wecos"
		},
		{"trans",
		"transgenero",
		"transexual",
		"tula",
		"transfobia",
		"Trans",
		"Transexual",
		"transGenero"
		},
		{
			"lesbiana",
			"lela",
			"camiona",
			"maraca",
			"gay",
			"fleto",
			"maraco",
			"Gays",
			"lelas",
			"camionas",
			"gays",
			"homosexual",
			"homo",
			"fletos",
			"maracos",
			"maricones",
			"tortillera",
			"trans",
			"transgenero",
			"transexual",
			"hombre",
			"tula",
			"hombre",
			"maracos",
			"maricones",
			"transfobia",
			"bisexual",
			"lgbt",
			"LGTB",
			"movilh",
			"MOVIL",
			"pansexual",
			"intersexual",
			"bi",
			"LGBTI","#gaychile","#bisexual","gay", "gays", "Gay", "Gays",
			"lesbiana", "lesbianas", "Lesbiana", "Lesbianas", "homosexual", "homosexuales", "Homosexual", "Homosexuales",
			"minoría sexual", "ley identidad de género", "identidad de género", "Identidad de género", "Identidad de Género",
			"transgenero", "Transgenero", "maraco", "marako", "fleto", "Fleto", "fletos", "Fletos",
			"Maraco", "Marako","trans","transexual","minoria","matrimonio","igualitario","sexista","identidad","lgbt","lgtb","comunidad", "LGTB", "maricon", "Marikon", "Maricon", "marikon", "maricones", "Marikones", "Maricones",
			"marikones", "lela", "lelas", "Lela", "Lelas", "camionas", "camiona", "Camionas", "Camiona", "#LGTB", "#SoyGay",
			"#SerGayNoEsUnDelito", "#SoyHomosexual", "@Movilh", "#LGTBFracasoDeSociedad", "#LeyDeIdentidadDeGéneroAhora","weco"
			,"maricon"
		
		}};
		

		
		
		try{
            indexer.createIndex();
            for(String tema: temas){
                tweets = new ArrayList<>();
                Stadistic stadistic = new Stadistic();
                int bandera = -1;
                if(tema=="lesbiana") {
                	bandera=0;
                }
                if(tema=="gay") {
                	bandera = 1;
                }
                if(tema =="transgenero") {
                	bandera = 2;
                }
                if(tema == "lgbt") {
                	bandera = 3;
                }
                for(String keyWord : keyWords[bandera]){
                    for(Document searchedTweet : indexer.searchTweets(keyWord)){
                    	
                        tweets.add(searchedTweet);
                    }
                }
        		int contadorPositivo = 0;
        		int contadorNegativo = 0;
        		int contadorContingencia = 0;

				for(Document tweet : tweets){
					sentiment = sentimentAnalyzer.calculateSentiment(tweet.get("tweet"));
					//si hay mas positivos
					if(sentiment[1] > sentiment[3] ){
						contadorPositivo = contadorPositivo+sentiment[1]+sentiment[2];
					}
						//si hay mas negativos
					if(sentiment[3] > sentiment[1] ) {
						contadorNegativo = contadorNegativo+sentiment[3]+sentiment[2];
					}

					contadorContingencia = contadorContingencia + sentiment[0];
				}

				stadistic.setPositiveStadistic(contadorPositivo);
				stadistic.setNegativeStadistic(contadorNegativo);
				stadistic.setContingencyStadistic(contadorContingencia);
				stadistic.setNameStadistic(tema);
				//stadisticRepository.save(stadistic);

			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	 /**
     * Método que se encarga de formar el grafo de influencia. Es un método programado que se realiza
     * diariamente, luego de crear los índices invertidos.
     */
	@Scheduled(cron = "*/100000 * * * * *")
    public void createGraph(){
    	System.out.println("PICO");
        
       
    	//twitterUserNodeRepository.deleteAll();

       

       
        
        
        List <Tuser> tusers = (List<Tuser>) tuserRepository.findAll();

        // Se almacenan los prestadores en el grafo
        
		List<Document> tweets;
		List<Tweet> tweetsFromMongo = new ArrayList<>();
		MongoCollection<org.bson.Document> collection = this.connection.getCollection();
		MongoCursor<org.bson.Document> tweetsDocs = collection.find().iterator();
		while(tweetsDocs.hasNext()){
			//System.out.println("----------------CACA-----------------");
			org.bson.Document tweetDoc = tweetsDocs.next();
			Tweet tweet = new Tweet(tweetDoc);
			tweetsFromMongo.add(tweet);	
			
		}
        for(Tuser tuser : tusers) {
        	 for(Tweet tweet : tweetsFromMongo) {
        		 try {
                     if(tweet.getTwitterUser().getName().equals(tuser.getNameTuser())) {
                    	 Tweet retweet= tweet.getRetweet();
                    	 if(this.twitterUserNodeRepository.findByName(tweet.getTwitterUser().getName()) != null){
                    		
                    	
                          	 
                    		 
                    		 TwitterUserNode twitterUserNode1 = this.twitterUserNodeRepository.findByName(tweet.getTwitterUser().getName());
                    		 
                    		 if(retweet != null){
                    			
                    			 TwitterUserNode retweetUser;
                    			if(this.twitterUserNodeRepository.findByName(retweet.getTwitterUser().getName()) != null) {
                    				retweetUser = this.twitterUserNodeRepository.findByName(retweet.getTwitterUser().getName());
                    			}
                    			else {
                    				retweetUser = new TwitterUserNode(retweet, twitterUserNode1.getIdTweet());
                    			}
                    	
                    		 
                               	//twitterUserNode1.addInfluenceRelationship(retweetUser);
                               	//this.twitterUserNodeRepository.save(retweetUser);
                            
                               	
                           	}
                    		 
                    		
                    			 //this.twitterUserNodeRepository.save(twitterUserNode1); 
                    		
                    			 
                    		
                    		 
                    		 
                    		 
                    	 }
                    	 else {
                    		 
                    		 // Se crea el usuario de Twitter como nodo
                    		 TwitterUserNode twitterUserNode = new TwitterUserNode(tweet, -1L);
                         	// Se agregan sus Retweets al grafo, si es que los tiene
                         	if(retweet != null){
                         		TwitterUserNode retweetUser;
                         			if(this.twitterUserNodeRepository.findByName(retweet.getTwitterUser().getName()) != null) {
                         				retweetUser = this.twitterUserNodeRepository.findByName(retweet.getTwitterUser().getName());
                         			}
                         			else {
                         				retweetUser = new TwitterUserNode(retweet, twitterUserNode.getIdTweet());
                         			}
                   	
                   		 
                              	//twitterUserNode.addInfluenceRelationship(retweetUser);
                              	//this.twitterUserNodeRepository.save(retweetUser);
                         		
                            	
                            
                        	}
                         	//this.twitterUserNodeRepository.save(twitterUserNode);
                    	 }
      
                             
                         }
        			 
        		 }catch(Exception e) {
        			 
        		 }

                 }

                   
        	 }
        }
       
            
          
        
    


}

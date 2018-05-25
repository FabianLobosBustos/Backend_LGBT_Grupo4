package edu.usach.lgbt.tweet;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import edu.usach.lgbt.tweet.objTweet.Tweet;
import edu.usach.lgbt.entities.Stadistic;
import edu.usach.lgbt.tweet.database.MongoConnection;
import edu.usach.lgbt.tweet.sentimentAnalyzer.SentimentAnalyzer;
import edu.usach.lgbt.tweet.sentimentAnalyzer.TweetIndexer;
import edu.usach.lgbt.repository.StadisticRepository;


import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Scheduler {

	@Autowired
	private StadisticRepository stadisticRepository;
	
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
	@Scheduled(cron = "*/10 * * * * *")
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
                    	System.out.println(searchedTweet.toString());
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
						contadorPositivo++;
					}
						//si hay mas negativos
					if(sentiment[3] > sentiment[1] ) {
						contadorNegativo++;
					}

					contadorContingencia = contadorContingencia + sentiment[0];
				}
				System.out.println(tema);
				System.out.println("contador positivo "+ contadorPositivo);
				System.out.println("contador negativo "+ contadorNegativo);
				System.out.println("contador contingencia "+ contadorContingencia);
				stadistic.setPositive_stadistic(contadorPositivo);
				stadistic.setNegative_stadistic(contadorNegativo);
				stadistic.setContingency_stadistic(contadorContingencia);
				stadistic.setName_stadistic(tema);
				stadisticRepository.save(stadistic);

			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}


}

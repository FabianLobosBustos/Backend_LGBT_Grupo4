package edu.usach.lgbt.tweet.database;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import edu.usach.lgbt.tweet.objTweet.Tweet;
import org.bson.Document;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

//Clase para establecer la conexión con MongoDB
public class MongoConnection {

    private String host;
    private String port;
    private String dbName;
    private MongoClient client;
    private MongoDatabase db;
    private String collName;
    private MongoCollection<Document> collection;

    public MongoConnection(String host, String port, String dbName, String collName){
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        this.collName = collName;
        createConnection();
    }

    public void createConnection(){
        this.client = new MongoClient(new ServerAddress(this.host, Integer.valueOf(this.port)));
        this.db = client.getDatabase(this.dbName);
    }

    public MongoCollection<Document> getCollection() {
        this.collection = db.getCollection(this.collName);
        return this.collection;
    }

    public List<Tweet> getTweetsFromMongo(MongoCollection<Document> docs){
        List<Tweet> tweetsFromMongo = new ArrayList<>();
        MongoCursor<Document> cursor = docs.find().iterator();
        while(cursor.hasNext()){
            tweetsFromMongo.add(new Tweet(cursor.next()));
        }
        return tweetsFromMongo;
    }

    public Tweet getTweetFromId(Long id){
        Document tweetDoc = this.collection.find(Filters.eq("id", id)).first();
        return new Tweet(tweetDoc);
    }
}

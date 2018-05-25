package edu.usach.lgbt.tweet.objTweet;
import org.bson.Document;
import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.UserMentionEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Clase que representa a un Tweet visto desde el punto de vista de la API de Twitter
public class Tweet {
    private Long id;
    private String text;
    private Date createdAt;
    private HashtagEntity[] hashtagEntities;
    private UserMentionEntity[] userMentionEntities;
    private int retweetCount;
    //private int userMentionsCount;
    //private TwitterUser twitterUser;
    private GeoLocation geoLocation;
    //private List<Tweet> retweets = null;


    //Constructor para crear Tweets desde MongoDB
    public Tweet(Document tweetFromDoc){
       

        this.id = tweetFromDoc.getLong("_id");
        this.text = tweetFromDoc.getString("text");
        this.createdAt = tweetFromDoc.getDate("createdAt");
        //this.retweets = JSONToTweet((List<Document>)tweetFromDoc.get("retweets"));
        this.geoLocation = new GeoLocation(tweetFromDoc);
       // this.twitterUser = new TwitterUser(tweetFromDoc);
        //this.userMentionsCount = tweetFromDoc.getInteger("userMentionsCount");
       // this.retweetCount = tweetFromDoc.getInteger("retweetCount");
    }

    public List<Tweet> JSONToTweet(List<Document> tweets){
        List<Tweet> listTweets = new ArrayList<>();
        if(tweets == null){
            return null;
        }
        for(Document tweet : tweets){
            Tweet tweet1 = new Tweet(tweet);
            listTweets.add(tweet1);
        }
        return listTweets;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public HashtagEntity[] getHashtagEntities() {
        return hashtagEntities;
    }

    public UserMentionEntity[] getUserMentionEntities() {
        return userMentionEntities;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

   /* public int getUserMentionsCount() {
        return userMentionsCount;
    }*/

    /*public TwitterUser getTwitterUser() {
        return twitterUser;
    }*/

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

   /* public List<Tweet> getRetweets() {
        return retweets;
    }*/
}

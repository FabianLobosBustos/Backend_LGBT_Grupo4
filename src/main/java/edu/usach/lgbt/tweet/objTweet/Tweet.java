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
    private TwitterUser twitterUser;
    private GeoLocation geoLocation;
    private Tweet retweet = null;


    //Constructor para crear Tweets desde MongoDB
    public Tweet(Document tweetFromDoc){
        this.id = tweetFromDoc.getLong("_id");
        this.text = tweetFromDoc.getString("text");
        this.createdAt = tweetFromDoc.getDate("createdAt");
        this.retweet = JSONtoTweet((Document) tweetFromDoc.get("retweetedStatus"));
        this.geoLocation = new GeoLocation(tweetFromDoc);
        this.twitterUser = new TwitterUser(tweetFromDoc);
        //this.userMentionsCount = tweetFromDoc.getInteger("userMentionsCount");
       // this.retweetCount = tweetFromDoc.getInteger("retweetCount");
    }
    
    public Tweet JSONtoTweet(Document retweetedStatus) {
    	if(retweetedStatus!= null) {
    		return new Tweet(retweetedStatus);
    	}else {
    		return null;
    	}
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

    public void setTwitterUser(TwitterUser twitterUser) {
		this.twitterUser = twitterUser;
	}

	public TwitterUser getTwitterUser() {
        return twitterUser;
    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }



	public Tweet getRetweet() {
		return retweet;
	}



	public void setRetweet(Tweet retweet) {
		this.retweet = retweet;
	}

    
}

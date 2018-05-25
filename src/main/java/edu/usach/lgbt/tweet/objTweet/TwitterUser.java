package edu.usach.lgbt.tweet.objTweet;
import org.bson.Document;

//Clase que representa a un Usuario de Twitter, visto desde el punto de vista de la API de Twitter
public class TwitterUser {
    private String screenName;
    private String name;
    private String location;
    private int followersCount;
    private int friendsCount;
    private boolean verified;

    

    //Constructor utilizado para crear el usuario del tweet desde MongoDB
    public TwitterUser(Document tweetFromDoc){
        this.screenName = tweetFromDoc.getString("user");
        this.name = tweetFromDoc.getString("name");
        this.location = tweetFromDoc.getString("country");
        //this.followersCount = tweetFromDoc.getInteger("followersCount");
        this.followersCount = 0;
        //this.friendsCount = tweetFromDoc.getInteger("friendsCount");
        this.friendsCount = 0;
        this.verified = tweetFromDoc.getBoolean("userVerified");
        
    }

    public String getScreenName() {
        return screenName;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public boolean isVerified() {
        return verified;
    }
}

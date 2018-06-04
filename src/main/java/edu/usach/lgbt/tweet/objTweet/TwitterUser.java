package edu.usach.lgbt.tweet.objTweet;
import org.bson.Document;


//Clase que representa a un Usuario de Twitter, visto desde el punto de vista de la API de Twitter
public class TwitterUser {
    private String screenName;
    private String name;
    private int followersCount;
    private int friendsCount;
    
    //Constructor utilizado para crear el usuario del tweet desde MongoDB
    public TwitterUser(Document tweetFromDoc){
    	org.bson.Document user = (Document) tweetFromDoc.get("user");
    	System.out.println("screen name: "+ user.getString("screenName") );
        this.screenName = user.getString("screenName");
        this.name = tweetFromDoc.getString("user.name");
        System.out.println("ESTAMOS POR ENTRAR A FOLLOWERS");
        System.out.println("followersss : " + tweetFromDoc.getInteger("user.followersCount"));
        //this.followersCount = tweetFromDoc.getInteger("user.followersCount");
        //this.friendsCount = tweetFromDoc.getInteger("user.friendsCount");
        System.out.println("SALIENDO DE USER...");
    }

    public String getScreenName() {
        return screenName;
    }

    public String getName() {
        return name;
    }



    public int getFollowersCount() {
        return followersCount;
    }

    public int getFriendsCount() {
        return friendsCount;
    }


}

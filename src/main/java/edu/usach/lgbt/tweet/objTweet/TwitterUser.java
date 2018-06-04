package edu.usach.lgbt.tweet.objTweet;
import org.bson.Document;


//Clase que representa a un Usuario de Twitter, visto desde el punto de vista de la API de Twitter
public class TwitterUser {
	private long id;
    private String screenName;
    private String name;
    private int followersCount;
    private int friendsCount;
    
    //Constructor utilizado para crear el usuario del tweet desde MongoDB
    public TwitterUser(Document tweetFromDoc){
    	org.bson.Document user = (Document) tweetFromDoc.get("user");
    	this.id = user.getLong("_id");
        this.screenName = user.getString("screenName");
        this.name = user.getString("name");
        this.followersCount = user.getInteger("followersCount");
        this.friendsCount = user.getInteger("friendsCount");
       
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


}

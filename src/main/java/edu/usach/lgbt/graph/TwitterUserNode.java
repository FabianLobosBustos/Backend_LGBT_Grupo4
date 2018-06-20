package edu.usach.lgbt.graph;


import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.usach.lgbt.tweet.objTweet.Tweet;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase que representa a un usuario de Twitter como un nodo en Neo4j.
 */
@NodeEntity
public class TwitterUserNode {

	@Id @GeneratedValue
    private Long id;
    private String username;
    private String name;
    private int retweetCount;
    private int followersCount;
    private String tweetText;
    private double influence;
    private Long idTweet;
    private Long idUser;
    private Long retweetTo;

    public TwitterUserNode(){} //Se requiere un constructor vacío

    public TwitterUserNode(Tweet tweet,Long retweetTo){
        this.username = tweet.getTwitterUser().getScreenName();
        this.name = tweet.getTwitterUser().getName();
        this.tweetText = tweet.getText();
        this.retweetCount = tweet.getRetweetCount();
        this.followersCount = tweet.getTwitterUser().getFollowersCount();
        this.influence = tweet.getTwitterUser().getFriendsCount() * 2 + tweet.getTwitterUser().getFollowersCount() * 8;;
        this.idTweet = tweet.getId();
        this.idUser = tweet.getTwitterUser().getId();
        this.retweetTo = retweetTo;
    }

    public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	/**
     * Relación entre usuarios, para saber quienes son los usuarios más influyentes.
     * La relación se representa por el atributo influenceRelationship, mientras
     * que el método que construye la relación es addInfluenceRelationship.
     */
	@JsonIgnore
    @Relationship(type = "influye en", direction = Relationship.UNDIRECTED)
    private Set<TwitterUserNode> influenceRelationship;

    public void addInfluenceRelationship(TwitterUserNode twitterUserNode){
        if(influenceRelationship == null) {
            influenceRelationship = new HashSet<>();
        }
        influenceRelationship.add(twitterUserNode);
    }

    //Getters and setters


    public Long getId() { return id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getRetweetCount() { return retweetCount; }

    public void setRetweetCount(int retweetCount) { this.retweetCount = retweetCount; }

    public int getFollowersCount() { return followersCount; }

    public void setFollowersCount(int followersCount) { this.followersCount = followersCount; }


    public Set<TwitterUserNode> getInfluenceRelationship() {
        return influenceRelationship;
    }

    public String getTweetText() {
        return tweetText;
    }

    public double getInfluence() {
        return influence;
    }

    public Long getIdTweet() {
        return idTweet;
    }

    public void setRetweetTo(Long retweetTo) {
        this.retweetTo = retweetTo;
    }

    public Long getRetweetTo() {
        return retweetTo;
    }

    public void setInfluenceRelationship(Set<TwitterUserNode> influenceRelationship) {
        this.influenceRelationship = influenceRelationship;
    }
}
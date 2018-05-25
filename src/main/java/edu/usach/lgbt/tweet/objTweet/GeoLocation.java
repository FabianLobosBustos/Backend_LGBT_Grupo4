package edu.usach.lgbt.tweet.objTweet;

import org.bson.Document;
import twitter4j.Place;

//Clase que representa la Geolocalización de un Tweet desde el punto de vista de la API de Twitter
public class GeoLocation {
    private String latitude = "none";
    private String longitude = "none";
    private String countryCode;
    private String country;

    //Constructor utilizado cuando se implemente el Twitter Streaming desde Spring
    public GeoLocation(twitter4j.GeoLocation geo, Place place){
        try{
            this.latitude = String.valueOf(geo.getLatitude());
            this.longitude = String.valueOf(geo.getLongitude());
        } catch (NullPointerException e){ }
        this.countryCode = place.getCountryCode();
        this.country = place.getCountry();
    }

    //Constructor utilizado para crear la geolocalización desde MongoDB
    public GeoLocation(Document tweetFromDoc){
        this.latitude = tweetFromDoc.getString("latitude");
        this.longitude = tweetFromDoc.getString("longitude");
        this.countryCode = tweetFromDoc.getString("tCode");
        this.country = tweetFromDoc.getString("tCountry");
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountry() {
        return country;
    }
}

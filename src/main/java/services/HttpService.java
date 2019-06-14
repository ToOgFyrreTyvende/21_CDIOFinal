package services;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import models.User;

public class HttpService {


    public User getUser(){
        User user = null;
        try {
            user = Unirest.get("http://www.mocky.io/v2/5d0379473000000f001f4d29").asObject(User.class).getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return user;

    }

}

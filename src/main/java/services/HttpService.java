package services;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import models.ProductBatch;
import models.User;
import utils.Consts;

public class HttpService {


    public User getUser(String id){
        User user = null;
        try {
            user = Unirest.get(Consts.URL + "/api/users/" + id).asObject(User.class).getBody();
        } catch (UnirestException e) {
            //e.printStackTrace();
        }
        return user;
    }

    public ProductBatch getProductBatch(String id){
        ProductBatch productBatch = null;
        try {
            productBatch = Unirest.get(Consts.URL + "/api/productBatches/" + id).asObject(ProductBatch.class).getBody();
        } catch (UnirestException e) {
            //e.printStackTrace();
        }
        return productBatch;
    }
}

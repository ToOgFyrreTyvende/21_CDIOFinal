package services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import models.Product;
import models.ProductBatch;
import models.Weighing;
import models.RawMatBatch;
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

    public Product getProduct(int id){
        Product product = null;
        try {
            product = Unirest.get(Consts.URL + "/api/products/" + id).asObject(Product.class).getBody();
        } catch (UnirestException e) {
            //e.printStackTrace();
        }
        return product;
    }

    public RawMatBatch getRawMatBatch(String id){
        RawMatBatch rawMatBatch = null;
        try {
            rawMatBatch = Unirest.get(Consts.URL + "/api/rawMatBatches/" + id).asObject(RawMatBatch.class).getBody();
        } catch (UnirestException e) {
            //e.printStackTrace();
        }
        return rawMatBatch;
    }

    public boolean saveWeighing(Weighing weighing){
        RawMatBatch rawMatBatch = null;
        try {

            HttpResponse postresp = Unirest.post(Consts.URL + "/api/weighing")
                    .header("Content-Type", "application/json")
                    .body(weighing)
                    .asJson();
            if (postresp.getStatus() == 204) return true;
            else return false;

        } catch (UnirestException e) {
            //e.printStackTrace();
        }
        return false;
    }
}

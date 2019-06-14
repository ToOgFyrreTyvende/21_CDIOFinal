import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import functionality.IWeightFunctionality;
import functionality.WeightFunctionality;
import models.User;
import services.HttpService;
import services.IMettlerScale;
import services.MettlerScale;
import ui.WeightUI;

public class main {
    public static void main(String[] args) throws IOException {

        Unirest.setObjectMapper(new ObjectMapper() {
            com.fasterxml.jackson.databind.ObjectMapper mapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public String writeValue(Object value) {
                try {
                    return mapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return null;
            }

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return mapper.readValue(value, valueType);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });


        HttpService  h = new HttpService();
        User us = h.getUser();
        System.out.println(us.getUserName());

        /*IMettlerScale scale = new MettlerScale("127.0.0.1", 8000);

        IWeightFunctionality wFunc = new WeightFunctionality(scale);
        WeightUI ui = new WeightUI(wFunc);

        ui.menu();*/

    }
}

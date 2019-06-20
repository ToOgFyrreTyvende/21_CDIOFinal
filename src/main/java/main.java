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
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws InterruptedException {

        Unirest.setObjectMapper(new ObjectMapper() {
            com.fasterxml.jackson.databind.ObjectMapper mapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public String writeValue(Object value) {
                try {
                    return mapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    //e.printStackTrace();
                }
                return null;
            }

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return mapper.readValue(value, valueType);
                } catch (IOException e) {
                    //e.printStackTrace();
                }
                return null;
            }
        });

        Scanner scanner = new Scanner(System.in);
        System.out.print("Host: ");
        String host = scanner.next();
        System.out.print("Port: ");
        int port = scanner.nextInt();

        IMettlerScale scale = new MettlerScale(host, port);

        IWeightFunctionality wFunc = new WeightFunctionality(scale);
        WeightUI ui = new WeightUI(wFunc);

        while (true) {
            try {
                ui.menu();
            } catch (Exception e) {
                System.out.println(e);
            }
            Thread.sleep(1000);
        }
    }
}

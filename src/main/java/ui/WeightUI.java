package ui;

import functionality.IWeightFunctionality;
import models.*;
import services.HttpService;
import utils.Helper;

import java.io.IOException;

public class WeightUI {

    private IWeightFunctionality wFunc;

    public WeightUI(IWeightFunctionality wFunc) {
        this.wFunc = wFunc;
    }

    public void menu() throws IOException, InterruptedException {

        System.out.println("In menu");

        HttpService http = new HttpService();
        User user;
        ProductBatch productBatch;
        RawMatBatch rawMatBatch;
        Product product;

        String displayText = "UserId";
        while (true) {
            String userID = wFunc.requestInput(String.format("%s OK",displayText));
            user = http.getUser(userID);
            if (user != null) {break;}
            else {displayText = "Invalid, OK";}
        }
        wFunc.getConfirmation(String.format("%s OK",user.getUserName()));

        displayText = "ProdBatchId";
        while (true) {
            String productBatchID = wFunc.requestInput(String.format("%s OK",displayText));
            productBatch = http.getProductBatch(productBatchID);
            if (productBatch != null) {break;}
            else {displayText = "Invalid, OK";}
        }
        product = http.getProduct(productBatch.getProdId());
        if (product == null) throw new IOException("product failed");
        wFunc.getConfirmation(String.format("%s OK",productBatch.getName()));

        displayText = "RawMatBId";
        while (true) {
            String rawMatBatchID = wFunc.requestInput(String.format("%s OK",displayText));
            rawMatBatch = http.getRawMatBatch(rawMatBatchID);
            if (rawMatBatch != null && Helper.rawMatInProduct(product,rawMatBatch)) {break;}
            else {displayText = "Invalid, OK";}
        }
        wFunc.getConfirmation(String.format("%s OK",rawMatBatch.getName()));
        double amount = Helper.getAmount(product,rawMatBatch);
        if (amount == 0.0) throw new IOException("amount failed");
        wFunc.getConfirmation(String.format("%s kg OK",amount));

        wFunc.getConfirmation("Clear weight OK");
        wFunc.taraWeight();

        wFunc.getConfirmation("Tara OK");
        Thread.sleep(3000);
        String tara = wFunc.getWeight(false);

        double taraWeight = Double.parseDouble(tara);
        wFunc.taraWeight();

        wFunc.getConfirmation("Netto OK");
        String netto = wFunc.getWeight(false);
        double nettoWeight = Double.parseDouble(netto);
        wFunc.taraWeight();

        wFunc.getConfirmation("Remove all OK");
        String brutto = wFunc.getWeight(true);
        double bruttoWeight = -(Double.parseDouble(brutto));
        wFunc.taraWeight();

        wFunc.getConfirmation("Complete OK");
        wFunc.taraWeight();

        Weighing weighing = new Weighing(rawMatBatch.getRmbId(), productBatch.getProdBatchId(), user.getUserId(), taraWeight, nettoWeight);
        boolean result = http.saveWeighing(weighing);

        if (result){
            System.out.println("Tara: " + taraWeight + " kg");
            System.out.println("Netto: " + nettoWeight + " kg");
            System.out.println("Brutto: " + bruttoWeight + " kg");
        }
        else {
            throw new IOException("Couldn't save weighing, you wasted your time :(");
        }
    }
}

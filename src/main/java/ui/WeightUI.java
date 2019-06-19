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

        HttpService http = new HttpService();
        User user;
        ProductBatch productBatch;
        RawMatBatch rawMatBatch;
        Product product;

        wFunc.getConfirmation("Press OK to initiate new");
        wFunc.taraWeight();

        String displayText = "User ID";
        while (true) {
            String userID = wFunc.requestInput(String.format("%s, press OK", displayText));
            user = http.getUser(userID);
            if (user != null) {
                break;
            } else {
                displayText = "Invalid ID";
            }
        }
        wFunc.getConfirmation(String.format("%s, confirm", user.getUserName()));

        displayText = "Product B. ID";
        while (true) {
            String productBatchID = wFunc.requestInput(String.format("%s, press OK", displayText));
            productBatch = http.getProductBatch(productBatchID);
            if (productBatch != null) {
                break;
            } else {
                displayText = "Invalid ID";
            }
        }
        product = http.getProduct(productBatch.getProdId());
        if (product == null) throw new IOException("product failed");
        wFunc.getConfirmation(String.format("%s, confirm", productBatch.getName()));

        displayText = "Raw Mat B. ID";
        while (true) {
            String rawMatBatchID = wFunc.requestInput(String.format("%s, press OK", displayText));
            rawMatBatch = http.getRawMatBatch(rawMatBatchID);
            if (rawMatBatch != null && Helper.rawMatInProduct(product, rawMatBatch)) {
                break;
            } else {
                displayText = "Invalid ID";
            }
        }
        wFunc.getConfirmation(String.format("%s, confirm", rawMatBatch.getName()));
        double amount = Helper.getAmount(product, rawMatBatch);
        if (amount == 0.0) throw new IOException("amount failed");
        wFunc.getConfirmation(String.format("%s kg, confirm ", amount));

        wFunc.getConfirmation("Clear weight, confirm");
        wFunc.taraWeight();

        wFunc.getConfirmation("Place Tara, press OK");
        Thread.sleep(2000);
        String tara = wFunc.getWeight();
        double taraWeight = Double.parseDouble(tara);
        wFunc.taraWeight();

        wFunc.getConfirmation("Place netto, press OK");
        Thread.sleep(2000);
        String netto = wFunc.getWeight();
        double nettoWeight = Double.parseDouble(netto);
        wFunc.taraWeight();

        wFunc.getConfirmation("Remove brutto, press OK");
        Thread.sleep(2000);
        String brutto = wFunc.getWeight();
        double bruttoWeight = -(Double.parseDouble(brutto));
        wFunc.taraWeight();

        wFunc.getConfirmation("Save weighing, press OK");
        wFunc.taraWeight();

        Weighing weighing = new Weighing(rawMatBatch.getRmbId(), productBatch.getProdBatchId(), user.getUserId(), taraWeight, nettoWeight);
        boolean result = http.saveWeighing(weighing);

        wFunc.getConfirmation("Complete, press OK");
        wFunc.taraWeight();

        Thread.sleep(5000);

        if (result) {
            System.out.println("Tara: " + taraWeight + " kg");
            System.out.println("Netto: " + nettoWeight + " kg");
            System.out.println("Brutto: " + bruttoWeight + " kg");
        } else {
            throw new IOException("Couldn't save weighing, you wasted your time :(");
        }
    }
}

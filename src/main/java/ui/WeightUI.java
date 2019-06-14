package ui;

import functionality.IWeightFunctionality;
import models.Product;
import models.ProductBatch;
import models.RawMatBatch;
import models.User;
import services.HttpService;
import utils.Helper;

import java.io.IOException;

public class WeightUI {

    private IWeightFunctionality wFunc;

    public WeightUI(IWeightFunctionality wFunc) {
        this.wFunc = wFunc;
    }

    public void menu() throws IOException {

        HttpService http = new HttpService();
        User user;
        ProductBatch productBatch;
        RawMatBatch rawMatBatch;
        Product product;

        String displayText = "Enter user ID";
        while (true) {
            String userID = wFunc.requestInput(String.format("%s .. press OK to confirm",displayText));
            user = http.getUser(userID);
            if (user != null) {break;}
            else {displayText = "Unvalid ID, try again";}
        }
        wFunc.getConfirmation(String.format("%s .. press OK to confirm",user.getUserName()));

        displayText = "Enter product batch ID";
        while (true) {
            String productBatchID = wFunc.requestInput(String.format("%s .. press OK to confirm",displayText));
            productBatch = http.getProductBatch(productBatchID);
            if (productBatch != null) {break;}
            else {displayText = "Unvalid ID, try again";}
        }
        product = http.getProduct(productBatch.getProdId());
        if (product == null) throw new IOException("product failed");
        wFunc.getConfirmation(String.format("%s .. press OK to confirm",productBatch.getName()));

        displayText = "Enter raw material batch ID";
        while (true) {
            String rawMatBatchID = wFunc.requestInput(String.format("%s .. press OK to confirm",displayText));
            rawMatBatch = http.getRawMatBatch(rawMatBatchID);
            if (rawMatBatch != null && Helper.rawMatInProduct(product,rawMatBatch)) {break;}
            else {displayText = "Unvalid ID, try again";}
        }
        wFunc.getConfirmation(String.format("%s .. press OK to confirm",rawMatBatch.getName()));
        double amount = Helper.getAmount(product,rawMatBatch);
        if (amount == 0.0) throw new IOException("amount failed");
        wFunc.getConfirmation(String.format("%s kg .. press OK to confirm",amount));

        wFunc.getConfirmation("Clear weight .. Press OK to confirm");
        wFunc.taraWeight();

        wFunc.getConfirmation("Place empty tara on weight .. Press OK to confirm");
        String tara = wFunc.getWeight();
        double taraWeight = Double.parseDouble(tara);
        wFunc.taraWeight();

        wFunc.getConfirmation("Place netto in tara .. Press OK to confirm");
        String netto = wFunc.getWeight();
        double nettoWeight = Double.parseDouble(netto);
        wFunc.taraWeight();

        wFunc.getConfirmation("Remove brutto from weight .. Press OK to confirm");
        String brutto = wFunc.getWeight();
        double bruttoWeight = -(Double.parseDouble(brutto));
        wFunc.taraWeight();

        wFunc.getConfirmation("Weighing complete .. Press OK to end");
        wFunc.taraWeight();

        // Send this to DB
        System.out.println("Tara: " + taraWeight + " kg");
        System.out.println("Netto: " + nettoWeight + " kg");
        System.out.println("Brutto: " + bruttoWeight + " kg");

    }
}

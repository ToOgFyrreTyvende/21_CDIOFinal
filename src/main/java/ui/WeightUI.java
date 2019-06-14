package ui;

import functionality.IWeightFunctionality;
import models.ProductBatch;
import models.RawMatBatch;
import models.User;
import services.HttpService;

public class WeightUI {

    private IWeightFunctionality wFunc;

    public WeightUI(IWeightFunctionality wFunc) {
        this.wFunc = wFunc;
    }

    public void menu(){

        HttpService http = new HttpService();
        User user;
        ProductBatch productBatch;
        RawMatBatch rawMatBatch;

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
        wFunc.getConfirmation(String.format("%s .. press OK to confirm",productBatch.getName()));

        displayText = "Enter raw material batch ID";
        while (true) {
            String rawMatBatchID = wFunc.requestInput(String.format("%s .. press OK to confirm",displayText));
            rawMatBatch = http.getRawMatBatch(rawMatBatchID);
            if (rawMatBatch != null) {break;} // check against DB
            else {displayText = "Unvalid ID, try again";}
        }
        wFunc.getConfirmation(String.format("%s .. press OK to confirm",rawMatBatch.getName()));

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

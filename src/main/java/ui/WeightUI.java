package ui;

import functionality.IWeightFunctionality;

public class WeightUI {

    private IWeightFunctionality wFunc;

    public WeightUI(IWeightFunctionality wFunc) {
        this.wFunc = wFunc;
    }

    public void menu(){

        String displayText = "Enter user ID";
        while (true) {
            String userID = wFunc.requestInput(String.format("%s .. press OK to confirm",displayText));
            // get userID.name from DB here
            if (userID.equals("12")) {break;} // check against DB
            else {displayText = "Unvalid user ID, try again";}
        }
        String username = "test name"; // this should come from DB
        wFunc.getConfirmation(String.format("%s .. press OK to confirm",username));

        displayText = "Enter raw material batch ID";
        while (true) {
            String rawMatBatchID = wFunc.requestInput(String.format("%s .. press OK to confirm",displayText));
            // get rawMatBatchID.name from DB here
            if (rawMatBatchID.equals("1234")) {break;} // check against DB
            else {displayText = "Unvalid raw material batch ID, try again";}
        }

        String rawMatBatch = "test batch"; // this should come from DB
        wFunc.getConfirmation(String.format("%s .. press OK to confirm",rawMatBatch));

        wFunc.getConfirmation(String.format("Clear weight .. Press OK to confirm"));
        wFunc.taraWeight();

        wFunc.getConfirmation(String.format("Place empty tara on weight .. Press OK to confirm"));
        String taraWeight = wFunc.getWeight();
        wFunc.taraWeight();

        wFunc.getConfirmation(String.format("Place netto in tara .. Press OK to confirm"));
        String nettoWeight = wFunc.getWeight();
        wFunc.taraWeight();

        wFunc.getConfirmation(String.format("Remove brutto from weight .. Press OK to confirm"));
        String bruttoWeight = wFunc.getWeight();
        wFunc.taraWeight();

    }
}

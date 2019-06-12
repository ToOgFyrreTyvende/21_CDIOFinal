package ui;

import functionality.IWeightFunctionality;

public class WeightUI {

    private IWeightFunctionality wFunc;

    public WeightUI(IWeightFunctionality wFunc) {
        this.wFunc = wFunc;
    }

    public void menu(){
        String userID = wFunc.requestInput("Enter user ID -> ok");
        // get userID.name from DB here
        String username = "test name";
        wFunc.displayText(username);
        String rawMatBatchID = wFunc.requestInput("Enter rawMatBatch ID");
    }

}

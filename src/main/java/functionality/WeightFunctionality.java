package functionality;

import services.IMettlerScale;

public class WeightFunctionality implements IWeightFunctionality {

    private IMettlerScale scale;

    public WeightFunctionality(IMettlerScale scale){
        this.scale = scale;
    }

    @Override
    public String requestInput(String msg){
        return scale.requestUserInput(msg).split("\"")[1];
    }
    @Override
    public boolean getConfirmation(String msg){
        String returnText = scale.requestUserInput(msg);
        return returnText.split(" ")[1].equals("A"); // A = måske "ok"
    }
    @Override
    public void taraWeight(){
        scale.taraWeight();
    }
    @Override
    public String getWeight(boolean sixth){
        return scale.getWeight(sixth);
    }

}

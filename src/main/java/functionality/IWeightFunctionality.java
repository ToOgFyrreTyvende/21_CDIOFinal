package functionality;

public interface IWeightFunctionality {
    String requestInput(String msg);
    boolean getConfirmation(String msg);
    void taraWeight();
    String getWeight();

    void displayText(String msg);
}

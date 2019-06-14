package services;

public interface IMettlerScale {
    String requestUserInput(String text);

    String requestUserNumber(String requestText);

    String taraWeight();
    String getWeight();
    boolean isConnected();
}

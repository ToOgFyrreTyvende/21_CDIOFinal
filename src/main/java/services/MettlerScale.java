package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MettlerScale implements IMettlerScale {

    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;

    public MettlerScale(String host, int port) {
        try {
            socket = new Socket(host,port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e){
            System.out.println("Connection failed");
            return;
        }
        System.out.println("Connection established");

    }

    @Override
    public String requestUserInput(String requestText) {
        scaleRequest(String.format("RM20 8 \"%s\" \"\" \"&3\"", requestText));
        String returnText = "";
        try{
            returnText = in.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }

        return returnText;
    }

    @Override
    public String requestUserNumber(String requestText) {
        scaleRequest(String.format("RM20 3 \"%s\" \"\" \"&3\"", requestText));
        String returnText = "";
        try{
            returnText = in.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }

        return returnText;
    }

    @Override
    public String taraWeight() {
        return scaleRequest("T").split(" ")[2];
    }

    @Override
    public String getWeight(boolean sixth) {
        if (sixth)
            return scaleRequest("S").split(" ")[6];
        else
            return scaleRequest("S").split(" ")[7];
    }

    @Override
    public boolean isConnected() {
        return socket.isConnected();
    }

    public String scaleRequest(String requestText) {
        out.println(requestText);
        String returnText = "";
        try{
            returnText = in.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
        return returnText;
    }
}

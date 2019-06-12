import java.io.IOException;
import functionality.IWeightFunctionality;
import functionality.WeightFunctionality;
import services.IMettlerScale;
import services.MettlerScale;
import ui.WeightUI;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {

        boolean defaultConnection = true;

        IMettlerScale scale = new MettlerScale("127.0.0.1", 8000);

        if (!defaultConnection) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("IP: ");
            String IP = scanner.next();
            System.out.print("Port: ");
            int Port = scanner.nextInt();
            scale = new MettlerScale(IP, Port);
        }

        IWeightFunctionality wFunc = new WeightFunctionality(scale);
        WeightUI ui = new WeightUI(wFunc);

        ui.menu();

    }
}

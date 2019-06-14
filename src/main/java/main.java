import java.io.IOException;
import functionality.IWeightFunctionality;
import functionality.WeightFunctionality;
import services.IMettlerScale;
import services.MettlerScale;
import ui.WeightUI;

public class main {
    public static void main(String[] args) throws IOException {

        IMettlerScale scale = new MettlerScale("127.0.0.1", 8000);

        IWeightFunctionality wFunc = new WeightFunctionality(scale);
        WeightUI ui = new WeightUI(wFunc);

        ui.menu();

    }
}

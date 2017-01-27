import communication.CommThread;
import controller.ControlPanel;
import controller.IControlPanel;

/**
 * Created by davide on 25/01/17.
 */
public class SmartAlarm {

    public static void main(String... args) throws Exception {
        IControlPanel cp = new ControlPanel();

        CommThread ct = new CommThread(args[0], cp);
        new Thread(ct).start();
    }
}

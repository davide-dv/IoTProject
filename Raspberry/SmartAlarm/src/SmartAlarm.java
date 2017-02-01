import communication.CommThread;
import controller.ControlPanelImpl;
import controller.ControlPanel;

/**
 * Created by davide on 25/01/17.
 */
public class SmartAlarm {

    public static void main(String... args) throws Exception {
        ControlPanel cp = new ControlPanelImpl();

        CommThread ct = new CommThread(args[0], cp);
        new Thread(ct).start();
    }
}

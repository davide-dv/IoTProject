import communication.CommThread;
import communication.SerialCommChannel;
import controller.ControlPanelImpl;
import controller.ControlPanel;

/**
 * Created by davide on 25/01/17.
 */
public class SmartAlarm {

    private static final int baudRate = 9600;

    public static void main(String... args) throws Exception {
        SerialCommChannel channel = new SerialCommChannel(args[0],baudRate);

        ControlPanel cp = new ControlPanelImpl(channel);

        CommThread ct = new CommThread(channel, cp);
        new Thread(ct).start();
    }
}

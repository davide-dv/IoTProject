package communication;

import controller.ControlPanel;
import database.DBOperations;
import database.MySQLOperations;


/**
 * Created by davide on 27/01/17.
 */
public class CommThread implements Runnable {

    private static final String temperature = "T";
    private static final String presence = "P";
    private static final String alarm = "A";

    private final MySQLOperations dbop;
    private final SerialCommChannel channel;
    private final ControlPanel cp;

    public CommThread(final SerialCommChannel channel, final ControlPanel cp) throws Exception {
        this.dbop = new MySQLOperations();
        this.cp = cp;
        this.channel = channel;

        System.out.println("Waiting Arduino for rebooting...");
        Thread.sleep(4000);
        System.out.println("Ready.");
    }

    @Override
    public void run() {
        while (true) {
            String msg = null;
            try {
                msg = channel.receiveMsg();
                this.cp.setComunication(true);

                System.out.println("Received: " + msg);

                if (msg.contains(temperature)) {
                    this.dbop.addTemperature(msg.substring(1));
                } else if (msg.contains(presence)) {
                    this.dbop.addEvent(DBOperations.TYPOLOGY.PRESENCE);
                } else if (msg.contains(alarm)) {
                    this.dbop.addEvent(DBOperations.TYPOLOGY.ALARM);
                    this.cp.setAlarm();
                }
                Thread.sleep(500);
                this.cp.setComunication(false);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

import Communication.SerialCommChannel;
import Database.MySQLOperations;

/**
 * Created by davide on 25/01/17.
 */
public class SmartAlarm {

    private static final String temperature = "T";
    private static final String presence = "P";
    private static final String alarm = "A";

    public static void main(String... args) throws Exception {
        final MySQLOperations dbop = new MySQLOperations();

        final SerialCommChannel channel = new SerialCommChannel(args[0],9600);
		/* attesa necessaria per fare in modo che Arduino completi il reboot */
        System.out.println("Waiting Arduino for rebooting...");
        Thread.sleep(4000);
        System.out.println("Ready.");


        while (true){
            String msg = channel.receiveMsg();
            System.out.println("Received: "+msg);

            if (msg.contains(temperature)) {
                dbop.addTemperature(msg.substring(1));
            } else if (msg.contains(presence)) {
                dbop.addEvent("presence");
            } else if (msg.contains(alarm)) {
                dbop.addEvent("alarm");
            }
            Thread.sleep(500);
        }
    }
}

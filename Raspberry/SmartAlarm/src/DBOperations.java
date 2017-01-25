/**
 * Created by davide on 25/01/17.
 */
public interface DBOperations {

    void addEvent(String date, String typology, String note);

    void addTemperature(String date, String value);
}

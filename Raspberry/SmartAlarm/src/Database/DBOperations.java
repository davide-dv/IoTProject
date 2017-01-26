package Database;

/**
 * Created by davide on 25/01/17.
 */
public interface DBOperations {

    /**
     * To add event record in database.
     * @param typology
     * @return
     */
    boolean addEvent(String typology);

    /**
     * To add temperature record in database.
     * @param value
     * @return
     */
    boolean addTemperature(String value);
}

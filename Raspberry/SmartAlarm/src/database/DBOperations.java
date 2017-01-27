package database;

/**
 * Created by davide on 25/01/17.
 */
public interface DBOperations {

    enum TYPOLOGY {
        ALARM("alarm"), PRESENCE("presence");

        private final String typology;
        TYPOLOGY(String typology) {
            this.typology = typology;
        }
        public String getTypology() {
            return typology;
        }
    }

    /**
     * To add event record in database.
     * @param typology
     * @return
     */
    boolean addEvent(TYPOLOGY typology);

    /**
     * To add temperature record in database.
     * @param value
     * @return
     */
    boolean addTemperature(String value);
}

package controller;

/**
 * Created by davide on 27/01/17.
 */
public interface ControlPanel {
    /**
     * To set alarm action
     */
    void setAlarm();

    /**
     * To set comunication action
     * @param state
     */

    void setComunication(boolean state);

}

package event;

public interface Event {

    /**
     * must be changed to false if event has to be ended
     */
    boolean alive = true;

    /**
     * update function, calling once every frame
     */
    void update();

    /**
     * starting function, calling once the event were loaded to processor
     */
    void start();

}

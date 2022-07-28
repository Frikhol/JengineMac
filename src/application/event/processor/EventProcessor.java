package event.processor;

import event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class EventProcessor {

    /**
     * event list for current application
     */
    private static List<Event> eventList =  new ArrayList<Event>();

    /**
     * pause flag
     */
    private static boolean pause = false;

    /**
     * the first buffer of events
     */
    private static Queue<Event> first = new ConcurrentLinkedQueue<>();

    /**
     * the second buffer of events
     */
    private static Queue<Event> second = new ConcurrentLinkedQueue<>();

    /**
     * adds an event to the processor
     * @param event - the event to be added
     */

    public static void addEvent(Event event) {
        eventList.add(event);
    }

    /**
     * processing events update
     */

    public static void processEvents() {
        if (!pause) {
            swap();
            for (Event event = second.peek(); event != null; event = second.poll()){
                event.update();
                    /*ArrayList<GameObject> objList = event.getGameObjectList();
                    if(!objList.isEmpty()) {
                        for(int i = 0; i < objList.size(); i++) {
                            objList.get(i).Update();
                        }
                    }*/
                if (event.alive)
                    first.add(event);
            }
        }
    }

    /**
     * swapping current GameEventProcessor buffers
     */

    private static void swap() {
        Queue<Event> temp = first;
        first = second;
        second = temp;
    }

    /**
     * pushing all game events of game
     *
     */
    public static void loadEvents() {
        for (Event event : eventList)
            pushEvent(event);
    }

    /**
     * used for first push GameEvent
     *
     * @param event - Event to push to processor
     */

    public static void pushEvent(Event event) {
        event.start();
        first.add(event);
    }

    /**
     * returns true if there are already pushed some events
     *
     * @return - true if there are already pushed some events
     */

    public static boolean hasEvents() {
        return !(first.isEmpty() && second.isEmpty());
    }

    /**
     * returns true if paused
     *
     * @return true if game was paused
     */
    public static boolean isPause() {
        return pause;
    }

    /**
     * sets pause state(T|F)
     *
     * @param pause - boolean for game pause state
     */
    public static void setPause(boolean pause) {
        EventProcessor.pause = pause;
    }
}

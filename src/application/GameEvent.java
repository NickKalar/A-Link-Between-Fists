package src.application;

import javafx.event.Event;
import javafx.event.EventType;

public class GameEvent extends Event{

    

    public static final EventType<GameEvent> ANY =
            new EventType<>(Event.ANY, "GAME_EVENT");

    public static final EventType<GameEvent> PLAYER1_GOT_HIT =
        new EventType<>(ANY, "PLAYER1_GOT_HIT");

    public static final EventType<GameEvent> PLAYER2_GOT_HIT =
        new EventType<>(ANY, "PLAYER2_GOT_HIT");

    public static final EventType<GameEvent> PLAYER3_GOT_HIT =
            new EventType<>(ANY, "PLAYER3_GOT_HIT");

    public static final EventType<GameEvent> PLAYER4_GOT_HIT =
            new EventType<>(ANY, "PLAYER4_GOT_HIT");
    
    public GameEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }
    
}
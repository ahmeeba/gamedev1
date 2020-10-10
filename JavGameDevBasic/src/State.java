import java.awt.*;

public abstract class State {


    //Game State Manager, stores a currentObject that gets ticked and rendered.
    //in the Game Class, if the currentState does not equal null, the tick and render methods belowe get called.
    private static State currentState = null;

    public static void setState(State state){
        currentState = state;
    }

    public static State getState(){
        return currentState;
    }

    //CLASS CODE BELOW

    protected Handler handler;

    public State(Handler handler){
        this.handler = handler;
    }

    //Class the code above has nothing to do with the abstract class, just put it in here for convenience
    public abstract void tick();

    //taking in a Graphics object, so the State class can also draw to the screen
    public abstract void render(Graphics g);

}

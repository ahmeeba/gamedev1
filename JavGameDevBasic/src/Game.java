//GAME LOOP ** Starts by Updating all the vars, positions of objects, etc.. Then renders everything to the screen, then REPEATS

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {
    //pretty much the main class, going to have all the code of the game
    //basic understanding of a thread - thread is a mini-program, runs within the big program, running completely seperately.

    private DisplayWindow display;

    //variables for the jframe window, height, width, and title that shows on the bar
    private int width, height;
    public String title;

    private boolean running = false;  //used to turn off and on the while loop in the run() method, stays off till the start method is called

    private Thread thread; //need to make a Thread data type to be able to use threads

    //bufferstategy tells a computer how to draw things to the screen, uses a buffer to do so.
    //buffer is like a hidden computer screen(memory that holds the same data as your comp screen). prevents flickering in the game
    private BufferStrategy bs;
    private Graphics g;

    //State var stuff, State just means switching from menu, to gameplay, to settings, different states
    //Declaring a State var, initializing it in init as a GameState, polymorphism, can do this because its abstract.
    private State gameState;
    private State menuState;

    //Key Input variable initialization
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //Camera
    private GameCamera gameCamera;

    //Handler
    private Handler handler;

    //Constructor
    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();

        //this will show the display on screen, run the code necessary to show the screen that is in DisplayWindow class.
        //in the main method I created a new Game object and gave it the "title" "height" and "width" parameters

    }

    //initializer method, initializes all the graphics of the game, is only called once when the run method is called.
    private void init(){
        display = new DisplayWindow(title,height,width);
        //gets Jframe of the display and adding a keyListener(allowing access to the keyboard)
        display.getFrame().addKeyListener(keyManager);
        //adding mouse manager to jframe and canvas, w/o canvas experienced bad glitching
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        //calls the init method in Assets class.
        Assets.init();

        handler = new Handler(this); //passing this since it takes in a gameObject
        gameCamera = new GameCamera(handler, 0, 0); //all the tiles drawn at their original position, no shift has happened yet

        //initialized a State asa GameState
        gameState = new GameState(handler); // initalized GameState class
        menuState = new MenuState(handler); //initialized MenuState class
        State.setState(menuState); //switch to gamestate.


    }

    //just the update method, updates everything on screen.
    private void tick(){
        keyManager.tick(); //needs to be put in and called before the State manager, otherwise input will not work.

        //if getState exists and isn't null, calls the tick method of the current state
        if(State.getState() !=null);
        State.getState().tick();
    }

    //renders objects to the screen
    private void render(){
        bs = display.getCanvas().getBufferStrategy();

        if (bs == null) { //if the canvas doesn't already have a buffer strategy, create one, don't exceed more than 3 buffers
            display.getCanvas().createBufferStrategy(3);
            return; //need to add a return or you will get errors
        }

        g = bs.getDrawGraphics(); // creates the 'paintbrush' for creating graphics, using the bufferstrategy
        //clear the screen
        g.clearRect(0, 0, width, height);


        //Draw inbetween this comment


        if(State.getState() !=null);  //calling the gameState tick and render method if the game is ran.
        State.getState().render(g);

        //Drawing end

        bs.show(); // always add these two lines of code when ending a drawing, or else errors will occur, shows drawing
        g.dispose(); // disposes of "the paintbrush" allocating memory, makes it good for garbage disposal/
    }

    //run method for threading
    public void run(){

        init();

        //frames per seconds(ticks per second), tick method to be called 60 times every second
        int fps = 60;
        // maximum amount of time in nanoseconds that it will have to execute the tick and render method to achieve the 60 fps target
        // using one billion because there are one billion nanoseconds in a second, so instead of measuring time in seconds going to be measuring them in nanoseconds
        double timePerTick = 1000000000 / fps;
        //the amount of time it will have till we have to call the tick and render method again.
        double delta = 0;
        //current time of our computer, used in the running method.
        long now;
        //returns the amount of time in nanoseconds that the computer is running at, return the current time of the computer in nanoseconds
        long lastTime = System.nanoTime();
        //once timer hits one second, prints onto the screen how many times tick and render were called
        long timer = 0;
        int ticks = 0;

        while(running){
           now = System.nanoTime();
           //adds to the delta variable the amount of time thats passed since the tick and render methods have been called and if they should be called again
           delta += (now - lastTime) / timePerTick;
           //add the amount of time to timer since the code above last ran, add the amount of nanaseconds that have passed
           timer += now - lastTime;
           lastTime = now;

           //if the delta time accumulates over 1 second then the if statement tells the run method to run tick and render
           if(delta >= 1) {
               tick();
               render();
               ticks++;
               delta--;
           }
           //displaying the frames/ticks per second on screen, if way above or below 60fps something wrong with the the code above/
           if(timer >= 1000000000){
               System.out.println("Ticks and Frames: " + ticks);
               ticks =0;
               timer = 0;
           }
        }

        stop();
    }

    //returns keyManager object so others can access it
    public KeyManager getKeyManager(){

        return keyManager;
    }

    //mousemanager getter
    public MouseManager getMouseManager(){
        return mouseManager;
    }

    //game camera getter
    public GameCamera getGameCamera(){
        return gameCamera;
    }

    public GameState getGameState(){return (GameState) gameState;}

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    //these methods are used for starting the threads, and stopping the threads, makes sure the threads are in sync when starting stopping
    public synchronized void start(){
        if(running)  // this if statement is for if the game is already running, if so then just return. since it shouldn't call the run method twice
            return;

        running = true;  //starts the while loop in run() method, starts the game loop

        //this used to run this class under a new thread.
        thread = new Thread(this);
        thread.start(); //calls the run method, so it can execute its code
    }

    //stop the threads from running, allocate the memory elsewhere.
    public synchronized void stop(){
        if(!running) //same as the if statement in the start method, except if running does not equal false, don't run any of the code below the if
            return;
        running = false;
        try {
            thread.join(); //hold the execution of the running thread until the specified thread is dead.
        } catch (InterruptedException e) {
            e.printStackTrace(); //help diagnosing with errors.
        }
    }

}

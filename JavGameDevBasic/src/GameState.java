import java.awt.*;

public class GameState extends State {



    //test
    private World world;



    public GameState(Handler handler){
        super(handler);
        world = new World(handler,"res/worlds/world1.txt");
        handler.setWorld(world); //sets the Handlers World object equal to world variable.





    //    game.getGameCamera().move(0, 0); tempcode camera checker, just keep it here for future reference.

    }

    @Override
    public void tick() {

        //calls the Player class tick() method over and over, used for controls input etc.
        world.tick();

    }

    @Override
    public void render(Graphics g) {

        //calls the Player render method, draws the player onto the screen.
       world.render(g);

       // Tile.tiles[0].render(g, 0, 0); // renders a grass tile to the screen. old code.

    }
}

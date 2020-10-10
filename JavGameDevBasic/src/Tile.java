import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    // Tile class that other specific tiles will extend, this class will generate new tiles to the screen using the id code of each specific tile, and its x, and y coordinates
    //more efficient way for level creation, maybe try and make a random level generate like minecraft later on.


    //STATIC CODE HERE*********************************************
    public static Tile[] tiles = new Tile[256];
    //when an id of 0 is called, it knows to grab the GrassTile class.
    public static Tile grassTile = new GrassTile(0);
    public static Tile waterTile = new WaterTile(1);
    public static Tile rockTile = new RockTile(2);
    public static Tile dirtTile = new DirtTile(3);


    //Class Code
    public static final int TILEWIDTH = 32, TILEHEIGHT = 32;

    protected BufferedImage texture;

    //making it final, since a each tile will have a different unique id that never changes.
    protected final int id;

    //Constructor,
    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;

        //when a subclass calls this constructor sets the tiles = to whatever the id for that subclass is.
        tiles[id] = this;

    }

    public void tick(){

    }

    //takes the x and y positions in the render method instead of the constructor
    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    //Any class that extends Tile will have a isSolid() method that returns false, unless its overidden to return true.
    public boolean isSolid(){
        //if it returns false that means the object is walkable on, if it returns true, it means you shouldn't be able to walk on it.
        return false;
    }

    public int getId(){
        return id;
    }

}

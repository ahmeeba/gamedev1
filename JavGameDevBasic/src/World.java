import java.awt.*;

public class World {

    private Handler handler;
    private int width, height;  //width and height of the pre-generated world
    private int spawnX, spawnY; //x and y coordinates of the player spawn point.
    //stores all the different tiles, will hold a bunch of Tile ids telling what id to display
    private int[][] tiles;

    //Entities
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    //load in a pre-made world, later I'll add randomly generated.
    public World(Handler handler, String path){
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 100, 100)); //spawning in the player
        entityManager.addEntity(new Tree(handler, 200, 300)); //spawning in a tree

        loadWorld(path);

        entityManager.getPlayer().setX(spawnX); //setting the x and y spawn position(100,100).
        entityManager.getPlayer().setY(spawnY);
    }

    public void tick(){
        entityManager.tick();
    }

    public void render(Graphics g){
        //xStart checks if 0(the index of the furthest left of the gamemap) if it is greater than the GameCamera xOffSet, just keep rendering it, if not, rendering the tiles to the right of GameCamera.
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH); //returns whatever the maximum number, returns the bigger of the two numbers. sets the x start to either 0 or whatever the gamecamera varibleis, prevents a number thats less tahn zero being passes.
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for(int y =yStart; y < yEnd; y++){  //instead of hardcoding 0 into the loop, set 0 equal to a variable.
            for(int x = xStart; x < xEnd; x++){  //sets x equal to the number tile visible to the left and right, if a tile isnt visible it won't be rendered.
                getTile(x,y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
        //entities
        entityManager.render(g);

    }

    // this method will look for the correct tile, for whatever index is called in the for loop.
    public Tile getTile(int x, int y){

        if(x < 0 || y < 0 || x >= width || y >= height ) //if the player makes it outside of boundaries, just treat it as they are standing on a grass tile, so the game doesnt crash
            return Tile.grassTile;

        Tile t = Tile.tiles[tiles[x][y]];
        //there are 256 tile array slots made, but so far only 4 are in use, so if a tile array index is called that doesnt have a tile made for it, it will just return a dirttile
        if(t == null)
            return Tile.dirtTile;
        return t;
    }

    public void loadWorld(String path){
        String file = Utils.loadFileAsString(path);  //loads in the txt file as a path.
        String[] wrldtxtsplit = file.split("\\s+"); //this line splits up the strings in world.txt file and adds it into a string array
        //reads the first four numbers of the txt file, which are the width,height of the world, and the player spawn coordinates
        width = Utils.parseInt(wrldtxtsplit[0]);  //parses the new string array data into an integer data type
        height = Utils.parseInt(wrldtxtsplit[1]);
        spawnX = Utils.parseInt(wrldtxtsplit[2]);
        spawnY = Utils.parseInt(wrldtxtsplit[3]);

        //every line below this will be for the actual world data, reading and generating tiles for the world.
        tiles = new int[width][height];
        for(int y = 0; y <height; y++){
            for(int x = 0; x < width; x++){
                tiles[x][y]=Utils.parseInt(wrldtxtsplit[(x + y * width) + 4]); //fits the multidimensional array variables into a one dimensional array
            }                                                                  //+4 because the the 4 declared variables up there aren't part of the array, so gotta skip ahead to reach the tile index
        }
    }

    //Getters
    public int getWidth(){  //for accessing the width and height of the World outside of this class.
        return width;
    }

    public int getHeight(){
        return height;
    }

}

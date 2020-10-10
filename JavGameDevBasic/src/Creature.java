//abstract because there will be many creatures in this game, such as the player
public abstract class Creature extends Entity {

    //Health and speed won't change for any creature, so making it final
    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    //setting the size of the creature sprites
    public static final int DEFAULT_CREATURE_WIDTH = 32, DEFAULT_CREATURE_HEIGHT = 32;  //originally 64x64..****

    protected int health;

    protected float speed;
    protected float xMove, yMove;


    //Constructor, instantiating the variables needed, pass the creatures location on the screen in params
    public Creature(Handler handler, float x, float y, int width, int height) {
        //super refers to the classes constructor
        super(handler,x, y, width, height);
        health = DEFAULT_HEALTH;
        speed= DEFAULT_SPEED;
        xMove = 0;
        yMove= 0;
    }

    //Separate move functions so that both can be adjusted individually.
    //checks to see what direction the player is moving, moves the collider rectangle to that position.
    public void move(){
        if (!checkEntityCollision(xMove, 0f))
            moveX();
        if (!checkEntityCollision(0f, yMove))
            moveY();
    }

    //collision detection movement for the X axis, moving left and right
    public void moveX(){
        //helps determine what corners to check for collision detection
        if(xMove > 0){ //if the variable is greater than zero, it means its moving right, moving up on x axis
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&       // gets the upper corner of the collision box, if there is no solid tile you can move
                 !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x +=xMove;

            }else{ //********** collision testing, delete if it messes it up
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
            }

        }else if(xMove < 0){ //if its less than zero it is moving left
            int tx = (int) (x + xMove + bounds.x ) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&       // gets the upper corner of the collision box, if there is no solid tile you can move
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                x += xMove;
            } else{ //********** collision testing, delete if it messes it up
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x ;
            }
        }
    }

    //collision detection movement for the Y axis, moving up and down
    public void moveY(){
        if(yMove < 0){  //moving up
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

            if(!collisionWithTile((int) (x + bounds.x) /Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) /Tile.TILEWIDTH, ty)){
                y += yMove;
            }else{ //********** collision testing, delete if it messes it up
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y ;
            }


        }else if(yMove > 0){ //moving down

            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

            if(!collisionWithTile((int) (x + bounds.x) /Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) /Tile.TILEWIDTH, ty)){
                y += yMove;
            }else{ //********** collision testing, delete if it messes it up
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
            }

        }
    }

    protected boolean collisionWithTile(int x, int y){
        return handler.getWorld().getTile(x, y).isSolid();
    }

    //xMove getters and setters
    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    //yMove getters and setters
    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    //setters amd getters for health
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    //setters and getters for speed
    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }


}

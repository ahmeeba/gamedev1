import java.awt.*;

public abstract class Entity {

    protected Handler handler;

    // entities x and y position
    protected float x,y;
    //size of the entity, for drawing different size sprites
    protected int width, height;

    protected Rectangle bounds;

    //position x getters and setters
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    //position y getters and setters
    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    //width setters and getters
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    //height setters and getters
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    //takes in starting position and sets up the x and y var
    public Entity(Handler handler, float x, float y, int width, int height){
        this.handler = handler;
        this.x =x;
        this.y=y;
        this.width = width;
        this.height = height;

        bounds = new Rectangle(0, 0, width, height);
    }

    //where the entity updates all its variable(move)
    public abstract void tick();
    //where the entity draws itself on the screen
    public abstract void render(Graphics g);

    //loop that entities use to check if they are colliding with other entities.
    public boolean checkEntityCollision(float xOffset, float yOffset){
        for (Entity e: handler.getWorld().getEntityManager().getEntities()){
            if (e.equals(this))//if statement so that the entity skips over itself while checking collision.
                continue;
            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
                return true;
        }
        return false;
    }

    //returns rectangle that is the perfect size for each entity, use as a collider box.
    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y+ bounds.y + yOffset), bounds.width, bounds.height);
    }
}

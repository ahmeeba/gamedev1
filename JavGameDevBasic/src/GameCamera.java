public class GameCamera {

    //the offset variables that tell the computer how far off to draw something from its original position,
    //gives the illusion of camera movement, when in reality the tiles shift position depending on where the offSet position is marked at.

    private Handler handler;
    private float xOffset, yOffset;


    public GameCamera(Handler handler, float xOffset, float yOffset){
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;

    }

    public void checkBlankSpace(){
        if(xOffset < 0){  //cuts camera scrolling when you go to far left.
            xOffset = 0;
        }else if(xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()){ //cuts camera scrolling for the right side
            xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
        }

        if(yOffset < 0){
            yOffset = 0;
        }else if(yOffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()){ //cuts camera scrolling for the right side
            yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
        }
    }

    //centering the camera around the player entity, so the players x and y coordinates get passed through the x and y offset, making the camera follow the player
    public void centerOnEntity(Entity e){
        xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
        yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;

        checkBlankSpace(); // calls method

    }

    public void move(float xAmnt, float yAmnt){
        xOffset += xAmnt;  //method will add whatever the x or y amount is to the offset variables, telling trhem how far to shift the tiles either on the x or y avis
        yOffset += yAmnt;

        checkBlankSpace();
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }

}

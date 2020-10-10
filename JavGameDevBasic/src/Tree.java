import java.awt.*;

//Making the tree tiles a static entity, maybe add an ability to cut down or blow up trees that block a path

public class Tree extends StaticEntity {
    //Constructor for the tree tile
    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
    }

    @Override
    public void tick() {

    }

    //Rendering the tree onto screen
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree,(int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
}

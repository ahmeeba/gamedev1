import java.awt.image.BufferedImage;

public class WaterTile extends Tile{
    public WaterTile( int id) {
        super(Assets.water, id);
    }
    //overrides the isSolid method in Tile();.
    public boolean isSolid(){
        return true;
    }
}

import java.awt.image.BufferedImage;

public class DirtTile extends Tile {
    //Constructor comes with a BufferedImage para, just delete it, and add the Assets. to the super to call the assets crop of the tile.
    public DirtTile(int id) {
        super(Assets.dirt, id);
    }
}

import java.awt.image.BufferedImage;

//Assets class for cropping each tile and player/npc sprite

public class Assets {

    //initializing all the assets that graphics that will be used and called on.
    public static BufferedImage grass, water, rock, dirt, tree;  //player deleted, adding bufferedimage array for animation
    public static BufferedImage[] player_down, player_up, player_left, player_right, player_idle;

    private static final int width = 32, height = 32;  //make these the widths and height of the char sprites.
    private static final int widthS = 32, heightS = 32;  //make these the widths and height of the level sprites.

    //loads in everything for the game, only is called once. efficient way to load in assets.
    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/TileSetTemp.png"));
        SpriteSheet csheet = new SpriteSheet(ImageLoader.loadImage("/textures/charsheet.png"));

        //using the asset sprite sheets, cropping them correctly using their width and height, good to make them around 32 or 16.
        //when they are further on the x or y axis, you have to calculate their x or y placement.
        //TileSetTemp is 32x32, so just do the math for its x and y axis accordingly.

        player_idle = new BufferedImage[1];
        player_down = new BufferedImage[2];
        player_up = new BufferedImage[2];
        player_left = new BufferedImage[2];
        player_right = new BufferedImage[2];

        //different walk cycle frames.
        player_down[0] = csheet.crop(0, 0, width, height);
        //player_down[1] = csheet.crop(width, 0, width, height);
        player_down[1] = csheet.crop(width*2, 0, width, height);

        player_up[0] = csheet.crop(width, height*3, width, height);
      //  player_up[1] = csheet.crop(0, height*3, width, height);
        player_up[1] = csheet.crop(width*2, height*3, width, height);

        player_right[0] = csheet.crop(0, height, width, height);
      //  player_right[1] = csheet.crop(width, height, width, height);
        player_right[1] = csheet.crop(width*2, height, width, height);

        player_left[0] = csheet.crop(0, height*2, width, height);
       // player_left[1] = csheet.crop(width, height*2, width, height);
        player_left[1] = csheet.crop(width*2, height*2, width, height);

        player_idle[0] = csheet.crop(width, 0, width, height );

      //  player = csheet.crop(0,0, width, height);

        grass = sheet.crop(0,0,widthS, heightS);
        rock = sheet.crop(widthS*2, 0, widthS, heightS);
        water = sheet.crop(widthS, 0, widthS,heightS);
        tree = sheet.crop(widthS*3, 0, widthS, heightS);
        dirt = sheet.crop(0, heightS, widthS, heightS);


    }

}

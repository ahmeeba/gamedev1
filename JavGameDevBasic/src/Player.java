import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    //original width/height of super was 50. collision box was doubled.

    // not needed since entity has a GameObject    private Game game;  //need this variable and pass it in paras to access GameManagers KeyManager and input.

    //Animation code
    private Animation animDown, animUp, animLeft, animRight, animIdle;

    public Player(Handler handler, float x, float y) {
        //in the super class, set x,y, coordinates, and the width and height of the sprite
        //can change the width and height to whatever tho, probably change it soon to a different one, 64x64 is kinda stocky looking
        super(handler, x, y, 35, 25); //potentially change height and width back to Creature.DEFAULT WIDTH/HEIGHT
       //  this.game = game;  not needed since the entity has a game initializer and game object.

        //setting the boundaries of the collision box, might need to customize if the character sprite changes..
        bounds.x = 2;
        bounds.y = 8;
        bounds.width = 8;
        bounds.height = 15;

        //animation initialization
        animDown = new Animation(135, Assets.player_down);  //player walking down animation //last speed use was 500
        animUp = new Animation(135, Assets.player_up);  //player walking down animation
        animLeft = new Animation(135, Assets.player_left);  //player walking down animation
        animRight = new Animation(135, Assets.player_right);  //player walking down animation
        animIdle = new Animation(0, Assets.player_idle);
    }

    @Override
    public void tick() {
        //animation
        animDown.tick(); //calling the tick method to update the index variable
        animUp.tick();
        animLeft.tick();
        animRight.tick();
        getInput(); //sets the move variables to 0 first, but will adjust them to a different int data depending on wehich key is pressed
        //calls the move method in creature class, adds three to the yMove or Xmove, adds a negative sign if moving up or left.
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    public void getInput(){

        xMove = 0;
        yMove = 0;
        //to move up on the yaxis the variable needs to be negative, and so on.
        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
    }

    @Override
    public void render(Graphics g) {
        //drawing the player sprite onto the screen, casting the varaibles which are floats into int variables.
        //using the x and y variables from the creature class, creature class gets the protected x and y variables from the entity class, big chain link
        g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);  //temporary, change this line of code when animation is implemeneted


        //adding in width and height, will resize the character to 64x64 since that's what the creature default height is set to.
        //- game.getGameCamera, just basic math that centers the cameras coordinates in the center of the character.

    /*
            //makes the collision box for the player visible, keep it here for testing purposes.
        g.setColor(Color.red);
        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
                bounds.width, bounds.height);    // making a rectangle collision box, to see the collision box's dimensions*/
    }

    //Animation frames, choosing the right frame depending on characters direction.
    private BufferedImage getCurrentAnimationFrame(){
        if(xMove < 0){
            return animLeft.getCurrentFrame();
        }else if(xMove > 0){
            return  animRight.getCurrentFrame();
        }else if(yMove < 0){
            return animUp.getCurrentFrame();
        }else if (yMove > 0){
            return animDown.getCurrentFrame();
        }else{
            return animIdle.getCurrentFrame();
        }
    }

}

import java.awt.image.BufferedImage;

public class Animation {

    private int speed, index;
    private long lastTime, timer;
    private BufferedImage[] frames;

    //Constructor instantiating needed variables, need the frame arrays passed in params.
    public Animation(int speed, BufferedImage[] frames){
        this.speed = speed;
        this.frames = frames;
        index = 0;
        lastTime = System.currentTimeMillis();
        timer = 0;
    }

    // tick method for loop
    public void tick(){
        //gets the time in milliseconds since this tick method and the previous tick method were called. adding that time to timer
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > speed){ //Conditional so timer doesn't exceed speed of animation
            index++;
            timer= 0;
            if(index >= frames.length){  //prevents the animation loop from exceeding the amount of frames that are in the animation. once its reached the last frame it will loop back to the first frame
                index = 0;
            }
        }
    }

    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
}

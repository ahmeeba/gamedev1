import java.awt.*;

public class MenuState extends State {

    public MenuState(Handler handler){
        super(handler);

    }

    @Override
    public void tick() {

        if(handler.getMouseManager().isLeftPressed())
            State.setState(handler.getGame().getGameState());

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.drawString("Click left mouse to start", 25, 80);
    }
}

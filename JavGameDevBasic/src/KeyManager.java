import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean up, down, left, right;

    //whenever a key is pressed, the keycode gets set in keyTyped as true(true that the key has been pressed)
    public KeyManager(){
        keys = new boolean[256];
    }

    public void tick(){
        up = keys[KeyEvent.VK_W]; //sets the up button as W key or Up arrow.
        down = keys[KeyEvent.VK_S]; //sets the down button as S key or Down arrow.
        left = keys[KeyEvent.VK_A]; //sets the left button as A key or Left arrow.
        right = keys[KeyEvent.VK_D]; //sets the up button as D key or Right arrow.
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    //called wheneevr a key is pressed, sets the keycode to true
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    //called whenever a key is released after being pressed, sets the keycode to false(not being pressed)
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}

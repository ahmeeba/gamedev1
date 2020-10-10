//import javafx.stage.Screen;

import javax.swing.*;
import java.awt.*;

public class GameMain extends JFrame {


       

        public static void main(String[] args) {

            //creates a new display window, will run the constructor in the DisplayWindow class, which will run the rest of the code
           Game game = new Game("Adventure", 500, 500);
           //calls the start() method which calls the run and init method, starting the game loop, renders and ticks
           game.start();

        }
}

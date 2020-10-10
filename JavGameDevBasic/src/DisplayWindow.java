import java.util.*;
import java.awt.*;
import java.util.concurrent.CancellationException;
import javax.swing.*;

public class DisplayWindow {

    //JFrame needs three main things for it to work, title, width, height, allows drawn canvas objects to be displayed
    private JFrame frame;

    //creating a canvas so that graphics can be displayed in the JFrame, can draw graphics to a canvas, add canvas element to the JFrame so the graphics can be seen
    private Canvas canvas;

    private String title;
    private int width,height;

    public DisplayWindow(String title, int width, int height){
        //using the 'this' keyword since there are other variables with the same name, make sure the computer knows we are talking about the ones in the parameters
        this.title = title;
        this.width = width;
        this.height = height;

        //running the code in the CreateDisplay method
        createDisplay();
    }

    //creating the window display method
    private void createDisplay(){
        //sets the title of the window
        frame = new JFrame(title);
        frame.setSize(width, height);
        //ensures your program stops running when the 'x' button is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //fixed resolution of the window jframe, can't be resized
        frame.setResizable(false);
        //ensures the window appears in the middle of the screen
        frame.setLocationRelativeTo(null);
        //makes the window visible
        frame.setVisible(true);

        //setting the canvas to about the same size as the JFrame so there is no empty space inbetween, using the same heights and widths
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false); //makes it so the JFrame is the only thing that has focus, allows key input to be read.

        //adding the canvas to the JFrame
        frame.add(canvas);
        //resizes the window a tiny bit so that all of the canvas can be seen.
        frame.pack();

        //something to think about later,, Maybe make the screen resizable later, check out other frame. methods to use..
    }

    //getter so i can access Canvas variable since its declared private. encapsulation
    public Canvas getCanvas(){
        return canvas;
    }

    //returns the JFrame frame variable that is declared at the top of the class, this way it makes the JFrame accessible
    public JFrame getFrame(){
        return frame;
    }

}



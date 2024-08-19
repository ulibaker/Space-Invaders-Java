package spaceinvaders;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Brick {
    Image image;
    int x,y;

    public Brick() {
        ImageIcon Brick5 = new ImageIcon(this.getClass().getResource("images/Brick5.jpg"));
        image = Brick5.getImage();
        x = 0;
        y = 650;
    }
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Image getImage(){
        return image;
    }
    public Rectangle getBounds(){
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }
    
}

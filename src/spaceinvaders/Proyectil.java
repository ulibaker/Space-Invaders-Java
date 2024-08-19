package spaceinvaders;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Proyectil {
    Image image;
    int x,y;
    final int s = 10;
    boolean visible;

    public Proyectil(int x, int y){
        ImageIcon bullet = new ImageIcon(this.getClass().getResource("images/Bullet.jpg"));
        image = bullet.getImage();
        visible = true;
        this.x = x;
        this.y = y;
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

    public boolean isVisible(){
        return visible;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

    public void update(){
        y -= s;
        if(y<0)
            visible = false;
    }
}
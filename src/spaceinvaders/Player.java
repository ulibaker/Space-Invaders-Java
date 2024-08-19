package spaceinvaders;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class Player {
    Image image;
    int x,y,dx;
    int s = 2;
    ArrayList bullets;
    boolean shot;
    
    Player(){
        ImageIcon ship2 = new ImageIcon(this.getClass().getResource("images/Ship2.jpg"));
        image = ship2.getImage();
        y = 700;
        x = 150-image.getWidth(null)/2;
        dx = 0;
        bullets = new ArrayList();
        shot = false;
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

    public ArrayList getBullets(){
        return bullets;
    }

    public void movement(){ 
        if((x>0 && dx<0) || (x<460 && dx>0))
            x += dx;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT)
            dx = s;
        if(key == KeyEvent.VK_LEFT)
            dx = s * -1;
        if(key == KeyEvent.VK_SPACE && shot==false)
        {
            shot = true;
            bullets.add(new Proyectil(x + image.getWidth(null)/2, y));
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT && dx < 0)
            dx = 0;
        if(key == KeyEvent.VK_RIGHT && dx > 0)
            dx = 0;


    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }
}
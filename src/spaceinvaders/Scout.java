package spaceinvaders;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.util.Random;
import java.util.ArrayList;

public class Scout {
    Image image;
    int x, y, vel; 
    boolean dir =false;
    boolean visible;
    Random random;
    
    public Scout(){
        ImageIcon EnemyScout = new ImageIcon(this.getClass().getResource("images/EnemyScout.jpg"));
        image = EnemyScout.getImage();       
        this.y = 100;
        vel = 3;
        dir = new Random().nextBoolean();     
        this.x = 200;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
    public void setVisible(boolean visible){
        this.visible = visible;
    }
    
    public boolean isVisible(){
        return visible;
    }
    public Rectangle getBounds(){
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }
    public void movementS(){ 
        if(x>0 && dir==false) {
            x += vel;
        }
        else if(x<460 && dir==true) {
            x -= vel;
        }
            
    }
}

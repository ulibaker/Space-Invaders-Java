package spaceinvaders;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.util.Random;
import java.util.ArrayList;

public class Invaders {
        Image image;
        int x, y, vel, dir, movedX, movedY, wentDown, bulletChance;
        int range;
        boolean visible, goDown;
        Random random;
        ArrayList attacks;
        
    public Invaders(int x, int y){
        ImageIcon EnemyFighter = new ImageIcon(this.getClass().getResource("images/EnemyFighter.jpg"));
        image = EnemyFighter.getImage();
        this.x = x;
        this.y = y;
        
        range = 200;
        vel = 1;
        movedX = 0;
        dir = 1; 
        visible = true;
        goDown = false;
        movedY = 0;
        wentDown = 0;
        random = new Random();
        bulletChance = 700; 
        attacks = new ArrayList();
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

    public Image getImage(){
        return image;
    }

    public int getSpeed(){
        return vel;
    }

    public void setSpeed(int speed){
        this.vel = speed;
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

    public void update(){
        if(movedX>range){
            movedX = 0;
            goDown = true;
            dir*=-1;
        }

        if(goDown){
            y++;     
            movedY++;
            if(movedY > image.getHeight(null))
            {
                goDown = false;
                movedY = 0;
                wentDown++;

            }
        } else {
            x += vel * dir;
            movedX+= vel;
        }

        if(random.nextInt()%bulletChance==1 && y < 650)
            attacks.add(new EnemyAttack(x, y));
    }

    public ArrayList getBombs(){
        return attacks;
    }
}
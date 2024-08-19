package spaceinvaders;

import javax.sound.sampled.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener {
    Timer timer;
    Player p;
    Brick brick;
    Scout eScout;
    Invaders invader[][];
    int InvF, InvR, invSX, invSY, invPG;
    Font font;
    String msg;
    int aliensLeft;
    int points=0;
    int lives=3;
    boolean appear;
    boolean gameEnded;
    
    public Board() throws FileNotFoundException, UnsupportedAudioFileException, IOException, LineUnavailableException{
        setDoubleBuffered(true);
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new Listener());
        
        p = new Player();
        brick = new Brick();
        
        File temitaF = new File("C:\\Users\\Red\\Documents\\NetBeansProjects\\SpaceInvaders\\src\\spaceinvaders\\temita.wav");
        AudioInputStream temitaAS = AudioSystem.getAudioInputStream(temitaF);
        
        Clip temitaC = AudioSystem.getClip();
        temitaC.open(temitaAS);
        
        temitaC.start();

        InvF = 8;
        InvR = 3;
        invSX = 20;
        invSY = 20;
        invPG = 3;
        invader = new Invaders[InvF][InvR];
        for(int i = 0; i < InvF; i++)
            for(int j = 0; j < InvR; j++){
                invader[i][j] = new Invaders(invSX, invSY);
                invader[i][j].setX(invSX + i*invader[i][j].getImage().getWidth(null) + i*invPG);
                invader[i][j].setY(invSY + j*invader[i][j].getImage().getHeight(null) + j*invPG);
            }

        aliensLeft = InvF * InvR;
        font = new Font("impact", Font.PLAIN, 20);
        msg = "Puntos: " + points;

        gameEnded = false;

        timer = new Timer(15, this);
        timer.start();
    }

    public void paint(Graphics g){
        
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(p.getImage(), p.getX(), p.getY(), this);       
        g2d.drawImage(brick.getImage(), brick.getX(), brick.getY(), this);
        ArrayList<Proyectil> lasers = p.getBullets();
        for(int i = 0; i < lasers.size(); i++){
            Proyectil l = lasers.get(i);
            g2d.drawImage(l.getImage(), l.getX(), l.getY(), this);
        }

        for(int i = 0; i < InvF; i++)
            for(int j = 0; j < InvR; j++){
                if(invader[i][j].isVisible())
                    g2d.drawImage(invader[i][j].getImage(), invader[i][j].getX(),
                            invader[i][j].getY(), this);
                ArrayList<EnemyAttack> bombs = invader[i][j].getBombs();
                for(int k=0; k < bombs.size(); k++){
                    EnemyAttack attacks = bombs.get(k);
                    g2d.drawImage(attacks.getImage(), attacks.getX(), attacks.getY(), this);
                }
            }

        g2d.setColor(Color.white);
        g2d.setFont(font);
        g2d.drawString(msg, 10, 20);
        g2d.drawLine(0, 650, 800, 650);

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void actionPerformed(ActionEvent e){
        p.movement();
        
        ArrayList<Proyectil> bullets = p.getBullets();
        for(int i = 0; i < bullets.size(); i++){
            Proyectil oa = bullets.get(i);
            if(oa.isVisible()) {
                oa.update();
            }
            else {
                bullets.remove(i);
                p.shot = false;
            }
                
        }

        for(int i = 0; i < InvF; i++){
            for(int j = 0; j < InvR; j++){
                invader[i][j].update();
                if(invader[i][j].getY() >= 650){
                    lives=0;
                    gameOver();    
                }

                
                ArrayList<EnemyAttack> attacks = invader[i][j].getBombs();
                for(int k=0; k < attacks.size(); k++){
                    EnemyAttack ea = attacks.get(k);
                    if(ea.isVisible())
                        ea.update();
                    else
                        attacks.remove(k);
                    if(ea.getBounds().intersects(brick.getBounds())){
                        ea.setVisible(false);
                    }

                    if(ea.getBounds().intersects(p.getBounds())) {
                        ea.setVisible(false);
                        p.x = 20;
                        lives--;
                        if(lives == 0) {
                            gameOver();
                        }
                    }
                }

                for(int li = 0; li < bullets.size(); li++){
                    Proyectil oa = bullets.get(li);
                    if(oa.getBounds().intersects(brick.getBounds())){
                        oa.setVisible(false);
                    }
                    if(oa.getBounds().intersects(invader[i][j].getBounds()) && oa.isVisible() && invader[i][j].isVisible()){
                        invader[i][j].setVisible(false);
                        oa.setVisible(false);
                        aliensLeft--;
                        points=points+20;
                        if(points == 1000){
                            lives++;
                            points =0;
                        }
                        if(aliensLeft==0) {
                            gameOver();
                        }
                       
                    }
                }
            }
        }

        if(!gameEnded)
            msg = "Puntos: " + points + "    Vidas: " + lives;

        repaint();
    }

    private class Listener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            p.keyPressed(e);
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                if(gameEnded){
                    for(int i = 0; i < InvF; i++)
                        for(int j = 0; j < InvR; j++){
                            invader[i][j] = null;
                            invader[i][j] = new Invaders(invSX, invSY);
                            invader[i][j].setX(invSX + i*invader[i][j].getImage().getWidth(null) + i*invPG);
                            invader[i][j].setY(invSY + j*invader[i][j].getImage().getHeight(null) + j*invPG);
                        }
                    gameEnded = false;
                    aliensLeft = InvF * InvR;
                    timer.start();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e){
            p.keyReleased(e);
        }
    }


    public void gameOver(){
        gameEnded = true;
        if(lives == 0) {
            msg = "GAME OVER";
            msg += " - Presione [ENTER] para reiniciar";
            points = 0;
            lives=3;
        }
        else {
            msg = "Felicidades";
            msg += " - Presione [ENTER] para continuar";
        }
        timer.stop();
    }
}
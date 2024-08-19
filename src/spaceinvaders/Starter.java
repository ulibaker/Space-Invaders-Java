package spaceinvaders;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class Starter extends JFrame {

    public Starter() throws UnsupportedAudioFileException, IOException, FileNotFoundException, LineUnavailableException{
        add(new Board());
        setTitle("Space Invaders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,800);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, FileNotFoundException, LineUnavailableException {
        new Starter();
    }
}
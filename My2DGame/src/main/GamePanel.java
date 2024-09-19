package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //screen settings
    final int fps = 60;
    final int originalTileSize = 16; //16x16 tiles
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48x48

    //how many tiles fit in the screen. 4 : 3 ratio
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //786 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels

    //will run until you stop it
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(this, keyHandler);
    TileManager tileManager = new TileManager(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);

        //improves game rendering performance
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setLayout(new FlowLayout());

        //allows the panel to be focused so it can receive key input
        this.setFocusable(true);
    };

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    //game loop
   @Override
   public void run() {
       double drawInterval = 1e09/fps;
       double delta = 0;
       int frameCounts = 0;
       long timer = 0;
       long lastTime = System.nanoTime();

       while(gameThread != null) {
           long currentTime = System.nanoTime();
           delta += (currentTime-lastTime)/drawInterval;
           timer += currentTime-lastTime;
           lastTime = currentTime;
           if(delta >= 1) {
               update();
               repaint();
               delta = 0;
               frameCounts++;
           }
           if(timer >= 1e09) {
               System.out.println("FPS: "+frameCounts);
               timer = 0;
               frameCounts = 0;
           }
       }
   }

    public void update() {
        player.update();
    }
    //built-in method on the JPanel class
    //a class with functions to draw objects on screen
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //converts Graphics to Graphics2D
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        player.draw(g2);
        //release resources used by the graphics context
        g2.dispose();
    }
}

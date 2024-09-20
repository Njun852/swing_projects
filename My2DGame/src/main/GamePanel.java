package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GamePanel extends JPanel implements Runnable{

    //screen settings
    public int zoomFactor = 1;
    final int fps = 60;
    final int originalTileSize = 16; //16x16 tiles
    final int scale = 3;
    public int tileSize = originalTileSize * scale; //48x48

    //how many tiles fit in the screen. 4 : 3 ratio
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //786 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldHeight = maxWorldRow * tileSize;
    public final int worldWidth = maxWorldCol * tileSize;

    //will run until you stop it
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    TileManager tileManager = new TileManager(this);
    public Player player = new Player(this, keyHandler);
    public CollisionChecker collisionChecker = new CollisionChecker(this);

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

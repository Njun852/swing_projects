package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    int tileSize = 5;
    int column = 200;
    int rows = 130;
    int height = tileSize * rows;
    int width = tileSize * column;

    Thread gameThread;
    CellManager cellManager = new CellManager(this);
    public GamePanel() {
        this.setPreferredSize(new Dimension(width, height));
        this.isDoubleBuffered();

    }

    @Override
    public void run() {
        int fps = 24;
        double drawInterval = 1e09/fps;
        double delta = 0;
        double time = System.nanoTime();
        double timer = 0;
        double counter = 0;
        while(gameThread != null) {
            double currentTime = System.nanoTime();
            delta += (currentTime-time)/drawInterval;
            timer += (currentTime-time);
            time = currentTime;
            if(delta >= 1) {
                repaint();
                cellManager.updateState();
                counter++;
                delta = 0;
            }
            if(timer >= 1e09) {
//                System.out.println("FPS: "+counter);
                counter = 0;
                timer = 0;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        this.setBackground(Color.BLACK);

        cellManager.drawCells(g2d);
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void stopGameThread() {
        gameThread = null;
    }
}

package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    final int tileSize = 20;
    final int numOfColumns = 30;
    final int numOfRows = 20;
    final int width = numOfColumns * tileSize;
    final int height = numOfRows * tileSize;

    final int fps = 60;
    double dropSpeed = (double) tileSize /5;
    Thread gameThread;
    char[][] gameState = new char[numOfRows][numOfColumns];
    Block currentBlock = new LineBlock(tileSize*3, tileSize*3);
    public GamePanel() {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(this);
        this.setLayout(new FlowLayout());
        this.setFocusable(true);
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        for (char[] chars : gameState) {
            Arrays.fill(chars, ' ');
        }
        gameThread.start();
    }

    public void update() {

    }
    @Override
    public void run() {
        double drawInterval = 1e9/fps;
        double lastTime = System.nanoTime();
        double delta = 0;

        while(gameThread != null) {
            double currentTime = System.nanoTime();
            delta += (currentTime-lastTime)/drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta = 0;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.dispose();

    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            currentBlock.changeState();
        }

    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int lastPos = currentBlock.posX;
        if(e.getKeyCode() == KeyEvent.VK_D) {
            currentBlock.posX += tileSize;
        }
        if(e.getKeyCode() == KeyEvent.VK_A) {
            currentBlock.posX -= tileSize;
        }
        if(currentBlock.posX < 0 || currentBlock.posX > width) {
            currentBlock.posX = lastPos;
        }

    }

}

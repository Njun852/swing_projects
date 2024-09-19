package main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel gPanel = new GamePanel();
        frame.add(gPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        gPanel.startGameThread();
    }
}

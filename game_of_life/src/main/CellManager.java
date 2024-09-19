package main;

import java.awt.*;
import java.util.ArrayList;

public class CellManager {
    boolean[] gameState;
    GamePanel gPanel;
    public CellManager(GamePanel gPanel) {
        this.gPanel = gPanel;
        gameState = new boolean[gPanel.column*gPanel.rows];
        generateStructure(getIndex(0, gPanel.rows/10), CellStructures.god);
        generateStructure(getIndex((int)(gPanel.column*0.88), gPanel.rows/10), CellStructures.god);
        generateStructure(getIndex(0, (int)(gPanel.rows*0.88)), CellStructures.god);
        generateStructure(getIndex((int)(gPanel.column*0.88),(int)(gPanel.rows*0.88)), CellStructures.god);
        generateStructure(getIndex((int)(gPanel.column*0.4), (int)(gPanel.rows*0.4)), CellStructures.mother);
//        randomState();
    }
    public void generateStructure(int index, int[] structure) {
        int[] pos = getXY(index);
        for(int i = 0; i < structure.length; i+=2) {
            int index1 = getIndex(pos[0]+structure[i], pos[1]+structure[i+1]);
            if(index1 < 0 || index1 > gameState.length-1) continue;
            gameState[index1] = true;
        }
    }
    public void randomState() {
        for(int i = 0; i < gameState.length; i++) {
            double chance = Math.random();
            gameState[i] = chance < 0.03;
            if(chance < 0.03) {
                generateStructure(i, CellStructures.spaceship);
            }
        }
    }
    public void updateState() {
        int[] directions = {1, 0, -1, 0, 0, 1, 0, -1, 1, 1, 1, -1, -1, 1, -1, -1};
        boolean[] newState = new boolean[gameState.length];
        for(int i = 0; i < gameState.length; i++) {
            int[] pos = getXY(i);
            int x = pos[0];
            int y = pos[1];
            int neighbors = 0;

            for(int j = 0; j < directions.length; j+=2) {
                int index = getIndex(x+directions[j], y+directions[j+1]);
                if(index < 0 || index > gameState.length-1) continue;
                if(gameState[index]) {
                    neighbors++;
                }
            }

            newState[i] = (gameState[i] && neighbors == 2)|| neighbors == 3;
        }
        gameState = newState;
    }
    private int[] getXY(int index) {
        int y = index/gPanel.column;
        int x = index - (gPanel.column*y);
        return new int[]{x, y};
    }
    private int getIndex(int x, int y) {
        return x + y * gPanel.column;
    }
    public void drawCells(Graphics2D g2d) {
        for(int i = 0; i < gameState.length; i++) {
            if(!gameState[i]) continue;
            g2d.setColor(Color.white);
            int[] pos = getXY(i);
            g2d.fillRect(pos[0]*gPanel.tileSize, pos[1]*gPanel.tileSize, gPanel.tileSize,
                    gPanel.tileSize);
        }
    }
}

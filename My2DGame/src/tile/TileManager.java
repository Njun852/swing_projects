package tile;

import entity.Player;
import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    public final Tile[] tile;
    public final int[][] mapTileNum;


    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        tile = new Tile[10]; //10 kinds of tile
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTileImage();
        getTileMap("/maps/world02.txt");
    }

    public void getTileImage() {
        try{
            tile[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png")));
            tile[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png")));
            tile[1].collision = true;
            tile[2] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/water.png")));
            tile[3] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png")));
            tile[4] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png")));
            tile[4].collision = true;
            tile[5] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getTileMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            for(int row = 0; row < gamePanel.maxWorldRow; row++) {
                String[] rowTypes = reader.readLine().split(" ");
                for(int col = 0; col < gamePanel.maxWorldCol; col++) {
                    mapTileNum[col][row] = Integer.parseInt(rowTypes[col]);
                }
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2d) {
        for(int col = 0; col < gamePanel.maxWorldCol; col++) {
            for(int row = 0; row < gamePanel.maxWorldRow; row++) {
                int worldX = col * gamePanel.tileSize;
                int worldY = row * gamePanel.tileSize;
                int screenX = worldX-gamePanel.player.worldX+gamePanel.player.screenX;
                int screenY = worldY-gamePanel.player.worldY+gamePanel.player.screenY;
                int tileType = mapTileNum[col][row];
                if(screenX < -gamePanel.tileSize || screenX > gamePanel.screenWidth+ gamePanel.tileSize) break;
                if(screenY < -gamePanel.tileSize || screenY > gamePanel.screenHeight+gamePanel.tileSize ) continue;
                g2d.drawImage(tile[tileType].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }
        }
    }
}

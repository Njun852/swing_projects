package tile;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[10]; //10 kinds of tile
        mapTileNum = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
        getTileImage();
        getTileMap("/maps/map01.txt");
    }

    public void getTileImage() {
        try{
            tile[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png")));
            tile[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png")));
            tile[2] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/water.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getTileMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            for(int row = 0; row < gamePanel.maxScreenRow; row++) {
                String[] rowTypes = reader.readLine().split(" ");
                for(int col = 0; col < gamePanel.maxScreenCol; col++) {
                    mapTileNum[col][row] = Integer.parseInt(rowTypes[col]);
                }
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2d) {
        for(int col = 0; col < gamePanel.maxScreenCol; col++) {
            for(int row = 0; row < gamePanel.maxScreenRow; row++) {
                int x = col * gamePanel.tileSize;
                int y = row * gamePanel.tileSize;
                int tileType = mapTileNum[col][row];
                g2d.drawImage(tile[tileType].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            }
        }

    }
}

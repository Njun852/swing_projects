package tile;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[10]; //10 kinds of tile
        getTileImage();
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

    public void draw(Graphics2D g2d) {
        g2d.drawImage(tile[0].image, 0, 0, gamePanel.tileSize, gamePanel.tileSize, null);
        g2d.drawImage(tile[1].image, gamePanel.tileSize, 0, gamePanel.tileSize, gamePanel.tileSize, null);
        g2d.drawImage(tile[0].image, 0, gamePanel.tileSize, gamePanel.tileSize, gamePanel.tileSize, null);
        g2d.drawImage(tile[2].image, gamePanel.tileSize*2, 0, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}

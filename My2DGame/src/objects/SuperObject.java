package objects;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY = 0;
    public int worldX, worldY;

    public void draw(Graphics2D g2d, GamePanel gp) {
        int x = worldX-gp.player.worldX+gp.player.screenX;
        int y = worldY-gp.player.worldY+gp.player.screenY;
        if(x < -gp.tileSize || x > gp.screenWidth+gp.tileSize ||
        y < -gp.tileSize || y > gp.screenHeight+gp.tileSize) return;
        g2d.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}

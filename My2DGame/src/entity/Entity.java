package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public BufferedImage up1, up2, right1, right2, left1, left2, down1, down2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
}

package entity;

import main.GamePanel;
import main.KeyHandler;
import tile.TileManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        screenX = gamePanel.screenWidth/2-(gamePanel.tileSize/2);
        screenY = gamePanel.screenHeight/2-(gamePanel.tileSize/2);
        solidArea = new Rectangle(8, 16, 32, 32);
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
       this.worldX = gamePanel.tileSize * 23;
       this.worldY = gamePanel.tileSize * 21;
        this.speed = 4;
        this.direction = "down";
    }

    public void update() {

        boolean keyIsPressed = keyHandler.downPressed || keyHandler.upPressed
                || keyHandler.rightPressed || keyHandler.leftPressed;

        if(!keyIsPressed) return;
        if(keyHandler.downPressed) {
            this.direction = "down";
        }
        if(keyHandler.rightPressed) {
            this.direction = "right";
        }
        if(keyHandler.leftPressed) {
            this.direction = "left";
        }
        if(keyHandler.upPressed) {
            this.direction = "up";
        }
        collisionOn = false;
        gamePanel.collisionChecker.checkTile(this);
        if(!collisionOn) {
            switch (direction) {
                case "up":
                    this.worldY -= this.speed;
                    break;
                case "down":
                    this.worldY += this.speed;
                    break;
                case "right":
                    this.worldX += this.speed;
                    break;
                case "left":
                    this.worldX -= this.speed;
                    break;
            }
        }
        spriteCounter++;
        if(spriteCounter > 10) {
            spriteNumber = spriteNumber == 2 ? 1 : 2;
            spriteCounter = 0;
        }
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
//      g2.setColor(Color.white);
//      g2.fillRect(this.x, this.y, gamePanel.tileSize, gamePanel.tileSize);
        BufferedImage image = null;
        switch (this.direction) {
            case "up":
                if(this.spriteNumber == 1) {
                    image = up1;
                }else {
                    image = up2;
                }
                break;
            case "down":
                if(this.spriteNumber == 1) {
                    image = down1;
                }else {
                    image = down2;
                }
                break;
            case "left":
                if(this.spriteNumber == 1) {
                    image = left1;
                }else{
                    image = left2;
                }
                break;
            case "right":
                if(this.spriteNumber == 1) {
                    image = right1;
                }else {
                    image = right2;
                    this.spriteCounter++;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}

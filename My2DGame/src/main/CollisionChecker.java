package main;

import entity.Entity;
import tile.TileManager;

import java.awt.*;

public class CollisionChecker {
    public GamePanel gamePanel;
    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void checkTile(Entity entity) {
        Rectangle solidArea = entity.solidArea;
        int entityWorldLeftX = entity.worldX + solidArea.x;
        int entityWorldRightX = entityWorldLeftX + solidArea.width;
        int entityWorldTopY = entity.worldY + solidArea.y;
        int entityWorldBottomY = entityWorldTopY + solidArea.height;

        int entityColLeft = entityWorldLeftX/gamePanel.tileSize;
        int entityColRight = entityWorldRightX/gamePanel.tileSize;
        int entityRowTop = entityWorldTopY/gamePanel.tileSize;
        int entityRowBottom = entityWorldBottomY/gamePanel.tileSize;

        int tileNum1, tileNum2;
        TileManager tileManager = gamePanel.tileManager;
        switch (entity.direction) {
            case "up":
                entityRowTop = (entityWorldTopY-entity.speed)/gamePanel.tileSize;
                tileNum1 = tileManager.mapTileNum[entityColLeft][entityRowTop];
                tileNum2 = tileManager.mapTileNum[entityColRight][entityRowTop];
                if(tileManager.tile[tileNum1].collision ||tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityRowBottom = (entityWorldBottomY+entity.speed)/gamePanel.tileSize;
                tileNum1 = tileManager.mapTileNum[entityColLeft][entityRowBottom];
                tileNum2 = tileManager.mapTileNum[entityColRight][entityRowBottom];
                if(tileManager.tile[tileNum1].collision || tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityColLeft = (entityWorldLeftX-entity.speed)/gamePanel.tileSize;
                tileNum1 = tileManager.mapTileNum[entityColLeft][entityRowBottom];
                tileNum2 = tileManager.mapTileNum[entityColLeft][entityRowTop];
                if(tileManager.tile[tileNum1].collision || tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityColRight = (entityWorldRightX+entity.speed)/gamePanel.tileSize;
                tileNum1 = tileManager.mapTileNum[entityColRight][entityRowTop];
                tileNum2 = tileManager.mapTileNum[entityColRight][entityRowBottom];
                if(tileManager.tile[tileNum1].collision || tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}

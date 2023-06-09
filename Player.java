package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    
    BufferedImage up1;
    
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 5;
        direction = "down";
    }
    
    public void update() {
        if(keyH.upPressed == true ||keyH.downPressed == true ||
                keyH.rightPressed == true ||keyH.leftPressed == true){
                    if(keyH.upPressed) {
            direction = "up";
            y -= speed;
        } else if(keyH.downPressed) {
            direction = "down";
            y += speed;
        } else if(keyH.leftPressed) {
            direction = "left";
            x -= speed;
        } else if(keyH.rightPressed) {
            direction = "right";
            x += speed;
        }
        spriteCounter ++;
        if(spriteCounter>12){
            if(spriteNum ==1){
            spriteNum = 2;
            }
            else if(spriteNum == 2){
            spriteNum = 1;
            }
            spriteCounter = 0;
        }
        
        }
    }
    
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch(direction) {
            case "up":
                if(spriteNum == 1){
                   image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                image = down1;
                }
                if(spriteNum == 2){
                image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                image = left1;
                }if(spriteNum == 2){
                image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                image = right1;
                }if(spriteNum == 2){
                image = right2;
                }
            default:
                break;
        }
        
        if(image != null) {
            g2.drawImage(image, x, y, gp.tileSize , gp.tileSize * 2,null);
        }
    }
}

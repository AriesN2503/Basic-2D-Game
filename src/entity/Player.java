package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import my2dgame.GamePanel;
import my2dgame.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;

        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Go_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Go_up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/res/player/Go_up3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/res/player/Go_up4.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Go_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Go_down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/res/player/Go_down3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/res/player/Go_down4.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Go_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Go_left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/player/Go_left3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/res/player/Go_left4.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Go_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Go_right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/player/Go_right3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/res/player/Go_right4.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        boolean isMoving = false;

        if (keyH.upPressed == true) {
            direction = "up";
            y -= speed;
            isMoving = true;
        } else if (keyH.downPressed == true) {
            direction = "down";
            y += speed;
            isMoving = true;
        } else if (keyH.leftPressed == true) {
            direction = "left";
            x -= speed;
            isMoving = true;
        } else if (keyH.rightPressed == true) {
            direction = "right";
            x += speed;
            isMoving = true;
        }

        spriteCounter++;

        if (isMoving) {
            spriteCounter++;
            if (spriteCounter > 20) {
                spriteNum++;
                if (spriteNum > 4) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            spriteNum = 0;
        }

        // System.out.println(spriteNum);
        // System.out.println(direction);
    }

    public void draw(Graphics2D g2) {
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage img = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    img = up1;
                } else if (spriteNum == 2) {
                    img = up2;
                } else if (spriteNum == 3) {
                    img = up3;
                } else if (spriteNum == 4) {
                    img = up4;
                } else {
                    img = up1;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    img = down1;
                } else if (spriteNum == 2) {
                    img = down2;
                } else if (spriteNum == 3) {
                    img = down3;
                } else if (spriteNum == 4) {
                    img = down4;
                } else {
                    img = down1;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    img = left1;
                } else if (spriteNum == 2) {
                    img = left2;
                } else if (spriteNum == 3) {
                    img = left3;
                } else if (spriteNum == 4) {
                    img = left4;
                } else {
                    img = left1;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    img = right1;
                } else if (spriteNum == 2) {
                    img = right2;
                } else if (spriteNum == 3) {
                    img = right3;
                } else if (spriteNum == 4) {
                    img = right4;
                } else {
                    img = right1;
                }
                break;
            default:
                img = down1;
                break;
        }
        if (img != null) {
            g2.drawImage(img, x, y, gp.tileSize, gp.tileSize, null);
        }
    }
}

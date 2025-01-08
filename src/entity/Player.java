package entity;

import my2dgame.GamePanel;
import my2dgame.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
    }


    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;
    }
}

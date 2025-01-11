package my2dgame;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Aries
 */
public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTING
    final int originalTileSize = 16; // Tile(ô lưới), a tile has a value of 16, mean that it have the width of 16p
                                     // and the height of 16px
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48px by now

    public final int maxScreenCol = 16; // have 16 tile for width
    public final int maxScreenRow = 12; // have 12 tile for height\
    public final int screenWidth = tileSize * maxScreenCol; // 768px
    public final int screenHeight = tileSize * maxScreenRow; // 576px

    // THREAD
    Thread gameThread;

    // KEY HANDLER
    KeyHandler keyH = new KeyHandler();

    Player player = new Player(this, keyH);

    TileManager tileManager = new TileManager(this);
    // // Set player's default position
    // int playerX = 100;
    // int playerY = 100;
    // int playerSpeed = 4; // px

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set size for this class (JPanel)
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // Turn on Double Buffer for drawing
        this.addKeyListener(keyH);
        this.setFocusable(true); // GamePanel can be focused to receivekey input
    }

    public void startGameThread() {
        gameThread = new Thread(this); // the 'this' mean that we passing GamePanel class to this Thread's constructor
        gameThread.start();
    }

    // FPS
    int FPS = 60;

    // LOOP
    // @Override
    // public void run() {

    // double drawInterval = 1000000000 / FPS; // 0.01666 seconds
    // double nextDrawTime = System.nanoTime() + drawInterval;

    // while (gameThread != null) {
    // long currentTime = System.nanoTime(); // basic it is a time in nanoseconds
    // System.err.println("Current Time is " + currentTime);

    // // 1 UPDATE: update character's positions
    // update();

    // // 2 DRAW: draw the screen with updated informatios.
    // repaint();// this is how you call the paintComponent() method

    // try {
    // double remainingTime = nextDrawTime - currentTime;
    // remainingTime = remainingTime / 1000000; // convert remainingTime from
    // nanoseconds to milliseconds

    // if (remainingTime < 0)
    // remainingTime = 0;

    // Thread.sleep((long) remainingTime);

    // nextDrawTime += drawInterval;

    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // }
    // }

    // This is the second method to loop the game
    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if (delta >= 1) {

                // 1 UPDATE: update character's positions
                update();

                // 2 DRAW: draw the screen with updated informatios.
                repaint();// this is how you call the paintComponent() method

                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {

        player.update();
    }

    public void paintComponent(Graphics g) { // paintComponent() ís a standard method in JPanel
        // Graphics is a class that has many functions to draw screen
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g; // typecast(ép kiểu), Graphics2D has more functions than the Graphics type
        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose(); // dispose() use to save memories,release any system resources that is using

    }
}

package my2dgame;

import javax.swing.JPanel;
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
    final int tileSize = originalTileSize * scale; // 48px by now

    final int maxScreenCol = 16; // have 16 tile for width
    final int maxScreenRow = 12; // have 12 tile for height\
    final int screenWidth = tileSize * maxScreenCol; // 768px
    final int screenHeight = tileSize * maxScreenRow; // 576px

    // THREAD
    Thread gameThread;

    // KEY HANDLER
    KeyHandler keyH = new KeyHandler();

    // Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 10; // px

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
    final int FPS = 60;

    // LOOP
    @Override
    public void run() {

        
        double drawInterval = 1000000000 / FPS; // 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            long currentTime = System.nanoTime(); // basic it is a time in nanoseconds
            System.err.println("Current Time is " + currentTime);

            // 1 UPDATE: update character's positions
            update();

            // 2 DRAW: draw the screen with updated informatios.
            repaint();// this is how you call the paintComponent() method

            try {
                double remainingTime = nextDrawTime - currentTime;
                remainingTime = remainingTime / 1000000; // convert remainingTime from nanoseconds to milliseconds

                if (remainingTime < 0)
                    remainingTime = 0;

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {

        if (keyH.upPressed == true) {
            playerY -= playerSpeed;
        } else if (keyH.downPressed == true) {
            playerY += playerSpeed;
        } else if (keyH.leftPressed == true) {
            playerX -= playerSpeed;
        } else if (keyH.rightPressed == true) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) { // paintComponent() ís a standard method in JPanel
        // Graphics is a class that has many functions to draw screen
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g; // typecast(ép kiểu), Graphics2D has more functions than the Graphics type
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose(); // dispose() use to save memories,release any system resources that is using

    }
}

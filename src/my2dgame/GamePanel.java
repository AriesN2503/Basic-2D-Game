package my2dgame;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

/**
 *
 * @author Aries
 */
public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTING
    final int originalTileSize = 16; // Tile(ô lưới), a tile has a value of 16, mean that it have the width of 16p
                                     //  and the height of 16px
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48px by now

    final int maxScreenCol = 16; // have 16 tile for width
    final int maxScreenRow = 12; // have 12 tile for height

    final int screenWidth = tileSize * maxScreenCol; // 768px
    final int screenHeight = tileSize * maxScreenRow; // 576px

    // THREAD
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set size for this class (JPanel)
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // Turn on Double Buffer for drawing
    }

    public void startGameThread() {
        gameThread = new Thread(this); // the 'this' mean that we passing GamePanel class to this Thread's constructor
        gameThread.start();
    }

    @Override
    public void run() {

    }
}

package tile;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import my2dgame.GamePanel;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("/res/maps/map_01.txt");
    }

    public void getTileImage() {
        try {

            tile[0] = new Tile();
            tile[0].img = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].img = ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].img = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tree.png"));

            tile[3] = new Tile();
            tile[3].img = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            if (is == null) {
                System.out.println("Map file not found!");
                return;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();
                if (line == null) {
                    break; // End of file
                }

                String numbers[] = line.split(" ");
                for (col = 0; col < gp.maxScreenCol; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                }
                row++;
                col = 0; // Reset column for the next row
            }
            br.close();

            // Debugging print statement
            System.out.println("Map loaded: ");
            for (int r = 0; r < gp.maxScreenRow; r++) {
                for (int c = 0; c < gp.maxScreenCol; c++) {
                    System.out.print(mapTileNum[c][r] + " ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        // CACH 1
        // int[][] map = { // Map layout as a 2D array of tile indexes
        // { 1, 1, 1, 1, 1 },
        // { 1, 0, 0, 0, 1 },
        // { 1, 0, 0, 0, 0 },
        // { 1, 0, 0, 0, 0 },
        // { 1, 2, 2, 1, 1 }
        // };

        // for (int row = 0; row < map.length; row++) {
        // for (int col = 0; col < map[row].length; col++) { // each row of the map can
        // have a different number of
        // // columns

        // int tileIndex = map[row][col];
        // g2.drawImage(tile[tileIndex].img, col * gp.tileSize, row * gp.tileSize,
        // gp.tileSize, gp.tileSize, null);
        // }
        // }

        // CACH 2
        // int col = 0, row = 0, x = 0, y = 0;

        // while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
        // g2.drawImage(tile[0].img, x, y, gp.tileSize, gp.tileSize, null);
        // col++;
        // x += gp.tileSize;

        // if(col == gp.maxScreenCol) {
        // col = 0;
        // x = 0;
        // row++;
        // y += gp.tileSize;
        // }
        // }

        // CACH 3
        int col = 0, row = 0, x = 0, y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].img, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}

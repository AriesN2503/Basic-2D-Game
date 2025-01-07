/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my2dgame;

import javax.swing.JFrame;

/**
 *
 * @author Aries
 */
public class My2DGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame window = new JFrame(); 
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //this to close window when user press 'X' on the top left conner
        
        window.setTitle("My First 2D Game"); //this is the title of the window (display at the top right conner)
        window.setResizable(false);
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack(); //set the size of the window base on the gamePanel size

        window.setLocationRelativeTo(null); //Not specify the location for the winfdow, and that make it at the center of the screen
        window.setVisible(true);

        gamePanel.startGameThread();
    }
    
}

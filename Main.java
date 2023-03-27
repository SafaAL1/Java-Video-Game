/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.JFrame;

/**
 *
 * @author Safa
 */
public class Main {
    public static void main(String[] args)
    {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        //this allows the user to close the window upon click X
        window.setResizable(false); 
        //this winow can not be resized
        window.setTitle("Rustic Row");
        // the name of the game for now
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        
        
        
        window.setLocationRelativeTo(null);
        //This will allow the windows to be displayed on the centre of the screen
        window.setVisible(true); 
        //So that the window will be able to show
        
        gamePanel.startGameThread();
    }
}

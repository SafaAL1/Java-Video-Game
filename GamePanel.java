/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import tile.TileManager;

/**
 *
 * @author Safa
 */
public class GamePanel extends JPanel implements Runnable{
//This class inhereits JPanel class   
//This class behaves such as a screen setting!
    final int originalTileSize = 32;
    final int scale = 3; //scaling so the tiles look bigger
   public  final int tileSize = originalTileSize*scale;//48x48
    public final int maxScreenCol = 32;
    public final int maxScreenRow = 24;
    final int screenWidth = tileSize * maxScreenCol;//2304 pixels
    final int screenHeight = tileSize * maxScreenRow;//1008 pixels
    //FPS
    int FPS = 100;
    TileManager tileM = new TileManager(this);
    
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,keyH);
    
    //set player's default position
    int playerX = 200;
    int playerY = 200;
    int playerSpeed = 4;
    public GamePanel() 
    {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
    
    }
    
    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() 
    {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while(gameThread != null) 
        {
            
            update();
            
            repaint();
           
            
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                
                if(remainingTime <0){
                remainingTime = 0;
                }
                
                Thread.sleep((long)(remainingTime));
                
                nextDrawTime += drawInterval;
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update() //this update method updates player's coordinates 
    {
      player.update();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);//draw tiles before player
        player.draw(g2);
        g2.dispose();
    }
}

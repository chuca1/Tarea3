/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

/**
 *
 * @author antoniomejorado
 */
public class Game implements Runnable {
    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private Player player;          // to use a player
    private KeyManager keyManager;  // to manage the keyboard
    private LinkedList <Enemy> lista;
    public int vidas;
    public int score;
    private LinkedList <Army> listaArmy;
    
    /**
     * to create title, width and height and set the game is still not running
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height  to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
    }

    /**
     * To get the width of the game window
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    /**
     * initializing the display window of the game
     */
    private void init() {
         display = new Display(title, getWidth(), getHeight());  
         Assets.init();
         lista = new LinkedList <Enemy>();
         int azar = ((int) Math.random() * 3) + 8;
         player = new Player(getWidth()/2 -50, getHeight()/2 - 50, 1, 100, 100, this);
         for(int i= 1; i<= azar; i++){
             Enemy enemy = new Enemy((int) (Math.random() * (getWidth() - 1000 - getWidth() + 101)) + getWidth() + 1000, 
                     (int) (Math.random() * getHeight()), 1, 100, 100, this);
             lista.add(enemy);
         }
         listaArmy = new LinkedList <Army>();
         int azarArmy = ((int) Math.random() * 6) + 10;
         for(int i= 1; i<= azarArmy; i++){
             Army army = new Army(((int) Math.random() * 901) - 100, 
                     (int) (Math.random() * getHeight()), 1, 100, 100, this);
             listaArmy.add(army);
         }
         display.getJframe().addKeyListener(keyManager);
         Assets.backSound.setLooping(true);
         Assets.backSound.play();
         int avidas = ((int) Math.random() * 3) + 3;
         vidas = avidas;
         score = 0;
    }
    
    
    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;
            
            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta --;
            }
        }
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }
   
    public void beep() {
        Assets.gunShot.play();
    }
    
    public void beepCollision(){
        Assets.collision.play();
    }
    
    public void beepCollisionArmy(){
        Assets.collisionArmy.play();
    }
    
    private void tick() {
        keyManager.tick();
        // avancing player with colision
        player.tick();
        for(Enemy enemy : lista) {
            enemy.tick();
            
            
            //if collision
            if(player.collision(enemy)) {
                this.vidas = this.vidas -1;
                beepCollision();
                if(this.vidas == 0){
                    this.stop();
                }
                enemy.setX((int) (Math.random() * (getWidth() - 1000 - getWidth() + 101)) + getWidth() + 1000);
                enemy.setY((int) (Math.random() * getHeight()));
                
            }
        }
        for(Army army : listaArmy){
            army.tick();
            
            if(player.collision(army)){
                this.score = this.score + 5;
                beepCollisionArmy();
            }
            //army.setX((int) ((int) Math.random() * 901) - 100);
            //army.setY((int) (Math.random() * getHeight()));
            
        }
    }
    
    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
        */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        }
        else
        {
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, width, height, null);
            player.render(g);
            for(Enemy enemy : lista) {
                enemy.render(g);
            }
            for(Army army : listaArmy) {
                army.render(g);
            }
            stringScore(g);
            bs.show();
            g.dispose();
        }
       
    }
    
    public void stringScore(Graphics g) {
        int fontSize = 20;

        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
     
        g.setColor(Color.red);
        
        //public string mensaje = "Vidas: " + vidas + " ";
        g.drawString("Vidas: ", 10, 20);
    }
    
    /**
     * setting the thead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    
    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }           
        }
    }

 
    


}

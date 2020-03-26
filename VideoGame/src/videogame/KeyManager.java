/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Diego Garza
 */
public class KeyManager implements KeyListener {

    public boolean up;      // flag to move up the player
    public boolean down;    // flag to move down the player
    public boolean left;    // flag to move left the player
    public boolean right;   // flag to move right the player
    public boolean pause;   // flag to pause the game
    public boolean save;    // flag to save the game
    public boolean load;    // flag to load the game

    private boolean keys[];  // to store all the flags for every key

    public KeyManager() {
        keys = new boolean[256];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // set true to every key pressed exept P
        if(e.getKeyCode()!=KeyEvent.VK_P){
            keys[e.getKeyCode()] = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released exept p
        
        if(e.getKeyCode()==KeyEvent.VK_P){
            // we do this in order to only react when its release
            keys[e.getKeyCode()] = !keys[e.getKeyCode()];
        }else{
            keys[e.getKeyCode()] = false;
        }
    }

    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        save = keys[KeyEvent.VK_G];
        load = keys[KeyEvent.VK_C];
        pause = keys[KeyEvent.VK_P];

    }
    //to use it when its pause
    public void pause() {
       pause = keys[KeyEvent.VK_P];
    }
}

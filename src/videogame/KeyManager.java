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
 * @author antoniomejorado
 */
public class KeyManager implements KeyListener {
    
    public boolean p;      // flag to move upright the player
    public boolean l;    // flag to move downright the player
    public boolean q;    // flag to move upleft the player
    public boolean a;   // flag to move downleft the player

    private boolean keys[];  // to store all the flags for every key
    
    public KeyManager() {
        keys = new boolean[256];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // set true to every key pressed
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        keys[e.getKeyCode()] = false;
    }
    
    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        p = keys[KeyEvent.VK_P];
        l = keys[KeyEvent.VK_L];
        q = keys[KeyEvent.VK_Q];
        a = keys[KeyEvent.VK_A];
    }
}

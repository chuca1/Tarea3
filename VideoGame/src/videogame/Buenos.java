/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author Diego Garza
 */
public class Buenos extends Item{
   /**
 *
 * crete direction and game
 */
    private int direction;
    private Game game;
    
     /**
 *
 * constructor
 */
    public Buenos(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
    }
 /**
 *
 * get direction
 */
    public int getDirection() {
        return direction;
    }

 /**
 *
 * set direction
 */
    public void setDirection(int direction) {
        this.direction = direction;
    }

 /**
 *
 * create tick
 */
    @Override
    public void tick() {
        
        int azar = (int)(Math.random() * ((3 - 1) + 1)) + 1;
        this.setX(this.getX() + azar);
        
        if (getX() + 60 >= game.getWidth()) {
            setX(0 - 60);
            setY((int) (Math.random() * game.getHeight()));
        }
    }
 /**
 *
 * draw image
 */
    
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.buenos, getX(), getY(), getWidth(), getHeight(), null);
    }
}

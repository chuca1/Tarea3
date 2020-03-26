/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author Diego Garza y Andrea Ramírez
 */
public class Enemy extends Item {

    private int direction;
    private Game game;
    private Animation animationLeft;    //to store the animation for going left
    
    /**
     * To build an enemy object
     * @param x an int value to get the x coordinate
     * @param y an int value to get the y coordinate
     * @param direction an int value to get the direction
     * @param width an int value to get the width
     * @param height an int value to get the height
     * @param game a game object to get outside elements
     */
    public Enemy(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        
        this.animationLeft = new Animation(Assets.enemyLeft, 100);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void tick() {

        int azar = (int) (Math.random() * ((5 - 3) + 1)) + 3;
        this.setX(this.getX() - azar);
        //updating animation
        this.animationLeft.tick();
        if (getX() < 0) {
            setX(game.getWidth() + 60);
            setY((int) (Math.random() * game.getHeight()));
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}

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
public class Player extends Item {

    private int direction;
    private int width;
    private int height;
    private Game game;
    
    //animation
    private Animation animationUp;  //to store the animation for going up
    private Animation animationLeft;    //to store the animation for going left
    private Animation animationDown;    //to store the animation for going down
    private Animation animationRight;   //to store the animation for going right
    private Animation animationStanding;//to store the animation for staying still

    /**
     * To build a Player object
     * @param x an int value to get the x coordinate
     * @param y an int value to get the y coordinate
     * @param direction an int value to get the direction
     * @param width an int value to get the width
     * @param height an int value to get the height
     * @param game a game object to get outside elements
     */
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, height, width);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        
        
        this.animationUp = new Animation(Assets.playerUp, 100);
        this.animationLeft = new Animation(Assets.playerLeft, 100);
        this.animationDown = new Animation(Assets.playerDown, 100);
        this.animationRight = new Animation(Assets.playerRight, 100);
        this.animationStanding = new Animation(Assets.playerStanding, 100);
    }

    public int getDirection() {
        return direction;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void tick() {
        // moving player depending on flags
        
        if (game.getKeyManager().up) {
           setY((getY() - 2)*direction);
           //updating animation
           this.animationUp.tick();
        }else if (game.getKeyManager().down) {
           setY((getY() + 2)*direction);
           //updating animation
           this.animationDown.tick();
        }else if (game.getKeyManager().left) {
           setX((getX() - 2)*direction);
           //updating animation
           this.animationLeft.tick();
        }else if (game.getKeyManager().right) {
            this.animationRight.tick();
            //updating animation
            setX((getX() + 2)*direction);
        }else{
            //updating animation
            this.animationStanding.tick();
        }

    }

    @Override
    public void render(Graphics g) {
        
        if (game.getKeyManager().up) {
           g.drawImage(animationUp.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }else if (game.getKeyManager().down) {
           g.drawImage(animationDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }else if (game.getKeyManager().left) {
           g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }else if (game.getKeyManager().right) {
            g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }else{
            g.drawImage(animationStanding.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
    }
}

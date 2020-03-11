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
public class Player extends Item {

    private int direction;
    private int width;
    private int height;
    private Game game;

    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, height, width);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
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
        if (game.getKeyManager().q) {
            setY(getY() - 1);
            setX(getX() - 1);

        }
        if (game.getKeyManager().a) {
            setX(getX() - 1);
            setY(getY() + 1);

        }
        if (game.getKeyManager().p) {

            setX(getX() + 1);
            setY(getY() - 1);

        }
        if (game.getKeyManager().l) {
            setY(getY() + 1);
            setX(getX() + 1);

        }
        // reset x position and y position if colision
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
    }
}

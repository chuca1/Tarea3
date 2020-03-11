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
public class Enemy extends Item {

    private int direction;
    private Game game;

    public Enemy(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
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
        if (getX() < 0) {
            setX(game.getWidth() + 60);
            setY((int) (Math.random() * game.getHeight()));
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enemy, getX(), getY(), getWidth(), getHeight(), null);
    }
}

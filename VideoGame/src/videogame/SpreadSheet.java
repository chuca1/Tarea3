/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author Diego Garza y Andrea Ramírez
 */
public class SpreadSheet {
    private BufferedImage sheet;    //to store the spritesheet
    
    /**
     * Create a new spreadsheet
     * @param sheet the image with the sprites
     */
    public SpreadSheet(BufferedImage sheet){
        this.sheet = sheet;
    }
    
    /**
     * Crop a sprite from the spreadsheet
     * @param x an int value with the x coordinate
     * @param y an int value with the y coordinate
     * @param width an int value with the width of the sprite
     * @param height an int value with the height of the sprite
     * @return 
     */
    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x,y,width,height);
    }
}
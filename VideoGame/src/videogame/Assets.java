/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package videogame;

import java.awt.image.BufferedImage;
/**
 *
 * @author Diego Garza
 */
public class Assets {

    /**
     * create background image
     */
    public static BufferedImage background; // to store background image

    /**
     * create player image
     */
    public static BufferedImage player;     // to store the player image

    /**
     * create enemy image
     */
    public static BufferedImage enemy;   

    /** 
    * create buenos image
     */
    public static BufferedImage buenos;

    /** 
     * create fin image
     */
    public static BufferedImage fin;

    /**
     * create backsoun image
     */
    public static SoundClip backSound;

    /**
     * create gunshot image
     */
    public static SoundClip gunShot;

    /**
     * create ow image
     */
    public static SoundClip ow;

    /**
     * create yey sound
     */
    public static SoundClip yey;
    
    
    //cambios de animation
    public static BufferedImage sprites;
    public static BufferedImage playerUp[];
    public static BufferedImage playerLeft[];
    public static BufferedImage playerDown[];
    public static BufferedImage playerRight[];

    /**
     * initializing the images and sounds of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/Background.jpg");
        player = ImageLoader.loadImage("/images/mario.png");
        enemy = ImageLoader.loadImage("/images/LBJ.png");
        buenos = ImageLoader.loadImage("/images/hongo.png");
        fin = ImageLoader.loadImage("/images/seacabo.jpg");
        backSound = new SoundClip("/sounds/back.wav");
        gunShot = new SoundClip("/sounds/Gunshot.wav");
        ow = new SoundClip("/sounds/preview.wav");
        yey = new SoundClip("/sounds/bueno.wav");
        
        sprites = ImageLoader.loadImage("/images/skull.png");
        SpreadSheet spreadsheet = new SpreadSheet(sprites);
        playerUp = new BufferedImage[9];
        playerLeft = new BufferedImage[9];
        playerDown = new BufferedImage[9];
        playerRight = new BufferedImage[9];
        for(int i = 0; i < 9; i++){
            playerUp[i] = spreadsheet.crop(i*64,0,64,64);
            playerLeft[i] = spreadsheet.crop(i*64,64,64,64);
            playerDown[i] = spreadsheet.crop(i*64,128,64,64);
            playerRight[i] = spreadsheet.crop(i*64,192,64,64);
        }
        
    }

}

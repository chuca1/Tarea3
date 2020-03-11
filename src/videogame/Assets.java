/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author antoniomejorado
 */
public class Assets {

    public static BufferedImage background; // to store background image
    public static BufferedImage player;     // to store the player image
    public static SoundClip backSound;
    public static SoundClip gunShot;
    public static BufferedImage enemy;      //to store the enemy image
    public static SoundClip collision;
    public static BufferedImage army;
    public static SoundClip collisionArmy;
    public static BufferedImage sprites;
    public static BufferedImage playerUp[];
    public static BufferedImage playerLeft[];
    public static BufferedImage playerDown[];
    public static BufferedImage playerRight[];
    
    
    
    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/Background.jpg");
        player = ImageLoader.loadImage("/images/mario.png");
        backSound = new SoundClip("/sounds/back.wav");
        gunShot = new SoundClip("/sounds/Gunshot.wav");
        enemy = ImageLoader.loadImage("/images/goomba.png");
        collision = new SoundClip("/sounds/collisionG.wav");
        army = ImageLoader.loadImage("/images/toad.png");
        collisionArmy = new SoundClip("/sounds/collisionArmy.wav");
        sprites = ImageLoader.loadImage("/images/skull.png");
        SpreadSheet spreadsheet = new SpreadSheet(sprites);
        playerUp = new BufferedImage[9];
        playerLeft = new BufferedImage[9];
        playerDown = new BufferedImage[9];
        playerRight = new BufferedImage[9];
        for(int i = 0; i < 9; i++){
            playerUp[i] = spreadsheet.crop(i*64,0, 64, 64);
            playerLeft[i] = spreadsheet.crop(i*64,64, 64, 64);
            playerDown[i] = spreadsheet.crop(i*64,128, 64, 64);
            playerRight[i] = spreadsheet.crop(i*64,192, 64, 64);
        }
    }

}

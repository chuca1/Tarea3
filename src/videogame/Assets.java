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
    }

}

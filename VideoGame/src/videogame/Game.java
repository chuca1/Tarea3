/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.LinkedList;
import static videogame.ReadandWrite.Load;
import static videogame.ReadandWrite.Saved;

/**
 *
 * @author Diego Garza
 */
public class Game implements Runnable {

    /**
     *
     * create variables
     */
    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private Player player;          // to use a player
    private KeyManager keyManager;  // to manage the keyboard
    private LinkedList<Enemy> lista;  //to manage enemys
    private LinkedList<Buenos> lista2;
    private int fontSize = 20;
    private String score = "0";
    private int vidaActual = 4;
    private String vidas;
    private boolean pause = false;

    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
    }

    /**
     *
     * get player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * To get the width of the game window
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * initializing the display window of the game
     */
    private void init() {

        vidas = Integer.toString((int) (Math.random() * ((5 - 3) + 1)) + 3);
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        player = new Player(getWidth() / 2 - 50, getHeight() / 2 - 50, 1, 100, 100, this);
        display.getJframe().addKeyListener(keyManager);
        lista = new LinkedList<Enemy>();
        lista2 = new LinkedList<Buenos>();
        int azar = (int) (Math.random() * ((10 - 8) + 1)) + 8;
        int azar2 = (int) (Math.random() * ((15 - 10) + 1)) + 10;
        Assets.backSound.setLooping(true);
        Assets.backSound.play();
        for (int i = 1; i <= azar; i++) {
            Enemy enemy = new Enemy(getWidth() + 100, (int) (Math.random() * getHeight()), 1, 100, 100, this);
            lista.add(enemy);
        }
        for (int i = 1; i <= azar2; i++) {
            Buenos buenos = new Buenos(getWidth() - (getWidth() + 100), (int) (Math.random() * getHeight()), 1, 70, 70, this);
            lista2.add(buenos);
        }
    }

    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;

            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }

        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    /**
     *
     * play yey sound
     */
    public void yey() {
        Assets.yey.play();
    }

    /**
     *
     * play ow sound
     */
    public void ow() {
        Assets.ow.play();
    }

    //esta funcion sirve para cargar el juego
    public void LoadP() {
        //abre el archivo
        ArrayList<String[]> datos = Load("juego.txt");
        //si hay algo vacia los buenos y los malos
        if (datos.size() > 0) {
            lista.clear();
            lista2.clear();
            //recorre el archivo
            for (int i = 0; i < datos.size(); i++) {
                //si es la linea de vidas cambia la vida y el score
                if ("V".equals(datos.get(i)[0])) {
                    vidas = datos.get(i)[1];
                    score = datos.get(i)[2];
                } //si es la linea de player cambia la poscicion
                else if ("P".equals(datos.get(i)[0])) {
                    player.setX(Integer.parseInt(datos.get(i)[1]));
                    player.setY(Integer.parseInt(datos.get(i)[2]));
                } //si es una linea de enemigo 
                else if ("E".equals(datos.get(i)[0])) {
                    lista.add(new Enemy(Integer.parseInt(datos.get(i)[1]), Integer.parseInt(datos.get(i)[2]), Integer.parseInt(datos.get(i)[3]),
                            Integer.parseInt(datos.get(i)[4]), Integer.parseInt(datos.get(i)[5]), this));
                } else if ("B".equals(datos.get(i)[0])) {
                    lista2.add(new Buenos(Integer.parseInt(datos.get(i)[1]), Integer.parseInt(datos.get(i)[2]), Integer.parseInt(datos.get(i)[3]),
                            Integer.parseInt(datos.get(i)[4]), Integer.parseInt(datos.get(i)[5]), this));
                }
            }
        }
    }

    public void SaveP() {
        //guardar juego
        Saved("Juego.txt", Integer.parseInt(vidas), Integer.parseInt(score), lista, lista2, player);
    }

    public void PressSave() {
        //mandar a llamar funcion
        if (keyManager.save) {
            SaveP();
        }
    }

    public void PressLoad() {
        //mandar a llamar cargado de juego
        if (keyManager.load) {
            LoadP();
        }
    }

    //poner pausa al juego
    public void PressPause() {
        //poner pausa
        if (keyManager.pause) {
            pause = true;
            Assets.backSound.play();
        } else {
            //quitar pausa
            pause = false;

        }

    }

    /**
     *
     * tick game
     */
    private void tick() {
        if (!pause) {
            keyManager.tick();
            // avancing player with colision
            player.tick();
            PressLoad();
            PressSave();
            PressPause();
            for (Enemy enemy : lista) {
                enemy.tick();
                if (player.collision(enemy)) {
                    enemy.setX(getWidth() + 100);
                    enemy.setY((int) (Math.random() * getHeight()));
                    ow();
                    if (vidaActual > 0) {
                        vidaActual--;
                    } else {
                        vidas = Integer.toString(Integer.parseInt(vidas) - 1);
                        vidaActual = 4;
                    }
                }
            }
            for (Buenos bueno : lista2) {
                bueno.tick();
                if (player.collision(bueno)) {
                    bueno.setX(-100);
                    bueno.setY((int) (Math.random() * getHeight()));
                    yey();
                    score = Integer.toString(Integer.parseInt(score) + 5);

                }
            }
            if (Integer.parseInt(vidas) <= 0) {
                render();
                running = false;
            }
        } //manter el boton de p funcionando
        else {
            keyManager.pause();
            PressPause();
        }

    }

    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, width, height, null);
            g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
            g.setColor(Color.red);
            g.drawString("Vidas " + vidas, 10, 20);
            g.drawString("Score " + score, 80, 20);
            player.render(g);
            for (Enemy enemy : lista) {
                enemy.render(g);
            }
            for (Buenos bueno : lista2) {
                bueno.render(g);
            }
            if (Integer.parseInt(vidas) <= 0) {
                g.drawImage(Assets.fin, 0, +30, width, height - 30, null);
                Assets.backSound.stop();
            }
            bs.show();
            g.dispose();
        }

    }

    /**
     * setting the thead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }

    }

    /**
     * stopping the thread
     */
    public synchronized void stop() {

        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

    }

}

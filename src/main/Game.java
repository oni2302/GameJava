package main;

import java.awt.Graphics;

import entities.Player;
import levels.LevelManager;
public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 144;
    private final int UPS_SET = 200;
    private Player player;
    private LevelManager levelManager;
    public final static int TILES_DEFAULT_SIZE =24;

    public final static float SCALE = 2f;
    public final static int TILES_IN_WIDTH = 24;
    public final static int TILES_IN_HEIGHT = 16;
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE*SCALE);
    public final static int GAME_WIDTH = TILES_SIZE*TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE*TILES_IN_HEIGHT;

    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();

    }

    private void initClasses() {
        levelManager = new LevelManager(this);
        player = new Player(200, 200,(int)(80*SCALE),(int)(80*SCALE));
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
    }

    public void update() {
        player.update();
        levelManager.update();
    }

    public void render(Graphics g) {
        levelManager.draw(g);
        player.render(g);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;// Nanoseconds
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long now;
        long lastFrame = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastCheck = System.nanoTime();
        long previousTime = System.nanoTime();
        double deltaU = 0;
        double deltaF = 0;
        while (true) {
            now = System.nanoTime();
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            // Cập nhật tính toán xử lí 200 lần mỗi giây
            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }
            // Vẽ lại khung hình sau xử lí 144 lần mỗi giây
            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }
            if (System.nanoTime() - lastCheck >= 1000000000) {
                lastCheck = System.nanoTime();
                frames = 0;    
                updates = 0;
            }

        }
    }
}

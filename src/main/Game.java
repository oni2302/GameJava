package main;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 144;
    private final int UPS_SET =200;

    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;// Nanoseconds
        double timePerUpdate = 1000000000.0/UPS_SET;
        long now;
        long lastFrame = System.nanoTime();
        int frames=0;
        int updates=0;
        long lastCheck = System.nanoTime();
        while (true) {
            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = now;
                frames++;
            }
            if (System.nanoTime() - lastCheck >= 1000000000) {
                lastCheck = System.nanoTime();
                gamePanel.updateFrameRate(frames);
                frames = 0;
            }

        }
    }
}

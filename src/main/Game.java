package main;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 144;
    private final int UPS_SET = 200;

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
            //Cập nhật tính toán xử lí 200 lần mỗi giây
            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }
            //Vẽ lại khung hình sau xử lí 144 lần mỗi giây
            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }
            if (System.nanoTime() - lastCheck >= 1000000000) {
                lastCheck = System.nanoTime();
                gamePanel.updateFrameRate(frames, updates);
                frames = 0;
                updates = 0;
            }

        }
    }
    public void update(){
        gamePanel.updateGame();
    }
}

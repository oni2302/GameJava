package main;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private KeyboardInputs keyboardInputs;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 10;
    private int playerAction = WALKING;
    private int playerDirection = 0;
    private boolean moving = false;
    private int xDelta = 200, yDelta = 200;
    private int frame = 0,update = 0;

    public GamePanel() {
        mouseInputs = new MouseInputs(this);
        keyboardInputs = new KeyboardInputs(this);

        importImg();
        loadAnimation();

        setPanelSize();
        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    //Hàm cập nhật game (xử lí tính toán)
    public void updateGame(){
        updateAnimationTick();
        updatePos();
        setAnimation();
    }

    public void updateFrameRate(int frame,int update) {
        this.frame = frame;
        this.update = update;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(animations[playerAction][aniIndex], xDelta, yDelta, 128, 128, null);
        g.drawString("FPS:" + frame+" UPS: "+update, xDelta, yDelta);
    }

    public void setAnimation() {
        if (!moving) {
            playerAction = IDLE;
        } else {
            playerAction = WALKING;
        }
    }

    public void setDirection(int direction) {
        playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void updatePos() {
        if (moving) {
            switch (playerDirection) {
                case LEFT:
                    xDelta -= 1;
                    break;
                case RIGHT:
                    xDelta += 1;
                    break;
                case UP:
                    yDelta -= 1;
                    break;
                case DOWN:
                    yDelta += 1;
                    break;

                default:
                    break;
            }
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
            }
        }
    }

    private void loadAnimation() {
        animations = new BufferedImage[3][12];
        for (int i = 0; i < animations.length; i++)
            for (int j = 0; j < animations[i].length; j++) {
                animations[i][j] = img.getSubimage(j * 32, i * 32, 32, 32);
            }
    }

    public void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void importImg() {
        File file = new File("res/player.png");
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
        }
    }

}

package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;
import javax.imageio.ImageIO;

public class Player extends Entity {
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 10;
    private int playerAction = WALKING;
    private int playerDirection = 0;
    private boolean moving = false;

    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update() {
        updateAnimationTick();
        setAnimation();
        updatePos();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int)x, (int)y, 128, 128, null);
    }

    private void loadAnimations() {
        File file = new File("res/player.png");
        try {
            BufferedImage img = ImageIO.read(file);
            animations = new BufferedImage[3][12];
            for (int i = 0; i < animations.length; i++)
                for (int j = 0; j < animations[i].length; j++) {
                    animations[i][j] = img.getSubimage(j * 32, i * 32, 32, 32);
                }
        } catch (IOException e) {
        }

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
                    x -= 1;
                    break;
                case RIGHT:
                    x += 1;
                    break;
                case UP:
                    y -= 1;
                    break;
                case DOWN:
                    y += 1;
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

}

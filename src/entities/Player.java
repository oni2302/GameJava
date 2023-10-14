package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static utilz.Constants.PlayerConstants.*;
import static utilz.LoadSave.*;

public class Player extends Entity {
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = WALKING;
    private int playerDirection = 0;
    private boolean moving = false;

    private boolean left, up, right, down;
    private float playerSpeed = 2.0f;

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
        g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, 128, 128, null);
    }

    private void loadAnimations() {
        BufferedImage img = GetSpriteAtlas(PLAYER_ATLAS);
        animations = new BufferedImage[3][8];
        for (int i = 0; i < animations.length; i++)
            for (int j = 0; j < animations[i].length; j++) {
                animations[i][j] = img.getSubimage(j * 58, i * 58, 58, 58);
            }

    }

    public void setAnimation() {
        int startAnim = playerAction;
        if (!moving) {
            playerAction = IDLE;
        } else {
            playerAction = WALKING;
        }
        if (startAnim != playerAction) {
            resetAnimIndex();
        }
    }

    private void resetAnimIndex() {
        aniIndex = 0;
        aniTick = 0;
    }

    private void updatePos() {
        moving = false;
        if (right && !left) {
            x += playerSpeed;
            moving = true;
        } else if (!right && left) {
            x -= playerSpeed;
            moving = true;
        }
        if (up && !down) {
            y -= playerSpeed;
            moving = true;
        } else if (!up && down) {
            y += playerSpeed;
            moving = true;
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

    public boolean isLeft() {
        return left;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isDown() {
        return down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}

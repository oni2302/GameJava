package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.HelpMethods;

import static utilz.Constants.PlayerConstants.*;
import static utilz.LoadSave.*;

public class Player extends Entity {
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 8;
    private int playerAction = WALKING;
    private int playerDirection = 0;
    private boolean moving = false;

    private boolean left, up, right, down;
    private float playerSpeed = 1.0f;

    private int[][] lvlData;
    private float xDrawOffset = 32 * Game.SCALE;
    private float yDrawOffset = 26 * Game.SCALE;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitBox(x, y, 16 * Game.SCALE, 32 * Game.SCALE);
        System.out.println(hitbox.x+" / "+hitbox.y);
        System.out.println(x+" / "+y);
    }

    public void update() {
        updateAnimationTick();
        setAnimation();
        updatePos();
        // updateHitbox();
    }

    public void render(Graphics g) {

        g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset),
                width, height, null);
        drawHitbox(g);
    }

    private void loadAnimations() {
        BufferedImage img = GetSpriteAtlas(PLAYER_RED_HOOD);
        animations = new BufferedImage[10][30];
        for (int i = 0; i < animations.length; i++)
            for (int j = 0; j < animations[i].length; j++) {
                animations[i][j] = img.getSubimage(j * 80, i * 80, 80, 80);
            }

    }

    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
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

        if (!left && !right && !up && !down) {
            return;
        }
        float xSpeed = 0, ySpeed = 0;

        if (right && !left) {
            xSpeed = playerSpeed;
            moving = true;
        } else if (!right && left) {
            xSpeed = -playerSpeed;
            moving = true;
        }
        if (up && !down) {
            ySpeed = -playerSpeed;
            moving = true;
        } else if (!up && down) {
            ySpeed = playerSpeed;
            moving = true;
        }
        // if (HelpMethods.CanMoveHere(xSpeed + x, ySpeed + y, width, height, lvlData)) {
        //     this.x += xSpeed;
        //     this.y += ySpeed;
        //     moving = true;
        // }
        if (HelpMethods.CanMoveHere(xSpeed + hitbox.x, ySpeed + hitbox.y, hitbox.width, hitbox.height, lvlData)) {
            hitbox.x += xSpeed;
            hitbox.y += ySpeed;
            moving = true;
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmountRedHood(playerAction)) {
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

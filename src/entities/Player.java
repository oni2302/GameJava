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

    private boolean left, up, right, down, jump;
    private float playerSpeed = 1.0f;

    private int[][] lvlData;
    private float xDrawOffset = 32 * Game.SCALE;
    private float yDrawOffset = 39 * Game.SCALE;
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitBox(x, y, 16 * Game.SCALE, 31 * Game.SCALE);
        
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
        if(!HelpMethods.IsEntityOnFloor(hitbox,lvlData)){
            inAir = true;
        }
    }

    public void setAnimation() {
        int startAnim = playerAction;
        if (!moving) {
            playerAction = IDLE;
        } else {
            playerAction = WALKING;
        }
        if(inAir){
            if(airSpeed<0){
                playerAction = JUMP;
            }else{
                playerAction = FALLING;
            }
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

        if (jump) {
            jump();
        }
        if (!left && !right && !inAir) {
            return;
        }
        float xSpeed = 0;
        if (left) {
            xSpeed -= playerSpeed;
        }
        if (right) {
            xSpeed += playerSpeed;
        }
        
        if (inAir) {
            if (HelpMethods.CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            } else {
                hitbox.y = HelpMethods.GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                if (airSpeed > 0) {
                    resetInAir();
                } else {
                    airSpeed = fallSpeedAfterCollision;
                }
                updateXPos(xSpeed);
            }

        } else {
            if(!HelpMethods.IsEntityOnFloor(hitbox, lvlData)){
                inAir=true;
            }
            updateXPos(xSpeed);
        }
        moving = true;
    }

    private void jump() {
        if (inAir) {
            return;
        }
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if (HelpMethods.CanMoveHere(xSpeed + hitbox.x, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
            hitbox.x += xSpeed;
            moving = true;
        } else {
            hitbox.x = HelpMethods.GetEntityXPosNextToWall(hitbox, xSpeed);
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

    public void setJump(boolean b) {
        jump = b;
    }
}


package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;

    public Entity(float x, float y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    protected void initHitBox(float x,float y,float w,float h) {
        this.hitbox = new Rectangle2D.Float(x, y, w, h);
    }
    protected void drawHitbox(Graphics g){
        g.setColor(Color.green);
        g.drawRect((int)hitbox.x, (int)hitbox.y, (int)hitbox.width, (int)hitbox.height);
    }
    // protected void updateHitbox() {
    //     hitbox.x = (int) x;
    //     hitbox.y = (int) y;
    // }
    public Rectangle2D.Float getHitbox(){
        return hitbox;
    }
}

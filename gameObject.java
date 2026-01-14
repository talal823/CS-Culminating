import java.awt.*;

public abstract class gameObject {
    protected Rectangle hitbox;
    protected int speed;

    public Rectangle getHitbox() 
    { 
        return hitbox; 
    }
    public abstract void update();
    public abstract void draw(Graphics g);
}

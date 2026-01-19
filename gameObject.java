import java.awt.*;

public abstract class gameObject {
    private  Rectangle hitbox;

    public Rectangle getHitbox() 
    { 
        return hitbox; 
    }
    public abstract void update();
    public abstract void draw(Graphics g);
}

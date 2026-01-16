import java.awt.*;

public final class Enemy extends gameObject{
    private int x;
    private int y;
    private final int width = 40;
    private final int height = 30;
    private final int speed;

    public Enemy(int x, int y, int speed) {
        this.setX(x);
        this.y = y;
        this.speed = speed;
    }

    public int getX() {
        return x;
        
    }

    public void setX(int x) {
        this.x = x;
        
    }

    public void update() { y += speed; }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(getX(), y, width, height);
    }

    public boolean collides(Player p) {
        Rectangle r1 = new Rectangle(getX(), y, width, height);
        Rectangle r2 = new Rectangle(p.getX(), p.getY(), p.getWidth(), p.getHeight());
        return r1.intersects(r2);
    }

    public int getY() { return y; }

    public int getHeight() {
        return height;
    }
}

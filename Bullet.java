import java.awt.*;

public class Bullet {

    private int x, y;
    private final int width = 4;
    private final int height = 10;
    private final int speed = 10;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        y -= speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
    }

    public boolean collides(Enemy e) {
        Rectangle r1 = new Rectangle(x, y, width, height);
        Rectangle r2 = new Rectangle(e.getX(), e.getY(), 40, 30);
        return r1.intersects(r2);
    }

    public int getY() { return y; }
    public int getX() { return x; }
}

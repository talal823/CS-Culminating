import java.awt.*;

public class Player {
    private int x, y, width = 50, height = 30;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void draw(Graphics g) {
        // DRAW IMAGE
        if (ImageManager.playerImg != null) {
            g.drawImage(ImageManager.playerImg, x, y, width, height, null);
        } 
        // FALLBACK: If image failed to load, draw a colored box
        else {
            g.setColor(Color.CYAN);
            g.fillRect(x, y, width, height);
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}

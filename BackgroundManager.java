import java.awt.*;
import java.util.Random;

public class BackgroundManager {

    private int type;
    private Random random = new Random();

    public BackgroundManager() {
        type = random.nextInt(1); // expand later
    }

    public void draw(Graphics g, int width, int height) {
        switch (type) {
            case 0 -> g.setColor(Color.BLACK);
        }
        g.fillRect(0, 0, width, height);
    }
}

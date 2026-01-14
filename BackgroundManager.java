import java.awt.*;
import java.util.Random;

public class BackgroundManager {
    private int type;
    private Random random = new Random();

    public BackgroundManager() {
        type = random.nextInt(3); // 0,1,2
    }

    public void draw(Graphics g, int width, int height) {
        switch (type) {
            case 0: g.setColor(Color.BLACK); break;
            //case 1: g.setColor(Color.DARK_GRAY); break;
            //case 2: g.setColor(Color.BLUE); break;
        }
        g.fillRect(0, 0, width, height);
    }
}

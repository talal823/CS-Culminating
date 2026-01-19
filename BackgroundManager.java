import java.awt.*;
import java.util.Random;

public class BackgroundManager {

    private int type;
    private Random random = new Random();

    public BackgroundManager() {
        type = random.nextInt(1);
    }

    public void draw(Graphics g, int width, int height) {
        // Draw image if available, otherwise fallback to black
        if (ImageManager.bgImg != null) {
             g.drawImage(ImageManager.bgImg, 0, 0, width, height, null);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, width, height);
        }
    }
}

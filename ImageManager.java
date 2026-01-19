import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageManager {

    // CHANGE THESE to match your exact filenames (case-sensitive!)
    public static final String PLAYER_FILE = "spaceship(2).jpg";
    public static final String ENEMY_FILE = "enemy(2).png";
    public static final String BACKGROUND_FILE = "background(1).jpg";

    public static Image playerImg;
    public static Image enemyImg;
    public static Image bgImg;

    static {
        try {
            // Using ImageIO.read ensures we get an IOException if the file is missing
            // This prevents "empty" images from being drawn
            playerImg = ImageIO.read(new File(PLAYER_FILE));
            enemyImg = ImageIO.read(new File(ENEMY_FILE));
            bgImg = ImageIO.read(new File(BACKGROUND_FILE));
        } catch (IOException e) {
            System.err.println("CRITICAL ERROR: Could not load an image.");
            System.err.println("Make sure image files are in the main project folder.");
            System.err.println("Error details: " + e.getMessage());
            // If loading fails, variables remain null, and the game will use colored squares instead.
        }
    }
}

import javax.swing.*;
import java.awt.*;

public class ImageManager {
    // For simplicity, currently we use colors in draw()
    // You can load images later:
    public static Image load(String path) {
        return new ImageIcon(path).getImage();
    }
}

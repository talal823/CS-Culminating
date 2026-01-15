import javax.swing.*;
import java.awt.*;

public class ImageManager {
    public static Image load(String path) {
        return new ImageIcon(path).getImage();
    }
}

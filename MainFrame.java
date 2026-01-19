import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Java Space Shooter");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Start with Login Panel
        setContentPane(new LoginPanel(this));
        setVisible(true);
    }

    // Helper to switch panels
    public void switchPanel(JPanel panel) {
        setContentPane(panel);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}

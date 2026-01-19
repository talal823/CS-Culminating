import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {

    private GameLogic logic;
    private Timer timer;
    private boolean isGuest;

    // Removed KeyManager from arguments because it is created here
    public GamePanel(MainFrame frame, String username, boolean isGuest) {
        this.isGuest = isGuest;

        setPreferredSize(new Dimension(800, 600));
        setFocusable(true);
        setBackground(Color.BLACK);

        // 1. Create Logic first (without KeyManager)
        logic = new GameLogic(username, isGuest);

        // 2. Create KeyManager passing Logic
        KeyManager keyManager = new KeyManager(logic);

        // 3. Connect KeyManager back to Logic
        logic.setKeyManager(keyManager);

        // 4. Add the listener
        addKeyListener(keyManager);

        // Game loop (≈60 FPS)
        timer = new Timer(16, this);
        timer.start();

        // Make sure panel gets focus
        SwingUtilities.invokeLater(this::requestFocusInWindow);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Background
        logic.getBackgroundManager().draw(g, getWidth(), getHeight());

        // Player
        logic.getPlayer().draw(g);

        // Bullets
        for (Bullet b : logic.getBullets()) {
            b.draw(g);
        }

        // Enemies
        for (Enemy e : logic.getEnemies()) {
            e.draw(g);
        }

        // HUD
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Score: " + logic.getScore(), 20, 30);

        if (!isGuest) {
            g.drawString("High Score: " + logic.getPersonalHighscore(), 20, 55);
        }

        // Game Over screen
        if (logic.isGameOver()) {
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME OVER", 260, 240);
        
            g.setFont(new Font("Arial", Font.PLAIN, 22));
            g.drawString("Your Score: " + logic.getScore(), 290, 290);
        
            if (!isGuest) {
                g.drawString("Personal High Score: " + logic.getPersonalHighscore(), 240, 325);
                g.drawString("Global High Score: " + logic.getGlobalHighscore(), 250, 360);
            } else {
                g.drawString("Global High Score: " + logic.getGlobalHighscore(), 270, 330);
            }
        
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.drawString("Press R to Restart", 300, 410);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!logic.isGameOver()) {
            logic.update();
        }
        repaint();
    }
}

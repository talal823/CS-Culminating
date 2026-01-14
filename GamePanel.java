import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener {
    private MainFrame frame;
    private GameLogic logic;
    private Timer timer;
    private String username;
    private boolean isGuest;

    public GamePanel(MainFrame frame, String username, boolean isGuest) {
        this.frame = frame;
        this.username = username;
        this.isGuest = isGuest;
    
        setFocusable(true);
        setBackground(Color.BLACK);
    
        logic = new GameLogic(username, isGuest);
    
        KeyManager km = new KeyManager(logic);
        addKeyListener(km);
    
        timer = new Timer(16, this);
        timer.start();
    
        SwingUtilities.invokeLater(() -> {
            requestFocusInWindow();
        });
    }
    

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw background
        logic.getBackgroundManager().draw(g, getWidth(), getHeight());

        // Draw player
        logic.getPlayer().draw(g);

        // Draw bullets
        for (Bullet b : logic.getBullets()) {
            b.draw(g);
        }

        // Draw enemies
        for (Enemy e : logic.getEnemies()) {
            e.draw(g);
        }

        // Draw HUD
        g.setColor(Color.WHITE);
        g.drawString("Score: " + logic.getScore(), 10, 20);
        if (!isGuest) {
            g.drawString("Personal Highscore: " + logic.getPersonalHighscore(), 10, 40);
        }

        // Game Over
        if (logic.isGameOver()) {
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("GAME OVER", getWidth() / 2 - 120, getHeight() / 2);
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.drawString("Press R to Restart", getWidth() / 2 - 90, getHeight() / 2 + 40);
            g.drawString("Global Highscore: " + FileManager.getGlobalHighscore(), getWidth() / 2 - 90, getHeight() / 2 + 70);
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

import java.awt.event.*;

public class KeyManager implements KeyListener {
    private GameLogic logic;
    private boolean shootingHeld = false;

    public KeyManager(GameLogic logic) {
        this.logic = logic;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) logic.moveLeft();
        if (key == KeyEvent.VK_RIGHT) logic.moveRight();
        if (key == KeyEvent.VK_SPACE) shootingHeld = true;
        if (key == KeyEvent.VK_R && logic.isGameOver()) {
            // restart game
            logic = new GameLogic("Guest", true); // For simplicity
        }
        // Continuous shooting
        if (shootingHeld) logic.shoot();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) shootingHeld = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}

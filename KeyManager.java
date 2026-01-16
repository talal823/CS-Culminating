import java.awt.event.*;

public class KeyManager implements KeyListener {

    private final GameLogic logic;

    public KeyManager(GameLogic logic) {
        this.logic = logic;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> logic.moveLeft();
            case KeyEvent.VK_RIGHT -> logic.moveRight();
            case KeyEvent.VK_SPACE -> logic.shoot();
            case KeyEvent.VK_R -> {
                if (logic.isGameOver()) logic.reset();
            }
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}

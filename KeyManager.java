import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private final GameLogic logic;

    // track which keys are currently pressed
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean spacePressed = false;

    public KeyManager(GameLogic logic) {
        this.logic = logic;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> leftPressed = true;
            case KeyEvent.VK_RIGHT -> rightPressed = true;
            case KeyEvent.VK_SPACE -> spacePressed = true;
            case KeyEvent.VK_R -> {
                if (logic.isGameOver()) logic.reset();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> leftPressed = false;
            case KeyEvent.VK_RIGHT -> rightPressed = false;
            case KeyEvent.VK_SPACE -> spacePressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    
    public void update() {
        if (leftPressed) logic.moveLeft();
        if (rightPressed) logic.moveRight();
        if (spacePressed) logic.shoot();
    }
}

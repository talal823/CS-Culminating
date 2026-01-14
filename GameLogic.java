import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameLogic {
    private Player player;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private BackgroundManager bgManager = new BackgroundManager();
    private Random random = new Random();
    private boolean gameOver = false;
    private int score = 0;
    private int frameCounter = 0;
    private int enemySpeed = 2;
    private int spawnRate = 25; // smaller = more frequent
    private String username;
    private boolean isGuest;

    public GameLogic(String username, boolean isGuest) {
        this.username = username;
        this.isGuest = isGuest;
        player = new Player(400, 500);
    }

    public void update() {
        frameCounter++;

        // Update score slowly for staying alive
        if (frameCounter % 10 == 0) {
            score += 1 + frameCounter / 1000; // slow then increasing
        }

        // Spawn enemies
        if (random.nextInt(spawnRate) == 0) {
            enemies.add(new Enemy(random.nextInt(760), -40, enemySpeed));
        }

        // Update bullets
        Iterator<Bullet> bulletIter = bullets.iterator();
        while (bulletIter.hasNext()) {
            Bullet b = bulletIter.next();
            b.update();
            if (b.getY() < 0) bulletIter.remove();
        }

        // Update enemies
        Iterator<Enemy> enemyIter = enemies.iterator();
        while (enemyIter.hasNext()) {
            Enemy e = enemyIter.next();
            e.update();
            if (e.getY() > 600) enemyIter.remove();
            if (e.collides(player)) gameOver();
        }

        // Check bullet-enemy collision
        bulletIter = bullets.iterator();
        while (bulletIter.hasNext()) {
            Bullet b = bulletIter.next();
            enemyIter = enemies.iterator();
            while (enemyIter.hasNext()) {
                Enemy e = enemyIter.next();
                if (b.collides(e)) {
                    bulletIter.remove();
                    enemyIter.remove();
                    score += 10;
                    break;
                }
            }
        }

        // Increase difficulty gradually
        if (frameCounter % 500 == 0 && enemySpeed < 10) {
            enemySpeed++;
            spawnRate = Math.max(20, spawnRate - 2);
        }
    }

    private void gameOver() {
        gameOver = true;
        if (!isGuest) {
            FileManager.saveScore(username, score);
        }
    }

    public Player getPlayer() { return player; }
    public ArrayList<Bullet> getBullets() { return bullets; }
    public ArrayList<Enemy> getEnemies() { return enemies; }
    public BackgroundManager getBackgroundManager() { return bgManager; }
    public int getScore() { return score; }
    public boolean isGameOver() { return gameOver; }
    public int getPersonalHighscore() { return FileManager.getPersonalHighscore(username); }

    // Shooting function called by KeyManager
    public void shoot() {
        bullets.add(new Bullet(player.getX() + player.getWidth()/2 - 2, player.getY()));
    }

    // Movement functions
    public void moveLeft() { if (player.getX() > 0) player.move(-20, 0); }
    public void moveRight() { if (player.getX() < 750) player.move(20, 0); }
}

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameLogic {

    // Core game objects
    private Player player;
    private ArrayList<Bullet> bullets;
    private ArrayList<Enemy> enemies;
    private BackgroundManager bgManager;

    // Utilities
    private Random random;
    private int globalHighscore;

    // Game state
    private boolean gameOver;
    private int score;
    private int personalHighscore;

    // Difficulty scaling
    private int frameCounter;
    private int enemySpeed;
    private int spawnRate;

    // Player info
    private String username;
    private boolean isGuest;

    // Shooting control
    private int shootCooldown;

    //constructor
    public GameLogic(String username, boolean isGuest) {
        this.username = username;
        this.isGuest = isGuest;

        player = new Player(400, 500);
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        bgManager = new BackgroundManager();
        random = new Random();
        // Load global highscore ONCE
        globalHighscore = FileManager.getGlobalHighscore();

        score = 0;
        frameCounter = 0;
        enemySpeed = 2;
        spawnRate = 25;
        shootCooldown = 0;
        gameOver = false;

        // Load personal highscore ONCE
        if (!isGuest) {
            personalHighscore = FileManager.getPersonalHighscore(username);
        } else {
            personalHighscore = 0;
        }
    }

    //update loop
    public void update() {
        frameCounter++;

        // Survival score (slow → faster)
        if (frameCounter % 10 == 0) {
            score += 1 + frameCounter / 1000;
        }

        // Enemy spawning
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
            if (e.collides(player)) {
                gameOver();
                return;
            }
        }

        // Bullet–enemy collision
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

        // Increase difficulty over time
        if (frameCounter % 500 == 0 && enemySpeed < 10) {
            enemySpeed++;
            spawnRate = Math.max(15, spawnRate - 2);
        }

        if (shootCooldown > 0) shootCooldown--;
    }

    //gameover screen
    private void gameOver() {
        gameOver = true;
    
        if (!isGuest) {
            FileManager.saveScore(username, score);
            personalHighscore = Math.max(personalHighscore, score);
        }
    
        // Refresh global highscore after saving
        globalHighscore = FileManager.getGlobalHighscore();
    }
    

    //actions
    public void shoot() {
        if (shootCooldown <= 0) {
            bullets.add(new Bullet(
                player.getX() + player.getWidth() / 2 - 2,
                player.getY()
            ));
            shootCooldown = 10; // fire rate
        }
    }

    public void moveLeft() {
        if (player.getX() > 0) {
            player.move(-8, 0);
        }
    }

    public void moveRight() {
        if (player.getX() < 750) {
            player.move(8, 0);
        }
    }

    //getters
    public Player getPlayer() {
        return player;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public BackgroundManager getBackgroundManager() {
        return bgManager;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getPersonalHighscore() {
        return personalHighscore;
    }
    public int getGlobalHighscore() {
        return globalHighscore;
    }
    

    public void reset() {
        bullets.clear();
        enemies.clear();
        score = 0;
        frameCounter = 0;
        enemySpeed = 2;
        spawnRate = 25;
        shootCooldown = 0;
        gameOver = false;
    }
}

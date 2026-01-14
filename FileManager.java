import java.io.*;
import java.util.*;

public class FileManager {
    private static final String USER_FILE = "users.txt";
    private static final String GLOBAL_FILE = "global.txt";

    // Save user scores: username password score
    public static boolean registerUser(String username, String password) {
        try {
            File file = new File(USER_FILE);
            if (!file.exists()) file.createNewFile();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.split(" ")[0].equals(username)) {
                    sc.close();
                    return false; // already exists
                }
            }
            sc.close();

            FileWriter fw = new FileWriter(file, true);
            fw.write(username + " " + password + " 0\n");
            fw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean verifyUser(String username, String password) {
        try {
            File file = new File(USER_FILE);
            if (!file.exists()) return false;
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(" ");
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    sc.close();
                    return true;
                }
            }
            sc.close();
        } catch (IOException e) { e.printStackTrace(); }
        return false;
    }

    public static void saveScore(String username, int score) {
        try {
            File file = new File(USER_FILE);
            if (!file.exists()) return;
            List<String> lines = new ArrayList<>();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(" ");
                if (parts[0].equals(username)) {
                    int oldScore = Integer.parseInt(parts[2]);
                    parts[2] = String.valueOf(Math.max(score, oldScore));
                    lines.add(parts[0] + " " + parts[1] + " " + parts[2]);
                } else {
                    lines.add(String.join(" ", parts));
                }
            }
            sc.close();
            FileWriter fw = new FileWriter(file);
            for (String line : lines) fw.write(line + "\n");
            fw.close();

            // Update global
            int globalHigh = getGlobalHighscore();
            if (score > globalHigh) {
                FileWriter gfw = new FileWriter(GLOBAL_FILE);
                gfw.write(String.valueOf(score));
                gfw.close();
            }

        } catch (IOException e) { e.printStackTrace(); }
    }

    public static int getPersonalHighscore(String username) {
        try {
            File file = new File(USER_FILE);
            if (!file.exists()) return 0;
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(" ");
                if (parts[0].equals(username)) {
                    sc.close();
                    return Integer.parseInt(parts[2]);
                }
            }
            sc.close();
        } catch (IOException e) { e.printStackTrace(); }
        return 0;
    }

    public static int getGlobalHighscore() {
        try {
            File file = new File(GLOBAL_FILE);
            if (!file.exists()) return 0;
            Scanner sc = new Scanner(file);
            int score = sc.nextInt();
            sc.close();
            return score;
        } catch (IOException e) { return 0; }
    }
}

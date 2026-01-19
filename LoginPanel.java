import java.awt.*;
import javax.swing.*;

public class LoginPanel extends JPanel {
    private MainFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    public LoginPanel(MainFrame frame) {
        this.frame = frame;
        setLayout(null);

        JLabel title = new JLabel("Space Shooter Login");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(250, 50, 300, 30);
        add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(200, 150, 100, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(300, 150, 200, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(200, 200, 100, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(300, 200, 200, 25);
        add(passwordField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(250, 270, 100, 30);
        loginBtn.addActionListener(e -> login());
        add(loginBtn);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(380, 270, 100, 30);
        registerBtn.addActionListener(e -> frame.switchPanel(new RegisterPanel(frame)));
        add(registerBtn);

        JButton guestBtn = new JButton("Play as Guest");
        guestBtn.setBounds(280, 330, 150, 30);
        guestBtn.addActionListener(e -> playGuest());
        add(guestBtn);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setBounds(200, 380, 400, 25);
        messageLabel.setForeground(Color.RED);
        add(messageLabel);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (FileManager.verifyUser(username, password)) {
            // Login success (GamePanel constructor now updated to match this)
            frame.switchPanel(new GamePanel(frame, username, false));
        } else {
            messageLabel.setText("Invalid username or password!");
        }
    }

    private void playGuest() {
        frame.switchPanel(new GamePanel(frame, "Guest", true));
    }
}

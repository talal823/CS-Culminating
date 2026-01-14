import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
    private MainFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    public RegisterPanel(MainFrame frame) {
        this.frame = frame;
        setLayout(null);

        JLabel title = new JLabel("Register New User");
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

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(250, 270, 100, 30);
        registerBtn.addActionListener(e -> registerUser());
        add(registerBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(380, 270, 100, 30);
        backBtn.addActionListener(e -> frame.switchPanel(new LoginPanel(frame)));
        add(backBtn);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setBounds(200, 320, 400, 25);
        messageLabel.setForeground(Color.RED);
        add(messageLabel);
    }

    private void registerUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (FileManager.registerUser(username, password)) {
            messageLabel.setForeground(Color.GREEN);
            messageLabel.setText("Registration successful! Go back to login.");
        } else {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Username already exists!");
        }
    }
}

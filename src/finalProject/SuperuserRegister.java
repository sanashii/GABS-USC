package finalProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SuperuserRegister {

    @SuppressWarnings("unused")
	SuperuserRegister(String username) {
        JFrame jFrame = new JFrame();

        // setting of default header w/ logo
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 177, 64, 255));
        headerPanel.setBounds(0, 0, 540, 80);
        headerPanel.setLayout(null);

        ImageIcon icon = new ImageIcon("src/resources/headerlogo.png");
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(icon);
        iconLabel.setHorizontalAlignment(JLabel.CENTER);
        iconLabel.setBounds(0, 0, 540, 80);

        headerPanel.add(iconLabel);

        JPanel jPanel = new JPanel();

        JLabel jLabel = new JLabel();

        JButton backButton = new JButton();
        backButton.setIcon(new ImageIcon("src/resources/previous.png"));
        backButton.setBounds(20, 20, 40, 40);
        backButton.setBorderPainted(false);
        backButton.addActionListener(e -> {
            jFrame.dispose();
            SuperuserGUI superUserMenu = new SuperuserGUI(username);
        });
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        headerPanel.add(backButton);

        jFrame.setTitle("GABS USC");
        jFrame.setSize(540, 810); // Android Screen Ratio
        jFrame.setLayout(null);
        jFrame.setResizable(false); // To Avoid changing the size of the screen
        jFrame.setLocationRelativeTo(null); // setting the JFrame to appear in the middle

        jFrame.getContentPane().setBackground(new Color(0, 177, 63));

        jPanel.setBackground(Color.white);
        jPanel.setBounds(80, 80, 350, 500);
        jPanel.setLayout(null);

        jLabel.setText("Superuser Registration");
        jLabel.setFont(new Font(null, Font.BOLD, 24));
        jLabel.setBounds(85, 20, 300, 50);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 80, 100, 30);
        JTextField emailTextField = new JTextField();
        emailTextField.setBounds(120, 80, 200, 30);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 130, 100, 30);
        JTextField usernameTextField = new JTextField();
        usernameTextField.setBounds(120, 130, 200, 30);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 180, 100, 30);
        JTextField passwordTextField = new JTextField();
        passwordTextField.setBounds(120, 180, 200, 30);

        JLabel retypedPasswordLabel = new JLabel("Re-enter password:");
        retypedPasswordLabel.setBounds(20, 230, 100, 30);
        JTextField retypedPasswordTextField = new JTextField();
        retypedPasswordTextField.setBounds(120, 230, 200, 30);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(120, 300, 100, 30);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailTextField.getText();
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();
                String retypedPassword = retypedPasswordTextField.getText();

                if (email.isEmpty() || username.isEmpty() || password.isEmpty() || retypedPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(jFrame, "Please fill in all fields.");
                    return;
                }

                if (!password.equals(retypedPassword)) {
                    JOptionPane.showMessageDialog(jFrame, "Passwords do not match.");
                    return;
                }
                
                String hashedPassword = PasswordHasher.hashPassword(password);
                
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                    String query = "SELECT * FROM users WHERE username = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, username);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(jFrame, "Username already exists.");
                        return;
                    }

                    String insertQuery = "INSERT INTO users (email, username, password) VALUES (?, ?, ?)";
                    PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                    insertStmt.setString(1, email);
                    insertStmt.setString(2, username);
                    insertStmt.setString(3, hashedPassword);
                    insertStmt.executeUpdate();
                    JOptionPane.showMessageDialog(jFrame, "Registration successful.");
                    jFrame.dispose();
                    @SuppressWarnings("unused")
					SuperuserGUI superUserMenu = new SuperuserGUI(username);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(jFrame, "An error occurred while registering.");
                }
            }
        });

        Border buttonBorder = BorderFactory.createLineBorder(new Color(29, 142, 0), 2, true);
        registerButton.setForeground(new Color(29, 142, 0));
        registerButton.setBackground(Color.white);
        registerButton.setBorder(buttonBorder);

        jPanel.add(jLabel);
        jPanel.add(emailLabel);
        jPanel.add(emailTextField);
        jPanel.add(usernameLabel);
        jPanel.add(usernameTextField);
        jPanel.add(passwordLabel);
        jPanel.add(passwordTextField);
        jPanel.add(retypedPasswordLabel);
        jPanel.add(retypedPasswordTextField);
        jPanel.add(registerButton);

        jFrame.add(headerPanel);
        jFrame.add(jPanel);
        jFrame.setVisible(true);        
    }
}

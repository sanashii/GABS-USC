package finalProject;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

public class ChangePassword { //TODO: experiment and fix
    @SuppressWarnings("unused")
	ChangePassword(String username) {
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
        JLabel passLabel = new JLabel();
        JLabel retypeLabel = new JLabel();

        JTextField enterNewPass = new JTextField();
        JTextField retypedPass = new JTextField();

        JButton changePassBtn = new JButton();
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

        jLabel.setText("Password Change");
        jLabel.setFont(new Font(null, Font.BOLD, 24));
        jLabel.setBounds(72, 50, 300, 100);

        passLabel.setText("Enter New Password:");
        passLabel.setFont(new Font(null, Font.PLAIN, 15));
        passLabel.setBounds(30, 110, 200, 100);
        enterNewPass.setBounds(30, 170, 290, 40);

        retypeLabel.setText("Re-enter New Password:");
        retypeLabel.setFont(new Font(null, Font.PLAIN, 15));
        retypeLabel.setBounds(30, 200, 200, 100);
        retypedPass.setBounds(30, 260, 290, 40);

        Border roundedBorder = BorderFactory.createCompoundBorder();
        changePassBtn.setBounds(100, 330, 150, 50);
        changePassBtn.setText("Change Password");
        changePassBtn.setFocusable(false);
        changePassBtn.setBackground(new Color(136, 191, 140, 255));
        changePassBtn.setBorder(roundedBorder);

        changePassBtn.addActionListener(e -> {
        	String newPassword = "";
            String retypedPassword = "";
        	newPassword = enterNewPass.getText();
            retypedPassword = retypedPass.getText();
            
            if (!newPassword.equals(retypedPassword)) { // checks if new pass & retyped pass matches
                JOptionPane.showMessageDialog(jFrame, "The new password and retyped password do not match!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String hashedPassword = PasswordHasher.hashPassword(newPassword);

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                connection.setAutoCommit(true);
                PreparedStatement statement = connection.prepareStatement("UPDATE users SET password = ? WHERE username = ?");
                statement.setString(1, hashedPassword);
                statement.setString(2, username);
                statement.executeUpdate();
                connection.close();
            } catch (ClassNotFoundException | SQLException e1) {
                e1.printStackTrace();
            }

            JOptionPane.showMessageDialog(jFrame, "Your password has been changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            SuperuserGUI superUserMenu = new SuperuserGUI(username);
            jFrame.dispose();
        });
        jPanel.add(jLabel);
        jPanel.add(passLabel);
        jPanel.add(enterNewPass);
        jPanel.add(retypeLabel);
        jPanel.add(retypedPass);
        jPanel.add(changePassBtn);

        jFrame.add(headerPanel);
        jFrame.add(jPanel);

        jFrame.setVisible(true);
    }
}

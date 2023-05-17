package finalProject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.Timer;

@SuppressWarnings("unused")
public class SuperuserGUI extends PasswordHasher{
    SuperuserGUI(String username) {
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
        jPanel.setBackground(Color.white);
        jPanel.setBounds(85, 80, 350, 580);
        jPanel.setLayout(null); 
        
        JLabel titleLabel = new JLabel("Superuser Menu");
        titleLabel.setBounds(75, 20, 400, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        jPanel.add(titleLabel);

        // add buttons with labels and set their positions
        JButton mapBtn = new JButton("USC Map Segment");
        mapBtn.setBounds(25, 100, 300, 50);
        mapBtn.setForeground(Color.white);
        mapBtn.setBackground(new Color(136, 191, 140, 255));
        mapBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MapSegmentGUI transpo = new MapSegmentGUI(username);
				jFrame.dispose();
			}
        });

        JButton transportBtn = new JButton("USC Transportation Segment");
        transportBtn.setBounds(25, 200, 300, 50);
        transportBtn.setForeground(Color.white);
        transportBtn.setBackground(new Color(136, 191, 140, 255));
        transportBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TranspoSegmentGUI transpo = new TranspoSegmentGUI(username);
				jFrame.dispose();
			}
        	
        });

        JButton addUserBtn = new JButton("Add a New Superuser");
        addUserBtn.setBounds(25, 300, 300, 50);
        addUserBtn.setForeground(Color.white);
        addUserBtn.setBackground(new Color(136, 191, 140, 255));
        addUserBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SuperuserRegister addSuperuser = new SuperuserRegister(username);
				jFrame.dispose();
			}
        });

        JButton changePassBtn = new JButton("Change Personal Password");
        changePassBtn.setBounds(25, 400, 300, 50);
        changePassBtn.setForeground(Color.white);
        changePassBtn.setBackground(new Color(136, 191, 140, 255));
        changePassBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChangePassword passChange = new ChangePassword(username);
				jFrame.dispose();
			}
        	
        });
 
        JPanel logoutBtnPanel = new JPanel();
        logoutBtnPanel.setBackground(Color.white);
        logoutBtnPanel.setBounds(425, 680, 100, 100);
        logoutBtnPanel.setLayout(null);

        ImageIcon logoutIcon = new ImageIcon("src/resources/logout.png");
        
        JButton deleteAccountBtn = new JButton("Delete Account");
        deleteAccountBtn.setBounds(100, 530, 150, 50);
        deleteAccountBtn.setForeground(Color.white);
        deleteAccountBtn.setBackground(Color.red);
        deleteAccountBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this account?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                        Statement stmt = conn.createStatement();
                        String query = "DELETE FROM users WHERE username='" + username + "'";
                        stmt.executeUpdate(query);
                        conn.close();
                        JOptionPane.showMessageDialog(null, "Account deleted successfully.");
                        jFrame.dispose();
                        AdminLoginGUI adminLogin = new AdminLoginGUI();
                    } catch (SQLException ex) {
                        System.out.println("SQLException: " + ex.getMessage());
                    }
                }
            }
        });

        // create a new button with the image for logout
        JButton logoutBtn = new JButton(logoutIcon);
        logoutBtn.setBounds(0, 0, 100, 100);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.setBorderPainted(false);


        logoutBtnPanel.add(logoutBtn);
        jPanel.add(deleteAccountBtn);
        logoutBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(jFrame, "Successfully logged out!");
				Timer timer = new Timer(2000, new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	AdminLoginGUI adminLogin = new AdminLoginGUI();
		                jFrame.dispose();
		            }
		        });
		        timer.setRepeats(false);
		        timer.start();
			}
			
		});
        jFrame.add(logoutBtnPanel);


        // add buttons to the jPanel panel
        jPanel.add(mapBtn);
        jPanel.add(transportBtn);
        jPanel.add(addUserBtn);
        jPanel.add(changePassBtn);
        jPanel.add(deleteAccountBtn);

        jFrame.getContentPane().setBackground(Color.white); // set main background to white

        jFrame.add(headerPanel); // add header panel to frame
        jFrame.add(jPanel);

        jFrame.setTitle("GABS USC");
        jFrame.setSize(540, 810); // Android Screen Ratio
        jFrame.setLayout(null);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null); // setting the JFrame to appear in the middle

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

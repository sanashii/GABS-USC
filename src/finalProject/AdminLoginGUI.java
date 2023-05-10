package finalProject;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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


public class AdminLoginGUI extends PasswordHasher{
	
	AdminLoginGUI(){
		
		JFrame jFrame = new JFrame();
		
		ImageIcon jIcon = new ImageIcon("src/resources/icon.png");
		ImageIcon jLock = new ImageIcon("src/resources/padlock.png");
		
		JPanel jPanel = new JPanel();
		
		JLabel jLabel = new JLabel();
		JLabel userLabel = new JLabel();
		JLabel passLabel = new JLabel();
		
		JTextField enterUser = new JTextField();
		JTextField enterPass = new JTextField();
		
		JButton loginBtn = new JButton();
		JButton loginPage = new JButton();
	
		
		jFrame.setTitle("GABS USC");
		jFrame.setSize(540,810); //Android Screen Ratio
		jFrame.setLayout(null);
		jFrame.setResizable(false); //To Avoid changing the size of the screen
		jFrame.setLocationRelativeTo(null); // setting the JFrame to appear in the middle
				
		jFrame.setIconImage(jIcon.getImage());//change image icon of frame
		jFrame.getContentPane().setBackground(new Color(0, 177, 63));
		
		jPanel.setBackground(Color.white);
		jPanel.setBounds(85,80,350,500);
		jPanel.setLayout(null);
		
		jLabel.setText("ADMIN LOGIN");
		jLabel.setFont(new Font(null, Font.BOLD, 24));
		jLabel.setBounds(90,50,300,100);
		
		userLabel.setText("Enter Username:");
		userLabel.setFont(new Font(null, Font.PLAIN, 15));
		userLabel.setBounds(30, 110, 200, 100);
		enterUser.setBounds(30,170,290,40);
		
		passLabel.setText("Enter Password:");
		passLabel.setFont(new Font(null, Font.PLAIN, 15));
		passLabel.setBounds(30, 200, 200, 100);
		enterPass.setBounds(30,260,290,40);
		
		Border roundedBorder = BorderFactory.createCompoundBorder();
		loginBtn.setBounds(100,330,150,50);
		loginBtn.setText("Login Admin");
		loginBtn.setFocusable(false);
		loginBtn.setForeground(Color.white);
		loginBtn.setBackground(new Color(136, 191, 140, 255));
		loginBtn.setBorder(roundedBorder);
		loginPage.setContentAreaFilled(false);
		loginPage.setBorderPainted(false);
		loginBtn.addActionListener(new ActionListener() {
		    @SuppressWarnings("unused")
			@Override
		    public void actionPerformed(ActionEvent e) {
		        String username = enterUser.getText();
		        String password = enterPass.getText();
		        String hashedPass = hashPassword(password); // hash the password they input & compare it with a hashed password from the database
		        
		        try {
		            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
		            
		            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
		            statement.setString(1, username);
		            statement.setString(2, hashedPass);
		            ResultSet resultSet = statement.executeQuery();
		            
		            if (resultSet.next()) {
		                SuperuserGUI superUserMenu = new SuperuserGUI(username);
		                jFrame.dispose();
		            } else {
		            	JOptionPane.showMessageDialog(jFrame, "Invalid username or password. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
		            }
		            
		            resultSet.close();
		            statement.close();
		            connection.close();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});
		
		JLabel forgotPasswordLabel = new JLabel("<html><u>Forgot Password? Click me!</u></html>");
		forgotPasswordLabel.setBounds(100, 420, 200, 50);
		forgotPasswordLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		forgotPasswordLabel.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		    	String username = enterUser.getText();
		    	try {
		            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
		            
		            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
		            statement.setString(1, username);
		            ResultSet resultSet = statement.executeQuery();
		            
		            if (resultSet.next()) {
		                ChangePassword passChangePage = new ChangePassword(username, "update");
		                jFrame.dispose();
		            } else {
		            	JOptionPane.showMessageDialog(jFrame, "User does not exist!", "Login Error", JOptionPane.ERROR_MESSAGE);
		            }
		            
		            resultSet.close();
		            statement.close();
		            connection.close();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    	
		    }
		});
		
		loginPage.setBounds(420,670,62,62);
		loginPage.setIcon(jLock);
		loginPage.setBackground(jFrame.getBackground());
		loginPage.setBorderPainted(false);
		loginPage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				IntroGUI introBtn = new IntroGUI();
				jFrame.dispose();
				
			}
			
		});
		
		jPanel.add(jLabel);
		jPanel.add(userLabel); jPanel.add(enterUser);
		jPanel.add(passLabel); jPanel.add(enterPass);
		jPanel.add(loginBtn);
		jPanel.add(forgotPasswordLabel);
		
		jFrame.add(jPanel);
		jFrame.add(loginPage);
		
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}


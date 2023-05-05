package finalProject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

@SuppressWarnings("unused")
public class SuperuserGUI extends PasswordHasher{
    SuperuserGUI() {
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
        jPanel.setBounds(85, 80, 350, 500);
        jPanel.setLayout(null);

        // add buttons with labels and set their positions
        JButton mapBtn = new JButton("USC Map Segment");
        mapBtn.setBounds(25, 50, 300, 50);
        mapBtn.setForeground(Color.white);
        mapBtn.setBackground(new Color(136, 191, 140, 255));

        JButton transportBtn = new JButton("USC Transportation Segment");
        transportBtn.setBounds(25, 125, 300, 50);
        transportBtn.setForeground(Color.white);
        transportBtn.setBackground(new Color(136, 191, 140, 255));

        JButton addUserBtn = new JButton("Add a New Superuser");
        addUserBtn.setBounds(25, 200, 300, 50);
        addUserBtn.setForeground(Color.white);
        addUserBtn.setBackground(new Color(136, 191, 140, 255));

        JButton changePassBtn = new JButton("Change Personal Password");
        changePassBtn.setBounds(25, 275, 300, 50);
        changePassBtn.setForeground(Color.white);
        changePassBtn.setBackground(new Color(136, 191, 140, 255));
        
        JPanel logoutBtnPanel = new JPanel();
        logoutBtnPanel.setBackground(Color.white);
        logoutBtnPanel.setBounds(435, 580, 100, 100);
        logoutBtnPanel.setLayout(null);

        ImageIcon logoutIcon = new ImageIcon("src/resources/logout.png");

        // create a new button with the image for logout
        JButton logoutBtn = new JButton(logoutIcon);
        logoutBtn.setBounds(0, 0, 100, 100);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.setBorderPainted(false);


        logoutBtnPanel.add(logoutBtn);
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

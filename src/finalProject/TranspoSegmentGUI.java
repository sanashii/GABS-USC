package finalProject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TranspoSegmentGUI {
	@SuppressWarnings("unused")
	TranspoSegmentGUI(String username){
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
        jPanel.setBounds(85, 83, 350, 500);
        jPanel.setLayout(null); 
        
        JLabel titleLabel = new JLabel("USC Transportation Segment");
        titleLabel.setBounds(20, 20, 400, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        jPanel.add(titleLabel);

        JButton addRouteButton = new JButton("Add New Route");
        addRouteButton.setBounds(25, 100, 300, 50);
        addRouteButton.setForeground(Color.white);
        addRouteButton.setBackground(new Color(136, 191, 140, 255));
        addRouteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addRouteButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        addRouteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					AddRouteGUI newRoute =  new AddRouteGUI(username);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				jFrame.dispose();
			}
        	
        });
        jPanel.add(addRouteButton);

        JButton editRouteButton = new JButton("Edit Route");
        editRouteButton.setBounds(25, 200, 300, 50);
        editRouteButton.setForeground(Color.white);
        editRouteButton.setBackground(new Color(136, 191, 140, 255));
        editRouteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        editRouteButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        editRouteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					EditRouteGUI newRoute =  new EditRouteGUI(username);
				} catch (FileNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				jFrame.dispose();
			}
        });
        jPanel.add(editRouteButton);

        JButton deleteRouteButton = new JButton("Delete Route");
        deleteRouteButton.setBounds(25, 300, 300, 50);
        deleteRouteButton.setForeground(Color.white);
        deleteRouteButton.setBackground(new Color(136, 191, 140, 255));
        deleteRouteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteRouteButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        deleteRouteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DeleteRouteGUI newRoute =  new DeleteRouteGUI(username);
				} catch (FileNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				jFrame.dispose();
			}
        });
        jPanel.add(deleteRouteButton);

        JButton updateFaresButton = new JButton("Update Fares");
        updateFaresButton.setBounds(25, 400, 300, 50);
        updateFaresButton.setForeground(Color.white);
        updateFaresButton.setBackground(new Color(136, 191, 140, 255));
        updateFaresButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateFaresButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        updateFaresButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UpdateFaresGUI newRoute =  new UpdateFaresGUI(username);
				jFrame.dispose();
			}
        	
        });
        jPanel.add(updateFaresButton);
        
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

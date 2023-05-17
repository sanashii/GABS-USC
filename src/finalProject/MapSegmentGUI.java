package finalProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MapSegmentGUI {
	@SuppressWarnings("unused")
	MapSegmentGUI(String username){
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
        headerPanel.add(iconLabel);


        JPanel jPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.white);
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.white);
        jPanel.setBackground(Color.white);
        jPanel.setBounds(85, 80, 350, 680);
        jPanel.setLayout(null); 
        
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel("USC Map Segment");
        titleLabel.setBounds(75, 20, 400, 90);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        int topPadding = 20;
        int leftPadding = 20;
        int bottomPadding = 50;
        int rightPadding = 20;
        titleLabel.setBorder(BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));
        jPanel.add(titleLabel);
        
        
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        // add buttons with labels and set their positions
        JButton addOfficeBtn = new JButton("Add Office");
        addOfficeBtn.setForeground(Color.white);
        addOfficeBtn.setBackground(new Color(136, 191, 140, 255));
        addOfficeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				AddOfficeGUI addOffice = new AddOfficeGUI(username);
				jFrame.dispose();
			}
        });

        JButton editOfficeBtn = new JButton("Edit Office");
        editOfficeBtn.setForeground(Color.white);
        editOfficeBtn.setBackground(new Color(136, 191, 140, 255));
        editOfficeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditOfficeGUI addOffice = new EditOfficeGUI(username);
				jFrame.dispose();
			}
        	
        });

        JButton deleteOfficeBtn = new JButton("Delete Office");
        deleteOfficeBtn.setForeground(Color.white);
        deleteOfficeBtn.setBackground(Color.red);
        deleteOfficeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteOfficeGUI addOffice = new DeleteOfficeGUI(username);
				jFrame.dispose();
			}
        });
 

        JButton addCanteenBtn = new JButton("Add Canteen");
        addCanteenBtn.setForeground(Color.white);
        addCanteenBtn.setBackground(new Color(136, 191, 140, 255));
        addCanteenBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddCanteenGUI addCanteen = new AddCanteenGUI(username);
				jFrame.dispose();
			}
        	
        });
        
        JButton addStallBtn = new JButton("Add Stall");
        addStallBtn.setForeground(Color.white);
        addStallBtn.setBackground(new Color(136, 191, 140, 255));
        addStallBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddStallGUI newStall = new AddStallGUI(username);
				jFrame.dispose();
			}
        });
        
        JButton editStallBtn = new JButton("Edit Stall");
        editStallBtn.setForeground(Color.white);
        editStallBtn.setBackground(new Color(136, 191, 140, 255));
        editStallBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditStallGUI editStall = new EditStallGUI(username);
				jFrame.dispose();
			}
        });
        
        JButton deleteStallBtn = new JButton("Delete Stall");
        deleteStallBtn.setForeground(Color.white);
        deleteStallBtn.setBackground(Color.red);
        deleteStallBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteStallGUI deleteStall = new DeleteStallGUI(username);
				jFrame.dispose();
			}
        });
        
        addOfficeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        editOfficeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteOfficeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addCanteenBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addStallBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        editStallBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteStallBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add buttons to leftPanel
        leftPanel.add(addOfficeBtn);
        leftPanel.add(Box.createVerticalStrut(50));
        leftPanel.add(editOfficeBtn);
        leftPanel.add(Box.createVerticalStrut(50));
        leftPanel.add(deleteOfficeBtn);

        // Add buttons to rightPanel
        rightPanel.add(addCanteenBtn);
        rightPanel.add(Box.createVerticalStrut(50));
        rightPanel.add(addStallBtn);
        rightPanel.add(Box.createVerticalStrut(50));
        rightPanel.add(editStallBtn);
        rightPanel.add(Box.createVerticalStrut(50));
        rightPanel.add(deleteStallBtn);
        

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(new BorderLayout());

        // Add leftPanel and rightPanel to mainPanel
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        // Add mainPanel to jPanel
        jPanel.add(mainPanel);

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

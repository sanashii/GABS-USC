package finalProject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        jPanel.setBackground(Color.white);
        jPanel.setBounds(85, 80, 350, 680);
        jPanel.setLayout(null); 
        
        JLabel titleLabel = new JLabel("USC Map Segment");
        titleLabel.setBounds(75, 20, 400, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        jPanel.add(titleLabel);

        // add buttons with labels and set their positions
        JButton addOfficeBtn = new JButton("Add Office");
        addOfficeBtn.setBounds(25, 80, 300, 50);
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
        editOfficeBtn.setBounds(25, 160, 300, 50);
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
        deleteOfficeBtn.setBounds(25, 240, 300, 50);
        deleteOfficeBtn.setForeground(Color.white);
        deleteOfficeBtn.setBackground(new Color(136, 191, 140, 255));
        deleteOfficeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteOfficeGUI addOffice = new DeleteOfficeGUI(username);
				jFrame.dispose();
			}
        });
 

        JButton addCanteenBtn = new JButton("Add Canteen");
        addCanteenBtn.setBounds(25, 340, 300, 50);
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
        addStallBtn.setBounds(25, 420, 300, 50);
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
        editStallBtn.setBounds(25, 500, 300, 50);
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
        deleteStallBtn.setBounds(25, 580, 300, 50);
        deleteStallBtn.setForeground(Color.white);
        deleteStallBtn.setBackground(new Color(136, 191, 140, 255));
        deleteStallBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteStallGUI deleteStall = new DeleteStallGUI(username);
				jFrame.dispose();
			}
        });
        
        jPanel.add(addOfficeBtn);
        jPanel.add(editOfficeBtn);
        jPanel.add(deleteOfficeBtn);
        jPanel.add(addCanteenBtn);
        jPanel.add(addStallBtn);
        jPanel.add(editStallBtn);
        jPanel.add(deleteStallBtn);

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

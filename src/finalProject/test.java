package finalProject;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.Border;

public class test {
    private JPanel sidebar;
    private boolean sidebarOpen = false;
    Map<String, Integer> routeNames;

    test(){
    	
    	JButton dropOpt1 = new JButton();
    	JPanel dropdownPanel1 = new JPanel();
    	
    	JButton dropOpt2 = new JButton();
    	JPanel dropdownPanel2 = new JPanel();

    	JButton dropOpt3 = new JButton();
    	JPanel dropdownPanel3 = new JPanel();

    	JButton dropOpt4 = new JButton();
    	JPanel dropdownPanel4 = new JPanel();
    	
    	JButton dropOpt5 = new JButton();
    	JPanel dropdownPanel5 = new JPanel();
    	
    	JButton dropOpt6 = new JButton();
    	JPanel dropdownPanel6 = new JPanel();
    	
    	JButton dropOpt7 = new JButton();
    	JPanel dropdownPanel7 = new JPanel();

    	JButton dropOpt8 = new JButton();
    	JPanel dropdownPanel8 = new JPanel();

    	
        JFrame jFrame = new JFrame();
        
        ImageIcon jIcon = new ImageIcon("src/resources/name.png");
        ImageIcon exitIcon = new ImageIcon("src/resources/exit.png");
        ImageIcon sideBar = new ImageIcon("src/resources/menu2.png");
        ImageIcon ayalaTtoTC = new ImageIcon("src/resources/routes/Ayala Terminal to TC.png");
        
        Image originalImage = jIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(130,94, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        Image ogSide = sideBar.getImage();
        Image scaledSide = ogSide.getScaledInstance(60,60, Image.SCALE_SMOOTH);
        ImageIcon scSide = new ImageIcon(scaledSide);

        JLabel jLabel = new JLabel();
        JButton jButton = new JButton();
        JPanel jPanel = new JPanel();
        sidebar = new JPanel();

        jFrame.setTitle("GABS USC");
        jFrame.setSize(540,810);
        jFrame.setLayout(null);
        jFrame.setLocationRelativeTo(null);//This will position the JFrame in the same place of the introGUI
        jFrame.setResizable(false);

        jFrame.setIconImage(jIcon.getImage());
        jFrame.getContentPane().setBackground(Color.WHITE);

        jPanel.setBackground(new Color(0, 177, 63));
        jPanel.setBounds(0,0,540,80);
        jPanel.setLayout(null);

        jLabel.setIcon(scaledIcon);
        jLabel.setBounds(190, -14, scaledIcon.getIconWidth(), scaledIcon.getIconHeight());
        jButton.setFocusable(false);
        jButton.setIcon(scSide);
        jButton.setBackground(new Color(0, 177, 63));
        jButton.setBounds(0,0,80,80);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleSidebar();
            }
        });

        
        sidebar.setBackground(new Color(118, 212, 152));
        sidebar.setBounds(-250, 80, 190, 730);
        
        sidebar.setLayout(null);
        
        JButton button1 = new JButton("USC TC: Map Guide");
        button1.setBounds(0,0,190,50);
        button1.setBorder(null);
        button1.setBackground(new Color(118, 212, 152));
        button1.setFocusable(false);
        button1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        
        button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MapPage toMap = new MapPage();
				jFrame.dispose();
			}
		});
        
        JButton button2 = new JButton("Transportation Guide");
        button2.setBounds(0,50,190,50);
        button2.setBorder(null);
        button2.setBackground(new Color(118, 212, 152));
        button2.setFocusable(false);
        button2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        JFrame frame = new JFrame("Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.add(button2);
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("To USC TC");
        JMenuItem menuItem2 = new JMenuItem("From USC TC");
        popupMenu.add(menuItem1);
        popupMenu.add(menuItem2);
        popupMenu.setPopupSize(190, 150);
        menuItem1.setBackground(new Color(118, 212, 152));
        menuItem2.setBackground(new Color(118, 212, 152));
        popupMenu.setBackground(new Color(118, 212, 152));
        button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				  popupMenu.show(button2, 0, button2.getHeight());
			}
		});
        menuItem1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 TranspoPageTo to = new TranspoPageTo();
				  jFrame.dispose();
			}
		});
        menuItem2	.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 TranspoPageFrom from = new TranspoPageFrom();
				  jFrame.dispose();
			}
		});
        JButton button3 = new JButton();
        button3.setIcon(exitIcon);
        button3.setBounds(20,630,40,40);
        button3.setBorder(null);
        
        button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				IntroGUI toIntro = new IntroGUI();
				jFrame.dispose();
			}
		});
//        button3.setFocusable(false);
        
        
        JButton button4 = new JButton();
        button4.setBounds(0,100,190,500);
        button4.setBorder(null);
        button4.setBackground(new Color(118, 212, 152));
        button4.setFocusable(false);
        
        
        Container container = sidebar.getParent();
        if (container != null) {
            container.setComponentZOrder(sidebar, 0);
        }
        sidebar.add(button1);
        sidebar.add(button2);
        sidebar.add(button3);
        sidebar.add(button4);
        
        jFrame.add(sidebar);
        
      //Code to insert a scrollbar into the frame
        JPanel transPoPanel = new JPanel();
        transPoPanel.setPreferredSize(new Dimension(0, 1050)); // Set the height of transPoPanel
        transPoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        transPoPanel.setLayout(null);
        
        // reference by andy
        routeNames = new HashMap<>();

		try {
		    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
		
		    // execute a SELECT query to fetch the route_id and route_name
		    PreparedStatement stmt = conn.prepareStatement("SELECT route_id, route_name FROM routes");
		    ResultSet rs = stmt.executeQuery();
		
		    // Step 3: Populate the map with the fetched values
		    while (rs.next()) {
		        int routeId = rs.getInt("route_id");
		        String routeName = rs.getString("route_name");
		        routeNames.put(routeName, routeId);
		    }
		
		    // Step 4: Close the ResultSet, Statement, and Connection
		    rs.close();
		    stmt.close();
		    conn.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		
		// System.out.println(routeNames); // debugging material
        JScrollPane scrollBar = new JScrollPane(transPoPanel);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        jFrame.add(scrollBar);
        jFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                scrollBar.setBounds(0, 80, jFrame.getWidth(), jFrame.getHeight());
            }
        });
        
        jFrame.add(scrollBar);
        jFrame.add(jLabel);
        jFrame.add(jButton);
        jFrame.add(jPanel);        
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (sidebarOpen && !sidebar.contains(e.getPoint())) {
                    toggleSidebar();
                }
            }
        });
    }

    private void toggleSidebar() {
        if (sidebarOpen) {
            sidebar.setLocation(-200, 80);
        } else {
            sidebar.setLocation(0, 80);
        }
        sidebarOpen = !sidebarOpen;
    }
}
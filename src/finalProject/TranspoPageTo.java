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

public class TranspoPageTo {
    private JPanel sidebar;
    private boolean sidebarOpen = false;
    Map<String, Integer> routeNames;

    TranspoPageTo(){
        JFrame jFrame = new JFrame();
        
        ImageIcon jIcon = new ImageIcon("src/resources/name.png");
        ImageIcon exitIcon = new ImageIcon("src/resources/exit.png");
        ImageIcon sideBar = new ImageIcon("src/resources/menu2.png");

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


        JButton dropOpt1 = new JButton();
        dropOpt1.setText("USC Main to USC TC");
        dropOpt1.setBorder(null);
        dropOpt1.setFocusable(false);
        dropOpt1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt1.setBounds(0,0,540,50);

        JPanel dropdownPanel1 = new JPanel();
        dropdownPanel1.setBounds(0, 50, 540, 200);
        dropdownPanel1.setBackground(Color.WHITE);
        dropdownPanel1.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel1.setLayout(null);
        dropdownPanel1.setVisible(false);

        // In your actionPerformed method for the dropOpt1 button
        dropOpt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel1.setVisible(!dropdownPanel1.isVisible());
                int routeId = routeNames.get(dropOpt1.getText()); // gets the ID from the hashmap based on the dropOpt1 text which matches with the one in the hashmap
                try {
                    // create a connection to the database and execute a query to retrieve the data
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM routes WHERE route_id = ?");
                    stmt.setInt(1, routeId);
                    ResultSet result = stmt.executeQuery();


                    // retrieve the data and create JLabels to display them
                    if (result.next()) {
                        String routeNameResult = result.getString("route_name");
                        double traditionalJeepFareResult = result.getDouble("traditionalJeep_Fare");
                        double modernJeepFareResult = result.getDouble("modernJeep_Fare");
                        String jeepsToTakeResult = result.getString("jeepsToTake");
                        String routeMapResult = result.getString("route_map");

                        JLabel labelName = new JLabel("Route Name: " + routeNameResult);
                        labelName.setBounds(20, 20, 100, 30);
                        dropdownPanel1.add(labelName);

                        JLabel labelTraditionalFare = new JLabel("Traditional Jeep Fare: " + traditionalJeepFareResult);
                        labelTraditionalFare.setBounds(20, 90, 400, 30);
                        dropdownPanel1.add(labelTraditionalFare);

                        JLabel labelModernFare = new JLabel("Modern Jeep Fare: " + modernJeepFareResult);
                        labelModernFare.setBounds(20, 120, 400, 30);
                        dropdownPanel1.add(labelModernFare);

                        JLabel labelJeepsToTake = new JLabel("Jeeps To Take: " + jeepsToTakeResult);
                        labelJeepsToTake.setBounds(20, 150, 400, 30);
                        dropdownPanel1.add(labelJeepsToTake);

                        // francis, adjust this lng para ma photo siya inig display
                        JLabel labelRouteMap = new JLabel("Route Map: " + routeMapResult);
                        labelRouteMap.setBounds(20, 180, 400, 30);
                        dropdownPanel1.add(labelRouteMap);
                    }

                    // close the connection and the statement
                    result.close();
                    stmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        JButton dropOpt2 = new JButton();
        dropOpt2.setText("Ayala Terminal to USC TC (62B)");
        dropOpt2.setBorder(null);
        dropOpt2.setFocusable(false);
        dropOpt2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt2.setBounds(0,80,540,50);
        
        JPanel dropdownPanel2 = new JPanel();
        dropdownPanel2.setBounds(0, 130, 540, 200);
        dropdownPanel2.setBackground(Color.WHITE);
        dropdownPanel2.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel2.setLayout(null);
        dropdownPanel2.setVisible(false);
        
        JLabel label2 = new JLabel("Option 2");
        label2.setBounds(100, 100, 100, 30);
        dropdownPanel2.add(label2);
                
        dropOpt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel2.setVisible(!dropdownPanel2.isVisible());
            }
        });
        
        JButton dropOpt3 = new JButton();
        dropOpt3.setText("Ayala Terminal to USC TC (62C)");
        dropOpt3.setBorder(null);
        dropOpt3.setFocusable(false);
        dropOpt3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt3.setBounds(0,160,540,50);
        
        JPanel dropdownPanel3 = new JPanel();
        dropdownPanel3.setBounds(0, 180, 540, 200);
        dropdownPanel3.setBackground(Color.WHITE);
        dropdownPanel3.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel3.setLayout(null);
        dropdownPanel3.setVisible(false);
        
        JLabel label3 = new JLabel("Option 3");
        label3.setBounds(100, 100, 100, 30);
        dropdownPanel3.add(label3);
                
        dropOpt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel3.setVisible(!dropdownPanel3.isVisible());
            }
        });
        
        JButton dropOpt4 = new JButton();
        dropOpt4.setText("Ayala Terminal to USC TC (Ayala - Guba)");
        dropOpt4.setBorder(null);
        dropOpt4.setFocusable(false);
        dropOpt4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt4.setBounds(0,240,540,50);
        
        JPanel dropdownPanel4 = new JPanel();
        dropdownPanel4.setBounds(0, 250, 540, 200);
        dropdownPanel4.setBackground(Color.WHITE);
        dropdownPanel4.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel4.setLayout(null);
        dropdownPanel4.setVisible(false);
        
        JLabel label4 = new JLabel("Option 4");
        label4.setBounds(100, 100, 100, 30);
        dropdownPanel4.add(label4);
                
        dropOpt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel4.setVisible(!dropdownPanel4.isVisible());
            }
        });
        
        JButton dropOpt5 = new JButton();
        dropOpt5.setText("Parkmall to USC TC (13H)");
        dropOpt5.setBorder(null);
        dropOpt5.setFocusable(false);
        dropOpt5.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt5.setBounds(0,320,540,50);
        
        JPanel dropdownPanel5 = new JPanel();
        dropdownPanel5.setBounds(0, 320, 540, 200);
        dropdownPanel5.setBackground(Color.WHITE);
        dropdownPanel5.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel5.setLayout(null);
        dropdownPanel5.setVisible(false);
        
        JLabel label5 = new JLabel("Option 5");
        label5.setBounds(100, 100, 100, 30);
        dropdownPanel5.add(label5);
                
        dropOpt5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel5.setVisible(!dropdownPanel5.isVisible());
            }
        });
        
        JButton dropOpt6 = new JButton();
        dropOpt6.setText("SM Consolacion to USC TC (24G)");
        dropOpt6.setBorder(null);
        dropOpt6.setFocusable(false);
        dropOpt6.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt6.setBounds(0,400,540,50);
        
        JPanel dropdownPanel6 = new JPanel();
        dropdownPanel6.setBounds(0, 410, 540, 200);
        dropdownPanel6.setBackground(Color.WHITE);
        dropdownPanel6.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel6.setLayout(null);
        dropdownPanel6.setVisible(false);
        
        JLabel label6 = new JLabel("Option 6");
        label6.setBounds(100, 100, 100, 30);
        dropdownPanel6.add(label6);
                
        dropOpt6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel6.setVisible(!dropdownPanel6.isVisible());
            }
        });
        
        JButton dropOpt7 = new JButton();
        dropOpt7.setText("J Mall to USC TC (13I)");
        dropOpt7.setBorder(null);
        dropOpt7.setFocusable(false);
        dropOpt7.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt7.setBounds(0,480,540,50);
        
        JPanel dropdownPanel7 = new JPanel();
        dropdownPanel7.setBounds(0, 490, 540, 200);
        dropdownPanel7.setBackground(Color.WHITE);
        dropdownPanel7.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel7.setLayout(null);
        dropdownPanel7.setVisible(false);
        
        JLabel label7 = new JLabel("Option 7");
        label7.setBounds(100, 100, 100, 30);
        dropdownPanel7.add(label7);
                
        dropOpt7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel7.setVisible(!dropdownPanel7.isVisible());
            }
        });
        
        JButton dropOpt8 = new JButton();
        dropOpt8.setText("MC Public Market to USC TC (22I)");
        dropOpt8.setBorder(null);
        dropOpt8.setFocusable(false);
        dropOpt8.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt8.setBounds(0,560,540,50);
        
        JPanel dropdownPanel8 = new JPanel();
        dropdownPanel8.setBounds(0, 570, 540, 200);
        dropdownPanel8.setBackground(Color.WHITE);
        dropdownPanel8.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel8.setLayout(null);
        dropdownPanel8.setVisible(false);
        
        JLabel label8 = new JLabel("Option 8");
        label8.setBounds(100, 100, 100, 30);
        dropdownPanel8.add(label8);
                
        dropOpt8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel8.setVisible(!dropdownPanel8.isVisible());
            }
        });
        
        JButton dropOpt9 = new JButton();
        dropOpt9.setText("Pacific Mall to USC TC (24G)");
        dropOpt9.setBorder(null);
        dropOpt9.setFocusable(false);
        dropOpt9.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt9.setBounds(0,640,540,50);
        
        JPanel dropdownPanel9 = new JPanel();
        dropdownPanel9.setBounds(0, 650, 540, 200);
        dropdownPanel9.setBackground(Color.WHITE);
        dropdownPanel9.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel9.setLayout(null);
        dropdownPanel9.setVisible(false);
        
        JLabel label9 = new JLabel("Option 9");
        label9.setBounds(100, 100, 100, 30);
        dropdownPanel9.add(label9);
                
        dropOpt9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel9.setVisible(!dropdownPanel9.isVisible());
            }
        });
        
        JButton dropOpt10 = new JButton();
        dropOpt10.setText("Tintay to USC TC (13C)");
        dropOpt10.setBorder(null);
        dropOpt10.setFocusable(false);
        dropOpt10.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt10.setBounds(0,720,540,50);
        
        JPanel dropdownPanel10 = new JPanel();
        dropdownPanel10.setBounds(0, 730, 540, 200);
        dropdownPanel10.setBackground(Color.WHITE);
        dropdownPanel10.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel10.setLayout(null);
        dropdownPanel10.setVisible(false);
        
        JLabel label10 = new JLabel("Option 10");
        label10.setBounds(100, 100, 100, 30);
        dropdownPanel10.add(label10);
                
        dropOpt10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel10.setVisible(!dropdownPanel10.isVisible());
            }
        });
        
        transPoPanel.add(dropOpt1); transPoPanel.add(dropdownPanel1);
        transPoPanel.add(dropOpt2); transPoPanel.add(dropdownPanel2);
        transPoPanel.add(dropOpt3); transPoPanel.add(dropdownPanel3);
        transPoPanel.add(dropOpt4); transPoPanel.add(dropdownPanel4);
        transPoPanel.add(dropOpt5); transPoPanel.add(dropdownPanel5);
        transPoPanel.add(dropOpt6); transPoPanel.add(dropdownPanel6);
        transPoPanel.add(dropOpt7); transPoPanel.add(dropdownPanel7);
        transPoPanel.add(dropOpt8); transPoPanel.add(dropdownPanel8);
        transPoPanel.add(dropOpt9); transPoPanel.add(dropdownPanel9);
        transPoPanel.add(dropOpt10); transPoPanel.add(dropdownPanel10);
        
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
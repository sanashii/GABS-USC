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
        
        ImageIcon jIcon = new ImageIcon("src/resources/nameTo.png");
        ImageIcon exitIcon = new ImageIcon("src/resources/exit.png");
        ImageIcon sideBar = new ImageIcon("src/resources/menu2.png");
        ImageIcon ayalaToTC = new ImageIcon("src/resources/routes/Ayala Terminal to TC.png");
        
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
        button2.setBounds(0, 50, 190, 50);
        button2.setBorder(null);
        button2.setBackground(new Color(118, 212, 152));
        button2.setFocusable(false);
        button2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));

        JFrame frame = new JFrame("Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.add(button2);

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("To USC TC");
        JMenuItem menuItem2 = new JMenuItem("From USC TC");
        popupMenu.add(menuItem1);
        popupMenu.add(menuItem2);
        menuItem1.setBackground(new Color(118, 212, 152));
        menuItem2.setBackground(new Color(118, 212, 152));
        popupMenu.setBackground(new Color(118, 212, 152));

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Calculate the required width based on menu items' preferred widths
                int requiredWidth = Math.max(menuItem1.getPreferredSize().width,
                        menuItem2.getPreferredSize().width) + 4; // Add some padding

                // Resize the frame width to fit the menu items
                int frameWidth = frame.getWidth();
                int frameHeight = frame.getHeight();
                frame.setSize(frameWidth + requiredWidth, frameHeight);

                // Show the popup menu
                popupMenu.show(button2, button2.getWidth(), 0);
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
		    PreparedStatement stmt = conn.prepareStatement("SELECT route_id, route_name FROM routes WHERE route_tag = 'T'");
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


       
        dropOpt1.setText("USC Main");
        dropOpt1.setBorder(null);
        dropOpt1.setFocusable(false);
        dropOpt1.setBackground(new Color(118, 212, 152));
        dropOpt1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        dropOpt1.setBounds(0,0,540,50);

        
        dropdownPanel1.setBounds(0, 50, 540, 250);
        dropdownPanel1.setBackground(Color.WHITE);
        dropdownPanel1.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel1.setLayout(null);
        dropdownPanel1.setVisible(false);

     // create a JLabel to display the image
        ImageIcon icon1 = new ImageIcon("src/resources/routes/USC Main to TC.png"); // create ImageIcon from file
        Image img1 = icon1.getImage(); // get the image from the ImageIcon
        Image scaledImg1 = img1.getScaledInstance(535, 175, Image.SCALE_SMOOTH); // scale the image to 50x50
        ImageIcon scaledIcon1 = new ImageIcon(scaledImg1);
        JLabel imageLabel1 = new JLabel(scaledIcon1);
        imageLabel1.setBounds(0, 0, 540, 145);
        dropdownPanel1.add(imageLabel1);

        
        // In your actionPerformed method for the dropOpt1 button
        dropOpt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                dropdownPanel1.setVisible(!dropdownPanel1.isVisible());
                dropOpt2.setVisible(false);
                dropOpt3.setVisible(false);
                dropOpt4.setVisible(false);
                if (!dropdownPanel1.isVisible()) {
                    dropOpt2.setVisible(true);
                    dropOpt3.setVisible(true);
                    dropOpt4.setVisible(true);
                    // add code to show other buttons if necessary
                }
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
                         
                       JLabel labelName = new JLabel("Route Name: " + routeNameResult);
                       labelName.setBounds(20, 150, 400, 30);
                       dropdownPanel1.add(labelName);

                       JLabel labelTraditionalFare = new JLabel("Traditional Jeep Fare: ₱" + traditionalJeepFareResult);
                       labelTraditionalFare.setBounds(20, 170, 400, 30);
                       dropdownPanel1.add(labelTraditionalFare);

                       JLabel labelModernFare = new JLabel("Modern Jeep Fare: ₱" + modernJeepFareResult);
                       labelModernFare.setBounds(20, 190, 400, 30);
                       dropdownPanel1.add(labelModernFare);

                       JLabel labelJeepsToTake = new JLabel("Jeeps To Take: " + jeepsToTakeResult);
                       labelJeepsToTake.setBounds(20, 210, 600, 30);
                       dropdownPanel1.add(labelJeepsToTake);
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
        
        
        dropOpt2.setText("Ayala Terminal");
        dropOpt2.setBorder(null);
        dropOpt2.setFocusable(false);
        dropOpt2.setBackground(new Color(118, 212, 152));
        dropOpt2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        dropOpt2.setBounds(0,80,540,50);
        
       
        dropdownPanel2.setBounds(0, 130, 540, 250);
        dropdownPanel2.setBackground(Color.WHITE);
        dropdownPanel2.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel2.setLayout(null);
        dropdownPanel2.setVisible(false);
        
        ImageIcon icon2 = new ImageIcon("src/resources/routes/Ayala Terminal to TC.png"); // create ImageIcon from file
        Image img2 = icon2.getImage(); // get the image from the ImageIcon
        Image scaledImg2 = img2.getScaledInstance(550, 150, Image.SCALE_SMOOTH); // scale the image to 50x50
        ImageIcon scaledIcon2 = new ImageIcon(scaledImg2);
        JLabel imageLabel2 = new JLabel(scaledIcon2);
        imageLabel2.setBounds(0, 0, 540, 145);
        dropdownPanel2.add(imageLabel2);
   
        dropOpt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel2.setVisible(!dropdownPanel2.isVisible());
                dropOpt3.setVisible(false);
                dropOpt4.setVisible(false);
                dropOpt5.setVisible(false);
                if (!dropdownPanel2.isVisible()) {
                    dropOpt3.setVisible(true);
                    dropOpt4.setVisible(true);
                    dropOpt5.setVisible(true);
                    // add code to show other buttons if necessary
                }
                int routeId = routeNames.get(dropOpt2.getText()); // gets the ID from the hashmap based on the dropOpt1 text which matches with the one in the hashmap
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
                         
                       JLabel labelName = new JLabel("Route Name: " + routeNameResult);
                       labelName.setBounds(20, 150, 400, 30);
                       dropdownPanel2.add(labelName);

                       JLabel labelTraditionalFare = new JLabel("Traditional Jeep Fare: ₱" + traditionalJeepFareResult);
                       labelTraditionalFare.setBounds(20, 170, 400, 30);
                       dropdownPanel2.add(labelTraditionalFare);

                       JLabel labelModernFare = new JLabel("Modern Jeep Fare: ₱" + modernJeepFareResult);
                       labelModernFare.setBounds(20, 190, 400, 30);
                       dropdownPanel2.add(labelModernFare);

                       JLabel labelJeepsToTake = new JLabel("Jeeps To Take: " + jeepsToTakeResult);
                       labelJeepsToTake.setBounds(20, 210, 600, 30);
                       dropdownPanel2.add(labelJeepsToTake);
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
        
        
        dropOpt3.setText("Parkmall");
        dropOpt3.setBorder(null);
        dropOpt3.setFocusable(false);
        dropOpt3.setBackground(new Color(118, 212, 152));
        dropOpt3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        dropOpt3.setBounds(0,160,540,50);
        
        
        dropdownPanel3.setBounds(0, 180, 540, 250);
        dropdownPanel3.setBackground(Color.WHITE);
        dropdownPanel3.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel3.setLayout(null);
        dropdownPanel3.setVisible(false);
        
        ImageIcon icon3 = new ImageIcon("src/resources/routes/Parkmall to USC TC.png"); // create ImageIcon from file
        Image img3 = icon3.getImage(); // get the image from the ImageIcon
        Image scaledImg3 = img3.getScaledInstance(580, 140, Image.SCALE_SMOOTH); // scale the image to 50x50
        ImageIcon scaledIcon3 = new ImageIcon(scaledImg3);
        JLabel imageLabel3 = new JLabel(scaledIcon3);
        imageLabel3.setBounds(0, 0, 540, 145);
        // set the icon to the image file path
        dropdownPanel3.add(imageLabel3);
       
        dropOpt3.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
                 dropdownPanel3.setVisible(!dropdownPanel3.isVisible());
                 dropOpt4.setVisible(false);
                 dropOpt5.setVisible(false);
                 dropOpt6.setVisible(false);
                 if (!dropdownPanel3.isVisible()) {
                     dropOpt4.setVisible(true);
                     dropOpt5.setVisible(true);
                     dropOpt6.setVisible(true);
                     // add code to show other buttons if necessary
                 }
                 int routeId = routeNames.get(dropOpt3.getText()); // gets the ID from the hashmap based on the dropOpt1 text which matches with the one in the hashmap
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
                          
                        JLabel labelName = new JLabel("Route Name: " + routeNameResult);
                        labelName.setBounds(20, 150, 400, 30);
                        dropdownPanel3.add(labelName);

                        JLabel labelTraditionalFare = new JLabel("Traditional Jeep Fare: ₱" + traditionalJeepFareResult);
                        labelTraditionalFare.setBounds(20, 170, 400, 30);
                        dropdownPanel3.add(labelTraditionalFare);

                        JLabel labelModernFare = new JLabel("Modern Jeep Fare: ₱" + modernJeepFareResult);
                        labelModernFare.setBounds(20, 190, 400, 30);
                        dropdownPanel3.add(labelModernFare);

                        JLabel labelJeepsToTake = new JLabel("Jeeps To Take: " + jeepsToTakeResult);
                        labelJeepsToTake.setBounds(20, 210, 600, 30);
                        dropdownPanel3.add(labelJeepsToTake);
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
        
      
        dropOpt4.setText("SM Consolacion");
        dropOpt4.setBorder(null);
        dropOpt4.setFocusable(false);
        dropOpt4.setBackground(new Color(118, 212, 152));
        dropOpt4.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        dropOpt4.setBounds(0,240,540,50);
        
       
        dropdownPanel4.setBounds(0, 250, 540, 250);
        dropdownPanel4.setBackground(Color.WHITE);
        dropdownPanel4.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel4.setLayout(null);
        dropdownPanel4.setVisible(false);
        
        ImageIcon icon4 = new ImageIcon("src/resources/routes/SM Consolacion to TC.png"); // create ImageIcon from file
        Image img4 = icon4.getImage(); // get the image from the ImageIcon
        Image scaledImg4 = img4.getScaledInstance(600, 145, Image.SCALE_SMOOTH); // scale the image to 50x50
        ImageIcon scaledIcon4 = new ImageIcon(scaledImg4);
        JLabel imageLabel4 = new JLabel(scaledIcon4);
        imageLabel4.setBounds(0, 0, 540, 145);
        dropdownPanel4.add(imageLabel4);
                
        dropOpt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel4.setVisible(!dropdownPanel4.isVisible());
                dropOpt5.setVisible(false);
                dropOpt6.setVisible(false);
                dropOpt7.setVisible(false);
                if (!dropdownPanel4.isVisible()) {
                    dropOpt5.setVisible(true);
                    dropOpt6.setVisible(true);
                    dropOpt7.setVisible(true);
                    // add code to show other buttons if necessary
                }
                int routeId = routeNames.get(dropOpt4.getText()); // gets the ID from the hashmap based on the dropOpt1 text which matches with the one in the hashmap
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
                         
                       JLabel labelName = new JLabel("Route Name: " + routeNameResult);
                       labelName.setBounds(20, 150, 400, 30);
                       dropdownPanel4.add(labelName);

                       JLabel labelTraditionalFare = new JLabel("Traditional Jeep Fare: ₱" + traditionalJeepFareResult);
                       labelTraditionalFare.setBounds(20, 170, 400, 30);
                       dropdownPanel4.add(labelTraditionalFare);

                       JLabel labelModernFare = new JLabel("Modern Jeep Fare: ₱" + modernJeepFareResult);
                       labelModernFare.setBounds(20, 190, 400, 30);
                       dropdownPanel4.add(labelModernFare);

                       JLabel labelJeepsToTake = new JLabel("Jeeps To Take: " + jeepsToTakeResult);
                       labelJeepsToTake.setBounds(20, 210, 600, 30);
                       dropdownPanel4.add(labelJeepsToTake);
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
        
       
        dropOpt5.setText("J Mall");
        dropOpt5.setBorder(null);
        dropOpt5.setFocusable(false);
        dropOpt5.setBackground(new Color(118, 212, 152));
        dropOpt5.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        dropOpt5.setBounds(0,320,540,50);
        
       
        dropdownPanel5.setBounds(0, 320, 540, 250);
        dropdownPanel5.setBackground(Color.WHITE);
        dropdownPanel5.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel5.setLayout(null);
        dropdownPanel5.setVisible(false);
        
        ImageIcon icon5 = new ImageIcon("src/resources/routes/J Mall to USC TC.png"); // create ImageIcon from file
        Image img5 = icon5.getImage(); // get the image from the ImageIcon
        Image scaledImg5 = img5.getScaledInstance(540, 110, Image.SCALE_SMOOTH); // scale the image to 50x50
        ImageIcon scaledIcon5 = new ImageIcon(scaledImg5);
        JLabel imageLabel5 = new JLabel(scaledIcon5);
        imageLabel5.setBounds(0, 0, 540, 145);
        dropdownPanel5.add(imageLabel5);
                
  
        dropOpt5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 dropdownPanel5.setVisible(!dropdownPanel5.isVisible());
                 dropOpt6.setVisible(false);
                 dropOpt7.setVisible(false);
                 dropOpt8.setVisible(false);
                 if (!dropdownPanel5.isVisible()) {
                     dropOpt6.setVisible(true);
                     dropOpt7.setVisible(true);
                     dropOpt8.setVisible(true);
                     // add code to show other buttons if necessary
                 }
                 int routeId = routeNames.get(dropOpt5.getText()); // gets the ID from the hashmap based on the dropOpt1 text which matches with the one in the hashmap
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
                          
                        JLabel labelName = new JLabel("Route Name: " + routeNameResult);
                        labelName.setBounds(20, 150, 400, 30);
                        dropdownPanel5.add(labelName);

                        JLabel labelTraditionalFare = new JLabel("Traditional Jeep Fare: ₱" + traditionalJeepFareResult);
                        labelTraditionalFare.setBounds(20, 170, 400, 30);
                        dropdownPanel5.add(labelTraditionalFare);

                        JLabel labelModernFare = new JLabel("Modern Jeep Fare: ₱" + modernJeepFareResult);
                        labelModernFare.setBounds(20, 190, 400, 30);
                        dropdownPanel5.add(labelModernFare);

                        JLabel labelJeepsToTake = new JLabel("Jeeps To Take: " + jeepsToTakeResult);
                        labelJeepsToTake.setBounds(20, 210, 600, 30);
                        dropdownPanel5.add(labelJeepsToTake);
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
        
      
        dropOpt6.setText("MC Public Market");
        dropOpt6.setBorder(null);
        dropOpt6.setFocusable(false);
        dropOpt6.setBackground(new Color(118, 212, 152));
        dropOpt6.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        dropOpt6.setBounds(0,400,540,50);
        
       
        dropdownPanel6.setBounds(0, 410, 540, 250);
        dropdownPanel6.setBackground(Color.WHITE);
        dropdownPanel6.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel6.setLayout(null);
        dropdownPanel6.setVisible(false);
        
        ImageIcon icon6 = new ImageIcon("src/resources/routes/MC Public Market to TC.png"); // create ImageIcon from file
        Image img6 = icon6.getImage(); // get the image from the ImageIcon
        Image scaledImg6 = img6.getScaledInstance(540, 110, Image.SCALE_SMOOTH); // scale the image to 50x50
        ImageIcon scaledIcon6 = new ImageIcon(scaledImg6);
        JLabel imageLabel6 = new JLabel(scaledIcon6);
        imageLabel6.setBounds(0, 0, 540, 145);
        dropdownPanel6.add(imageLabel6);
                
     
        dropOpt6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 dropdownPanel6.setVisible(!dropdownPanel6.isVisible());
                 dropOpt7.setVisible(false);
                 dropOpt8.setVisible(false);
                 if (!dropdownPanel6.isVisible()) {
                     dropOpt7.setVisible(true);
                     dropOpt8.setVisible(true);
                    
                     // add code to show other buttons if necessary
                 }
                 int routeId = routeNames.get(dropOpt6.getText()); // gets the ID from the hashmap based on the dropOpt1 text which matches with the one in the hashmap
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
                          
                        JLabel labelName = new JLabel("Route Name: " + routeNameResult);
                        labelName.setBounds(20, 150, 400, 30);
                        dropdownPanel6.add(labelName);

                        JLabel labelTraditionalFare = new JLabel("Traditional Jeep Fare: ₱" + traditionalJeepFareResult);
                        labelTraditionalFare.setBounds(20, 170, 400, 30);
                        dropdownPanel6.add(labelTraditionalFare);

                        JLabel labelModernFare = new JLabel("Modern Jeep Fare: ₱" + modernJeepFareResult);
                        labelModernFare.setBounds(20, 190, 400, 30);
                        dropdownPanel6.add(labelModernFare);

                        JLabel labelJeepsToTake = new JLabel("Jeeps To Take: " + jeepsToTakeResult);
                        labelJeepsToTake.setBounds(20, 210, 600, 30);
                        dropdownPanel6.add(labelJeepsToTake);
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
        
        
        dropOpt7.setText("Pacific Mall");
        dropOpt7.setBorder(null);
        dropOpt7.setFocusable(false);
        dropOpt7.setBackground(new Color(118, 212, 152));
        dropOpt7.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        dropOpt7.setBounds(0,480,540,50);
        
       
        dropdownPanel7.setBounds(0, 490, 540, 250);
        dropdownPanel7.setBackground(Color.WHITE);
        dropdownPanel7.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel7.setLayout(null);
        dropdownPanel7.setVisible(false);
        
        ImageIcon icon7 = new ImageIcon("src/resources/routes/Pacific Mall to TC.png"); // create ImageIcon from file
        Image img7 = icon7.getImage(); // get the image from the ImageIcon
        Image scaledImg7 = img7.getScaledInstance(540, 110, Image.SCALE_SMOOTH); // scale the image to 50x50
        ImageIcon scaledIcon7 = new ImageIcon(scaledImg7);
        JLabel imageLabel7 = new JLabel(scaledIcon7);
        imageLabel7.setBounds(0, 0, 540, 145);
        dropdownPanel7.add(imageLabel7);
        
        dropOpt7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 dropdownPanel7.setVisible(!dropdownPanel7.isVisible());
                 dropOpt8.setVisible(false);
                 if (!dropdownPanel7.isVisible()) {
                     dropOpt8.setVisible(true);
                   
                     // add code to show other buttons if necessary
                 }
                 int routeId = routeNames.get(dropOpt7.getText()); // gets the ID from the hashmap based on the dropOpt1 text which matches with the one in the hashmap
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
                          
                        JLabel labelName = new JLabel("Route Name: " + routeNameResult);
                        labelName.setBounds(20, 150, 400, 30);
                        dropdownPanel7.add(labelName);

                        JLabel labelTraditionalFare = new JLabel("Traditional Jeep Fare: ₱" + traditionalJeepFareResult);
                        labelTraditionalFare.setBounds(20, 170, 400, 30);
                        dropdownPanel7.add(labelTraditionalFare);

                        JLabel labelModernFare = new JLabel("Modern Jeep Fare: ₱" + modernJeepFareResult);
                        labelModernFare.setBounds(20, 190, 400, 30);
                        dropdownPanel7.add(labelModernFare);

                        JLabel labelJeepsToTake = new JLabel("Jeeps To Take: " + jeepsToTakeResult);
                        labelJeepsToTake.setBounds(20, 210, 600, 30);
                        dropdownPanel7.add(labelJeepsToTake);
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
        
        
        dropOpt8.setText("Tintay");
        dropOpt8.setBorder(null);
        dropOpt8.setFocusable(false);
        dropOpt8.setBackground(new Color(118, 212, 152));
        dropOpt8.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        dropOpt8.setBounds(0,560,540,50);
        
       
        dropdownPanel8.setBounds(0, 570, 540, 250);
        dropdownPanel8.setBackground(Color.WHITE);
        dropdownPanel8.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel8.setLayout(null);
        dropdownPanel8.setVisible(false);
        
        ImageIcon icon8 = new ImageIcon("src/resources/routes/Tintay to TC.png"); // create ImageIcon from file
        Image img8 = icon8.getImage(); // get the image from the ImageIcon
        Image scaledImg8 = img8.getScaledInstance(540, 130, Image.SCALE_SMOOTH); // scale the image to 50x50
        ImageIcon scaledIcon8 = new ImageIcon(scaledImg8);
        JLabel imageLabel8 = new JLabel(scaledIcon8);
        imageLabel8.setBounds(0, 0, 540, 145);
        dropdownPanel8.add(imageLabel8);
        
     
        dropOpt8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 dropdownPanel8.setVisible(!dropdownPanel8.isVisible());
               
                 int routeId = routeNames.get(dropOpt8.getText()); // gets the ID from the hashmap based on the dropOpt1 text which matches with the one in the hashmap
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
                          
                        JLabel labelName = new JLabel("Route Name: " + routeNameResult);
                        labelName.setBounds(20, 150, 400, 30);
                        dropdownPanel8.add(labelName);

                        JLabel labelTraditionalFare = new JLabel("Traditional Jeep Fare: ₱" + traditionalJeepFareResult);
                        labelTraditionalFare.setBounds(20, 170, 400, 30);
                        dropdownPanel8.add(labelTraditionalFare);

                        JLabel labelModernFare = new JLabel("Modern Jeep Fare: ₱" + modernJeepFareResult);
                        labelModernFare.setBounds(20, 190, 400, 30);
                        dropdownPanel8.add(labelModernFare);

                        JLabel labelJeepsToTake = new JLabel("Jeeps To Take: " + jeepsToTakeResult);
                        labelJeepsToTake.setBounds(20, 210, 600, 30);
                        dropdownPanel8.add(labelJeepsToTake);
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
        
       
       
        transPoPanel.add(dropOpt1); transPoPanel.add(dropdownPanel1);
        transPoPanel.add(dropOpt2); transPoPanel.add(dropdownPanel2);
        transPoPanel.add(dropOpt3); transPoPanel.add(dropdownPanel3);
        transPoPanel.add(dropOpt4); transPoPanel.add(dropdownPanel4);
        transPoPanel.add(dropOpt5); transPoPanel.add(dropdownPanel5);
        transPoPanel.add(dropOpt6); transPoPanel.add(dropdownPanel6);
        transPoPanel.add(dropOpt7); transPoPanel.add(dropdownPanel7);
        transPoPanel.add(dropOpt8); transPoPanel.add(dropdownPanel8);
       
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
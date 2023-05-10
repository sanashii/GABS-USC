package finalProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class MapPage {
	private JPanel sidebar;
    private boolean sidebarOpen = false;

    MapPage(){
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
        jFrame.setLocationRelativeTo(null); //This will position the JFrame in the same place of the introGUI
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
        sidebar.setBounds(-200, 80, 190, 730);
        
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
				new MapPage();
				jFrame.dispose();
			}
		});
        
        JButton button2 = new JButton("Transportation Guide");
        button2.setBounds(0,50,190,50);
        button2.setBorder(null);
        button2.setBackground(new Color(118, 212, 152));
        button2.setFocusable(false);
        button2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        
        button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new TranspoPageTo();
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
				new IntroGUI();
				jFrame.dispose();
			}
		});
        
        sidebar.add(button1);
        sidebar.add(button2);
        sidebar.add(button3);
     
        jFrame.add(sidebar);

        JPanel mapPanel = new JPanel();
        mapPanel.setPreferredSize(new Dimension(1280, 824)); // Set the height of transPoPanel
        mapPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mapPanel.setLayout(null);
        
        ImageIcon map = new ImageIcon("src/resources/map.png");
        JLabel mapLabel = new JLabel();
        mapLabel.setIcon(map);
        mapLabel.setBounds(0, 0, map.getIconWidth(), map.getIconHeight());

        //Codes for LB Buidling
        ImageIcon lbImage = new ImageIcon("src/resources/building_codes/LB Building.png");
        Image lbImg = lbImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon lbIcon = new ImageIcon(lbImg);
        JButton lbBtn = new JButton(lbIcon);
        lbBtn.setBounds(218, 275, 100, 100);
        lbBtn.setFocusable(false);
        lbBtn.setBorder(null);
        lbBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // retrieve the office names from the database
                String[] officeNames = null;
                String[] stallNames = null;
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                    PreparedStatement stmt = conn.prepareStatement("SELECT office_name FROM offices WHERE building_code='LB'");
                    ResultSet result = stmt.executeQuery();
                    PreparedStatement stmt2 = conn.prepareStatement("SELECT stall_name FROM stalls WHERE building_code='LB'");
                    ResultSet result2 = stmt2.executeQuery();

                    // create an array to store the office names and stall names
                    List<String> namesList = new ArrayList<>();
                    while (result.next()) {
                        namesList.add(result.getString("office_name"));
                    }
                    officeNames = namesList.toArray(new String[namesList.size()]);
                    
                    List<String> stallnamesList = new ArrayList<>();
                    while (result2.next()) {
                        stallnamesList.add(result2.getString("stall_name"));
                    }
                    stallNames = stallnamesList.toArray(new String[stallnamesList.size()]);

                    // close the resources
                    result.close();
                    stmt.close();
                    result2.close();
                    stmt2.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                // create a JComboBox with the retrieved office names
                JComboBox<String> buildingCodeBox = new JComboBox<String>();
                buildingCodeBox.addItem("Select option...");
                for (String name : officeNames) {
                    buildingCodeBox.addItem(name);
                }

                // create a JLabel to display the office information
                JLabel officeInfoLabel = new JLabel("");
                officeInfoLabel.setVerticalAlignment(JLabel.TOP);

                // create a JPanel to contain the components
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(new JLabel("View list of offices:"), BorderLayout.NORTH);
                panel.add(buildingCodeBox, BorderLayout.CENTER);
                panel.add(officeInfoLabel, BorderLayout.SOUTH);
                panel.setPreferredSize(new Dimension(0, 100)); //This line of code alters the height of the JOptionPanel
                
                // add an ActionListener to the JComboBox
                buildingCodeBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedOffice = (String) buildingCodeBox.getSelectedItem();
                        if (!selectedOffice.equals("Select option...")) {
                            // retrieve the office information from the database
                            try {
                                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                                PreparedStatement stmt = conn.prepareStatement("SELECT office_name, building_code, location, hours FROM offices WHERE office_name=? AND building_code='LB'"); // the AND clause is used to ensure that the item is found in this specific building code since item name duplication with a different building_code is possible
                                stmt.setString(1, selectedOffice);
                                ResultSet result = stmt.executeQuery();
                                if (result.next()) {
                                    // display the office information in the label
                                    String officeInfo = "<html><b>Office Name:</b> " + result.getString("office_name") + "<br>"
                                            + "<b>Building Code:</b> " + result.getString("building_code") + "<br>"
                                            + "<b>Location:</b> " + result.getString("location") + "<br>"
                                            + "<b>Hours:</b> " + result.getString("hours") + "<br>";
                                    officeInfoLabel.setText(officeInfo);
                                }
                                // close the resources
                                result.close();
                                stmt.close();
                                conn.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            // clear the office information label
                            officeInfoLabel.setText("");
                        }
                    }
                });
                
                // create a JComboBox with the retrieved stall names
                JComboBox<String> stallBox = new JComboBox<String>();
                stallBox.addItem("Select option...");
                for (String name : stallNames) {
                    stallBox.addItem(name);
                }
                
                JLabel stallLabel = new JLabel("");
                stallLabel.setVerticalAlignment(JLabel.TOP);

                // create a JPanel to contain the components
                JPanel panel2 = new JPanel(new BorderLayout());
                panel2.add(new JLabel("View list of stalls:"), BorderLayout.NORTH);
                panel2.add(stallBox, BorderLayout.CENTER);
                panel2.add(stallLabel, BorderLayout.SOUTH);
//                panel2.setPreferredSize(new Dimension(0, 500));

                // add an ActionListener to the JComboBox
                stallBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedStall = (String) stallBox.getSelectedItem();
                        if (!selectedStall.equals("Select option...")) {
                            // retrieve the office information from the database
                            try {
                                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                                PreparedStatement stmt = conn.prepareStatement("SELECT stall_name, building_code, average_cost FROM stalls WHERE stall_name=? AND building_code='LB'");
                                stmt.setString(1, selectedStall);
                                ResultSet result = stmt.executeQuery();
                                if (result.next()) {
                                    // display the office information in the label
                                    String stallInfo = "<html><b>Stall Name:</b> " + result.getString("stall_name") + "<br>"
                                            + "<b>Building Code:</b> " + result.getString("building_code") + "<br>"
                                            + "<b>Average:</b> " + result.getDouble("average_cost") + "<br>";
                                    stallLabel.setText(stallInfo);
                                }
                                // close the resources
                                result.close();
                                stmt.close();
                                conn.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            // clear the office information label
                            stallLabel.setText("");
                        }
                    }
                });
                Object[] options = {"OK", "Cancel"};

             // Create a new JPanel and add both panels to it
             JPanel combinedPanel = new JPanel(new GridLayout(2, 1));
             combinedPanel.add(panel);
             combinedPanel.add(panel2);

             // Create a new JScrollPane and set its viewport to the combined panel
             JScrollPane scrollPane = new JScrollPane(combinedPanel);

             // Show the scroll pane inside the option dialog
             int choice = JOptionPane.showOptionDialog(null, scrollPane, "LB Building", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            }
        });
        
        //Codes for SMED Building
        ImageIcon smedImage = new ImageIcon("src/resources/building_codes/SMED Building.png");
        Image smedImg = smedImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon smedIcon = new ImageIcon(smedImg);
        
        JButton smedBtn = new JButton(smedIcon);
        smedBtn.setBounds(595,317,100,100);
        smedBtn.setFocusable(false);
        smedBtn.setBorder(null);
        smedBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				JOptionPane.showMessageDialog(jFrame, "SMED Building");
				String[] officeNames = null;
                String[] stallNames = null;
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                    PreparedStatement stmt = conn.prepareStatement("SELECT office_name FROM offices WHERE building_code='SM'");
                    ResultSet result = stmt.executeQuery();
                    PreparedStatement stmt2 = conn.prepareStatement("SELECT stall_name FROM stalls WHERE building_code='SM'");
                    ResultSet result2 = stmt2.executeQuery();

                    // create an array to store the office names and stall names
                    List<String> namesList = new ArrayList<>();
                    while (result.next()) {
                        namesList.add(result.getString("office_name"));
                    }
                    officeNames = namesList.toArray(new String[namesList.size()]);
                    
                    List<String> stallnamesList = new ArrayList<>();
                    while (result2.next()) {
                        stallnamesList.add(result2.getString("stall_name"));
                    }
                    stallNames = stallnamesList.toArray(new String[stallnamesList.size()]);

                    // close the resources
                    result.close();
                    stmt.close();
                    result2.close();
                    stmt2.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                // create a JComboBox with the retrieved office names
                JComboBox<String> buildingCodeBox = new JComboBox<String>();
                buildingCodeBox.addItem("Select option...");
                for (String name : officeNames) {
                    buildingCodeBox.addItem(name);
                }

                // create a JLabel to display the office information
                JLabel officeInfoLabel = new JLabel("");
                officeInfoLabel.setVerticalAlignment(JLabel.TOP);

                // create a JPanel to contain the components
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(new JLabel("View list of offices:"), BorderLayout.NORTH);
                panel.add(buildingCodeBox, BorderLayout.CENTER);
                panel.add(officeInfoLabel, BorderLayout.SOUTH);
                panel.setPreferredSize(new Dimension(0, 100)); //This line of code alters the height of the JOptionPanel

                // add an ActionListener to the JComboBox
                buildingCodeBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedOffice = (String) buildingCodeBox.getSelectedItem();
                        if (!selectedOffice.equals("Select option...")) {
                            // retrieve the office information from the database
                            try {
                                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                                PreparedStatement stmt = conn.prepareStatement("SELECT office_name, building_code, location, hours FROM offices WHERE office_name=? AND building_code='SM'"); // the AND clause is used to ensure that the item is found in this specific building code since item name duplication with a different building_code is possible
                                stmt.setString(1, selectedOffice);
                                ResultSet result = stmt.executeQuery();
                                if (result.next()) {
                                    // display the office information in the label
                                    String officeInfo = "<html><b>Office Name:</b> " + result.getString("office_name") + "<br>"
                                            + "<b>Building Code:</b> " + result.getString("building_code") + "<br>"
                                            + "<b>Location:</b> " + result.getString("location") + "<br>"
                                            + "<b>Hours:</b> " + result.getString("hours") + "<br>";
                                    officeInfoLabel.setText(officeInfo);
                                }
                                // close the resources
                                result.close();
                                stmt.close();
                                conn.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            // clear the office information label
                            officeInfoLabel.setText("");
                        }
                    }
                });
                
                // create a JComboBox with the retrieved stall names
                JComboBox<String> stallBox = new JComboBox<String>();
                stallBox.addItem("Select option...");
                for (String name : stallNames) {
                    stallBox.addItem(name);
                }
                
                JLabel stallLabel = new JLabel("");
                stallLabel.setVerticalAlignment(JLabel.TOP);

                // create a JPanel to contain the components
                JPanel panel2 = new JPanel(new BorderLayout());
                panel2.add(new JLabel("View list of stalls:"), BorderLayout.NORTH);
                panel2.add(stallBox, BorderLayout.CENTER);
                panel2.add(stallLabel, BorderLayout.SOUTH);

                // add an ActionListener to the JComboBox
                stallBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedStall = (String) stallBox.getSelectedItem();
                        if (!selectedStall.equals("Select option...")) {
                            // retrieve the office information from the database
                            try {
                                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                                PreparedStatement stmt = conn.prepareStatement("SELECT stall_name, building_code, average_cost FROM stalls WHERE stall_name=? AND building_code='SM'");
                                stmt.setString(1, selectedStall);
                                ResultSet result = stmt.executeQuery();
                                if (result.next()) {
                                    // display the office information in the label
                                    String stallInfo = "<html><b>Stall Name:</b> " + result.getString("stall_name") + "<br>"
                                            + "<b>Building Code:</b> " + result.getString("building_code") + "<br>"
                                            + "<b>Average:</b> " + result.getDouble("average_cost") + "<br>";
                                    stallLabel.setText(stallInfo);
                                }
                                // close the resources
                                result.close();
                                stmt.close();
                                conn.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            // clear the office information label
                            stallLabel.setText("");
                        }
                    }
                });
                Object[] options = {"OK", "Cancel"};

             // Create a new JPanel and add both panels to it
             JPanel combinedPanel = new JPanel(new GridLayout(2, 1));
             combinedPanel.add(panel);
             combinedPanel.add(panel2);

             // Create a new JScrollPane and set its viewport to the combined panel
             JScrollPane scrollPane = new JScrollPane(combinedPanel);

             // Show the scroll pane inside the option dialog
             int choice = JOptionPane.showOptionDialog(null, scrollPane, "SMED Building", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

			}
		});
        
        //Codes for PE Building
        ImageIcon peImage = new ImageIcon("src/resources/building_codes/PE Building.png");
        Image peImg = peImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon peIcon = new ImageIcon(peImg);
        
        JButton peBtn = new JButton(peIcon);
        peBtn.setBounds(809,330,80,80);
        peBtn.setFocusable(false);
        peBtn.setBorder(null);
        peBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] officeNames = null;
                String[] stallNames = null;
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                    PreparedStatement stmt = conn.prepareStatement("SELECT office_name FROM offices WHERE building_code='PE'");
                    ResultSet result = stmt.executeQuery();
                    PreparedStatement stmt2 = conn.prepareStatement("SELECT stall_name FROM stalls WHERE building_code='PE'");
                    ResultSet result2 = stmt2.executeQuery();

                    // create an array to store the office names and stall names
                    List<String> namesList = new ArrayList<>();
                    while (result.next()) {
                        namesList.add(result.getString("office_name"));
                    }
                    officeNames = namesList.toArray(new String[namesList.size()]);
                    
                    List<String> stallnamesList = new ArrayList<>();
                    while (result2.next()) {
                        stallnamesList.add(result2.getString("stall_name"));
                    }
                    stallNames = stallnamesList.toArray(new String[stallnamesList.size()]);

                    // close the resources
                    result.close();
                    stmt.close();
                    result2.close();
                    stmt2.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                // create a JComboBox with the retrieved office names
                JComboBox<String> buildingCodeBox = new JComboBox<String>();
                buildingCodeBox.addItem("Select option...");
                for (String name : officeNames) {
                    buildingCodeBox.addItem(name);
                }

                // create a JLabel to display the office information
                JLabel officeInfoLabel = new JLabel("");
                officeInfoLabel.setVerticalAlignment(JLabel.TOP);

                // create a JPanel to contain the components
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(new JLabel("View list of offices:"), BorderLayout.NORTH);
                panel.add(buildingCodeBox, BorderLayout.CENTER);
                panel.add(officeInfoLabel, BorderLayout.SOUTH);
                panel.setPreferredSize(new Dimension(0, 100)); //This line of code alters the height of the JOptionPanel

                // add an ActionListener to the JComboBox
                buildingCodeBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedOffice = (String) buildingCodeBox.getSelectedItem();
                        if (!selectedOffice.equals("Select option...")) {
                            // retrieve the office information from the database
                            try {
                                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                                PreparedStatement stmt = conn.prepareStatement("SELECT office_name, building_code, location, hours FROM offices WHERE office_name=? AND building_code='PE'"); // the AND clause is used to ensure that the item is found in this specific building code since item name duplication with a different building_code is possible
                                stmt.setString(1, selectedOffice);
                                ResultSet result = stmt.executeQuery();
                                if (result.next()) {
                                    // display the office information in the label
                                    String officeInfo = "<html><b>Office Name:</b> " + result.getString("office_name") + "<br>"
                                            + "<b>Building Code:</b> " + result.getString("building_code") + "<br>"
                                            + "<b>Location:</b> " + result.getString("location") + "<br>"
                                            + "<b>Hours:</b> " + result.getString("hours") + "<br>";
                                    officeInfoLabel.setText(officeInfo);
                                }
                                // close the resources
                                result.close();
                                stmt.close();
                                conn.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            // clear the office information label
                            officeInfoLabel.setText("");
                        }
                    }
                });
                
                // create a JComboBox with the retrieved stall names
                JComboBox<String> stallBox = new JComboBox<String>();
                stallBox.addItem("Select option...");
                for (String name : stallNames) {
                    stallBox.addItem(name);
                }
                
                JLabel stallLabel = new JLabel("");
                stallLabel.setVerticalAlignment(JLabel.TOP);

                // create a JPanel to contain the components
                JPanel panel2 = new JPanel(new BorderLayout());
                panel2.add(new JLabel("View list of stalls:"), BorderLayout.NORTH);
                panel2.add(stallBox, BorderLayout.CENTER);
                panel2.add(stallLabel, BorderLayout.SOUTH);

                // add an ActionListener to the JComboBox
                stallBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedStall = (String) stallBox.getSelectedItem();
                        if (!selectedStall.equals("Select option...")) {
                            // retrieve the office information from the database
                            try {
                                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                                PreparedStatement stmt = conn.prepareStatement("SELECT stall_name, building_code, average_cost FROM stalls WHERE stall_name=? AND building_code='PE'");
                                stmt.setString(1, selectedStall);
                                ResultSet result = stmt.executeQuery();
                                if (result.next()) {
                                    // display the office information in the label
                                    String stallInfo = "<html><b>Stall Name:</b> " + result.getString("stall_name") + "<br>"
                                            + "<b>Building Code:</b> " + result.getString("building_code") + "<br>"
                                            + "<b>Average:</b> " + result.getDouble("average_cost") + "<br>";
                                    stallLabel.setText(stallInfo);
                                }
                                // close the resources
                                result.close();
                                stmt.close();
                                conn.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            // clear the office information label
                            stallLabel.setText("");
                        }
                    }
                });
                Object[] options = {"OK", "Cancel"};

             // Create a new JPanel and add both panels to it
             JPanel combinedPanel = new JPanel(new GridLayout(2, 1));
             combinedPanel.add(panel);
             combinedPanel.add(panel2);

             // Create a new JScrollPane and set its viewport to the combined panel
             JScrollPane scrollPane = new JScrollPane(combinedPanel);

             // Show the scroll pane inside the option dialog
             int choice = JOptionPane.showOptionDialog(null, scrollPane, "PE Building", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			}
		});
        
        //Codes for RH Building
        ImageIcon rhImage = new ImageIcon("src/resources/building_codes/RH Building.png");
        Image rhImg = rhImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon rhIcon = new ImageIcon(rhImg);
        
        JButton rhBtn = new JButton(rhIcon);
        rhBtn.setBounds(920,317,80,80);
        rhBtn.setFocusable(false);
        rhBtn.setBorder(null);
        rhBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				JOptionPane.showMessageDialog(jFrame, "RH Building");
				String[] officeNames = null;
                String[] stallNames = null;
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                    PreparedStatement stmt = conn.prepareStatement("SELECT office_name FROM offices WHERE building_code='RH'");
                    ResultSet result = stmt.executeQuery();
                    PreparedStatement stmt2 = conn.prepareStatement("SELECT stall_name FROM stalls WHERE building_code='RH'");
                    ResultSet result2 = stmt2.executeQuery();

                    // create an array to store the office names and stall names
                    List<String> namesList = new ArrayList<>();
                    while (result.next()) {
                        namesList.add(result.getString("office_name"));
                    }
                    officeNames = namesList.toArray(new String[namesList.size()]);
                    
                    List<String> stallnamesList = new ArrayList<>();
                    while (result2.next()) {
                        stallnamesList.add(result2.getString("stall_name"));
                    }
                    stallNames = stallnamesList.toArray(new String[stallnamesList.size()]);

                    // close the resources
                    result.close();
                    stmt.close();
                    result2.close();
                    stmt2.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                // create a JComboBox with the retrieved office names
                JComboBox<String> buildingCodeBox = new JComboBox<String>();
                buildingCodeBox.addItem("Select option...");
                for (String name : officeNames) {
                    buildingCodeBox.addItem(name);
                }

                // create a JLabel to display the office information
                JLabel officeInfoLabel = new JLabel("");
                officeInfoLabel.setVerticalAlignment(JLabel.TOP);

                // create a JPanel to contain the components
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(new JLabel("View list of offices:"), BorderLayout.NORTH);
                panel.add(buildingCodeBox, BorderLayout.CENTER);
                panel.add(officeInfoLabel, BorderLayout.SOUTH);
                panel.setPreferredSize(new Dimension(0, 100)); //This line of code alters the height of the JOptionPanel

                // add an ActionListener to the JComboBox
                buildingCodeBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedOffice = (String) buildingCodeBox.getSelectedItem();
                        if (!selectedOffice.equals("Select option...")) {
                            // retrieve the office information from the database
                            try {
                                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                                PreparedStatement stmt = conn.prepareStatement("SELECT office_name, building_code, location, hours FROM offices WHERE office_name=? AND building_code='RH'"); // the AND clause is used to ensure that the item is found in this specific building code since item name duplication with a different building_code is possible
                                stmt.setString(1, selectedOffice);
                                ResultSet result = stmt.executeQuery();
                                if (result.next()) {
                                    // display the office information in the label
                                    String officeInfo = "<html><b>Office Name:</b> " + result.getString("office_name") + "<br>"
                                            + "<b>Building Code:</b> " + result.getString("building_code") + "<br>"
                                            + "<b>Location:</b> " + result.getString("location") + "<br>"
                                            + "<b>Hours:</b> " + result.getString("hours") + "<br>";
                                    officeInfoLabel.setText(officeInfo);
                                }
                                // close the resources
                                result.close();
                                stmt.close();
                                conn.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            // clear the office information label
                            officeInfoLabel.setText("");
                        }
                    }
                });
                
                // create a JComboBox with the retrieved stall names
                JComboBox<String> stallBox = new JComboBox<String>();
                stallBox.addItem("Select option...");
                for (String name : stallNames) {
                    stallBox.addItem(name);
                }
                
                JLabel stallLabel = new JLabel("");
                stallLabel.setVerticalAlignment(JLabel.TOP);

                // create a JPanel to contain the components
                JPanel panel2 = new JPanel(new BorderLayout());
                panel2.add(new JLabel("View list of stalls:"), BorderLayout.NORTH);
                panel2.add(stallBox, BorderLayout.CENTER);
                panel2.add(stallLabel, BorderLayout.SOUTH);

                // add an ActionListener to the JComboBox
                stallBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedStall = (String) stallBox.getSelectedItem();
                        if (!selectedStall.equals("Select option...")) {
                            // retrieve the office information from the database
                            try {
                                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                                PreparedStatement stmt = conn.prepareStatement("SELECT stall_name, building_code, average_cost FROM stalls WHERE stall_name=? AND building_code='RH'");
                                stmt.setString(1, selectedStall);
                                ResultSet result = stmt.executeQuery();
                                if (result.next()) {
                                    // display the office information in the label
                                    String stallInfo = "<html><b>Stall Name:</b> " + result.getString("stall_name") + "<br>"
                                            + "<b>Building Code:</b> " + result.getString("building_code") + "<br>"
                                            + "<b>Average:</b> " + result.getDouble("average_cost") + "<br>";
                                    stallLabel.setText(stallInfo);
                                }
                                // close the resources
                                result.close();
                                stmt.close();
                                conn.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            // clear the office information label
                            stallLabel.setText("");
                        }
                    }
                });
                Object[] options = {"OK", "Cancel"};

             // Create a new JPanel and add both panels to it
             JPanel combinedPanel = new JPanel(new GridLayout(2, 1));
             combinedPanel.add(panel);
             combinedPanel.add(panel2);

             // Create a new JScrollPane and set its viewport to the combined panel
             JScrollPane scrollPane = new JScrollPane(combinedPanel);

             // Show the scroll pane inside the option dialog
             int choice = JOptionPane.showOptionDialog(null, scrollPane, "RH Building", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			}
		});
        
        //Codes for LRC Building
        ImageIcon lrcImage = new ImageIcon("src/resources/building_codes/LRC Building.png");
        Image lrcImg = lrcImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon lrcIcon = new ImageIcon(lrcImg);
        
        JButton lrcBtn = new JButton(lrcIcon);
        lrcBtn.setBounds(910,117,100,100);
        lrcBtn.setFocusable(false);
        lrcBtn.setBorder(null);
        lrcBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				JOptionPane.showMessageDialog(jFrame, "LRC Building");
				String[] officeNames = null;
                String[] stallNames = null;
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                    PreparedStatement stmt = conn.prepareStatement("SELECT office_name FROM offices WHERE building_code='JB'");
                    ResultSet result = stmt.executeQuery();
                    PreparedStatement stmt2 = conn.prepareStatement("SELECT stall_name FROM stalls WHERE building_code='JB'");
                    ResultSet result2 = stmt2.executeQuery();

                    // create an array to store the office names and stall names
                    List<String> namesList = new ArrayList<>();
                    while (result.next()) {
                        namesList.add(result.getString("office_name"));
                    }
                    officeNames = namesList.toArray(new String[namesList.size()]);
                    
                    List<String> stallnamesList = new ArrayList<>();
                    while (result2.next()) {
                        stallnamesList.add(result2.getString("stall_name"));
                    }
                    stallNames = stallnamesList.toArray(new String[stallnamesList.size()]);

                    // close the resources
                    result.close();
                    stmt.close();
                    result2.close();
                    stmt2.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                // create a JComboBox with the retrieved office names
                JComboBox<String> buildingCodeBox = new JComboBox<String>();
                buildingCodeBox.addItem("Select option...");
                for (String name : officeNames) {
                    buildingCodeBox.addItem(name);
                }

                // create a JLabel to display the office information
                JLabel officeInfoLabel = new JLabel("");
                officeInfoLabel.setVerticalAlignment(JLabel.TOP);

                // create a JPanel to contain the components
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(new JLabel("View list of offices:"), BorderLayout.NORTH);
                panel.add(buildingCodeBox, BorderLayout.CENTER);
                panel.add(officeInfoLabel, BorderLayout.SOUTH);
                panel.setPreferredSize(new Dimension(0, 100)); //This line of code alters the height of the JOptionPanel

                // add an ActionListener to the JComboBox
                buildingCodeBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedOffice = (String) buildingCodeBox.getSelectedItem();
                        if (!selectedOffice.equals("Select option...")) {
                            // retrieve the office information from the database
                            try {
                                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                                PreparedStatement stmt = conn.prepareStatement("SELECT office_name, building_code, location, hours FROM offices WHERE office_name=? AND building_code='JB'"); // the AND clause is used to ensure that the item is found in this specific building code since item name duplication with a different building_code is possible
                                stmt.setString(1, selectedOffice);
                                ResultSet result = stmt.executeQuery();
                                if (result.next()) {
                                    // display the office information in the label
                                    String officeInfo = "<html><b>Office Name:</b> " + result.getString("office_name") + "<br>"
                                            + "<b>Building Code:</b> " + result.getString("building_code") + "<br>"
                                            + "<b>Location:</b> " + result.getString("location") + "<br>"
                                            + "<b>Hours:</b> " + result.getString("hours") + "<br>";
                                    officeInfoLabel.setText(officeInfo);
                                }
                                // close the resources
                                result.close();
                                stmt.close();
                                conn.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            // clear the office information label
                            officeInfoLabel.setText("");
                        }
                    }
                });
                
                // create a JComboBox with the retrieved stall names
                JComboBox<String> stallBox = new JComboBox<String>();
                stallBox.addItem("Select option...");
                for (String name : stallNames) {
                    stallBox.addItem(name);
                }
                
                JLabel stallLabel = new JLabel("");
                stallLabel.setVerticalAlignment(JLabel.TOP);

                // create a JPanel to contain the components
                JPanel panel2 = new JPanel(new BorderLayout());
                panel2.add(new JLabel("View list of stalls:"), BorderLayout.NORTH);
                panel2.add(stallBox, BorderLayout.CENTER);
                panel2.add(stallLabel, BorderLayout.SOUTH);

                // add an ActionListener to the JComboBox
                stallBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedStall = (String) stallBox.getSelectedItem();
                        if (!selectedStall.equals("Select option...")) {
                            // retrieve the office information from the database
                            try {
                                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                                PreparedStatement stmt = conn.prepareStatement("SELECT stall_name, building_code, average_cost FROM stalls WHERE stall_name=? AND building_code='JB'");
                                stmt.setString(1, selectedStall);
                                ResultSet result = stmt.executeQuery();
                                if (result.next()) {
                                    // display the office information in the label
                                    String stallInfo = "<html><b>Stall Name:</b> " + result.getString("stall_name") + "<br>"
                                            + "<b>Building Code:</b> " + result.getString("building_code") + "<br>"
                                            + "<b>Average:</b> " + result.getDouble("average_cost") + "<br>";
                                    stallLabel.setText(stallInfo);
                                }
                                // close the resources
                                result.close();
                                stmt.close();
                                conn.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            // clear the office information label
                            stallLabel.setText("");
                        }
                    }
                });
                Object[] options = {"OK", "Cancel"};

             // Create a new JPanel and add both panels to it
             JPanel combinedPanel = new JPanel(new GridLayout(2, 1));
             combinedPanel.add(panel);
             combinedPanel.add(panel2);

             // Create a new JScrollPane and set its viewport to the combined panel
             JScrollPane scrollPane = new JScrollPane(combinedPanel);

             // Show the scroll pane inside the option dialog
             int choice = JOptionPane.showOptionDialog(null, scrollPane, "LRC Building", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			}
		});
        
        //Codes for MR Building
        ImageIcon mrImage = new ImageIcon("src/resources/building_codes/MR Building.png");
        Image mrImg = mrImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon mrIcon = new ImageIcon(mrImg);
        
        JButton mrBtn = new JButton(mrIcon);
        mrBtn.setBounds(730,50,80,80);
        mrBtn.setFocusable(false);
        mrBtn.setBorder(null);
        mrBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				JOptionPane.showMessageDialog(jFrame, "MR Building");
				String[] officeNames = null;
                String[] stallNames = null;
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                    PreparedStatement stmt = conn.prepareStatement("SELECT office_name FROM offices WHERE building_code='MR'");
                    ResultSet result = stmt.executeQuery();
                    PreparedStatement stmt2 = conn.prepareStatement("SELECT stall_name FROM stalls WHERE building_code='MR'");
                    ResultSet result2 = stmt2.executeQuery();

                    // create an array to store the office names and stall names
                    List<String> namesList = new ArrayList<>();
                    while (result.next()) {
                        namesList.add(result.getString("office_name"));
                    }
                    officeNames = namesList.toArray(new String[namesList.size()]);
                    
                    List<String> stallnamesList = new ArrayList<>();
                    while (result2.next()) {
                        stallnamesList.add(result2.getString("stall_name"));
                    }
                    stallNames = stallnamesList.toArray(new String[stallnamesList.size()]);

                    // close the resources
                    result.close();
                    stmt.close();
                    result2.close();
                    stmt2.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                // create a JComboBox with the retrieved office names
                JComboBox<String> buildingCodeBox = new JComboBox<String>();
                buildingCodeBox.addItem("Select option...");
                for (String name : officeNames) {
                    buildingCodeBox.addItem(name);
                }

                // create a JLabel to display the office information
                JLabel officeInfoLabel = new JLabel("");
                officeInfoLabel.setVerticalAlignment(JLabel.TOP);

                // create a JPanel to contain the components
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(new JLabel("View list of offices:"), BorderLayout.NORTH);
                panel.add(buildingCodeBox, BorderLayout.CENTER);
                panel.add(officeInfoLabel, BorderLayout.SOUTH);
                panel.setPreferredSize(new Dimension(0, 100)); //This line of code alters the height of the JOptionPanel

                // add an ActionListener to the JComboBox
                buildingCodeBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedOffice = (String) buildingCodeBox.getSelectedItem();
                        if (!selectedOffice.equals("Select option...")) {
                            // retrieve the office information from the database
                            try {
                                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                                PreparedStatement stmt = conn.prepareStatement("SELECT office_name, building_code, location, hours FROM offices WHERE office_name=? AND building_code='MR'"); // the AND clause is used to ensure that the item is found in this specific building code since item name duplication with a different building_code is possible
                                stmt.setString(1, selectedOffice);
                                ResultSet result = stmt.executeQuery();
                                if (result.next()) {
                                    // display the office information in the label
                                    String officeInfo = "<html><b>Office Name:</b> " + result.getString("office_name") + "<br>"
                                            + "<b>Building Code:</b> " + result.getString("building_code") + "<br>"
                                            + "<b>Location:</b> " + result.getString("location") + "<br>"
                                            + "<b>Hours:</b> " + result.getString("hours") + "<br>";
                                    officeInfoLabel.setText(officeInfo);
                                }
                                // close the resources
                                result.close();
                                stmt.close();
                                conn.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            // clear the office information label
                            officeInfoLabel.setText("");
                        }
                    }
                });
                
                // create a JComboBox with the retrieved stall names
                JComboBox<String> stallBox = new JComboBox<String>();
                stallBox.addItem("Select option...");
                for (String name : stallNames) {
                    stallBox.addItem(name);
                }
                
                JLabel stallLabel = new JLabel("");
                stallLabel.setVerticalAlignment(JLabel.TOP);

                // create a JPanel to contain the components
                JPanel panel2 = new JPanel(new BorderLayout());
                panel2.add(new JLabel("View list of stalls:"), BorderLayout.NORTH);
                panel2.add(stallBox, BorderLayout.CENTER);
                panel2.add(stallLabel, BorderLayout.SOUTH);

                // add an ActionListener to the JComboBox
                stallBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedStall = (String) stallBox.getSelectedItem();
                        if (!selectedStall.equals("Select option...")) {
                            // retrieve the office information from the database
                            try {
                                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                                PreparedStatement stmt = conn.prepareStatement("SELECT stall_name, building_code, average_cost FROM stalls WHERE stall_name=? AND building_code='MR'");
                                stmt.setString(1, selectedStall);
                                ResultSet result = stmt.executeQuery();
                                if (result.next()) {
                                    // display the office information in the label
                                    String stallInfo = "<html><b>Stall Name:</b> " + result.getString("stall_name") + "<br>"
                                            + "<b>Building Code:</b> " + result.getString("building_code") + "<br>"
                                            + "<b>Average:</b> " + result.getDouble("average_cost") + "<br>";
                                    stallLabel.setText(stallInfo);
                                }
                                // close the resources
                                result.close();
                                stmt.close();
                                conn.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            // clear the office information label
                            stallLabel.setText("");
                        }
                    }
                });
                Object[] options = {"OK", "Cancel"};

             // Create a new JPanel and add both panels to it
             JPanel combinedPanel = new JPanel(new GridLayout(2, 1));
             combinedPanel.add(panel);
             combinedPanel.add(panel2);

             // Create a new JScrollPane and set its viewport to the combined panel
             JScrollPane scrollPane = new JScrollPane(combinedPanel);

             // Show the scroll pane inside the option dialog
             int choice = JOptionPane.showOptionDialog(null, scrollPane, "MR Building", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			}
		});
        
        //Codes for AF Building
        ImageIcon afImage = new ImageIcon("src/resources/building_codes/SAFAD Building.png");
        Image afImg = afImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon afIcon = new ImageIcon(afImg);
        
        JButton afBtn = new JButton(afIcon);
        afBtn.setBounds(581,67,100,100);
        afBtn.setFocusable(false);
        afBtn.setBorder(null);
        afBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				JOptionPane.showMessageDialog(jFrame, "AF Building");
				String[] officeNames = null;
                String[] stallNames = null;
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                    PreparedStatement stmt = conn.prepareStatement("SELECT office_name FROM offices WHERE building_code='AF'");
                    ResultSet result = stmt.executeQuery();
                    PreparedStatement stmt2 = conn.prepareStatement("SELECT stall_name FROM stalls WHERE building_code='AF'");
                    ResultSet result2 = stmt2.executeQuery();

                    // create an array to store the office names and stall names
                    List<String> namesList = new ArrayList<>();
                    while (result.next()) {
                        namesList.add(result.getString("office_name"));
                    }
                    officeNames = namesList.toArray(new String[namesList.size()]);
                    
                    List<String> stallnamesList = new ArrayList<>();
                    while (result2.next()) {
                        stallnamesList.add(result2.getString("stall_name"));
                    }
                    stallNames = stallnamesList.toArray(new String[stallnamesList.size()]);

                    // close the resources
                    result.close();
                    stmt.close();
                    result2.close();
                    stmt2.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                // create a JComboBox with the retrieved office names
                JComboBox<String> buildingCodeBox = new JComboBox<String>();
                buildingCodeBox.addItem("Select option...");
                for (String name : officeNames) {
                    buildingCodeBox.addItem(name);
                }

                // create a JLabel to display the office information
                JLabel officeInfoLabel = new JLabel("");
                officeInfoLabel.setVerticalAlignment(JLabel.TOP);

                // create a JPanel to contain the components
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(new JLabel("View list of offices:"), BorderLayout.NORTH);
                panel.add(buildingCodeBox, BorderLayout.CENTER);
                panel.add(officeInfoLabel, BorderLayout.SOUTH);
                panel.setPreferredSize(new Dimension(0, 100)); //This line of code alters the height of the JOptionPanel

                // add an ActionListener to the JComboBox
                buildingCodeBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedOffice = (String) buildingCodeBox.getSelectedItem();
                        if (!selectedOffice.equals("Select option...")) {
                            // retrieve the office information from the database
                            try {
                                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                                PreparedStatement stmt = conn.prepareStatement("SELECT office_name, building_code, location, hours FROM offices WHERE office_name=? AND building_code='AF'"); // the AND clause is used to ensure that the item is found in this specific building code since item name duplication with a different building_code is possible
                                stmt.setString(1, selectedOffice);
                                ResultSet result = stmt.executeQuery();
                                if (result.next()) {
                                    // display the office information in the label
                                    String officeInfo = "<html><b>Office Name:</b> " + result.getString("office_name") + "<br>"
                                            + "<b>Building Code:</b> " + result.getString("building_code") + "<br>"
                                            + "<b>Location:</b> " + result.getString("location") + "<br>"
                                            + "<b>Hours:</b> " + result.getString("hours") + "<br>";
                                    officeInfoLabel.setText(officeInfo);
                                }
                                // close the resources
                                result.close();
                                stmt.close();
                                conn.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            // clear the office information label
                            officeInfoLabel.setText("");
                        }
                    }
                });
                
                // create a JComboBox with the retrieved stall names
                JComboBox<String> stallBox = new JComboBox<String>();
                stallBox.addItem("Select option...");
                for (String name : stallNames) {
                    stallBox.addItem(name);
                }
                
                JLabel stallLabel = new JLabel("");
                stallLabel.setVerticalAlignment(JLabel.TOP);

                // create a JPanel to contain the components
                JPanel panel2 = new JPanel(new BorderLayout());
                panel2.add(new JLabel("View list of stalls:"), BorderLayout.NORTH);
                panel2.add(stallBox, BorderLayout.CENTER);
                panel2.add(stallLabel, BorderLayout.SOUTH);

                // add an ActionListener to the JComboBox
                stallBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedStall = (String) stallBox.getSelectedItem();
                        if (!selectedStall.equals("Select option...")) {
                            // retrieve the office information from the database
                            try {
                                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                                PreparedStatement stmt = conn.prepareStatement("SELECT stall_name, building_code, average_cost FROM stalls WHERE stall_name=? AND building_code='AF'");
                                stmt.setString(1, selectedStall);
                                ResultSet result = stmt.executeQuery();
                                if (result.next()) {
                                    // display the office information in the label
                                    String stallInfo = "<html><b>Stall Name:</b> " + result.getString("stall_name") + "<br>"
                                            + "<b>Building Code:</b> " + result.getString("building_code") + "<br>"
                                            + "<b>Average:</b> " + result.getDouble("average_cost") + "<br>";
                                    stallLabel.setText(stallInfo);
                                }
                                // close the resources
                                result.close();
                                stmt.close();
                                conn.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            // clear the office information label
                            stallLabel.setText("");
                        }
                    }
                });
                Object[] options = {"OK", "Cancel"};

             // Create a new JPanel and add both panels to it
             JPanel combinedPanel = new JPanel(new GridLayout(2, 1));
             combinedPanel.add(panel);
             combinedPanel.add(panel2);

             // Create a new JScrollPane and set its viewport to the combined panel
             JScrollPane scrollPane = new JScrollPane(combinedPanel);

             // Show the scroll pane inside the option dialog
             int choice = JOptionPane.showOptionDialog(null, scrollPane, "AF Building", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			}
		});
        
        //Codes for Dormitory Codes
        ImageIcon dormImage = new ImageIcon("src/resources/building_codes/Dormitory.png");
        Image dormImg = dormImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon dormIcon = new ImageIcon(dormImg);
        
        JButton dormBtn = new JButton(dormIcon);
        dormBtn.setBounds(595,480,80,80);
        dormBtn.setFocusable(false);
        dormBtn.setBorder(null);
        dormBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] officeNames = null;
                String[] stallNames = null;
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                    PreparedStatement stmt = conn.prepareStatement("SELECT office_name FROM offices WHERE building_code='DR'");
                    ResultSet result = stmt.executeQuery();
                    PreparedStatement stmt2 = conn.prepareStatement("SELECT stall_name FROM stalls WHERE building_code='DR'");
                    ResultSet result2 = stmt2.executeQuery();

                    // create an array to store the office names and stall names
                    List<String> namesList = new ArrayList<>();
                    while (result.next()) {
                        namesList.add(result.getString("office_name"));
                    }
                    officeNames = namesList.toArray(new String[namesList.size()]);
                    
                    List<String> stallnamesList = new ArrayList<>();
                    while (result2.next()) {
                        stallnamesList.add(result2.getString("stall_name"));
                    }
                    stallNames = stallnamesList.toArray(new String[stallnamesList.size()]);

                    // close the resources
                    result.close();
                    stmt.close();
                    result2.close();
                    stmt2.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                // create a JComboBox with the retrieved office names
                JComboBox<String> buildingCodeBox = new JComboBox<String>();
                buildingCodeBox.addItem("Select option...");
                for (String name : officeNames) {
                    buildingCodeBox.addItem(name);
                }

                // create a JLabel to display the office information
                JLabel officeInfoLabel = new JLabel("");
                officeInfoLabel.setVerticalAlignment(JLabel.TOP);

                // create a JPanel to contain the components
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(new JLabel("View list of offices:"), BorderLayout.NORTH);
                panel.add(buildingCodeBox, BorderLayout.CENTER);
                panel.add(officeInfoLabel, BorderLayout.SOUTH);
                panel.setPreferredSize(new Dimension(0, 100)); //This line of code alters the height of the JOptionPanel

                // add an ActionListener to the JComboBox
                buildingCodeBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedOffice = (String) buildingCodeBox.getSelectedItem();
                        if (!selectedOffice.equals("Select option...")) {
                            // retrieve the office information from the database
                            try {
                                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                                PreparedStatement stmt = conn.prepareStatement("SELECT office_name, building_code, location, hours FROM offices WHERE office_name=? AND building_code='DR'"); // the AND clause is used to ensure that the item is found in this specific building code since item name duplication with a different building_code is possible
                                stmt.setString(1, selectedOffice);
                                ResultSet result = stmt.executeQuery();
                                if (result.next()) {
                                    // display the office information in the label
                                    String officeInfo = "<html><b>Office Name:</b> " + result.getString("office_name") + "<br>"
                                            + "<b>Building Code:</b> " + result.getString("building_code") + "<br>"
                                            + "<b>Location:</b> " + result.getString("location") + "<br>"
                                            + "<b>Hours:</b> " + result.getString("hours") + "<br>";
                                    officeInfoLabel.setText(officeInfo);
                                }
                                // close the resources
                                result.close();
                                stmt.close();
                                conn.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            // clear the office information label
                            officeInfoLabel.setText("");
                        }
                    }
                });
                
                // create a JComboBox with the retrieved stall names
                JComboBox<String> stallBox = new JComboBox<String>();
                stallBox.addItem("Select option...");
                for (String name : stallNames) {
                    stallBox.addItem(name);
                }
                
                JLabel stallLabel = new JLabel("");
                stallLabel.setVerticalAlignment(JLabel.TOP);

                // create a JPanel to contain the components
                JPanel panel2 = new JPanel(new BorderLayout());
                panel2.add(new JLabel("View list of stalls:"), BorderLayout.NORTH);
                panel2.add(stallBox, BorderLayout.CENTER);
                panel2.add(stallLabel, BorderLayout.SOUTH);

                // add an ActionListener to the JComboBox
                stallBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedStall = (String) stallBox.getSelectedItem();
                        if (!selectedStall.equals("Select option...")) {
                            // retrieve the office information from the database
                            try {
                                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                                PreparedStatement stmt = conn.prepareStatement("SELECT stall_name, building_code, average_cost FROM stalls WHERE stall_name=? AND building_code='DR'");
                                stmt.setString(1, selectedStall);
                                ResultSet result = stmt.executeQuery();
                                if (result.next()) {
                                    // display the office information in the label
                                    String stallInfo = "<html><b>Stall Name:</b> " + result.getString("stall_name") + "<br>"
                                            + "<b>Building Code:</b> " + result.getString("building_code") + "<br>"
                                            + "<b>Average:</b> " + result.getDouble("average_cost") + "<br>";
                                    stallLabel.setText(stallInfo);
                                }
                                // close the resources
                                result.close();
                                stmt.close();
                                conn.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            // clear the office information label
                            stallLabel.setText("");
                        }
                    }
                });
                Object[] options = {"OK", "Cancel"};

             // Create a new JPanel and add both panels to it
             JPanel combinedPanel = new JPanel(new GridLayout(2, 1));
             combinedPanel.add(panel);
             combinedPanel.add(panel2);

             // Create a new JScrollPane and set its viewport to the combined panel
             JScrollPane scrollPane = new JScrollPane(combinedPanel);

             // Show the scroll pane inside the option dialog
             int choice = JOptionPane.showOptionDialog(null, scrollPane, "Dormitory", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			}
		});
        
        mapPanel.add(mapLabel);
        mapPanel.add(lbBtn);
        mapPanel.add(smedBtn);
        mapPanel.add(peBtn);
        mapPanel.add(rhBtn);
        mapPanel.add(lrcBtn);
        mapPanel.add(mrBtn);
        mapPanel.add(afBtn);
        mapPanel.add(dormBtn);
        
        JScrollPane scrollBar = new JScrollPane(mapPanel);
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
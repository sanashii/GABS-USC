package finalProject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EditOfficeGUI {
	String selectedOption;
	String selectedBuilding;
	String selectedOption2;
	Date openingHours;
	Date closingHours;
	
	@SuppressWarnings("unused")
	EditOfficeGUI(String username){
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
        jPanel.setBounds(85, 83, 350, 540);
        jPanel.setLayout(null); 
        
        JLabel titleLabel = new JLabel("Editing an Office");
        titleLabel.setBounds(72, 20, 400, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        jPanel.add(titleLabel);
        
        JButton backButton = new JButton();
        backButton.setIcon(new ImageIcon("src/resources/previous.png"));
        backButton.setBounds(20, 20, 40, 40);
        backButton.setBorderPainted(false);
        backButton.addActionListener(e -> {
            jFrame.dispose();
            MapSegmentGUI superUserMenu = new MapSegmentGUI(username);
        });
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        headerPanel.add(backButton);
        
        jFrame.getContentPane().setBackground(Color.white); // set main background to white

        jFrame.add(headerPanel);
        jFrame.add(jPanel);

        JLabel officeIDLabel = new JLabel("Enter office ID:");
        officeIDLabel.setBounds(20, 80, 300, 30);
        jPanel.add(officeIDLabel);
        JTextField officeIDField = new JTextField();
        officeIDField.setBounds(20, 120, 150, 30);
        jPanel.add(officeIDField);

        JLabel buildingCodeLabel = new JLabel("Select building:");
        buildingCodeLabel.setBounds(20, 170, 300, 30);
        jPanel.add(buildingCodeLabel);
        String[] options = {"Lawrence Bunzel Building (LB)", 
        					"University Dormitory (DR)", 
        					"Enrique Shoenig (ES)", 
        					"Franz Oster (FO)", 
        					"Edgar Oehler (EO)", 
        					"SMED Building (SM)", 
        					"Philip Van Engelen Building (PE)", 
        					"Robert Hoeppener Building (RH)", 
        					"Joseph Baumgartner LRC (JB)", 
        					"Michael Richartz Center (MR)", 
        					"SAFAD Building (AF)"};
        JComboBox<String> buildingCodeBox = new JComboBox<String>(options);
        buildingCodeBox.setBounds(20, 210, 250, 30);

        buildingCodeBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    switch((String) buildingCodeBox.getSelectedItem()) {
                        case "Lawrence Bunzel Building (LB)":
                            selectedOption = "LB";
                            break;
                        case "University Dormitory (DR)":
                            selectedOption = "DR";
                            break;
                        case "Enrique Shoenig (ES)":
                            selectedOption = "ES";
                            break;
                        case "Franz Oster (FO)":
                            selectedOption = "FO";
                            break;
                        case "Edgar Oehler (EO)":
                            selectedOption = "EO";
                            break;
                        case "SMED Building (SM)":
                            selectedOption = "SM";
                            break;
                        case "Philip Van Engelen Building (PE)":
                            selectedOption = "PE";
                            break;
                        case "Robert Hoeppener Building (RH)":
                            selectedOption = "RH";
                            break;
                        case "Joseph Baumgartner LRC (JB)":
                            selectedOption = "JB";
                            break;
                        case "Michael Richartz Center (MR)":
                            selectedOption = "MR";
                            break;
                        case "SAFAD Building (AF)":
                            selectedOption = "AF";
                            break;
                    }
                }
            }
        });

        jPanel.add(buildingCodeBox);

        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setBounds(20, 260, 300, 30);
        jPanel.add(locationLabel);
        JTextField locationField = new JTextField();
        locationField.setBounds(20, 300, 150, 30);
        jPanel.add(locationField);
        
        JButton editTimeOffice = new JButton("Edit Time");
        Border buttonBorder = BorderFactory.createLineBorder(new Color(29, 142, 0), 2, true);
        editTimeOffice.setBounds(110, 400, 120, 30);
        editTimeOffice.setForeground(new Color(29, 142, 0));
        editTimeOffice.setBackground(Color.white);
        editTimeOffice.setBorder(buttonBorder);
        editTimeOffice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditOfficeTimeGUI time = new EditOfficeTimeGUI(username);
				jFrame.dispose();
			}
        });
        
        JButton editOffice = new JButton("Apply Changes");
        buttonBorder = BorderFactory.createLineBorder(new Color(29, 142, 0), 2, true);
        editOffice.setBounds(110, 480, 120, 30);
        editOffice.setForeground(new Color(29, 142, 0));
        editOffice.setBackground(Color.white);
        editOffice.setBorder(buttonBorder);
        editOffice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                    Statement statement = connection.createStatement();
                    
                    int id = Integer.parseInt(officeIDField.getText());

                    // Check if the ID exists in the table
                    ResultSet idCheck = statement.executeQuery("SELECT * FROM offices WHERE office_ID = " + id);
                    if (!idCheck.next()) {
                        JOptionPane.showMessageDialog(jPanel, "Office ID does not exist.");
                        return;
                    }
                    
                    String sql = "UPDATE offices SET building_code = ?, location = ? WHERE office_ID = " + id;

                    String buildingCode = selectedOption;
                    String location = locationField.getText();

                    // Check if input fields have changed
                    boolean hasChanged = false;
                    ResultSet rs = statement.executeQuery("SELECT * FROM offices WHERE office_ID = " + id);
                    if (rs.next()) {
                    	String currentBuilding = rs.getString("building_code");
                        String currentLocation = rs.getString("location");
                        if (!currentBuilding.equals(buildingCode) || !currentLocation.equals(location)) {
                            hasChanged = true;
                        }
                    }
                    rs.close();

                    if (hasChanged) {
                        java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, buildingCode);
                        preparedStatement.setString(2, location);
                        preparedStatement.executeUpdate();
                        preparedStatement.close();
                    }

                    statement.close();
                    connection.close();

                    JOptionPane.showMessageDialog(jFrame, "Successfully updated office!");
                    jFrame.dispose();
                    EditOfficeGUI add = new EditOfficeGUI(username); // calls itself so if every they want to add another one, they wont be exited from the menu
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }

            }

        });
        jPanel.add(editOffice);
        
        JButton viewOfficesTable = new JButton("View Offices Table");
        viewOfficesTable.setBounds(110, 440, 120, 30);
        viewOfficesTable.setForeground(new Color(29, 142, 0));
        viewOfficesTable.setBackground(Color.white);
        viewOfficesTable.setBorder(buttonBorder);
        viewOfficesTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				ViewOfficesTable viewOfficesTable = new ViewOfficesTable(username, "edit");
				jFrame.dispose();
			}
        	
        });
        jPanel.add(viewOfficesTable);
        
        
        jFrame.setTitle("GABS USC");
        jFrame.setSize(540, 810); // Android Screen Ratio
        jFrame.setLayout(null);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null); // setting the JFrame to appear in the middle

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

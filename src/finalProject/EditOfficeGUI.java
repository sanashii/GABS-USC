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
	String id;
	String selectedOption;
	String selectedBuilding;
	String selectedOption2;
	Date openingHours;
	Date closingHours;
	//! CONSTRAINT: have to edit all field due to conlfict in database updates
	
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

        JLabel officeIDLabel = new JLabel("Select office ID:");
        officeIDLabel.setBounds(20, 80, 300, 30);
        jPanel.add(officeIDLabel);

        // Replace the JTextField with a JComboBox
        JComboBox<String> officeIDComboBox = new JComboBox<String>();
        officeIDComboBox.setBounds(20, 120, 300, 30);
        jPanel.add(officeIDComboBox);

        // Fetch the IDs from the database and populate the JComboBox
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
            Statement statement = connection.createStatement();

            ResultSet idResultSet = statement.executeQuery("SELECT office_ID FROM offices");
            while (idResultSet.next()) {
                id = idResultSet.getString("office_ID");
                officeIDComboBox.addItem(id);
            }

            statement.close();
            connection.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        JLabel buildingCodeLabel = new JLabel("Select building:");
        buildingCodeLabel.setBounds(20, 170, 300, 30);
        jPanel.add(buildingCodeLabel);
        String[] options = {"Select Building",
        					"Lawrence Bunzel Building (LB)", 
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
        buildingCodeBox.setBounds(20, 210, 300, 30);

        buildingCodeBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    switch((String) buildingCodeBox.getSelectedItem()) {
                    	case "Select Building":
                    		selectedOption = null;
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
        locationField.setBounds(20, 300, 300, 30);
        jPanel.add(locationField);
        
        JLabel hoursLabel = new JLabel("Hours:");
        hoursLabel.setBounds(20, 350, 300, 30);
        jPanel.add(hoursLabel);
        openingHours = new Date(); // set the default value to the current time
        SpinnerDateModel openingHoursModel = new SpinnerDateModel(openingHours, null, null, Calendar.HOUR_OF_DAY);
        JSpinner openingHoursSpinner = new JSpinner(openingHoursModel);
        openingHours = (Date) openingHoursSpinner.getValue();

        closingHours = new Date(); 
        SpinnerDateModel closingHoursModel = new SpinnerDateModel(closingHours, null, null, Calendar.HOUR_OF_DAY);
        JSpinner closingHoursSpinner = new JSpinner(closingHoursModel);
        closingHours = (Date) closingHoursSpinner.getValue();

        JSpinner.DateEditor editor = new JSpinner.DateEditor(openingHoursSpinner, "hh:mm a");
        openingHoursSpinner.setEditor(editor);

        editor = new JSpinner.DateEditor(closingHoursSpinner, "hh:mm a");
        closingHoursSpinner.setEditor(editor);
        
        openingHoursSpinner.setPreferredSize(new Dimension(150, 30));
        openingHoursSpinner.addChangeListener(new ChangeListener() { //updates the time based on the one that was set
            public void stateChanged(ChangeEvent e) {
                openingHours = (Date) openingHoursSpinner.getValue();
            }
        });
        closingHoursSpinner.setPreferredSize(new Dimension(150, 30));
        closingHoursSpinner.addChangeListener(new ChangeListener() { //updates the time based on the one that was set
            public void stateChanged(ChangeEvent e) {
                closingHours = (Date) closingHoursSpinner.getValue();
            }
        });

        JPanel hoursPanel = new JPanel();
        hoursPanel.setLayout(new GridLayout(1,2));
        hoursPanel.add(openingHoursSpinner);
        hoursPanel.add(new JLabel(" to "));
        hoursPanel.add(closingHoursSpinner);

        hoursPanel.setBounds(20, 390, 220, 30);
        jPanel.add(hoursPanel);
        
        JButton editOffice = new JButton("Apply Changes");
        Border buttonBorder = BorderFactory.createLineBorder(new Color(29, 142, 0), 2, true);
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

                    // Check if the ID exists in the table
                    ResultSet idCheck = statement.executeQuery("SELECT * FROM offices WHERE office_ID = " + id);
                    if (!idCheck.next()) {
                        JOptionPane.showMessageDialog(jPanel, "Office ID does not exist.");
                        return;
                    }
                    
                    String sql = "UPDATE offices SET building_code = ?, location = ?, hours = ? WHERE office_ID = " + id;

                    String buildingCode = selectedOption;
                    String location = locationField.getText();
                    DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
					String hours = dateFormat.format(openingHours) + " - " + dateFormat.format(closingHours);
                    java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, buildingCode);
                    preparedStatement.setString(2, location);
                    preparedStatement.setString(3, hours);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    

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

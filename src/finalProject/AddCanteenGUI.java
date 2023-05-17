package finalProject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class AddCanteenGUI {
	String selectedOption;
	@SuppressWarnings("unused")
	AddCanteenGUI(String username){
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
        
        JLabel titleLabel = new JLabel("Adding a Canteen");
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

        JLabel canteenNameLabel = new JLabel("Enter canteen name:");
        canteenNameLabel.setBounds(20, 80, 300, 30);
        jPanel.add(canteenNameLabel);
        JTextField canteenNameField = new JTextField();
        canteenNameField.setBounds(20, 120, 300, 30);
        jPanel.add(canteenNameField);


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

        JButton addCanteen = new JButton("Add Canteen");
        Border buttonBorder = BorderFactory.createLineBorder(new Color(29, 142, 0), 2, true);
        addCanteen.setBounds(110, 480, 120, 30);
        addCanteen.setForeground(new Color(29, 142, 0));
        addCanteen.setBackground(Color.white);
        addCanteen.setBorder(buttonBorder);
        addCanteen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String canteenName = canteenNameField.getText();
		        String buildingCode = selectedOption;
		        
				if (canteenName.isEmpty() || buildingCode == null) {
		            JOptionPane.showMessageDialog(jFrame, "Please fill in all fields.");
		            return;
		        }
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
			        Statement statement = connection.createStatement();
			        String sql = "INSERT INTO canteens (canteen_name, building_code) " +
			                 		"VALUES (?, ?)";

			        // Check if canteen already exists in same building
			        String checkSql = "SELECT * FROM canteens WHERE canteen_name = ? AND building_code = ?";
			        java.sql.PreparedStatement checkStatement = connection.prepareStatement(checkSql);
			        checkStatement.setString(1, canteenName);
			        checkStatement.setString(2, buildingCode);
			        ResultSet resultSet = checkStatement.executeQuery();
			        if (resultSet.next()) {
			            // Canteen already exists
			            JOptionPane.showMessageDialog(jFrame, "Canteen already exists in this building.");
			            return;
			        }
			        checkStatement.close();
			        resultSet.close();

			        // If canteen does not already exist, add to database
			        String addSql = "INSERT INTO canteens (canteen_name, building_code) VALUES (?, ?)";
			        java.sql.PreparedStatement preparedStatement = connection.prepareStatement(addSql);
			        preparedStatement.setString(1, canteenName);
			        preparedStatement.setString(2, buildingCode);

			        int option = JOptionPane.showConfirmDialog(jFrame, "Are you sure you want to add this canteen?", "Add Canteen", JOptionPane.YES_NO_OPTION);
			        if (option == JOptionPane.YES_OPTION) {
			            preparedStatement.executeUpdate();
			            JOptionPane.showMessageDialog(jFrame, "Successfully added canteen!");
			            jFrame.dispose();
			            @SuppressWarnings("unused")
			            AddCanteenGUI newCanteen = new AddCanteenGUI(username);
			        }
			        preparedStatement.close();

				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				
			}
        	
        });
        jPanel.add(addCanteen);
        
        JButton viewOfficesTable = new JButton("View Canteens Table");
        viewOfficesTable.setBounds(110, 440, 120, 30);
        viewOfficesTable.setForeground(new Color(29, 142, 0));
        viewOfficesTable.setBackground(Color.white);
        viewOfficesTable.setBorder(buttonBorder);
        viewOfficesTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				ViewCanteensTable viewCanteensTable = new ViewCanteensTable(username);
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

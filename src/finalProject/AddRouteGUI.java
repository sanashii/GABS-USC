package finalProject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AddRouteGUI {
	String selectedOption;
	@SuppressWarnings("unused")
	AddRouteGUI(String username) throws SQLException, FileNotFoundException{
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
        jPanel.setBounds(85, 83, 350, 560);
        jPanel.setLayout(null); 
        
        JLabel titleLabel = new JLabel("Adding a New Route");
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
            TranspoSegmentGUI superUserMenu = new TranspoSegmentGUI(username);
        });
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        headerPanel.add(backButton);
        
        jFrame.getContentPane().setBackground(Color.white); // set main background to white

        jFrame.add(headerPanel);
        jFrame.add(jPanel);

        JLabel routeNameLabel = new JLabel("Enter route name:");
        routeNameLabel.setBounds(20, 80, 300, 30);
        jPanel.add(routeNameLabel);
        JTextField routeNameField = new JTextField();
        routeNameField.setBounds(20, 120, 300, 30);
        jPanel.add(routeNameField);


        JLabel jeepsToTakeLabel = new JLabel("Jeepneys to take (separated by a ','):");
        jeepsToTakeLabel.setBounds(20, 170, 300, 30);
        jPanel.add(jeepsToTakeLabel);
        JTextField jeepsToTakeField = new JTextField();
        jeepsToTakeField.setBounds(20, 210, 300, 30);
        jPanel.add(jeepsToTakeField);

        JLabel uploadRouteLabel = new JLabel("Upload route:");
        uploadRouteLabel.setBounds(20, 260, 300, 30);
        jPanel.add(uploadRouteLabel);

        JTextField uploadRouteField = new JTextField();
        uploadRouteField.setBounds(20, 300, 185, 30);
        uploadRouteField.setEditable(false); // Disable direct text input
        jPanel.add(uploadRouteField);

        // Button to open file chooser
        JButton selectFileButton = new JButton("Select File");
        selectFileButton.setBounds(220, 300, 100, 30);
        selectFileButton.setForeground(new Color(29, 142, 0));
        selectFileButton.setBackground(Color.white);
        jPanel.add(selectFileButton);

        // ActionListener for selectFileButton
        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Route Files", "png", "jpg", "jpeg"); // Customize the file types if needed
                fileChooser.setFileFilter(filter);

                int result = fileChooser.showOpenDialog(jFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getPath();
                    String fileName = fileChooser.getSelectedFile().getName(); // Extract the file name
                    uploadRouteField.setText(fileName);
                }
            }
        });
        
        JLabel routeTagLabel = new JLabel("Select route tag:");
        routeTagLabel.setBounds(20, 350, 300, 30);
        jPanel.add(routeTagLabel);
        String[] options = {"Select Route Tag...",
        					"From USC TC",
        					"To USC TC"};
        JComboBox<String> routeTagBox = new JComboBox<String>(options);
        routeTagBox.setBounds(20, 390, 300, 30);

        routeTagBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    switch((String) routeTagBox.getSelectedItem()) {
	                    case "Select Building":
	                		selectedOption = "N";
                        case "From USC TC":
                            selectedOption = "F";
                            break;
                        case "To USC TC":
                            selectedOption = "T";
                            break;
                    }
                }
            }
        });

        jPanel.add(routeTagBox);

        JButton addRoute = new JButton("Add Route");
        Border buttonBorder = BorderFactory.createLineBorder(new Color(29, 142, 0), 2, true);
        addRoute.setBounds(110, 460, 120, 30);
        addRoute.setForeground(new Color(29, 142, 0));
        addRoute.setBackground(Color.white);
        addRoute.setBorder(buttonBorder);
        addRoute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String routeName = routeNameField.getText();
		        String jeepsToTake = jeepsToTakeField.getText();
		        String routeMapFile = "src/resources/routes/" + uploadRouteField.getText();
		        char routeTag = selectedOption.charAt(0);

		        if (routeName.isEmpty() || jeepsToTake.isEmpty() || routeMapFile.isEmpty() || routeTag == ' ') {
		            JOptionPane.showMessageDialog(jFrame, "Please fill in all fields.");
		            return;
		        }
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
			        Statement statement = connection.createStatement();
			        String sql = "INSERT INTO routes (route_name, traditionalJeep_Fare, modernJeep_Fare, jeepsToTake, route_map, route_tag) VALUES (?, ?, ?, ?, ?, ?)";

			        double traditionalJeepFare = 0.0;
			        double modernJeepFare = 0.0;
			        
			        // Check if office already exists in same building
			        String checkSql = "SELECT * FROM routes WHERE route_name = ?";
			        java.sql.PreparedStatement checkStatement = connection.prepareStatement(checkSql);
			        checkStatement.setString(1, routeName);
			        ResultSet resultSet = checkStatement.executeQuery();
			        if (resultSet.next()) {
			            // Route already exists
			            JOptionPane.showMessageDialog(jFrame, "Route already exists.");
			            return;
			        }

			        ResultSet resultSet2 = statement.executeQuery("SELECT traditionalJeep_fare, modernJeep_fare FROM routes WHERE route_id = 1");

			        if (resultSet2.next()) {
			            traditionalJeepFare = resultSet2.getDouble("traditionalJeep_Fare");
			            modernJeepFare = resultSet2.getDouble("modernJeep_Fare");
			        }

			        java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
			        preparedStatement.setString(1, routeName);
			        preparedStatement.setDouble(2, traditionalJeepFare);
			        preparedStatement.setDouble(3, modernJeepFare);
			        preparedStatement.setString(4, jeepsToTake);
			        preparedStatement.setString(5, routeMapFile);
			        preparedStatement.setString(6, String.valueOf(routeTag));
			        preparedStatement.executeUpdate();

			        preparedStatement.close();
			        statement.close();
			        connection.close();
			        
			        JOptionPane.showMessageDialog(jFrame, "Successfully added route!");
			        jFrame.dispose();
			        try {
						AddRouteGUI transpo = new AddRouteGUI(username);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
        	
        });
        jPanel.add(addRoute);
        
        JButton viewRoutesTable = new JButton("View Routes Table");
        viewRoutesTable.setBounds(110, 520, 120, 30);
        viewRoutesTable.setForeground(new Color(29, 142, 0));
        viewRoutesTable.setBackground(Color.white);
        viewRoutesTable.setBorder(buttonBorder);
        viewRoutesTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewRoutesTable viewRoutesTable = new ViewRoutesTable(username, "add");
				jFrame.dispose();
			}
        });
        jPanel.add(viewRoutesTable);
        
        
        jFrame.setTitle("GABS USC");
        jFrame.setSize(540, 810); // Android Screen Ratio
        jFrame.setLayout(null);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null); // setting the JFrame to appear in the middle

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
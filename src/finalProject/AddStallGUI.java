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
import java.util.HashMap;
import java.util.Map;

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

public class AddStallGUI {
	String selectedCanteen;
	String selectedBuildingCode;
	@SuppressWarnings("unused")
	AddStallGUI(String username){
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
        
        JLabel titleLabel = new JLabel("Adding a Stall");
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

        JLabel stallNameLabel = new JLabel("Enter stall name:");
        stallNameLabel.setBounds(20, 80, 300, 30);
        jPanel.add(stallNameLabel);
        JTextField stallNameField = new JTextField();
        stallNameField.setBounds(20, 120, 150, 30);
        jPanel.add(stallNameField);


        JLabel buildingCodeLabel = new JLabel("Select canteen:");
        buildingCodeLabel.setBounds(20, 170, 300, 30);
        jPanel.add(buildingCodeLabel);

        Map<String, String> canteenMap = new HashMap<String, String>();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password")) {
            Statement stmt = conn.createStatement();
            String sql = "SELECT canteen_name, building_code FROM canteens";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("canteen_name");
                String buildingCode = rs.getString("building_code");
                canteenMap.put(name, buildingCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] options = canteenMap.keySet().toArray(new String[canteenMap.size()]);

        JComboBox<String> buildingCodeBox = new JComboBox<String>(options);
        buildingCodeBox.setBounds(20, 210, 250, 30);
        jPanel.add(buildingCodeBox);

        buildingCodeBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedCanteen = (String) buildingCodeBox.getSelectedItem();
                    selectedBuildingCode = canteenMap.get(selectedCanteen); // used to add the building code for the stall while still relating it with its canteen
                }
            }
        });
        jPanel.add(buildingCodeBox);

        JLabel costLabel = new JLabel("Average cost:");
        costLabel.setBounds(20, 260, 300, 30);
        jPanel.add(costLabel);
        JTextField costField = new JTextField();
        costField.setBounds(20, 300, 150, 30);
        jPanel.add(costField);
        
        JButton addOffice = new JButton("Add Stall");
        Border buttonBorder = BorderFactory.createLineBorder(new Color(29, 142, 0), 2, true);
        addOffice.setBounds(110, 480, 120, 30);
        addOffice.setForeground(new Color(29, 142, 0));
        addOffice.setBackground(Color.white);
        addOffice.setBorder(buttonBorder);
        addOffice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                    
                    // check if the stall name and building code already exist in the database
                    String stallName = stallNameField.getText();
                    String buildingCode = selectedBuildingCode;
                    String cost = costField.getText();
                    if (stallName.isEmpty() || buildingCode.isEmpty() || cost.isEmpty()) {
                        JOptionPane.showMessageDialog(jFrame, "Please fill up all fields.");
                        return;
                    }
                    String sql = "SELECT * FROM stalls WHERE stall_name = ? AND building_code = ?";
                    java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, stallName);
                    preparedStatement.setString(2, buildingCode);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    
                    if (resultSet.next()) {
                        // stall name and building code already exist, display an error message
                        JOptionPane.showMessageDialog(jFrame, "Stall name and building code already exist.");
                    } else {
                        // stall name and building code don't exist, perform the INSERT query
                        sql = "INSERT INTO stalls (stall_name, building_code, average_cost) " +
                                  "VALUES (?, ?, ?)";
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, stallName);
                        preparedStatement.setString(2, buildingCode);
                        preparedStatement.setString(3, costField.getText());
                        preparedStatement.executeUpdate();
                        
                        JOptionPane.showMessageDialog(jFrame, "Successfully added stall!");
                    }
                    
                    preparedStatement.close();
                    resultSet.close();
                    connection.close();
                    
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
        jPanel.add(addOffice);
        
        JButton viewStallsTable = new JButton("View Stalls Table");
        viewStallsTable.setBounds(110, 440, 120, 30);
        viewStallsTable.setForeground(new Color(29, 142, 0));
        viewStallsTable.setBackground(Color.white);
        viewStallsTable.setBorder(buttonBorder);
        viewStallsTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				ViewStallsTableGUI viewStallsTable = new ViewStallsTableGUI(username, "add");
				jFrame.dispose();
			}
        	
        });
        jPanel.add(viewStallsTable);
        
        
        jFrame.setTitle("GABS USC");
        jFrame.setSize(540, 810); // Android Screen Ratio
        jFrame.setLayout(null);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null); // setting the JFrame to appear in the middle

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

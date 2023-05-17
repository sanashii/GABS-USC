package finalProject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class DeleteStallGUI {
	DeleteStallGUI(String username){
		JFrame jFrame = new JFrame();
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
        
        JLabel titleLabel = new JLabel("Deleting a Stall");
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
            @SuppressWarnings("unused")
			MapSegmentGUI superUserMenu = new MapSegmentGUI(username);
        });
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        headerPanel.add(backButton);
        
        jFrame.getContentPane().setBackground(Color.white); // set main background to white

        jFrame.add(headerPanel);
        jFrame.add(jPanel);

        JLabel stallIDLabel = new JLabel("Select stall ID:");
        stallIDLabel.setBounds(20, 80, 300, 30);
        jPanel.add(stallIDLabel);

        // Replace the JTextField with a JComboBox
        JComboBox<String> stallIDComboBox = new JComboBox<String>();
        stallIDComboBox.setBounds(20, 120, 300, 30);
        jPanel.add(stallIDComboBox);

        // Fetch the stall IDs and names from the database and populate the JComboBox
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
            Statement statement = connection.createStatement();

            ResultSet idResultSet = statement.executeQuery("SELECT stall_id, stall_name, building_code FROM stalls");
            while (idResultSet.next()) {
                String id = idResultSet.getString("stall_id");
                String name = idResultSet.getString("stall_name");
                String canteen = idResultSet.getString("building_code");
                String displayText = id + " - " + name + " - " + canteen;
                stallIDComboBox.addItem(displayText);
            }

            statement.close();
            connection.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        JButton deleteStall = new JButton("Delete Stall");
        Border buttonBorder = BorderFactory.createLineBorder(new Color(29, 142, 0), 2, true);
        deleteStall.setBounds(110, 480, 120, 30);
        deleteStall.setForeground(new Color(29, 142, 0));
        deleteStall.setBackground(Color.white);
        deleteStall.setBorder(buttonBorder);
        deleteStall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                    Statement statement = connection.createStatement();

                    // Get the selected stall ID from the JComboBox
                    String selectedStallID = (String) stallIDComboBox.getSelectedItem();
                    if (selectedStallID == null) {
                        JOptionPane.showMessageDialog(jPanel, "Please select a stall ID.");
                        return;
                    }

                    // Extract the stall ID from the selected item
                    String[] parts = selectedStallID.split(" - ");
                    String stallId = parts[0];
                    String stallName = parts[1];
                    String canteen = parts[2];

                    // Check if the ID exists in the table
                    ResultSet idCheck = statement.executeQuery("SELECT * FROM stalls WHERE stall_id = " + stallId);
                    if (!idCheck.next()) {
                        JOptionPane.showMessageDialog(jPanel, "Stall ID does not exist.");
                        return;
                    }

                    // Show confirmation dialog with stall ID and name
                    int confirmResult = JOptionPane.showConfirmDialog(jFrame, "Are you sure you want to delete this stall?\n\nStall ID: " + stallId + "\nStall Name: " + stallName + "\nBuilding Code: " + canteen, "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                    if (confirmResult == JOptionPane.YES_OPTION) {
                        String sql = "DELETE FROM stalls WHERE stall_id = " + stallId;

                        int result = statement.executeUpdate(sql);

                        if (result == 1) {
                            JOptionPane.showMessageDialog(jFrame, "Successfully deleted stall with ID " + stallId + "!");
                            jFrame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(jFrame, "Error deleting stall with ID " + stallId + ". Please check if the ID is valid.");
                        }

                        statement.close();
                        connection.close();

                        @SuppressWarnings("unused")
                        DeleteStallGUI delete = new DeleteStallGUI(username);
                        jFrame.dispose();
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
        jPanel.add(deleteStall);
        
        JButton viewStallsTable = new JButton("View Stalls Table");
        viewStallsTable.setBounds(110, 440, 120, 30);
        viewStallsTable.setForeground(new Color(29, 142, 0));
        viewStallsTable.setBackground(Color.white);
        viewStallsTable.setBorder(buttonBorder);
        viewStallsTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewStallsTableGUI viewStallsTable = new ViewStallsTableGUI(username, "delete");
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

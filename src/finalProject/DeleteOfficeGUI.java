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

public class DeleteOfficeGUI {
	@SuppressWarnings("unused")
	DeleteOfficeGUI(String username){
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
        
        JLabel titleLabel = new JLabel("Deleting an Office");
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
                String id = idResultSet.getString("office_ID");
                officeIDComboBox.addItem(id);
            }

            statement.close();
            connection.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        JButton deleteOffice = new JButton("Delete Office");
        Border buttonBorder = BorderFactory.createLineBorder(new Color(29, 142, 0), 2, true);
        deleteOffice.setBounds(110, 480, 120, 30);
        deleteOffice.setForeground(new Color(29, 142, 0));
        deleteOffice.setBackground(Color.white);
        deleteOffice.setBorder(buttonBorder);
        deleteOffice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                    Statement statement = connection.createStatement();

                    // Get the selected office ID from the JComboBox
                    String selectedOfficeID = (String) officeIDComboBox.getSelectedItem();
                    if (selectedOfficeID == null) {
                        JOptionPane.showMessageDialog(jPanel, "Please select an office ID.");
                        return;
                    }

                    int id = Integer.parseInt(selectedOfficeID);

                    // Check if the ID exists in the table
                    ResultSet idCheck = statement.executeQuery("SELECT * FROM offices WHERE office_ID = " + id);
                    if (!idCheck.next()) {
                        JOptionPane.showMessageDialog(jPanel, "Office ID does not exist.");
                        return;
                    }

                    // Fetch the office details
                    ResultSet officeDetails = statement.executeQuery("SELECT office_name, building_code FROM offices WHERE office_ID = " + id);
                    if (officeDetails.next()) {
                        String officeName = officeDetails.getString("office_name");
                        String buildingCode = officeDetails.getString("building_code");

                        // Confirmation dialog box
                        int confirm = JOptionPane.showConfirmDialog(jPanel, "Are you sure you want to delete the office with ID " + id + "?\n\nOffice Name: " + officeName + "\nBuilding Code: " + buildingCode, "Confirm Delete", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            String sql = "DELETE FROM offices WHERE office_ID = " + id;

                            int result = statement.executeUpdate(sql);

                            if (result == 1) {
                                JOptionPane.showMessageDialog(jFrame, "Successfully deleted office with ID " + id + "!");
                                jFrame.dispose();
                            } else {
                                JOptionPane.showMessageDialog(jFrame, "Error deleting office with ID " + id + ". Please check if the ID is valid.");
                            }

                            statement.close();
                            connection.close();

                            DeleteOfficeGUI delete = new DeleteOfficeGUI(username);
                            jFrame.dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(jPanel, "Failed to retrieve office details for ID " + id + ".");
                    }

                    statement.close();
                    connection.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
        jPanel.add(deleteOffice);
        
        JButton viewOfficesTable = new JButton("View Offices Table");
        viewOfficesTable.setBounds(110, 440, 120, 30);
        viewOfficesTable.setForeground(new Color(29, 142, 0));
        viewOfficesTable.setBackground(Color.white);
        viewOfficesTable.setBorder(buttonBorder);
        viewOfficesTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewOfficesTable viewOfficesTable = new ViewOfficesTable(username, "delete");
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

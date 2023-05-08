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

        JLabel stallIDLabel = new JLabel("Enter stall ID:");
        stallIDLabel.setBounds(20, 80, 300, 30);
        jPanel.add(stallIDLabel);
        JTextField stallIDField = new JTextField();
        stallIDField.setBounds(20, 120, 150, 30);
        jPanel.add(stallIDField);

        JButton deleteOffice = new JButton("Delete Stall");
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

                    int id = Integer.parseInt(stallIDField.getText());

                    // Check if the ID exists in the table
                    ResultSet idCheck = statement.executeQuery("SELECT * FROM stalls WHERE stall_id = " + id);
                    if (!idCheck.next()) {
                        JOptionPane.showMessageDialog(jPanel, "Stall ID does not exist.");
                        return;
                    }
                    
                    String stallId = stallIDField.getText(); // Get the inputted route ID
                    String sql = "DELETE FROM stalls WHERE stall_id = " + stallId; // SQL query to delete the row with the given route ID

                    int result = statement.executeUpdate(sql); 

                    if (result == 1) { 
                        JOptionPane.showMessageDialog(jFrame, "Successfully deleted route with ID " + stallId + "!");
                        jFrame.dispose();
                    } else { 
                        JOptionPane.showMessageDialog(jFrame, "Error deleting route with ID " + stallId + ". Please check if the ID is valid.");
                    }

                    statement.close();
                    connection.close();
                    
                    @SuppressWarnings("unused")
					DeleteStallGUI delete = new DeleteStallGUI(username);
                    jFrame.dispose();

                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }

            }

        });
        jPanel.add(deleteOffice);
        
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

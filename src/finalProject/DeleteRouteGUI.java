package finalProject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
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

public class DeleteRouteGUI {
	@SuppressWarnings("unused")
	DeleteRouteGUI(String username) throws SQLException, FileNotFoundException{
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
        jPanel.setBounds(85, 83, 350, 500);
        jPanel.setLayout(null); 
        
        JLabel titleLabel = new JLabel("Deleting a Route");
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

        JLabel routeIDLabel = new JLabel("Enter route id to delete:");
        routeIDLabel.setBounds(20, 80, 300, 30);
        jPanel.add(routeIDLabel);
        JTextField routeIDField = new JTextField();
        routeIDField.setBounds(20, 120, 150, 30);
        jPanel.add(routeIDField);

        JButton deleteRoute = new JButton("Delete Route");
        Border buttonBorder = BorderFactory.createLineBorder(new Color(29, 142, 0), 2, true);
        deleteRoute.setBounds(110, 380, 120, 30);
        deleteRoute.setForeground(new Color(29, 142, 0));
        deleteRoute.setBackground(Color.white);
        deleteRoute.setBorder(buttonBorder);
        deleteRoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                    Statement statement = connection.createStatement();

                    int id = Integer.parseInt(routeIDField.getText());

                    // Check if the ID exists in the routes table
                    ResultSet idCheck = statement.executeQuery("SELECT * FROM routes WHERE route_id = " + id);
                    if (!idCheck.next()) {
                        JOptionPane.showMessageDialog(jPanel, "Route ID does not exist.");
                        return;
                    }

                    int confirmDialogResult = JOptionPane.showConfirmDialog(jFrame, "Are you sure you want to delete the route with ID " + id + "?");
                    if (confirmDialogResult == JOptionPane.YES_OPTION) {
                        String sql = "DELETE FROM routes WHERE route_id = " + id; // SQL query to delete the row with the given route ID

                        int result = statement.executeUpdate(sql);

                        if (result == 1) {
                            JOptionPane.showMessageDialog(jFrame, "Successfully deleted route with ID " + id + "!");
                            jFrame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(jFrame, "Error deleting route with ID " + id + ". Please check if the ID is valid.");
                        }

                        statement.close();
                        connection.close();

                        try {
                            DeleteRouteGUI delete = new DeleteRouteGUI(username);
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        jFrame.dispose();
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }

            }

        });

        jPanel.add(deleteRoute);
        
        JButton viewRoutesTable = new JButton("View Routes Table");
        viewRoutesTable.setBounds(110, 440, 120, 30);
        viewRoutesTable.setForeground(new Color(29, 142, 0));
        viewRoutesTable.setBackground(Color.white);
        viewRoutesTable.setBorder(buttonBorder);
        viewRoutesTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewRoutesTable viewRoutesTable = new ViewRoutesTable(username, "delete");
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
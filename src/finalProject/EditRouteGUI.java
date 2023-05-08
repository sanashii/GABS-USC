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

public class EditRouteGUI {
	@SuppressWarnings("unused")
	EditRouteGUI(String username) throws SQLException, FileNotFoundException{
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
        
        JLabel titleLabel = new JLabel("Editing a Route");
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
            TranspoSegmentGUI superUserMenu = new TranspoSegmentGUI(username);
            jFrame.dispose();
        });
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        headerPanel.add(backButton);
        
        jFrame.getContentPane().setBackground(Color.white); // set main background to white

        jFrame.add(headerPanel);
        jFrame.add(jPanel);

        JLabel routeIDLabel = new JLabel("Enter route id:");
        routeIDLabel.setBounds(20, 80, 300, 30);
        jPanel.add(routeIDLabel);
        JTextField routeIDField = new JTextField();
        routeIDField.setBounds(20, 120, 150, 30);
        jPanel.add(routeIDField);

        JLabel newJeepsToTakeLabel = new JLabel("New set of jeepneys to take:");
        newJeepsToTakeLabel.setBounds(20, 170, 300, 30);
        jPanel.add(newJeepsToTakeLabel);
        JTextField newJeepsToTakeField = new JTextField();
        newJeepsToTakeField.setBounds(20, 210, 150, 30);
        jPanel.add(newJeepsToTakeField);

        JLabel newRouteFile = new JLabel("Enter new route file name:");
        newRouteFile.setBounds(20, 260, 300, 30);
        jPanel.add(newRouteFile);
        JTextField newRouteFileField = new JTextField();
        newRouteFileField.setBounds(20, 300, 150, 30);
        jPanel.add(newRouteFileField);

        JButton editRoute = new JButton("Apply");
        Border buttonBorder = BorderFactory.createLineBorder(new Color(29, 142, 0), 2, true);
        editRoute.setBounds(110, 380, 120, 30);
        editRoute.setForeground(new Color(29, 142, 0));
        editRoute.setBackground(Color.white);
        editRoute.setBorder(buttonBorder);
        editRoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                    Statement statement = connection.createStatement();

                    int id = Integer.parseInt(routeIDField.getText());

                    // Checking of id existence
                    ResultSet idCheck = statement.executeQuery("SELECT * FROM routes WHERE route_id = " + id);
                    if (!idCheck.next()) {
                        JOptionPane.showMessageDialog(jPanel, "Route ID does not exist.");
                        return;
                    }

                    // If ID exists, proceed with updating the record
                    String sql = "UPDATE routes SET jeepsToTake = ?, route_map = ? WHERE route_id = " + id;

                    String jeepsToTake = newJeepsToTakeField.getText();
                    String routeMapFile = "src/resources/routes/" + newRouteFileField.getText();

                    java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, jeepsToTake);
                    preparedStatement.setString(2, routeMapFile);
                    preparedStatement.executeUpdate();

                    preparedStatement.close();
                    statement.close();
                    connection.close();

                    JOptionPane.showMessageDialog(jFrame, "Successfully updated route!");
                    jFrame.dispose();
                    try {
						EditRouteGUI transpo = new EditRouteGUI(username);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
                } catch (SQLException | ClassNotFoundException | NumberFormatException e1) {
                    e1.printStackTrace();
                }
            }
        });

        jPanel.add(editRoute);
        
        JButton viewRoutesTable = new JButton("View Routes Table");
        viewRoutesTable.setBounds(110, 440, 120, 30);
        viewRoutesTable.setForeground(new Color(29, 142, 0));
        viewRoutesTable.setBackground(Color.white);
        viewRoutesTable.setBorder(buttonBorder);
        viewRoutesTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewRoutesTable viewRoutesTable = new ViewRoutesTable(username, "edit");
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
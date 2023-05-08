package finalProject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewRoutesTable {
	@SuppressWarnings("unused")
	ViewRoutesTable(String username, String returnPage){
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
        
        JLabel titleLabel = new JLabel("Routes Table");
        titleLabel.setBounds(80, 20, 400, 30);
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
            switch(returnPage) {
            	case "delete":
					try {
						DeleteRouteGUI deletePage = new DeleteRouteGUI(username);
					} catch (FileNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
					break;
            	case "add":
            		try {
						AddRouteGUI addPage = new AddRouteGUI(username);
					} catch (FileNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
            		break;
            	case "edit":
            		try {
						EditRouteGUI editPage = new EditRouteGUI(username);
					} catch (FileNotFoundException | SQLException e1) {
						e1.printStackTrace();
					} 
					break;
            }
        });
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        headerPanel.add(backButton);
        
        jFrame.getContentPane().setBackground(Color.white); // set main background to white

        jFrame.add(headerPanel); // add header panel to frame

        String[] columnNames = {"Route ID", "Route Name", "Traditional Jeep Fare", "Modern Jeep Fare", "Jeeps To Take", "Route Map"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 70, 350, 400);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(450, 350));
        jPanel.add(scrollPane);
        jFrame.add(jPanel);

        table.setFillsViewportHeight(true);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password")) {
            Statement stmt = conn.createStatement();
            String sql = "SELECT route_id, route_name, traditionalJeep_Fare, modernJeep_Fare, jeepsToTake, route_map FROM routes";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("route_id");
                String name = rs.getString("route_name");
                double traditionalJeepneyFare = rs.getDouble("traditionalJeep_Fare");
                double modernJeepneyFare = rs.getDouble("modernJeep_Fare");
                String jeepsToTake = rs.getString("jeepsToTake");
                String route_map = rs.getString("route_map");
                Object[] rowData = {id, name, traditionalJeepneyFare, modernJeepneyFare, jeepsToTake, route_map};
                model.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        jFrame.setTitle("GABS USC");
        jFrame.setSize(540, 810); // Android Screen Ratio
        jFrame.setLayout(null);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null); // setting the JFrame to appear in the middle

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

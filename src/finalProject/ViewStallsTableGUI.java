package finalProject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewStallsTableGUI {
	@SuppressWarnings("unused")
	ViewStallsTableGUI(String username, String returnPage){
		
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
        
        JLabel titleLabel = new JLabel("Stalls Table");
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
					DeleteStallGUI deletePage = new DeleteStallGUI(username);
					break;
            	case "add":
					AddStallGUI addPage = new AddStallGUI(username);
            		break;
            	case "edit":
					EditStallGUI editPage = new EditStallGUI(username);
					break;
            }
        });
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        headerPanel.add(backButton);
        
        jFrame.getContentPane().setBackground(Color.white); // set main background to white

        jFrame.add(headerPanel); // add header panel to frame

        String[] columnNames = {"Stall ID", "Stall Name", "Building Code", "Average Cost"};
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
            String sql = "SELECT stall_id, stall_name, building_code, average_cost FROM stalls";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("stall_id");
                String name = rs.getString("stall_name");
                String buildingCode = rs.getString("building_code");
                double cost = rs.getDouble("average_cost");
                Object[] rowData = {id, name, buildingCode, cost};
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

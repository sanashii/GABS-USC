package finalProject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EditStallGUI {
	String selectedOption;
	String selectedBuilding;
	String selectedOption2;
	Date openingHours;
	Date closingHours;
	EditStallGUI(String username){
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
        
        JLabel titleLabel = new JLabel("Editing a Stall");
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

        JLabel stallIDLabel = new JLabel("Enter stall ID:");
        stallIDLabel.setBounds(20, 80, 300, 30);
        jPanel.add(stallIDLabel);
        JTextField stallIDField = new JTextField();
        stallIDField.setBounds(20, 120, 150, 30);
        jPanel.add(stallIDField);

        JLabel costLabel = new JLabel("New average cost:");
        costLabel.setBounds(20, 170, 300, 30);
        jPanel.add(costLabel);
        JTextField costField = new JTextField();
        costField.setBounds(20, 210, 150, 30);
        jPanel.add(costField);

        JButton editOffice = new JButton("Apply Changes");
        Border buttonBorder = BorderFactory.createLineBorder(new Color(29, 142, 0), 2, true);
        editOffice.setBounds(110, 480, 120, 30);
        editOffice.setForeground(new Color(29, 142, 0));
        editOffice.setBackground(Color.white);
        editOffice.setBorder(buttonBorder);
        editOffice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                    Statement statement = connection.createStatement();

                    int id = Integer.parseInt(stallIDField.getText());

                    // Check if the ID exists in the table
                    ResultSet idCheck = statement.executeQuery("SELECT * FROM stalls WHERE stall_ID = " + id);
                    if (!idCheck.next()) {
                        JOptionPane.showMessageDialog(jPanel, "Stall ID does not exist.");
                        return;
                    }

                    // Retrieve the stall name associated with the ID
                    String stallName = idCheck.getString("stall_name");

                    // Use a prepared statement to update all rows in the table with the same stall name
                    String sql = "UPDATE stalls SET average_cost = ? WHERE stall_name = ?";
                    java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setDouble(1, Double.parseDouble(costField.getText()));
                    preparedStatement.setString(2, stallName);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();

                    statement.close();
                    connection.close();

                    JOptionPane.showMessageDialog(jFrame, "Successfully updated stall!");
                    jFrame.dispose();
                    EditStallGUI edit = new EditStallGUI(username); // calls itself so if every they want to add another one, they wont be exited from the menu
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }

            }

        });

        jPanel.add(editOffice);
        
        JButton viewStallsTable = new JButton("View Stalls Table");
        viewStallsTable.setBounds(110, 440, 120, 30);
        viewStallsTable.setForeground(new Color(29, 142, 0));
        viewStallsTable.setBackground(Color.white);
        viewStallsTable.setBorder(buttonBorder);
        viewStallsTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				ViewStallsTableGUI viewStallsTable = new ViewStallsTableGUI(username, "edit");
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

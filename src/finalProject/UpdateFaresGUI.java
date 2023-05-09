package finalProject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

public class UpdateFaresGUI {
	@SuppressWarnings("unused")
	UpdateFaresGUI(String username){
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
        
        JLabel titleLabel = new JLabel("Update fares");
        titleLabel.setBounds(100, 20, 400, 30);
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

        jFrame.add(headerPanel); // add header panel to frame
        jFrame.add(jPanel);

        double traditionalJeepneyFare = 0.0;
        double modernJeepneyFare = 0.0;

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT traditionalJeep_fare, modernJeep_fare FROM routes WHERE route_id = 1");

            while (resultSet.next()) {
                traditionalJeepneyFare = resultSet.getDouble("traditionalJeep_fare");
                modernJeepneyFare = resultSet.getDouble("modernJeep_fare");
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JLabel traditionalJeepneyFareLabel = new JLabel("Traditional Jeepney Fare: " + traditionalJeepneyFare);
        traditionalJeepneyFareLabel.setBounds(20, 80, 300, 30);
        jPanel.add(traditionalJeepneyFareLabel);

        JTextField traditionalJeepneyFareTextField = new JTextField();
        traditionalJeepneyFareTextField.setBounds(20, 120, 150, 30);
        jPanel.add(traditionalJeepneyFareTextField);


        JLabel modernJeepneyFareLabel = new JLabel("Modern Jeepney Fare: " + modernJeepneyFare);
        modernJeepneyFareLabel.setBounds(20, 170, 300, 30);
        jPanel.add(modernJeepneyFareLabel);

        JTextField modernJeepneyFareTextField = new JTextField();
        modernJeepneyFareTextField.setBounds(20, 210, 150, 30);
        jPanel.add(modernJeepneyFareTextField);

        
        
        double traditionalFareHolder = traditionalJeepneyFare;
        double modernFareHolder = modernJeepneyFare;

        JButton updateButton = new JButton("Update Fare");
        updateButton.setBounds(110, 300, 120, 30);
        updateButton.addActionListener(e -> {
            String traditionalJeepneyFareText = traditionalJeepneyFareTextField.getText();
            String modernJeepneyFareText = modernJeepneyFareTextField.getText();

            double traditionalJeepneyFareValue = traditionalJeepneyFareText.isEmpty() ? traditionalFareHolder : 0;
            double modernJeepneyFareValue = modernJeepneyFareText.isEmpty() ? modernFareHolder : 0;

            try {
                if (!traditionalJeepneyFareText.isEmpty()) {
                    traditionalJeepneyFareValue = Double.parseDouble(traditionalJeepneyFareText);
                }
                if (!modernJeepneyFareText.isEmpty()) {
                    modernJeepneyFareValue = Double.parseDouble(modernJeepneyFareText);
                }
                // if the inputs are valid numbers, update the fares
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gabs_usc", "superuser", "password");
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE routes SET traditionalJeep_fare = ?, modernJeep_fare = ?");
                preparedStatement.setDouble(1, traditionalJeepneyFareValue);
                preparedStatement.setDouble(2, modernJeepneyFareValue);
                preparedStatement.executeUpdate();

                JOptionPane.showMessageDialog(jFrame, "Successfully updated fares!");
                TranspoSegmentGUI tranpo = new TranspoSegmentGUI(username);
                jFrame.dispose();

                connection.close();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(jFrame, "Invalid input. Please enter a valid number.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        Border buttonBorder = BorderFactory.createLineBorder(new Color(29, 142, 0), 2, true);
        updateButton.setForeground(new Color(29, 142, 0));
        updateButton.setBackground(Color.white);
        updateButton.setBorder(buttonBorder);

        jPanel.add(traditionalJeepneyFareLabel);
        jPanel.add(traditionalJeepneyFareTextField);
        jPanel.add(modernJeepneyFareLabel);
        jPanel.add(modernJeepneyFareTextField);
        jPanel.add(updateButton);

        
        jFrame.setTitle("GABS USC");
        jFrame.setSize(540, 810); // Android Screen Ratio
        jFrame.setLayout(null);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null); // setting the JFrame to appear in the middle

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

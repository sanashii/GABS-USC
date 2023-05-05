package finalProject;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("unused")
public class SuperuserGUI extends PasswordHasher{
	SuperuserGUI() {
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
		jPanel.setBounds(85,80,350,500);
		jPanel.setLayout(null);

		jFrame.getContentPane().setBackground(Color.white); // set main background to white

		jFrame.add(headerPanel); // add header panel to frame
		jFrame.add(jPanel);

		jFrame.setTitle("GABS USC");
		jFrame.setSize(540,810); //Android Screen Ratio
		jFrame.setLayout(null);
		jFrame.setResizable(false);

		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

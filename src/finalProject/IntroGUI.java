package finalProject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;


public class IntroGUI{
	
	IntroGUI(){
		JFrame jFrame = new JFrame();//creates the frame
		
		ImageIcon jIcon = new ImageIcon("src/resources/icon.png");//create the image icon

		ImageIcon jImage = new ImageIcon("src/resources/name.png");
		
		ImageIcon jLock = new ImageIcon("src/resources/padlock.png");
		
		JLabel jLabel = new JLabel(); //create the labels
		JButton jButton = new JButton();//create the buttons
		JButton loginPage = new JButton();
		
		//Setting the JFrame to Android Screen
		jFrame.setTitle("GABS USC");
		jFrame.setSize(540,810); //Android Screen Ratio
		jFrame.setLayout(null);
		jFrame.setResizable(false); //To Avoid changing the size of the screen
				
		jFrame.setIconImage(jIcon.getImage());//change image icon of frame
		jFrame.getContentPane().setBackground(new Color(0, 177, 63));//to set the background color same as the logo
				
		jLabel.setIcon(jImage);
		jLabel.setBounds(70,130,354,258);//trying to match the size of the image
				
		Border roundedBorder = BorderFactory.createCompoundBorder();
				
		jButton.setBounds(190,500,150,50);
		jButton.setText("Start App");
		jButton.setFocusable(false);
		jButton.setBackground(Color.white);
		jButton.setBorder(roundedBorder);
//		jButton.addActionListener(this);
		
		jButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	MapPage map = new MapPage();
		  
		    	jFrame.dispose();
		    }
		});
		
		
		loginPage.setBounds(420,670,62,62);
		loginPage.setIcon(jLock);
		loginPage.setBackground(jFrame.getBackground());
		loginPage.setBorderPainted(false);
		loginPage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				AdminLoginGUI adminLogin = new AdminLoginGUI();
				jFrame.dispose();
				
			}
			
		});
		
		jFrame.add(jLabel);
		jFrame.add(jButton);
		jFrame.add(loginPage);
		jFrame.setLocationRelativeTo(null); // setting the JFrame to appear in the middle
				
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
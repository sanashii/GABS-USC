package finalProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TranspoPage {
    private JPanel sidebar;
    private boolean sidebarOpen = false;

    TranspoPage(){
        JFrame jFrame = new JFrame();
        
        ImageIcon jIcon = new ImageIcon("src/resources/name.png");
        ImageIcon exitIcon = new ImageIcon("src/resources/exit.png");
        ImageIcon sideBar = new ImageIcon("src/resources/menu2.png");

        Image originalImage = jIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(130,94, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        Image ogSide = sideBar.getImage();
        Image scaledSide = ogSide.getScaledInstance(60,60, Image.SCALE_SMOOTH);
        ImageIcon scSide = new ImageIcon(scaledSide);

        JLabel jLabel = new JLabel();
        JButton jButton = new JButton();
        JPanel jPanel = new JPanel();
        sidebar = new JPanel();

        jFrame.setTitle("GABS USC");
        jFrame.setSize(540,810);
        jFrame.setLayout(null);
        jFrame.setLocationRelativeTo(null);//This will position the JFrame in the same place of the introGUI
        jFrame.setResizable(false);

        jFrame.setIconImage(jIcon.getImage());
        jFrame.getContentPane().setBackground(Color.WHITE);

        jPanel.setBackground(new Color(0, 177, 63));
        jPanel.setBounds(0,0,540,80);
        jPanel.setLayout(null);

        jLabel.setIcon(scaledIcon);
        jLabel.setBounds(190, -14, scaledIcon.getIconWidth(), scaledIcon.getIconHeight());
        jButton.setFocusable(false);
        jButton.setIcon(scSide);
        jButton.setBackground(new Color(0, 177, 63));
        jButton.setBounds(0,0,80,80);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleSidebar();
            }
        });

        
        sidebar.setBackground(new Color(118, 212, 152));
        sidebar.setBounds(-250, 80, 190, 730);
        
        sidebar.setLayout(null);
        
        JButton button1 = new JButton("USC TC: Map Guide");
        button1.setBounds(0,0,190,50);
        button1.setBorder(null);
        button1.setBackground(new Color(118, 212, 152));
        button1.setFocusable(false);
        button1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        
        button1.addActionListener(new ActionListener() {
			
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MapPage toMap = new MapPage();
				jFrame.dispose();
			}
		});
        
        JButton button2 = new JButton("Transportation Guide");
        button2.setBounds(0,50,190,50);
        button2.setBorder(null);
        button2.setBackground(new Color(118, 212, 152));
        button2.setFocusable(false);
        button2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        JFrame frame = new JFrame("Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.add(button2);
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("To USC TC");
        JMenuItem menuItem2 = new JMenuItem("From USC TC");
        popupMenu.add(menuItem1);
        popupMenu.add(menuItem2);
        popupMenu.setPopupSize(190, 150);
        menuItem1.setBackground(new Color(118, 212, 152));
        menuItem2.setBackground(new Color(118, 212, 152));
        popupMenu.setBackground(new Color(118, 212, 152));
        button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				  popupMenu.show(button2, 0, button2.getHeight());
			}
		});
        menuItem1.addActionListener(new ActionListener() {
			
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				  MapPage test = new MapPage();
				  jFrame.dispose();
			}
		});
        menuItem2	.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				  @SuppressWarnings("unused")
				MapPage test = new MapPage();
				  jFrame.dispose();
			}
		});
        JButton button3 = new JButton();
        button3.setIcon(exitIcon);
        button3.setBounds(20,630,40,40);
        button3.setBorder(null);
        
        button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				IntroGUI toIntro = new IntroGUI();
				jFrame.dispose();
			}
		});
//        button3.setFocusable(false);
        
        
        JButton button4 = new JButton();
        button4.setBounds(0,100,190,50);
        button4.setBorder(null);
        button4.setBackground(new Color(118, 212, 152));
        button4.setFocusable(false);
        
        
        Container container = sidebar.getParent();
        if (container != null) {
            container.setComponentZOrder(sidebar, 0);
        }
        sidebar.add(button1);
        sidebar.add(button2);
        sidebar.add(button3);
        sidebar.add(button4);
//        JLabel namePage = new JLabel();
//        namePage.setText("Transpo Page");
//        namePage.setLayout(null);
//        namePage.setBounds(200,200,100,100);
//        jFrame.add(namePage);
//        To identify that this is the Transpo Page
        
        //First Drop-down Panel with the First Button 
        JButton dropOpt1 = new JButton();
        dropOpt1.setText("USC TC to SM Cebu");
        dropOpt1.setBorder(null);
        dropOpt1.setFocusable(false);
        dropOpt1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt1.setBounds(0,80,540,50);
        
        JPanel dropdownPanel1 = new JPanel();
        dropdownPanel1.setBounds(0, 130, 540, 200);
        dropdownPanel1.setBackground(Color.WHITE);
        dropdownPanel1.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel1.setLayout(null);
        dropdownPanel1.setVisible(false);

        JLabel label1 = new JLabel("Option 1");
        label1.setBounds(20, 20, 100, 30);
        dropdownPanel1.add(label1);
        
        dropOpt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel1.setVisible(!dropdownPanel1.isVisible());
            }
        });

        //Second drop-down Panel with Second button
        JButton dropOpt2 = new JButton();
        dropOpt2.setText("USC TC to Ayala Cebu");
        dropOpt2.setBorder(null);
        dropOpt2.setFocusable(false);
        dropOpt2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt2.setBounds(0,130,540,50);
        
        JPanel dropdownPanel2 = new JPanel();
        dropdownPanel2.setBounds(0, 180, 540, 200);
        dropdownPanel2.setBackground(Color.WHITE);
        dropdownPanel2.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel2.setLayout(null);
        dropdownPanel2.setVisible(false);
        
        JLabel label2 = new JLabel("Option 2");
        label2.setBounds(100, 100, 100, 30);
        dropdownPanel2.add(label2);
                
        dropOpt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel2.setVisible(!dropdownPanel2.isVisible());
            }
        });
        
        
        JButton dropOpt3 = new JButton();
        dropOpt3.setText("USC TC to Metro Ayala");
        dropOpt3.setBorder(null);
        dropOpt3.setFocusable(false);
        dropOpt3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt3.setBounds(0,180,540,50);
        
        JPanel dropdownPanel3 = new JPanel();
        dropdownPanel3.setBounds(0, 230, 540, 200);
        dropdownPanel3.setBackground(Color.WHITE);
        dropdownPanel3.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel3.setLayout(null);
        dropdownPanel3.setVisible(false);
        
        JLabel label3 = new JLabel("Option 3");
        label3.setBounds(100, 100, 100, 30);
        dropdownPanel3.add(label3);
                
        dropOpt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel3.setVisible(!dropdownPanel3.isVisible());
            }
        });
        
        jFrame.add(sidebar);
        
        jFrame.add(dropOpt1);
        jFrame.add(dropdownPanel1);
        
        jFrame.add(dropOpt2);
        jFrame.add(dropdownPanel2);
        
        jFrame.add(dropOpt3);
        jFrame.add(dropdownPanel3);
        
        jFrame.add(jLabel);
        jFrame.add(jButton);
        jFrame.add(jPanel);        
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (sidebarOpen && !sidebar.contains(e.getPoint())) {
                    toggleSidebar();
                }
            }
        });
    }

    private void toggleSidebar() {
        if (sidebarOpen) {
            sidebar.setLocation(-200, 80);
        } else {
            sidebar.setLocation(0, 80);
        }
        sidebarOpen = !sidebarOpen;
    }
}
package finalProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TranspoPageFrom {
    private JPanel sidebar;
    private boolean sidebarOpen = false;

    TranspoPageFrom(){
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
				// TODO Auto-generated method stub
				  popupMenu.show(button2, 0, button2.getHeight());
			}
		});
        menuItem1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 TranspoPageTo to = new TranspoPageTo();
				  jFrame.dispose();
			}
		});
        menuItem2	.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 TranspoPageFrom from = new TranspoPageFrom();
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
				// TODO Auto-generated method stub
				IntroGUI toIntro = new IntroGUI();
				jFrame.dispose();
			}
		});
//        button3.setFocusable(false);
        
        
        
        JButton button4 = new JButton();
        button4.setBounds(0,100,190,500);
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

        jFrame.add(sidebar);
        
      //Code to insert a scrollbar into the frame
        JPanel transPoPanel = new JPanel();
        transPoPanel.setPreferredSize(new Dimension(630, 1200)); // Set the height to be larger than 810
        transPoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        transPoPanel.setLayout(null);
        
        JButton dropOpt1 = new JButton();
        dropOpt1.setText("USC TC to Ayala Terraces (13C)");
        dropOpt1.setBorder(null);
        dropOpt1.setFocusable(false);
        dropOpt1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt1.setBounds(0,0,540,50);
        
        JPanel dropdownPanel1 = new JPanel();
        dropdownPanel1.setBounds(0, 50, 540, 200);
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
        
        JButton dropOpt2 = new JButton();
        dropOpt2.setText("USC TC to Metro Ayala (13C)");
        dropOpt2.setBorder(null);
        dropOpt2.setFocusable(false);
        dropOpt2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt2.setBounds(0,80,540,50);
        
        JPanel dropdownPanel2 = new JPanel();
        dropdownPanel2.setBounds(0, 90, 540, 200);
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
        dropOpt3.setText("USC TC to Metro Ayala (62B)");
        dropOpt3.setBorder(null);
        dropOpt3.setFocusable(false);
        dropOpt3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt3.setBounds(0,160,540,50);
        
        JPanel dropdownPanel3 = new JPanel();
        dropdownPanel3.setBounds(0, 170, 540, 200);
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
        
        JButton dropOpt4 = new JButton();
        dropOpt4.setText("USC TC to Metro Ayala (62C)");
        dropOpt4.setBorder(null);
        dropOpt4.setFocusable(false);
        dropOpt4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt4.setBounds(0,240,540,50);
        
        JPanel dropdownPanel4 = new JPanel();
        dropdownPanel4.setBounds(0, 250, 540, 200);
        dropdownPanel4.setBackground(Color.WHITE);
        dropdownPanel4.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel4.setLayout(null);
        dropdownPanel4.setVisible(false);
        
        JLabel label4 = new JLabel("Option 4");
        label4.setBounds(100, 100, 100, 30);
        dropdownPanel4.add(label4);
                
        dropOpt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel4.setVisible(!dropdownPanel4.isVisible());
            }
        });
        
        JButton dropOpt5 = new JButton();
        dropOpt5.setText("USC TC to Ayala Terraces (Guba - Ayala)");
        dropOpt5.setBorder(null);
        dropOpt5.setFocusable(false);
        dropOpt5.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt5.setBounds(0,320,540,50);
        
        JPanel dropdownPanel5 = new JPanel();
        dropdownPanel5.setBounds(0, 330, 540, 200);
        dropdownPanel5.setBackground(Color.WHITE);
        dropdownPanel5.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel5.setLayout(null);
        dropdownPanel5.setVisible(false);
        
        JLabel label5 = new JLabel("Option 5");
        label5.setBounds(100, 100, 100, 30);
        dropdownPanel5.add(label5);
                
        dropOpt5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel5.setVisible(!dropdownPanel5.isVisible());
            }
        });
        
        JButton dropOpt6 = new JButton();
        dropOpt6.setText("USC TC to I.T. Park");
        dropOpt6.setBorder(null);
        dropOpt6.setFocusable(false);
        dropOpt6.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt6.setBounds(0,400,540,50);
        
        JPanel dropdownPanel6 = new JPanel();
        dropdownPanel6.setBounds(0, 410, 540, 200);
        dropdownPanel6.setBackground(Color.WHITE);
        dropdownPanel6.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel6.setLayout(null);
        dropdownPanel6.setVisible(false);
        
        JLabel label6 = new JLabel("Option 6");
        label6.setBounds(100, 100, 100, 30);
        dropdownPanel6.add(label6);
                
        dropOpt6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel6.setVisible(!dropdownPanel6.isVisible());
            }
        });
        
        JButton dropOpt7 = new JButton();
        dropOpt7.setText("USC TC to Parkmall (13H)");
        dropOpt7.setBorder(null);
        dropOpt7.setFocusable(false);
        dropOpt7.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt7.setBounds(0,480,540,50);
        
        JPanel dropdownPanel7 = new JPanel();
        dropdownPanel7.setBounds(0, 490, 540, 200);
        dropdownPanel7.setBackground(Color.WHITE);
        dropdownPanel7.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel7.setLayout(null);
        dropdownPanel7.setVisible(false);
        
        JLabel label7 = new JLabel("Option 7");
        label7.setBounds(100, 100, 100, 30);
        dropdownPanel7.add(label7);
                
        dropOpt7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel7.setVisible(!dropdownPanel7.isVisible());
            }
        });
        
        JButton dropOpt8 = new JButton();
        dropOpt8.setText("USC TC to SM Conscolacion (24G)");
        dropOpt8.setBorder(null);
        dropOpt8.setFocusable(false);
        dropOpt8.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt8.setBounds(0,560,540,50);
        
        JPanel dropdownPanel8 = new JPanel();
        dropdownPanel8.setBounds(0, 570, 540, 200);
        dropdownPanel8.setBackground(Color.WHITE);
        dropdownPanel8.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel8.setLayout(null);
        dropdownPanel8.setVisible(false);
        
        JLabel label8 = new JLabel("Option 8");
        label8.setBounds(100, 100, 100, 30);
        dropdownPanel8.add(label8);
                
        dropOpt8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel8.setVisible(!dropdownPanel8.isVisible());
            }
        });
        
        JButton dropOpt9 = new JButton();
        dropOpt9.setText("USC TC to J Mall (13I)");
        dropOpt9.setBorder(null);
        dropOpt9.setFocusable(false);
        dropOpt9.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt9.setBounds(0,640,540,50);
        
        JPanel dropdownPanel9 = new JPanel();
        dropdownPanel9.setBounds(0, 650, 540, 200);
        dropdownPanel9.setBackground(Color.WHITE);
        dropdownPanel9.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel9.setLayout(null);
        dropdownPanel9.setVisible(false);
        
        JLabel label9 = new JLabel("Option 9");
        label9.setBounds(100, 100, 100, 30);
        dropdownPanel9.add(label9);
                
        dropOpt9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel9.setVisible(!dropdownPanel9.isVisible());
            }
        });
        
        JButton dropOpt10 = new JButton();
        dropOpt10.setText("USC TC to MC Public Market (22I)");
        dropOpt10.setBorder(null);
        dropOpt10.setFocusable(false);
        dropOpt10.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt10.setBounds(0,720,540,50);
        
        JPanel dropdownPanel10 = new JPanel();
        dropdownPanel10.setBounds(0, 730, 540, 200);
        dropdownPanel10.setBackground(Color.WHITE);
        dropdownPanel10.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel10.setLayout(null);
        dropdownPanel10.setVisible(false);
        
        JLabel label10 = new JLabel("Option 10");
        label10.setBounds(100, 100, 100, 30);
        dropdownPanel10.add(label10);
                
        dropOpt10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel10.setVisible(!dropdownPanel10.isVisible());
            }
        });
        
        JButton dropOpt11 = new JButton();
        dropOpt11.setText("USC TC to Pacific Mall (24G)");
        dropOpt11.setBorder(null);
        dropOpt11.setFocusable(false);
        dropOpt11.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt11.setBounds(0,800,540,50);
        
        JPanel dropdownPanel11 = new JPanel();
        dropdownPanel11.setBounds(0, 810, 540, 200);
        dropdownPanel11.setBackground(Color.WHITE);
        dropdownPanel11.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel11.setLayout(null);
        dropdownPanel11.setVisible(false);
        
        JLabel label11 = new JLabel("Option 11");
        label11.setBounds(100, 100, 100, 30);
        dropdownPanel11.add(label11);
                
        dropOpt11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel11.setVisible(!dropdownPanel11.isVisible());
            }
        });
        
        JButton dropOpt12 = new JButton();
        dropOpt12.setText("USC TC to Tintay (13C)");
        dropOpt12.setBorder(null);
        dropOpt12.setFocusable(false);
        dropOpt12.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        dropOpt12.setBounds(0,880,540,50);
        
        JPanel dropdownPanel12 = new JPanel();
        dropdownPanel12.setBounds(0, 890, 540, 200);
        dropdownPanel12.setBackground(Color.WHITE);
        dropdownPanel12.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        dropdownPanel12.setLayout(null);
        dropdownPanel12.setVisible(false);
        
        JLabel label12 = new JLabel("Option 12");
        label12.setBounds(100, 100, 100, 30);
        dropdownPanel12.add(label12);
                
        dropOpt12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownPanel12.setVisible(!dropdownPanel12.isVisible());
            }
        });
        
        transPoPanel.add(dropOpt1); transPoPanel.add(dropdownPanel1);
        transPoPanel.add(dropOpt2); transPoPanel.add(dropdownPanel2);
        transPoPanel.add(dropOpt3); transPoPanel.add(dropdownPanel3);
        transPoPanel.add(dropOpt4); transPoPanel.add(dropdownPanel4);
        transPoPanel.add(dropOpt5); transPoPanel.add(dropdownPanel5);
        transPoPanel.add(dropOpt6); transPoPanel.add(dropdownPanel6);
        transPoPanel.add(dropOpt7); transPoPanel.add(dropdownPanel7);
        transPoPanel.add(dropOpt8); transPoPanel.add(dropdownPanel8);
        transPoPanel.add(dropOpt9); transPoPanel.add(dropdownPanel9);
        transPoPanel.add(dropOpt10); transPoPanel.add(dropdownPanel10);
        transPoPanel.add(dropOpt11); transPoPanel.add(dropdownPanel11);
        transPoPanel.add(dropOpt12); transPoPanel.add(dropdownPanel12);
        
        JScrollPane scrollBar = new JScrollPane(transPoPanel);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        jFrame.add(scrollBar);
        jFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                scrollBar.setBounds(0, 80, jFrame.getWidth(), jFrame.getHeight());
            }
        });
        
        jFrame.add(scrollBar);
        
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
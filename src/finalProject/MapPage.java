package finalProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class MapPage {
	private JPanel sidebar;
    private boolean sidebarOpen = false;

    MapPage(){
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
        jFrame.setLocationRelativeTo(null); //This will position the JFrame in the same place of the introGUI
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
        sidebar.setBounds(-200, 80, 190, 730);
        
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
				new MapPage();
				jFrame.dispose();
			}
		});
        
        JButton button2 = new JButton("Transportation Guide");
        button2.setBounds(0,50,190,50);
        button2.setBorder(null);
        button2.setBackground(new Color(118, 212, 152));
        button2.setFocusable(false);
        button2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        
        button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TranspoPageTo();
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
				new IntroGUI();
				jFrame.dispose();
			}
		});
//        button3.setFocusable(false);
        
        sidebar.add(button1);
        sidebar.add(button2);
        sidebar.add(button3);
     
        jFrame.add(sidebar);

        JPanel mapPanel = new JPanel();
        mapPanel.setPreferredSize(new Dimension(1280, 824)); // Set the height of transPoPanel
        mapPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mapPanel.setLayout(null);
        
        ImageIcon map = new ImageIcon("src/resources/map.png");
        JLabel mapLabel = new JLabel();
        mapLabel.setIcon(map);
        mapLabel.setBounds(0, 0, map.getIconWidth(), map.getIconHeight());

        JButton lbBtn = new JButton();
        lbBtn.setText("LB Building");
        lbBtn.setBounds(218,275,100,100);
        lbBtn.setFocusable(false);
        lbBtn.setBorder(null);
        lbBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(jFrame, "LB Building");
			}
		});
        
        JButton smedBtn = new JButton();
        smedBtn.setText("SMED Building");
        smedBtn.setBounds(595,317,100,100);
        smedBtn.setFocusable(false);
        smedBtn.setBorder(null);
        smedBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(jFrame, "SMED Building");
			}
		});
        
        JButton peBtn = new JButton();
        peBtn.setText("PE Building");
        peBtn.setBounds(809,330,80,80);
        peBtn.setFocusable(false);
        peBtn.setBorder(null);
        peBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(jFrame, "PE Building");
			}
		});
        
        JButton rhBtn = new JButton();
        rhBtn.setText("RH Building");
        rhBtn.setBounds(920,317,80,80);
        rhBtn.setFocusable(false);
        rhBtn.setBorder(null);
        rhBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(jFrame, "RH Building");
			}
		});
        
        JButton lrcBtn = new JButton();
        lrcBtn.setText("LRC Building");
        lrcBtn.setBounds(910,117,100,100);
        lrcBtn.setFocusable(false);
        lrcBtn.setBorder(null);
        lrcBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(jFrame, "LRC Building");
			}
		});
        
        JButton mrBtn = new JButton();
        mrBtn.setText("MR Building");
        mrBtn.setBounds(730,50,80,80);
        mrBtn.setFocusable(false);
        mrBtn.setBorder(null);
        mrBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(jFrame, "MR Building");
			}
		});
        
        JButton afBtn = new JButton();
        afBtn.setText("AF Building");
        afBtn.setBounds(580,70,100,100);
        afBtn.setFocusable(false);
        afBtn.setBorder(null);
        afBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(jFrame, "AF Building");
			}
		});
        
        JButton dormBtn = new JButton();
        dormBtn.setText("Dormitory");
        dormBtn.setBounds(595,480,80,80);
        dormBtn.setFocusable(false);
        dormBtn.setBorder(null);
        dormBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(jFrame, "Dormitory");
			}
		});
        
        mapPanel.add(mapLabel);
        mapPanel.add(lbBtn);
        mapPanel.add(smedBtn);
        mapPanel.add(peBtn);
        mapPanel.add(rhBtn);
        mapPanel.add(lrcBtn);
        mapPanel.add(mrBtn);
        mapPanel.add(afBtn);
        mapPanel.add(dormBtn);
        
        JScrollPane scrollBar = new JScrollPane(mapPanel);
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

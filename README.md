# OOP-FP
github repo for the final project in OOP

## DEFAULT JFRAME SETTINGS
```java
// setting of default header w/ logo & GUI size
		JFrame jFrame = new JFrame();
		
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
```
## GENERAL RULES
1] git pull from main to your branch and only merge once your assigned features are complete

2] (Inclusion of icons on your page) always use the format "src/resources/iconName.format" and place them in the resources folder under src

3] Java Application Login credentials: username: `yourlastname` ; password: `yourlastname`

### Modules
- Application Superuser Functionalities
- USC Map Segment
- Transportation Guide

## DATABASE GUIDE
1. download MySQL on your device (https://dev.mysql.com/downloads/ && VIDEO GUIDE: https://youtu.be/GoQq5D_ntiY)
2. create a database in your local mysql cmd. note that the defualt port will be used, `3306`.
```sql
mysql -u root -p
CREATE DATABASE gabs_usc;
CREATE USER 'superuser'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON gabs_usc.* TO 'superuser'@'%';
FLUSH PRIVILEGES;
```

3. test the db connection in your java file
```java
String jdbcUrl = "jdbc:mysql://mysql-localhost:3306/gabs_usc";
String username = "superuser";
String password = "password";
Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
```
4. to import the mysql data unto your local database provided by the dump file found in the resources folder, run these commands in you mysql cmd:
```mysql
mysqldump -u root -p gabs_usc < gabs_usc.sql
```

in the event wherein you happen to add additional data into your local database, create a new dump file and update it accordingly on github in order for the team to keep posted with the changes
```mysql
mysqldump -u root -p gabs_usc > gabs_usc.sql
```
## DATABASE ERROR HANDLING
Tf ever you encounter an error saying
```java
java.sql.SQLException: No suitable driver found for jdbc:mysql://localhost:3306/gabs_usc
```
It means that the MySQL JDBC Driverhas not been included in your project's classpath. To resolve this issue, please do the following:
1. Make sure that when you installed MySQL, you included the installation for `Connector/J 8.0.33`, to check, open your MySQL Installer and see if your have Connector/J installed, if not kindly install it but most likely installs along with all the dependencies needed.
2. In Eclipse, right-click on your project and select Properties.
3. In the Properties dialog box, select Java Build Path from the left-hand side menu.
4. In the Java Build Path dialog box, select the Libraries tab.
5. Click on the Add External JARs button and navigate to the location where you downloaded the MySQL JDBC driver JAR file. (possible path for the JAR File: `C:\Program Files (x86)\MySQL\Connector J 8.0`)
6. Select the JAR file and click on Open to add it to your project's `classpath`.
7. Click on OK / Apply to close the Properties dialog box.

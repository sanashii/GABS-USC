# OOP-FP
github repo for the final project in OOP

## DEFAULT JFRAME SETTINGS
```java
//Setting the JFrame to Android Screen
		jFrame.setTitle("GABS USC");
		jFrame.setSize(540,810); //Android Screen Ratio
		jFrame.setLayout(null);
		jFrame.setResizable(false); //To Avoid changing the size of the screen
```
## GENERAL RULES
1] git pull from main to your branch and only merge once your assigned features are complete

2] (Setting icons in your page) always use the format "src/resources/iconName.format" and place them in the resources folder under src

### Modules
- Application Superuser Functionalities
- USC Map Segment
- Transportation Guide

## DATABASE GUIDE
1. download MySQL on your device (https://dev.mysql.com/downloads/ && VIDEO GUIDE: https://youtu.be/GoQq5D_ntiY)
2. create a database in your local mysql cmd. note that the defualt port will be used, 3306.
```sql
mysql -u root -p
CREATE DATABASE gabs_usc;
CREATE USER 'superuser'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON gabs_usc.* TO 'superuser'@'%';
FLUSH PRIVILEGES;
```
3. configure mysql to allow remote connections. go to `my.cnf` or `my.ini` file and set the bind-address option to
```sql
bind-address = 0.0.0.0
```
this will set your device listen for connections on all network interfaces. just remember to change it back to the default loopback address which is most likely `127.0.0.1` to avoid future project clashes
4. test the db connection in your java file
```java
String jdbcUrl = "jdbc:mysql://mysql-localhost:3306/gabs_usc";
String username = "superuser";
String password = "password";
Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
```

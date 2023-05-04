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

2] (Inclusion of icons on your page) always use the format "src/resources/iconName.format" and place them in the resources folder under src

3] username format (superuser): `yourlastname` ; password: `yourlastname`

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

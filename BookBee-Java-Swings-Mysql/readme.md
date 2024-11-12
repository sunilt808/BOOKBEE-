## Prototype of Online ebook selling Desktop application

- With Simple Recommender System
- Admin panel for admin to update and track books

### Setup

- java (supports all version)
- Mysql server and mysql jdbc driver 

### Running Program

> compiling all classes in the package modules

```shell
$ javac -d . View.java
$ javac -d . model.java
$ javac -d . searchpageframe.java
$ javac -d . UserProfile.java
$ javac -d . Loginform.java
$ javac -d . Cart.java
$ javac -d . Adminhome.java
```

> now run the application

```shell
$ javac Main.java

$ java Main
```

- Note 1: This project needs mysql database bookbee with four tables :- books,cart,orders,users. All the sample content of this tables(fake datas to test the prototype) are exported to `Mysql_databas_tables_data` folder

- Note 2: Before compiling the code change the DB connection username and password of yours in model,Loginform, Searchpageframe and Adminhome java programmes.  

---# BookBee-Java-Swings-Mysql

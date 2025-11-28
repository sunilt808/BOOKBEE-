# 📚 BookBee – Online eBook Selling Desktop Application
# 💻 Java Swing + 🗄️ MySQL Prototype with Simple Recommender System

# ---------------------------------------------------------
# ⚙️ REQUIREMENTS
# ---------------------------------------------------------
# ✔️ Java (any version)
# ✔️ MySQL Server
# ✔️ MySQL JDBC Driver (add to classpath)

# ---------------------------------------------------------
# 🛠️ COMPILATION STEPS
# ---------------------------------------------------------
javac -d . View.java
javac -d . model.java
javac -d . searchpageframe.java
javac -d . UserProfile.java
javac -d . Loginform.java
javac -d . Cart.java
javac -d . Adminhome.java

# Compile Main Class
javac Main.java

# ▶️ Run Application
java Main

# ---------------------------------------------------------
# 🗄️ MYSQL DATABASE SETUP
# ---------------------------------------------------------
# Create database
mysql -u your_username -p -e "CREATE DATABASE bookbee;"

# Required tables:
# 📘 books
# 🛒 cart
# 📦 orders
# 👤 users

# Import sample test data
mysql -u your_username -p bookbee < Mysql_databas_tables_data/books.sql
mysql -u your_username -p bookbee < Mysql_databas_tables_data/cart.sql
mysql -u your_username -p bookbee < Mysql_databas_tables_data/orders.sql
mysql -u your_username -p bookbee < Mysql_databas_tables_data/users.sql

# ---------------------------------------------------------
# ⚠️ IMPORTANT NOTE
# ---------------------------------------------------------
# ✏️ Update your MySQL DB username & password in:
#   - model.java
#   - Loginform.java
#   - searchpageframe.java
#   - Adminhome.java

# 🔐 Change DB credentials BEFORE compiling.

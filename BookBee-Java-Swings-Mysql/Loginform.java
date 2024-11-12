

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class Loginform extends JFrame implements ActionListener {

    // FRAME 1
    JFrame jf1 = new JFrame();
    JPanel panel1 = new JPanel();
    JButton user = new JButton("USER");
    JButton admin = new JButton("ADMIN");

    // FRAME 2
    JFrame jf2 = new JFrame();
    JPanel panel2 = new JPanel();
    JLabel login_label = new JLabel("Login Form");
    JLabel info = new JLabel();
    JLabel login_email = new JLabel("Enter Email        :");
    JLabel login_password = new JLabel("Enter Password  :");
    JTextField tf1 = new JTextField();
    JPasswordField p1 = new JPasswordField();
    JButton btn1 = new JButton("Submit");
    JButton reset = new JButton("Reset");
    JButton signup_button = new JButton("Click here to create an account.");

    // IMAGE LABELS
    JLabel imageLabel = new JLabel();
    JLabel image1 = new JLabel();
    JLabel user_background = new JLabel();

    // FRAME 3
    JFrame jf3 = new JFrame();
    JPanel panel3 = new JPanel();
    JLabel background_label = new JLabel();
    JLabel signup_form = new JLabel("Signup Form");
    JLabel name = new JLabel("Name                      :");
    JLabel email_id = new JLabel("Email-ID                :");
    JLabel password = new JLabel("Create Passowrd    :");
    JLabel confirm_password = new JLabel("Confirm Password  :");
    JTextField Name_txtfield = new JTextField();
    JTextField email_txtfield = new JTextField();
    JPasswordField password_txtfield = new JPasswordField();
    JPasswordField confirmpassword_txtfield = new JPasswordField();
    JButton login = new JButton("LOGIN");
    JButton submit_button = new JButton("Submit");
    JButton clear_button = new JButton("Clear");
    JButton back = new JButton("BACK");

    public Loginform() {

        // ADDING IMAGE
        imageLabel.setBounds(180, 10, 300, 300);
        ImageIcon iconLogo = new ImageIcon(
                new ImageIcon("images/logo.png").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
        imageLabel.setIcon(iconLogo);

        background_label.setBounds(0, 0, 1100, 800);
        ImageIcon img = new ImageIcon(
                new ImageIcon("images/image.jpg").getImage().getScaledInstance(1100, 800, Image.SCALE_SMOOTH));
        background_label.setIcon(img);

        image1.setBounds(0, 0, 1100, 800);
        ImageIcon image = new ImageIcon(
                new ImageIcon("images/image2.jpg").getImage().getScaledInstance(1100, 800, Image.SCALE_SMOOTH));
        image1.setIcon(image);

        // pack();
        setLocationRelativeTo(null);// *** this will center your app ***
        setResizable(false);

        // SET BOUNDS OF FRAME 1
        panel1.setBounds(180, 80, 700, 600);
        user.setBounds(280, 300, 150, 50);
        admin.setBounds(280, 360, 150, 50);
        user.setFont(new Font("ANDALUS", Font.BOLD, 20));
        admin.setFont(new Font("ANDALUS", Font.BOLD, 20));
        user.setBackground(new Color(10, 122, 140));
        user.setForeground(Color.white);
        admin.setBackground(new Color(10, 122, 140));
        admin.setForeground(Color.white);
        jf1.add(panel1);
        panel1.setBackground(new Color(255, 255, 255));
        panel1.add(imageLabel);
        jf1.add(background_label);
        panel1.setBackground(new Color(210, 241, 247));
        panel1.add(user);
        panel1.add(admin);
        jf1.setSize(1100, 800);
        jf1.setResizable(false);// cant adjust size(max,min)
        jf1.setBackground(new Color(255, 255, 255));
        panel1.setLayout(null);
        jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf1.setLayout(null);
        // jf1.pack();
        jf1.setLocationRelativeTo(null);
        jf1.setResizable(false);// *** this will center your app ***
        jf1.setVisible(true);
        user.addActionListener(this);
        admin.addActionListener(this);

        // SET BOUNDS OF FRAME 2
        panel2.setBounds(180, 80, 700, 600);
        panel2.setLayout(null);
        panel2.setBackground(new Color(223, 244, 247));
        panel2.add(login_email);
        panel2.add(login_password);
        panel2.add(tf1);
        panel2.add(p1);
        panel2.add(btn1);
        panel2.add(signup_button);
        panel2.add(reset);
        panel2.add(login_label);
        login_label.setForeground(new Color(10, 122, 144));
        login_label.setFont(new Font("Serif", Font.BOLD, 38));
        login_label.setBounds(250, 25, 400, 50);
        login_email.setBounds(120, 160, 200, 30);
        login_password.setBounds(120, 200, 200, 30);
        tf1.setBounds(290, 160, 200, 30);
        p1.setBounds(290, 200, 200, 30);
        login_email.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 20));
        login_password.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 20));
        btn1.setBounds(290, 260, 100, 30);
        reset.setBounds(420, 260, 100, 30);
        btn1.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 20));
        reset.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 20));
        btn1.setBackground(new Color(10, 122, 140));
        btn1.setForeground(Color.white);
        reset.setBackground(new Color(10, 122, 140));
        reset.setForeground(Color.white);
        signup_button.setBorderPainted(false);
        signup_button.setBounds(250, 400, 300, 50);
        signup_button.setBackground(new Color(223, 244, 247));
        signup_button.setForeground(Color.black);
        signup_button.setFont(new Font("Serif", Font.BOLD, 17));

        // SET BOUNDS OF FRAME 3
        panel3.setBounds(180, 80, 700, 600);
        panel3.setBackground(new Color(223, 244, 247));
        panel3.setLayout(null);
        panel3.add(signup_form);
        panel3.add(name);
        panel3.add(email_id);
        panel3.add(password);
        panel3.add(confirm_password);
        panel3.add(Name_txtfield);
        panel3.add(email_txtfield);
        panel3.add(password_txtfield);
        panel3.add(confirmpassword_txtfield);
        panel3.add(submit_button);
        panel3.add(clear_button);
        panel3.add(back);
        signup_form.setForeground(new Color(10, 122, 144));
        signup_form.setFont(new Font("Serif", Font.BOLD, 38));
        signup_form.setBounds(250, 25, 400, 50);
        name.setBounds(120, 160, 200, 30);
        email_id.setBounds(120, 200, 200, 30);
        password.setBounds(120, 240, 200, 30);
        confirm_password.setBounds(120, 280, 200, 30);
        name.setFont(new Font("Serif", Font.PLAIN, 20));
        email_id.setFont(new Font("Serif", Font.PLAIN, 20));
        password.setFont(new Font("Serif", Font.PLAIN, 20));
        confirm_password.setFont(new Font("Serif", Font.PLAIN, 20));
        Name_txtfield.setBounds(325, 160, 200, 30);
        email_txtfield.setBounds(325, 200, 200, 30);
        password_txtfield.setBounds(325, 240, 200, 30);
        confirmpassword_txtfield.setBounds(325, 280, 200, 30);
        submit_button.setBounds(290, 345, 100, 30);
        clear_button.setBounds(420, 345, 100, 30);
        submit_button.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 20));
        clear_button.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 20));
        submit_button.setBackground(new Color(10, 122, 140));
        submit_button.setForeground(Color.white);
        clear_button.setBackground(new Color(10, 122, 140));
        clear_button.setForeground(Color.white);
        login.setBounds(370, 440, 100, 30);
        login.setBackground(new Color(77, 87, 92));
        login.setForeground(Color.white);
        back.setBounds(50, 25, 100, 20);
        back.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 20));
        back.setBackground(new Color(10, 122, 140));
        back.setForeground(Color.white);

        info.setBounds(400, 380, 100, 50);

        // ADDING ACTION LISTENER TO THE BUTTON
        btn1.addActionListener(this);
        signup_button.addActionListener(this);
        reset.addActionListener(this);
        submit_button.addActionListener(this);
        clear_button.addActionListener(this);
        back.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == user) {
            panel2.add(signup_button);
            jf2.add(panel2);
            jf2.add(image1);
            jf2.setSize(1100, 800);
            jf2.setLayout(null);
            jf2.setResizable(false);
            login_label.setText("Login Form");

            jf2.setLocationRelativeTo(null); // *** this will center your app ***
            jf2.setVisible(true);
            ;
        }
        if (e.getSource() == admin) {
            panel2.remove(signup_button);
            jf2.add(panel2);
            jf2.add(image1);
            jf2.setSize(1100, 800);
            jf2.setLayout(null);
            jf2.setResizable(false);
            login_label.setText("Admin Login");
            jf2.setLocationRelativeTo(null); // *** this will center your app ***
            jf2.setVisible(true);

        }

        if (e.getSource() == signup_button) {

            jf3.add(panel3);
            jf3.add(background_label);
            jf3.setSize(1100, 800);
            jf3.setLayout(null);
            jf3.setResizable(false);

            jf3.setLocationRelativeTo(null); // *** this will center your app ***
            jf3.setVisible(true);
            jf2.setVisible(false);

        }
        if (e.getSource() == submit_button) {
            String NAME = Name_txtfield.getText();

            String a = null, b = null;
            char[] c = password_txtfield.getPassword();
            char[] f = confirmpassword_txtfield.getPassword();
            String PASSWORD = new String(c);
            String CONFIRM_PASSWORD = new String(f);
            String pattern = ("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
            String EMAIL_ID = email_txtfield.getText();

            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(EMAIL_ID);
            try {
                Connection co = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookbee", "root", "Mysql@78298365");
                Statement sta = co.createStatement();
                co.setAutoCommit(true);
                PreparedStatement ps = co.prepareStatement("insert into users (name,email,password) values(?,?,?)");
                ps.setString(1, NAME);
                ps.setString(2, EMAIL_ID);
                ps.setString(3, PASSWORD);
                if (NAME.length() < 1) {
                    JOptionPane.showMessageDialog(submit_button, "All fields should be entered");
                } else if (EMAIL_ID.length() < 1) {
                    JOptionPane.showMessageDialog(submit_button, "All fields should be entered");
                } else if (PASSWORD.length() < 1) {
                    JOptionPane.showMessageDialog(submit_button, "All fields should be entered");
                } else if (!m.find()) {
                    JOptionPane.showMessageDialog(submit_button, "Please Enter Valid Email");
                } else if (PASSWORD.length() <= 8) {
                    JOptionPane.showMessageDialog(submit_button, "password field should contain atleast 8 characters");
                    password_txtfield.setText("");
                    confirmpassword_txtfield.setText("");
                } else {

                    if (PASSWORD.equalsIgnoreCase((CONFIRM_PASSWORD))) {
                        try {
                            PreparedStatement ns = co.prepareStatement("SELECT * from users where email=?");
                            ns.setString(1, EMAIL_ID);
                            ResultSet rs = ns.executeQuery();
                            if (rs.next()) {
                                JOptionPane.showMessageDialog(submit_button, "The Email ID Already Exist");

                            } else {
                                JOptionPane.showMessageDialog(submit_button,
                                        "Welcome,Your account is successfully created");
                                ps.executeUpdate();
                                jf3.dispose();
                                jf2.setVisible(true);

                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    } else {
                        JOptionPane.showMessageDialog(submit_button, "Please type the password and confirm correctly");

                        password_txtfield.setText("");
                        confirmpassword_txtfield.setText("");

                    }
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        if (e.getSource() == clear_button) {
            Name_txtfield.setText("");
            email_txtfield.setText("");
            password_txtfield.setText("");
            confirmpassword_txtfield.setText("");

        }
        if (e.getSource() == btn1) {

            String EMAIL_ID = tf1.getText();
            String PASSWORD = String.valueOf(p1.getPassword());
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookbee", "root", "Mysql@78298365");
                Statement st = con.createStatement();
                con.setAutoCommit(true);
                PreparedStatement prepare = con.prepareStatement("select * from users where email=? AND password=?");
                prepare.setString(1, EMAIL_ID);
                prepare.setString(2, PASSWORD);
                ResultSet rs = prepare.executeQuery();
                System.out.println(EMAIL_ID + PASSWORD);

                if (rs.next()) {
                    if (rs.getString("email").equals(EMAIL_ID) && rs.getString("password").equals(PASSWORD)) {
                        System.out.println(EMAIL_ID + PASSWORD);
                        if (!rs.getBoolean("is_admin")) {
                            JOptionPane.showMessageDialog(btn1, "Login Successful");
                            Searchpageframe.searchpagecall(rs.getInt("id"));
                            jf2.dispose();
                        } else {
                            JOptionPane.showMessageDialog(btn1, "Welcome Admin");
                            Adminhome ad = new Adminhome();
                            jf2.dispose();
                            ad.adminhomepage("admin");
                        }
                    } else {
                        JOptionPane.showMessageDialog(btn1, "Invalid Email-id or Password");
                    }
                } else {
                    JOptionPane.showMessageDialog(btn1, "There is no account on this email");

                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        if (e.getSource() == reset) {
            tf1.setText("");
            p1.setText("");

        }

        if (e.getSource() == back) {
            jf2.add(panel2);
            jf2.add(image1);
            jf2.setSize(1100, 800);
            jf2.setLayout(null);
            jf2.setResizable(false);
            jf3.dispose();
            jf2.setVisible(true);
        }

    }

}

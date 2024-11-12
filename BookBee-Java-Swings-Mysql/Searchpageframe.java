

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.DimensionUIResource;

import java.awt.Dimension;

class List {
    static int flag = 0;
    static String nx;

    static void cList(String name[], int n, int id[], String category, JFrame f) {
        JFrame frame;

        if (n > 0) {
            frame = new JFrame("result for " + category);
            JPanel panel = new JPanel(new BorderLayout());
            JPanel p1 = new JPanel();
            ArrayList<String> myList = new ArrayList<>(10);
            for (int index = 0; index < n; index++) {
                myList.add(name[index]);
            }
            final JList<String> list = new JList<String>(myList.toArray(new String[myList.size()]));
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setViewportView(list);
            scrollPane.getVerticalScrollBar().setBackground(new Color(10, 122, 140));
            list.setLayoutOrientation(JList.VERTICAL);
            panel.add(scrollPane);
            p1.add(panel);
            list.setFixedCellHeight(50);
            list.setBackground(new Color(128, 218, 232));
            list.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            list.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    String named = list.getSelectedValue();
                    int idvalue = list.getSelectedIndex();
                    System.out.println(id[idvalue] + " " + named);
                    model m = new model();
                    m.assignBookId(id[idvalue]);
                    new View(m, m.getSuggestion(), id[idvalue]);
                    frame.dispose();
                    f.dispose();
                }
            });
            flag = 0;
            frame.add(panel);
            frame.setSize(500, 250);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);

            frame.setVisible(true);
        } else {
            if (flag == 0) {

                int type = 3;
                flag = 1;
                MysqlCon x = new MysqlCon();
                x.createconnection(List.nx, "SELECT name,id,author FROM books", type, f);
            } else {
                JOptionPane.showMessageDialog(null, "Book not found");
                flag = 0;
            }
        }
    }
}

class MysqlCon {
    String createconnection(String category, String sql, int type, JFrame f) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookbee", "root", "Mysql@78298365");
            int id[] = new int[100];
            int i = 0;
            String name[] = new String[100];
            if (type == 1) {
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, category);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    name[i] = rs.getString("name");
                    id[i] = rs.getInt("id");
                    i++;
                }
                List.cList(name, i, id, category, f);
            } else if (type == 2) {
                String temp = null;
                String temp2 = null;
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                int count = 0;
                while (rs.next()) {
                    temp = rs.getString("name");
                    boolean b = temp.toLowerCase().contains(category.toLowerCase().trim());
                    String d[] = temp.split(" ");
                    count = 0;
                    for (int p = 0; p < d.length; p++) {
                        boolean c = category.toLowerCase().contains(d[p].toLowerCase());
                        if (c == true) {
                            count++;
                        }
                    }
                    if (b == true || count > 1) {
                        name[i] = rs.getString("name");
                        id[i] = rs.getInt("id");
                        i++;
                    }
                }
                String a[] = category.trim().split(" +");
                if (a.length != 1 && i == 0) {
                    for (int k = 0; k < a.length; k++) {
                        Statement stm = con.createStatement();
                        ResultSet rst = stm.executeQuery(sql);
                        while (rst.next()) {
                            temp = rst.getString("name");
                            boolean b = temp.toLowerCase().contains(a[k].toLowerCase());
                            boolean c = a[k].toLowerCase().contains(temp.toLowerCase());
                            if (b == true || c == true) {
                                int w = 1;
                                for (int j = 0; j < i; j++) {
                                    if (rst.getInt("id") == id[j])
                                        w = 2;
                                }
                                if (w == 1) {
                                    id[i] = rst.getInt("id");
                                    name[i] = rst.getString("name");
                                    i++;
                                }
                            }
                        }
                    }
                }
                List.cList(name, i, id, category, f);
            } else {
                String temp = null;
                String author[] = new String[100];

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                int count = 0;
                while (rs.next()) {
                    temp = rs.getString("author");
                    boolean b = temp.toLowerCase().contains(category.toLowerCase().trim());
                    String d[] = temp.split(" ");
                    count = 0;
                    for (int p = 0; p < d.length; p++) {
                        boolean c = category.toLowerCase().contains(d[p].toLowerCase());
                        if (c == true) {
                            count++;
                        }
                    }
                    if (b == true || count > 1) {
                        name[i] = rs.getString("author");
                        id[i] = rs.getInt("id");
                        author[i] = rs.getString("name");
                        i++;
                    }
                }
                String a[] = category.trim().split(" +");
                if (a.length != 1 && i == 0) {
                    for (int k = 0; k < a.length; k++) {
                        Statement stm = con.createStatement();
                        ResultSet rst = stm.executeQuery(sql);
                        while (rst.next()) {
                            temp = rst.getString("author");
                            boolean b = temp.toLowerCase().contains(a[k].toLowerCase());
                            boolean c = a[k].toLowerCase().contains(temp.toLowerCase());
                            if (b == true || c == true) {
                                int w = 1;
                                for (int j = 0; j < i; j++) {
                                    if (rst.getInt("id") == id[j])
                                        w = 2;
                                }
                                if (w == 1) {
                                    id[i] = rst.getInt("id");
                                    name[i] = rst.getString("author");
                                    author[i] = rst.getString("name");
                                    i++;
                                }
                            }
                        }
                    }
                }
                List.cList(author, i, id, category, f);
            }
            con.close();
        } catch (Exception k) {
            System.out.println(k);
        }
        return null;
    }

    // kkk

    static String image[] = new String[2];

    static String[] getmaxidimage() {
        return MysqlCon.image;
    }
}

class Imageresize {
    static ImageIcon imageresize(Object imageName, int a, int b) {
        BufferedImage myPicture;
        try {
            if (imageName instanceof String) {
                myPicture = ImageIO.read(new File((String) imageName));
            } else {
                myPicture = ImageIO.read((InputStream) imageName);

            }
            // myPicture = ImageIO.read(new File(fileloc));
            ImageIcon imageIcon = new ImageIcon(myPicture);
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(a, b, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            return imageIcon;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}

public class Searchpageframe {
    static int id = 0;
    static int userid;

    public static void searchpagecall(int uid) {
        userid = uid;
        JFrame searchframe = new JFrame();
        MysqlCon makecon = new MysqlCon();

        JPanel panel1 = new JPanel();
        panel1.setBounds(0, 0, 1100, 180);
        panel1.setBackground(new Color(10, 122, 140));
        searchframe.add(panel1);

        JPanel panel2 = new JPanel();
        panel2.setBounds(850, 180, 250, 620);
        panel2.setBackground(new Color(128, 218, 232));
        searchframe.add(panel2);

        JLabel label1 = null;
        String loc = "images/logo.png";
        int a = 500, b = 150;
        label1 = new JLabel(Imageresize.imageresize(loc, a, b));
        label1.setBounds(100, 0, 900, 180);
        panel1.add(label1);

        JButton profilebutton = null;
        loc = "images/profileicon.png";
        a = 50;
        b = 50;
        profilebutton = new JButton(Imageresize.imageresize(loc, a, b));
        profilebutton.setBounds(970, 190, 50, 50);
        profilebutton.setBackground(new Color(10, 122, 140));
        profilebutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                UserProfile project1 = new UserProfile();
                searchframe.dispose();
            }
        });
        panel2.add(profilebutton);

        JButton cartbutton = null;
        loc = "images/carticon.jpg";
        a = 50;
        b = 50;
        cartbutton = new JButton(Imageresize.imageresize(loc, a, b));
        cartbutton.setBounds(1030, 190, 50, 50);
        cartbutton.setBackground(new Color(10, 122, 140));
        cartbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("cart button");

                Cart a = new Cart();
                searchframe.dispose();
            }
        });
        panel2.add(cartbutton);
        final JLabel space = new JLabel("");
        space.setBounds(970, 300, 100, 100);
        space.setPreferredSize(new Dimension(30, 150));

        final JLabel add = new JLabel("NEW LAUNCH");
        add.setBounds(970, 400, 100, 100);
        add.setFont(new Font("Verdana", Font.BOLD, 20));
        panel2.add(space);
        panel2.add(add);

        JButton launch = null;
        a = 170;
        b = 240;
        Object l;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookbee", "root", "Mysql@78298365");
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT id FROM books ORDER BY id DESC");
            r.next();
            id = r.getInt("id");

            Statement st = c.createStatement();
            ResultSet rt = st.executeQuery("SELECT * FROM books");
            while (rt.next()) {
                // loc = "images/" + rt.getString("image");
                l = rt.getBinaryStream("imagefile");
                launch = new JButton(Imageresize.imageresize(l, a, b));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        launch.setBounds(855, 400, 240, 400);
        launch.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                model m = new model();
                m.assignBookId(id);
                new View(m, m.getSuggestion(), id);
                searchframe.dispose();
            }
        });
        panel2.add(launch);

        JTextField searchfield = new JTextField();
        searchfield.setBounds(100, 200, 650, 50);
        searchfield.setFont(new Font("Verdana", Font.BOLD, 20));
        searchframe.add(searchfield);

        final JLabel explore = new JLabel("Explore");
        explore.setBounds(100, 300, 100, 50);
        explore.setFont(new Font("Verdana", Font.BOLD, 20));
        searchframe.add(explore);

        JButton searchbutton = null;
        loc = "images/search.png";
        a = 50;
        b = 50;
        searchbutton = new JButton(Imageresize.imageresize(loc, a, b));
        searchbutton.setBounds(750, 200, 50, 50);
        searchbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = null;
                name = searchfield.getText();
                List.nx = name;
                String sql = "SELECT name,id FROM books";
                int type = 2;
                makecon.createconnection(name, sql, type, searchframe);
            }
        });
        searchframe.add(searchbutton);

        JButton entertainment = new JButton("entertainment");
        entertainment.setForeground(new Color(255, 255, 255));
        entertainment.setBounds(100, 350, 300, 100);
        entertainment.setFont(new Font("Verdana", Font.BOLD, 20));
        entertainment.setBackground(new Color(10, 122, 140));
        entertainment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int type = 1;
                String category = "Adventure";
                String sql = "SELECT name,id,author FROM books WHERE category = ?";
                String name = makecon.createconnection(category, sql, type, searchframe);
            }
        });
        searchframe.add(entertainment);

        JButton history = new JButton("SET2 ");
        history.setBounds(500, 350, 300, 100);
        history.setForeground(new Color(255, 255, 255));
        history.setFont(new Font("Verdana", Font.BOLD, 20));
        history.setBackground(new Color(10, 122, 140));
        history.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int type = 1;
                String category = "";
                String sql = "SELECT name,id FROM books ";
                String name = makecon.createconnection(category, sql, type, searchframe);
            }
        });
        searchframe.add(history);

        JButton story = new JButton("SET3");
        story.setBounds(100, 500, 300, 100);
        story.setForeground(new Color(255, 255, 255));
        story.setFont(new Font("Verdana", Font.BOLD, 20));
        story.setBackground(new Color(10, 122, 140));
        story.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int type = 1;
                String category = "Set 3";
                String sql = "SELECT name,id FROM books ";
                String name = makecon.createconnection(category, sql, type, searchframe);
            }
        });
        searchframe.add(story);

        JButton knowledge = new JButton("Set 4");
        knowledge.setForeground(new Color(255, 255, 255));
        knowledge.setBounds(500, 500, 300, 100);
        knowledge.setFont(new Font("Verdana", Font.BOLD, 20));
        knowledge.setBackground(new Color(10, 122, 140));
        knowledge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int type = 1;
                String category = "Set 4";
                String sql = "SELECT name,id FROM books";
                String name = makecon.createconnection(category, sql, type, searchframe);
            }
        });
        searchframe.add(knowledge);
        model.userid = userid;
        searchframe.setSize(1100, 800);
        searchframe.setLayout(null);
        searchframe.setLocationRelativeTo(null);
        searchframe.setResizable(false);
        searchframe.setVisible(true);
        searchframe.getContentPane().setBackground(Color.WHITE);
        searchframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}

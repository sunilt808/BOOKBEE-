

import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.util.ArrayList;
import java.util.Locale.Category;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TableView.TableRow;
import modules.model;
import modules.*;

class Admin {
    static void insertmethod(String name, Float price, String author, String category, int publisher_id,
            String description, InputStream fis, File image, String datafile) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookbee", "root", "Mysql@78298365");
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO `bookbee`.`books` (`name`, `price`,`author`,`category`, `publisher_id`, `description`,`imagefile`) "
                            + "values (?,?,?,?,?,?,?)");
            stmt.setString(1, name);
            stmt.setFloat(2, price);
            stmt.setString(3, author);
            stmt.setString(4, category);
            stmt.setInt(5, publisher_id);
            stmt.setString(6, description);
            stmt.setBinaryStream(7, fis, image.length());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void deletemethod(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookbee", "root", "Mysql@78298365");
            PreparedStatement stmt = con.prepareStatement("DELETE FROM books " + "WHERE id =?;");
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void updateBookDetails(int id, String name, int price, String author, String Category) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookbee", "root", "Mysql@78298365");
            PreparedStatement stmt = con.prepareStatement(
                    "UPDATE `bookbee`.`books` SET `name` = ?, `price` = ?, `author` = ?,`category`=? WHERE (`id` = ?);");

            stmt.setString(1, name);
            stmt.setInt(2, price);
            stmt.setString(3, author);
            stmt.setString(4, Category);
            stmt.setInt(5, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void deleteuser(String email) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookbee", "root", "Mysql@78298365");
            PreparedStatement stmt = con.prepareStatement("DELETE FROM users " + "WHERE email =?;");
            stmt.setString(1, email);
            int rows = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "user deleted Successfully");
            System.out.println(rows);
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "invalid user");
            System.out.println(e);
        }
    }
}

public class Adminhome {
    File image = null;

    void adminhomepage(String adminname) {

        JFrame adminframe = new JFrame();

        JPanel panel1 = new JPanel();
        panel1.setBounds(0, 0, 1100, 180);
        panel1.setBackground(new Color(10, 122, 140));
        adminframe.add(panel1);

        JPanel panel2 = new JPanel();
        panel2.setBounds(850, 180, 250, 620);
        panel2.setBackground(new Color(128, 218, 232));
        adminframe.add(panel2);

        JLabel label1 = null;
        String loc = "images/logo.png";
        int a = 500, b = 130;
        label1 = new JLabel(imageresize(loc, a, b));
        label1.setBounds(100, 0, 900, 180);
        panel1.add(label1);

        JLabel label2 = new JLabel("Welcome Admin!");
        label2.setBounds(100, 200, 650, 50);
        label2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        adminframe.add(label2);

        JButton profilebutton = null;
        loc = "images/profileicon.png";
        a = 50;
        b = 50;
        profilebutton = new JButton(imageresize(loc, a, b));
        profilebutton.setBounds(970, 190, 50, 50);
        profilebutton.setBackground(new Color(10, 122, 140));
        profilebutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adminframe.dispose();
            }
        });
        panel2.add(profilebutton);

        JButton insertbook = new JButton("ADD BOOK");
        insertbook.setFont(new Font("Verdana", Font.BOLD, 18));
        insertbook.setBounds(300, 300, 300, 100);
        insertbook.setBackground(new Color(10, 122, 140));
        insertbook.setForeground(Color.WHITE);
        insertbook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame insertframe = new JFrame("INSERT FRAME");

                JPanel panel1 = new JPanel();
                panel1.setBounds(0, 0, 1100, 180);
                panel1.setBackground(new Color(10, 122, 140));
                insertframe.add(panel1);

                JPanel panel2 = new JPanel();
                panel2.setBounds(850, 180, 250, 620);
                panel2.setBackground(new Color(128, 218, 232));
                insertframe.add(panel2);

                JLabel label1 = null;
                String loc = "images/logo.png";
                int a = 500, b = 130;
                label1 = new JLabel(imageresize(loc, a, b));
                label1.setBounds(100, 0, 900, 180);
                panel1.add(label1);

                JLabel namelabel = new JLabel("BOOK NAME");
                namelabel.setFont(new Font("Verdana", Font.BOLD, 18));
                namelabel.setBounds(100, 200, 200, 50);
                insertframe.add(namelabel);

                JTextField namefield = new JTextField();
                namefield.setBounds(350, 200, 400, 50);
                insertframe.add(namefield);

                JLabel authorlabel = new JLabel("AUTHOR NAME");
                authorlabel.setFont(new Font("Verdana", Font.BOLD, 18));
                authorlabel.setBounds(100, 300, 200, 50);
                insertframe.add(authorlabel);

                JTextField authorfield = new JTextField();
                authorfield.setBounds(350, 300, 400, 50);
                insertframe.add(authorfield);

                JLabel categorylabel = new JLabel("BOOK CATEGORY");
                categorylabel.setFont(new Font("Verdana", Font.BOLD, 18));
                categorylabel.setBounds(100, 400, 200, 50);
                insertframe.add(categorylabel);

                JTextField categoryfield = new JTextField();
                categoryfield.setBounds(350, 400, 400, 50);
                insertframe.add(categoryfield);

                JLabel publisheridlabel = new JLabel("PUBLISHER");
                publisheridlabel.setFont(new Font("Verdana", Font.BOLD, 18));
                publisheridlabel.setBounds(100, 500, 200, 50);
                insertframe.add(publisheridlabel);

                JTextField publisheridfield = new JTextField();
                publisheridfield.setBounds(350, 500, 400, 50);
                insertframe.add(publisheridfield);

                JLabel pricelabel = new JLabel("BOOK PRICE");
                pricelabel.setFont(new Font("Verdana", Font.BOLD, 18));
                pricelabel.setFont(new Font("Verdana", Font.BOLD, 18));
                pricelabel.setBounds(100, 600, 200, 50);
                insertframe.add(pricelabel);

                JTextField pricefield = new JTextField();
                pricefield.setBounds(350, 600, 400, 50);
                insertframe.add(pricefield);

                JButton nextbutton = new JButton("NEXT-->");
                nextbutton.setBounds(400, 700, 100, 50);
                nextbutton.setFont(new Font("Verdana", Font.BOLD, 14));
                nextbutton.setBackground(Color.WHITE);
                nextbutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        insertframe.dispose();

                        JFrame insertframe = new JFrame("INSERT FRAME");

                        JPanel panel1 = new JPanel();
                        panel1.setBounds(0, 0, 1100, 180);
                        panel1.setBackground(new Color(10, 122, 140));
                        insertframe.add(panel1);

                        JPanel panel2 = new JPanel();
                        panel2.setBounds(850, 180, 250, 620);
                        panel2.setBackground(new Color(128, 218, 232));
                        insertframe.add(panel2);

                        JLabel label1 = null;
                        String loc = "images/logo.png";
                        int a = 500, b = 130;
                        label1 = new JLabel(imageresize(loc, a, b));
                        label1.setBounds(100, 0, 900, 180);
                        panel1.add(label1);
                        JTextField imagefield = new JTextField();
                        imagefield.setBounds(350, 200, 400, 50);
                        insertframe.add(imagefield);

                        JButton imagelabel = new JButton("UPLOAD IMAGE");
                        imagelabel.setBounds(100, 200, 200, 50);
                        imagelabel.setFont(new Font("Verdana", Font.BOLD, 15));
                        imagelabel.setBackground(Color.red);
                        imagelabel.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JFileChooser chooser;
                                FileNameExtensionFilter filter;
                                chooser = new JFileChooser();
                                chooser.setCurrentDirectory(null);
                                filter = new FileNameExtensionFilter("jpeg, gif and png files", "jpg", "gif", "png");
                                chooser.addChoosableFileFilter(filter);
                                try {
                                    int i = chooser.showOpenDialog(insertframe);
                                    if (i == JFileChooser.APPROVE_OPTION) {
                                        image = chooser.getSelectedFile();
                                        imagefield.setText(image.getAbsolutePath());
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });

                        insertframe.add(imagelabel);

                        JLabel deslabel = new JLabel("BOOK DESCRIPTION");
                        deslabel.setFont(new Font("Verdana", Font.BOLD, 16));
                        deslabel.setBounds(100, 300, 200, 50);
                        insertframe.add(deslabel);

                        JTextField desfield = new JTextField();
                        desfield.setBounds(350, 300, 400, 50);
                        insertframe.add(desfield);

                        JButton datalabel = new JButton("UPLOAD BOOK");
                        datalabel.setFont(new Font("Verdana", Font.BOLD, 14));
                        datalabel.setBounds(100, 400, 200, 50);
                        datalabel.setBackground(Color.RED);
                        insertframe.add(datalabel);

                        JButton insertbutton = new JButton("ADD BOOK");
                        insertbutton.setBounds(450, 600, 100, 50);
                        insertbutton.setBackground(new Color(10, 122, 140));
                        insertbutton.setFont(new Font("Verdana", Font.BOLD, 18));
                        insertbutton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                String name = namefield.getText();
                                String author = authorfield.getText();
                                String category = categoryfield.getText();
                                int publisher_id = Integer.parseInt(publisheridfield.getText());
                                float price = Float.parseFloat(pricefield.getText());
                                try {
                                    FileInputStream fis = new FileInputStream(image);
                                    String description = desfield.getText();

                                    Admin.insertmethod(name, price, author, category, publisher_id, description, fis,
                                            image, "datafile");
                                    JOptionPane.showMessageDialog(null, "Book added successfully");
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                insertframe.dispose();
                                // System.out.println(name + " " + author + " " + category + " " + publisher_id
                                // + " "
                                // + price + " " + image + " " + description + " ");
                            }
                        });
                        insertframe.add(insertbutton);
                        insertframe.setSize(1100, 800);
                        insertframe.setLayout(null);
                        insertframe.setLocationRelativeTo(null);
                        insertframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        insertframe.setVisible(true);

                    }
                });
                insertframe.add(nextbutton);

                insertframe.setSize(1100, 800);
                insertframe.setLayout(null);
                insertframe.setLocationRelativeTo(null);
                insertframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                insertframe.setVisible(true);

            }
        });
        adminframe.add(insertbook);

        JButton removebook = new JButton("Remove BOOK");
        removebook.setFont(new Font("Verdana", Font.BOLD, 18));
        removebook.setBounds(450, 300, 300, 100);
        removebook.setBackground(new Color(10, 122, 140));
        removebook.setForeground(Color.WHITE);
        removebook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame removeframe = new JFrame("REMOVE BOOK");
                JPanel panel1 = new JPanel();
                panel1.setBounds(0, 0, 1100, 180);
                panel1.setBackground(new Color(10, 122, 140));
                removeframe.add(panel1);

                JPanel panel2 = new JPanel();
                panel2.setBounds(850, 180, 250, 620);
                panel2.setBackground(new Color(128, 218, 232));
                removeframe.add(panel2);

                JLabel label1 = null;
                String loc = "images/logo.png";
                int a = 500, b = 140;
                label1 = new JLabel(imageresize(loc, a, b));
                label1.setBounds(100, 0, 900, 180);
                panel1.add(label1);

                JLabel idlabel = new JLabel("ENTER BOOK NAME");
                idlabel.setBounds(100, 200, 200, 50);
                removeframe.add(idlabel);

                JTextField idfield = new JTextField();
                idfield.setBounds(350, 200, 400, 50);
                removeframe.add(idfield);

                JButton removebutton = new JButton("REMOVE");
                removebutton.setBounds(450, 600, 100, 50);
                removebutton.setBackground(new Color(10, 122, 140));
                removebutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int id = Integer.parseInt(idfield.getText());
                        Admin.deletemethod(id);
                        JOptionPane.showMessageDialog(null, "Book removed successfully");
                    }
                });
                removeframe.add(removebutton);
                removeframe.setSize(1100, 800);
                removeframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                removeframe.setLayout(null);
                removeframe.setVisible(true);
            }
        });
        // adminframe.add(removebook);

        JButton removeuser = new JButton("REMOVE USER");
        removeuser.setBounds(300, 450, 300, 100);
        removeuser.setFont(new Font("Verdana", Font.BOLD, 18));
        removeuser.setBackground(new Color(10, 122, 140));
        removeuser.setForeground(Color.WHITE);
        adminframe.add(removeuser);
        removeuser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame insertframe = new JFrame("INSERT FRAME");

                JPanel panel1 = new JPanel();
                panel1.setBounds(0, 0, 1100, 180);
                panel1.setBackground(new Color(10, 122, 140));
                insertframe.add(panel1);

                JPanel panel2 = new JPanel();
                panel2.setBounds(850, 180, 250, 620);
                panel2.setBackground(new Color(128, 218, 232));
                insertframe.add(panel2);

                JLabel label1 = null;
                String loc = "images/logo.png";
                int a = 500, b = 140;
                label1 = new JLabel(Imageresize.imageresize(loc, a, b));
                label1.setBounds(100, 0, 900, 180);
                panel1.add(label1);

                JLabel namelabel = new JLabel("ENTER USER EMAIL");
                namelabel.setFont(new Font("Verdana", Font.BOLD, 18));
                namelabel.setBounds(100, 200, 200, 50);
                insertframe.add(namelabel);

                JTextField namefield = new JTextField();
                namefield.setBounds(350, 200, 400, 50);
                insertframe.add(namefield);
                JButton insertbutton = new JButton("Delete User");
                insertbutton.setFont(new Font("Verdana", Font.BOLD, 18));
                insertbutton.setForeground(Color.WHITE);
                insertbutton.setBounds(450, 600, 100, 50);
                insertbutton.setBackground(new Color(10, 122, 140));
                insertbutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String name = namefield.getText();
                        Admin.deleteuser(name);
                        insertframe.dispose();

                    }
                });
                insertframe.add(insertbutton);
                insertframe.setSize(1100, 800);
                insertframe.setLayout(null);
                insertframe.setLocationRelativeTo(null);
                insertframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                insertframe.setVisible(true);
            }
        });

        JButton exploreall = new JButton("EDIT BOOKS");
        exploreall.setBounds(100, 600, 300, 100);
        exploreall.setFont(new Font("Verdana", Font.BOLD, 18));
        exploreall.setBackground(new Color(10, 122, 140));
        exploreall.setForeground(Color.WHITE);
        adminframe.add(exploreall);
        exploreall.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new ExploreBook();
            }
        });

        JButton viewbutton = new JButton("Track Book");
        viewbutton.setFont(new Font("Verdana", Font.BOLD, 18));
        viewbutton.setBounds(500, 600, 300, 100);
        viewbutton.setBackground(new Color(10, 122, 140));
        viewbutton.setForeground(Color.WHITE);
        adminframe.add(viewbutton);
        viewbutton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new TrackBook();
            }
        });

        // JButton updatebutton = new JButton("UPDATE");
        // updatebutton.setBounds(450, 600, 300, 100);
        // updatebutton.setBackground(new Color(10, 122, 140));
        // adminframe.add(updatebutton);

        adminframe.setSize(1100, 800);
        adminframe.setLayout(null);
        adminframe.setVisible(true);
        adminframe.getContentPane().setBackground(Color.WHITE);
        adminframe.setLocationRelativeTo(null);
        adminframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public ImageIcon imageresize(String fileloc, int a, int b) {
        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File(fileloc));
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

class ExploreBook {
    // JFrame f=new JFrame();
    JFrame f = new JFrame("Explore");
    // private TableCellRenderer rightRenderer_c;
    int total;

    ExploreBook() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1100, 140);
        panel.setBackground(new Color(10, 122, 140));
        JLabel l3;
        l3 = new JLabel("Books List");
        l3.setBounds(100, 224, 400, 30);
        l3.setFont(new Font("Serif", Font.BOLD, 20));
        l3.setBackground(Color.WHITE);
        l3.setForeground(Color.RED);

        JPanel panel1 = new JPanel();
        panel1.setBounds(0, 0, 1100, 180);
        panel1.setBackground(new Color(10, 122, 140));
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel panel2 = new JPanel();

        panel2.setBounds(0, 0, 1100, 620);
        panel2.setBackground(new Color(10, 122, 140));
        panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel label1 = null;
        String loc1 = "images/logo.png";
        int a1 = 400, b1 = 130;
        label1 = new JLabel(imageresize(loc1, a1, b1));
        label1.setBounds(100, 0, 900, 180);
        panel1.add(label1);

        JButton profilebutton = null;
        String loc2 = "images/profileicon.png";
        int a2 = 50;
        int b2 = 50;
        profilebutton = new JButton(imageresize(loc2, a2, b2));
        // profilebutton.setBounds(970, 190, 50, 50);
        profilebutton.setBackground(new Color(10, 122, 140));
        profilebutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();

            }
        });
        panel2.add(profilebutton);

        JButton cartbutton = null;
        String loc3 = "images/search.png";
        int a3 = 50;
        int b3 = 50;
        cartbutton = new JButton(imageresize(loc3, a3, b3));
        cartbutton.setBounds(1030, 190, 50, 50);
        cartbutton.setBackground(new Color(10, 122, 140));
        cartbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        // panel2.add(cartbutton);
        // cartbutton.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(panel1);
        panel.add(panel2);
        // panel.add(panel2);

        ArrayList books = new ArrayList();
        ArrayList price = new ArrayList();
        ArrayList id = new ArrayList();
        ArrayList no_sold = new ArrayList();
        ArrayList author = new ArrayList();
        ArrayList category = new ArrayList();
        try {
            model xs = new model();
            ResultSet rs = xs.getBookDetails();
            while (rs.next()) {
                books.add(rs.getString("name"));
                price.add(rs.getInt("price"));
                id.add(rs.getInt("id"));
                author.add(rs.getString("author"));
                category.add(rs.getString("category"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        String data[][] = new String[books.size() + 1][5];
        total = 0;
        for (int i = 0; i < books.size(); i++) {
            data[i][0] = id.get(i).toString();
            data[i][1] = (String) books.get(i);
            data[i][2] = "Rs. " + price.get(i).toString();
            data[i][3] = (String) author.get(i);
            data[i][4] = (String) category.get(i);
        }
        // data[books.size()][1] = "Total";
        // data[books.size()][2] = "Rs. " + total;
        String column[] = { "Book Id", "Book Name", "Price", "Author", "Category" };
        DefaultTableModel model = new DefaultTableModel(data, column);
        JTable jt = new JTable(model);
        jt.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt.setBackground(Color.WHITE);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jt.getColumnCount(); i++) {
            jt.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }
        jt.setRowHeight(jt.getRowHeight() + 20);
        jt.setFont(new Font("Verdana", Font.BOLD, 14));
        // jt.setBounds(0,36,700,800);
        JScrollPane sp = new JScrollPane(jt);
        sp.setPreferredSize(new Dimension(300, 700));
        sp.setBounds(100, 300, 900, 200);
        sp.setBackground(new Color(210, 241, 247));
        JButton c = new JButton("Update book");
        JButton remove = new JButton("Delete Book");
        jt.getColumnModel().getColumn(0).setPreferredWidth(50);
        jt.getColumnModel().getColumn(1).setPreferredWidth(400);
        jt.getColumnModel().getColumn(2).setPreferredWidth(100);
        jt.getColumnModel().getColumn(3).setPreferredWidth(50);
        jt.getColumnModel().getColumn(4).setPreferredWidth(50);

        // jt.getColumnModel().getColumn(0).setBackground(Color.RED);
        // jt.getColumnModel().getColumn(1).setForeground(Color.WHITE);

        remove.setBounds(740, 570, 140, 30);
        remove.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent ae) {
                int k = jt.getSelectedRow();
                if (jt.getSelectedRow() >= 0) {
                    model m = new model();
                    Admin.deletemethod(Integer.valueOf(id.get(k).toString()));
                    JOptionPane.showMessageDialog(f, "Book Deleted Successfully", "Insane error",
                            JOptionPane.ERROR_MESSAGE);
                    books.remove(k);
                    price.remove(k);
                    author.remove(k);
                    category.remove(k);
                    f.dispose();
                    new ExploreBook();

                } else {
                    JOptionPane.showMessageDialog(f, "Please Select the Book to delete", "Insane error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        });
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JTextField xField = new JTextField(25);
                JTextField yField = new JTextField(25);
                JTextField x2Field = new JTextField(25);
                JTextField y2Field = new JTextField(25);

                JPanel myPanel = new JPanel();
                myPanel.setLayout(new GridLayout(4, 2));
                myPanel.setPreferredSize(new Dimension(600, 100));

                JLabel x1 = new JLabel("Book Name");
                myPanel.add(x1);
                // myPanel.add(Box.createHorizontalStrut(5)); // a spacer
                myPanel.add(xField);
                myPanel.add(Box.createVerticalStrut(15));

                JLabel x2 = new JLabel("Book Price");
                myPanel.add(x2);
                // myPanel.add(Box.createHorizontalStrut(5)); // a spacer
                myPanel.add(yField);
                myPanel.add(Box.createVerticalStrut(15));

                JLabel x3 = new JLabel("Author Name");
                myPanel.add(x3);
                // myPanel.add(Box.createHorizontalStrut(5)); // a spacer
                myPanel.add(x2Field);
                myPanel.add(Box.createVerticalStrut(15));
                JLabel x4 = new JLabel("Category");
                myPanel.add(x4);
                // myPanel.add(Box.createHorizontalStrut(5)); // a spacer
                myPanel.add(y2Field);
                myPanel.add(Box.createVerticalStrut(15));
                int k = jt.getSelectedRow();
                if (jt.getSelectedRow() >= 0) {

                    xField.setText(books.get(k).toString());
                    yField.setText(price.get(k).toString());
                    x2Field.setText(author.get(k).toString());
                    y2Field.setText(category.get(k).toString());

                    int result = JOptionPane.showConfirmDialog(f, myPanel, "Update the details",
                            JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        model m = new model();
                        Admin.updateBookDetails((int) Integer.valueOf(id.get(k).toString()), xField.getText(),
                                (int) Integer.valueOf(yField.getText()), x2Field.getText(), y2Field.getText());
                        books.set(k, xField.getText());
                        price.set(k, yField.getText());
                        author.set(k, x2Field.getText());
                        category.set(k, y2Field.getText());
                        System.out.println("x value: " + xField.getText());
                        System.out.println("y value: " + yField.getText());
                        JOptionPane.showMessageDialog(f, "Book Details Updated Successfully", "you did that",
                                JOptionPane.INFORMATION_MESSAGE);
                        f.dispose();
                        new ExploreBook();
                    }

                } else {
                    JOptionPane.showMessageDialog(f, "Please Select the Book To Update", "Insane error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        });
        c.setBounds(500, 600, 120, 30);
        f.getContentPane().setBackground(new Color(210, 241, 247));
        // f.add(l1);
        // f.add(l2);
        // f.add(l3);
        f.add(remove);
        f.add(sp);
        f.add(c);
        f.add(panel);
        f.add(l3);
        // panel.add(l4);
        f.setSize(1100, 800);
        f.setLayout(null);
        panel.setLayout(null);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }

    public ImageIcon imageresize(String fileloc, int a, int b) {
        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File(fileloc));
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

class TrackBook {
    // JFrame f=new JFrame();
    JFrame f = new JFrame("Explore");
    // private TableCellRenderer rightRenderer_c;
    int total;

    TrackBook() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1100, 140);
        panel.setBackground(new Color(10, 122, 140));
        JLabel l3;
        l3 = new JLabel("Books List");
        l3.setBounds(100, 224, 400, 30);
        l3.setFont(new Font("Serif", Font.BOLD, 20));
        l3.setBackground(Color.WHITE);
        l3.setForeground(Color.RED);

        JPanel panel1 = new JPanel();
        panel1.setBounds(0, 0, 1100, 180);
        panel1.setBackground(new Color(10, 122, 140));
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel panel2 = new JPanel();

        panel2.setBounds(0, 0, 1100, 620);
        panel2.setBackground(new Color(10, 122, 140));
        panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel label1 = null;
        String loc1 = "images/logo.png";
        int a1 = 400, b1 = 130;
        label1 = new JLabel(imageresize(loc1, a1, b1));
        label1.setBounds(100, 0, 900, 180);
        panel1.add(label1);

        JButton profilebutton = null;
        String loc2 = "images/profileicon.png";
        int a2 = 50;
        int b2 = 50;
        profilebutton = new JButton(imageresize(loc2, a2, b2));
        // profilebutton.setBounds(970, 190, 50, 50);
        profilebutton.setBackground(new Color(10, 122, 140));
        profilebutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();

            }
        });
        panel2.add(profilebutton);

        JButton cartbutton = null;
        String loc3 = "images/search.png";
        int a3 = 50;
        int b3 = 50;
        cartbutton = new JButton(imageresize(loc3, a3, b3));
        cartbutton.setBounds(1030, 190, 50, 50);
        cartbutton.setBackground(new Color(10, 122, 140));
        cartbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        // panel2.add(cartbutton);
        // cartbutton.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(panel1);
        panel.add(panel2);
        // panel.add(panel2);

        ArrayList books = new ArrayList();
        ArrayList price = new ArrayList();
        ArrayList id = new ArrayList();
        ArrayList no_sold = new ArrayList();
        ArrayList rating = new ArrayList();
        ArrayList pubid = new ArrayList();
        try {
            model m = new model();
            ResultSet rs = m.getBookDetails();
            while (rs.next()) {
                books.add(rs.getString("name"));
                no_sold.add(rs.getInt("total_sold"));
                id.add(rs.getInt("id"));
                rating.add(rs.getFloat("rating"));
                pubid.add(rs.getString("publisher_id"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        String data[][] = new String[books.size() + 1][5];
        total = 0;
        for (int i = 0; i < books.size(); i++) {
            data[i][0] = id.get(i).toString();
            data[i][1] = (String) books.get(i);
            data[i][2] = pubid.get(i).toString();
            data[i][3] = no_sold.get(i).toString();
            data[i][4] = rating.get(i).toString();
        }
        // data[books.size()][1] = "Total";
        // data[books.size()][2] = "Rs. " + total;
        String column[] = { "Book Id", "Book Name", "Publisher Id", "Total Books Sold", "Rating" };
        DefaultTableModel model = new DefaultTableModel(data, column);
        JTable jt = new JTable(model);
        jt.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt.setBackground(Color.WHITE);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jt.getColumnCount(); i++) {
            jt.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }
        jt.setRowHeight(jt.getRowHeight() + 20);
        jt.setFont(new Font("Serif", Font.BOLD, 18));
        // jt.setBounds(0,36,700,800);
        JScrollPane sp = new JScrollPane(jt);
        sp.setPreferredSize(new Dimension(300, 700));
        sp.setBounds(100, 300, 900, 200);
        sp.setBackground(new Color(210, 241, 247));
        JButton c = new JButton("Update book");
        JButton remove = new JButton("Delete Book");
        jt.getColumnModel().getColumn(0).setPreferredWidth(30);
        jt.getColumnModel().getColumn(1).setPreferredWidth(400);
        jt.getColumnModel().getColumn(2).setPreferredWidth(30);
        jt.getColumnModel().getColumn(3).setPreferredWidth(50);
        jt.getColumnModel().getColumn(4).setPreferredWidth(50);

        // jt.getColumnModel().getColumn(0).setBackground(Color.RED);
        // jt.getColumnModel().getColumn(1).setForeground(Color.WHITE);

        f.getContentPane().setBackground(new Color(210, 241, 247));
        // f.add(l1);
        // f.add(l2);
        // f.add(l3);

        f.add(sp);

        f.add(panel);
        f.add(l3);
        // panel.add(l4);
        f.setSize(1100, 800);
        f.setLayout(null);
        panel.setLayout(null);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }

    public ImageIcon imageresize(String fileloc, int a, int b) {
        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File(fileloc));
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

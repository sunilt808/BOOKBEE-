

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import static java.sql.JDBCType.NULL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.DefaultStyledDocument.ElementSpec;
import javax.swing.text.TableView.TableRow;

import java.awt.*;

import modules.Searchpageframe;
import modules.model;

import javax.swing.table.*;

public class UserProfile {
    // JFrame f=new JFrame();
    JFrame f = new JFrame("Profile");
    private TableCellRenderer rightRenderer_c;
    int total;

    UserProfile() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1100, 140);
        panel.setBackground(new Color(10, 122, 140));
        JLabel l1, l2, l3;
        JLabel l4;
        l4 = null;

        int a = 900;
        int b = 170;

        l3 = new JLabel("Books You have Bought");
        l3.setBounds(100, 224, 400, 30);
        l3.setFont(new Font("Serif", Font.BOLD, 20));
        l3.setBackground(Color.WHITE);
        l3.setForeground(Color.RED);
        l3.setBackground(Color.WHITE);
        l3.setFont(new Font("Verdana", Font.BOLD, 20));
        l3.setForeground(new Color(10, 122, 140));

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
                System.out.println("cart button");
                Searchpageframe.searchpagecall(model.userid);
                f.dispose();

            }
        });
        panel2.add(cartbutton);
        // cartbutton.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(panel1);
        panel.add(panel2);
        // panel.add(panel2);

        ArrayList books = new ArrayList();
        ArrayList price = new ArrayList();
        try {
            model m = new model();
            // model.userid = 12;
            ResultSet rs = m.getOrderDetails();
            while (rs.next()) {
                books.add(rs.getString("name"));
                price.add(rs.getString("price"));
                System.out.println(rs.getString("name"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        String data[][] = new String[books.size() + 1][2];
        total = 0;
        for (int i = 0; i < books.size(); i++) {
            data[i][0] = String.valueOf(i + 1);
            data[i][1] = (String) books.get(i);
            // data[i][2] = "Rs ." + (String) price.get(i);
            // total += Integer.valueOf(String.valueOf(price.get(i)));
        }
        // data[books.size()][1] = "Total";
        // data[books.size()][2] = "Rs. " + total;
        String column[] = { "Item No", "Book Name" };
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
        JButton c = new JButton("Read Book");
        JButton remove = new JButton("Give Rating");
        remove.setBackground(Color.red);
        remove.setPreferredSize(new Dimension(120, 40));
        remove.setForeground(Color.white);

        c.setBackground(new Color(224, 211, 90));
        c.setPreferredSize(new Dimension(120, 40));
        c.setForeground(Color.BLACK);
        jt.getColumnModel().getColumn(0).setPreferredWidth(200);
        jt.getColumnModel().getColumn(1).setPreferredWidth(450);
        // jt.getColumnModel().getColumn(2).setPreferredWidth(200);

        // jt.getColumnModel().getColumn(0).setBackground(Color.RED);
        // jt.getColumnModel().getColumn(1).setForeground(Color.WHITE);

        remove.setBounds(740, 570, 140, 30);
        remove.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent ae) {
                String[] options = { "1", "2", "3", "4", "5" };
                if (jt.getSelectedRow() >= 0) {
                    int x = JOptionPane.showOptionDialog(f,
                            "Give Ratings for Book " + books.get(jt.getSelectedRow()).toString(), "Ratings",
                            JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    System.out.println(x);
                    model m = new model();
                    m.setRating(x, jt.getSelectedRow());
                } else {
                    JOptionPane.showMessageDialog(f, "Please Select the Book to give Rating", "Inane error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        });
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JTextField xField = new JTextField(5);
                JTextField yField = new JTextField(5);

                JOptionPane.showMessageDialog(null, "Enjoy Your Book");

            }

        });
        c.setBounds(500, 600, 120, 30);
        f.getContentPane().setBackground(new Color(232, 232, 232));
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
        f.setLocationRelativeTo(null);
        panel.setLayout(null);
        f.setResizable(false);
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

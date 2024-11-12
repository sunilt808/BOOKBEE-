

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class View extends JFrame implements ActionListener {
    JTextField tf;
    JLabel bookName, price, category, author, rating, available, comment, user, rev;
    JButton addToCart, goBack, post, revbutton;
    JPanel suggestion, navigation, reviewPane, resultPane, displayPane, margin, details, buttonPane, commentPane;
    JPanel[] boxpane = new JPanel[6];
    JButton[] button = new JButton[6];
    JTextArea review, review2;
    GridBagConstraints gdc = new GridBagConstraints();

    private Object imageName;
    private String authorName;
    private int priceAmount;
    private String bookDescription;
    private String bookcategry;
    private String book;
    private float rate;
    private int[] j;
    private int bookid;

    public View(model m, int[] j, int bookid) {
        this.j = j;
        this.bookid = bookid;
        setBookDetails(m);
        initGui();
        suggestiongui(j);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // pack();

        setSize(1100, 900);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }

    public void initGui() {
        navigation = new JPanel(new GridLayout(1, 2));
        suggestion = new JPanel();
        resultPane = new JPanel(new BorderLayout());
        reviewPane = new JPanel();
        displayPane = new JPanel();
        margin = new JPanel();
        details = new JPanel();
        buttonPane = new JPanel();

        // suggestion.setLayout(new BorderLayout(0, 30));
        suggestion.setBackground(new Color(232, 232, 232));
        navigation.setBackground(new Color(10, 122, 140));
        reviewPane.setBackground(new Color(232, 232, 232));
        displayPane.setBackground(new Color(232, 232, 232));
        margin.setBackground(new Color(232, 232, 232));
        details.setBackground(new Color(232, 232, 232));
        buttonPane.setBackground(new Color(232, 232, 232));

        navigation.setPreferredSize(new Dimension(0, 125));
        suggestion.setPreferredSize(new Dimension(300, 100));
        reviewPane.setPreferredSize(new Dimension(0, 270));
        margin.setPreferredSize(new Dimension(40, 0));
        buttonPane.setPreferredSize(new Dimension(80, 10));

        add(suggestion, BorderLayout.EAST);
        add(navigation, BorderLayout.NORTH);
        add(resultPane, BorderLayout.CENTER);
        add(margin, BorderLayout.WEST);
        resultPane.add(displayPane, BorderLayout.CENTER);

        ImageIcon imageIcon = setImage(imageName, 250, 300);
        final JLabel picLabel = new JLabel(imageIcon);
        displayPane.setLayout(new BorderLayout());
        displayPane.add(picLabel, BorderLayout.WEST);
        displayPane.add(details, BorderLayout.CENTER);
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));

        bookName = new JLabel("Harry potter and The Half Blood Prince");
        bookName.setText(book);
        bookName.setFont(new Font("Serif", Font.BOLD, 22));

        price = new JLabel("Rs. 900 /-");
        price.setText("Rs. " + Integer.toString(priceAmount));
        price.setFont(new Font("Verdana", Font.PLAIN, 18));

        author = new JLabel("J.K. Rowling");
        author.setText(authorName);
        author.setFont(new Font("Verdana", Font.PLAIN, 18));

        rating = new JLabel("4.5 / 5 of 243 ratings ");
        rating.setText(Float.toString(rate) + "/5 rating");
        rating.setFont(new Font("Verdana", Font.PLAIN, 18));

        available = new JLabel("In Stock");
        available.setText(bookcategry);
        available.setFont(new Font("Verdana", Font.PLAIN, 18));

        addToCart = new JButton("Add To Cart");
        addToCart.addActionListener(this);
        addToCart.setBackground(Color.red);
        addToCart.setPreferredSize(new Dimension(140, 30));
        addToCart.setForeground(Color.white);
        // addToCart.setFont(new Font("Verdana", Font.PLAIN, 14));
        // buttonPane.setLayout();
        buttonPane.add(addToCart);
        // goBack = new JButton("Search Others");
        // buttonPane.add(goBack);

        details.add(Box.createVerticalGlue());

        details.add(bookName);
        details.add(Box.createRigidArea(new Dimension(20, 20)));
        details.add(price);
        details.add(Box.createRigidArea(new Dimension(20, 20)));
        details.add(author);
        details.add(Box.createRigidArea(new Dimension(20, 20)));
        details.add(rating);
        details.add(Box.createRigidArea(new Dimension(20, 20)));
        details.add(available);
        details.add(Box.createRigidArea(new Dimension(20, 20)));

        details.add(Box.createVerticalGlue());

        details.add(buttonPane);

        bookName.setAlignmentX(Component.LEFT_ALIGNMENT);
        price.setAlignmentX(Component.LEFT_ALIGNMENT);
        rating.setAlignmentX(Component.LEFT_ALIGNMENT);

        displayPane.add(details);

        // commentPane = new JPanel();
        JEditorPane textArea = new JEditorPane();
        textArea.setContentType("text/html");
        textArea.setText(
                "<html><p style=\"color:gray;font-weight:medium;font-size:17px\"><b>Book Description:</b></p><p style=\"color:black;font-size:15px;font-style:italic;\">"
                        + bookDescription + "</p><br><br><p>----------------------------</P></html>");
        textArea.setFont(new Font("Serif", Font.PLAIN, 18));
        // By default the JTextArea is editable, calling
        textArea.setBackground(new Color(247, 235, 235));
        // setEditable(false) produce uneditable JTextArea.
        textArea.setEditable(false);

        // textArea.setLineWrap(true);

        reviewPane.setPreferredSize(new Dimension(180, 700));
        JScrollPane scrollreview = new JScrollPane(textArea);
        // scrollreview.setLayout();
        // scrollreview.getViewport().setMinimumSize(new Dimension(30, 50));
        scrollreview.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollreview.setPreferredSize(new Dimension(40, 170));
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                scrollreview.getViewport().setViewPosition(new Point(0, 0));
            }
        });
        // reviewPane.add(commentPane);
        // reviewPane.add(review2);

        resultPane.add(scrollreview, BorderLayout.SOUTH);

        JPanel panel1 = new JPanel();
        panel1.setBounds(0, 0, 1100, 180);
        panel1.setBackground(new Color(10, 122, 140));
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        // panel.setLayout(new FlowLayout());

        // JPanel panel2 = new JPanel();
        // panel2.setBounds(850, 180, 250, 620);
        // panel2.setBackground(new Color(128, 218, 232));

        JLabel label1 = null;
        String loc1 = "logo.png";
        int a1 = 420, b1 = 115;
        label1 = new JLabel(setImage(loc1, a1, b1));
        label1.setBounds(70, 0, 900, 180);
        panel1.add(label1);
        JPanel panel2 = new JPanel();
        panel2.setBounds(0, 0, 1100, 620);
        panel2.setBackground(new Color(10, 122, 140));
        panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton profilebutton = null;
        String loc2 = "carticon.jpg";
        int a2 = 45;
        int b2 = 45;
        profilebutton = new JButton(setImage(loc2, a2, b2));
        // profilebutton.setBounds(970, 190, 50, 50);
        profilebutton.setBackground(new Color(10, 122, 140));
        profilebutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("my profile");
                Cart x = new Cart();
                dispose();
            }
        });
        panel2.add(profilebutton);

        JButton cartbutton = null;
        String loc3 = "search.png";
        int a3 = 45;
        int b3 = 45;
        cartbutton = new JButton(setImage(loc3, a3, b3));
        cartbutton.setBounds(1030, 190, 50, 50);
        cartbutton.setBackground(new Color(10, 122, 140));
        cartbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("cart button");
                Searchpageframe.searchpagecall(model.userid);
                dispose();

            }
        });
        panel2.add(cartbutton);

        JButton profbutton = null;
        String loc4 = "profileicon.png";
        int a4 = 45;
        int b4 = 45;
        profbutton = new JButton(setImage(loc4, a4, b4));
        profbutton.setBounds(1090, 190, 50, 50);
        profbutton.setBackground(new Color(10, 122, 140));
        profbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserProfile project1 = new UserProfile();
                dispose();

            }
        });
        panel2.add(profbutton);
        // cartbutton.setAlignmentX(Component.LEFT_ALIGNMENT);
        navigation.add(panel1);
        navigation.add(panel2);

    }

    public void suggestiongui(int[] j) {
        // suggestion.setLayout(n;
        for (int i = 0; i < 6; i++) {
            boxpane[i] = new JPanel();
            boxpane[i].setLayout(new GridBagLayout());
            boxpane[i].setPreferredSize(new Dimension(280, 170));
            boxpane[i].setBackground(new Color(217, 249, 255));
            gdc = createGbc(0, 0);
            model x = new model();
            System.out.println(j[i]);
            x.assignBookId(j[i]);

            ImageIcon imageIcon = setImage(x.getImageName(), 100, 100);
            final JLabel picLabel = new JLabel(imageIcon);
            boxpane[i].add(picLabel, gdc);

            JLabel name = new JLabel("BOOK1");
            name.setBackground(new Color(10, 122, 140));
            name.setForeground(Color.BLACK);

            name.setFont(new Font("Verdana", Font.BOLD, 12));
            name.setPreferredSize(new Dimension(130, 20));
            name.setText(x.getBookName());

            gdc = createGbc(1, 0);
            boxpane[i].add(name, gdc);

            JLabel price = new JLabel("price");
            price.setText("Rs. " + Integer.toString(x.getPrice()));
            gdc = createGbc(1, 1);
            boxpane[i].add(price, gdc);

            button[i] = new JButton("view");
            button[i].setBackground(new Color(10, 122, 140));
            button[i].setForeground(Color.WHITE);
            button[i].setFont(new Font("Verdana", Font.BOLD, 11));
            gdc = createGbc(1, 2);
            button[i].addActionListener(this);
            boxpane[i].add(button[i], gdc);
            suggestion.setLayout(new GridLayout(6, 1));
            suggestion.add(boxpane[i]);
        }

    }

    private GridBagConstraints createGbc(int x, int y) {
        Insets WEST_INSETS = new Insets(5, 0, 5, 5);
        Insets EAST_INSETS = new Insets(5, 0, 5, 5);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        gbc.gridheight = (x == 0) ? 3 : 1;

        gbc.anchor = (x == 0) ? GridBagConstraints.WEST : GridBagConstraints.WEST;
        gbc.fill = (x == 0) ? GridBagConstraints.BOTH : GridBagConstraints.EAST;

        gbc.insets = (x == 0) ? WEST_INSETS : EAST_INSETS;
        gbc.weightx = (x == 0) ? 0.1 : 0.1;
        gbc.weighty = 1.0;
        return gbc;
    }

    private ImageIcon setImage(Object imageSource, int height, int width) {
        if (imageSource == null) {
            System.err.println("Image source is null");
            return null;
        }

        BufferedImage myPicture = null;
        try {
            // Check if the image source is a String (file path)
            if (imageSource instanceof String) {
                String imagePath = "images/" + (String) imageSource;
                myPicture = ImageIO.read(new File(imagePath));
            }
            // Check if the image source is an InputStream
            else if (imageSource instanceof InputStream) {
                myPicture = ImageIO.read((InputStream) imageSource);
            } else {
                System.err.println("Unsupported image source type: " + imageSource.getClass().getName());
                return null;
            }

            // Ensure the image was read successfully
            if (myPicture == null) {
                System.err.println("Failed to read image: " + imageSource);
                return null;
            }

            // Scale the image smoothly
            Image scaledImage = myPicture.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);

        } catch (IOException e) {
            System.err.println("Error loading image: " + imageSource);
            e.printStackTrace();
        }

        return null;
    }



    public void setBookDetails(model m) {
     imageName = m.getImageName();
        authorName = m.getAuthor();
        priceAmount = m.getPrice();
        bookDescription = m.getDescription();
        bookcategry = m.getCategory();
        book = m.getBookName();
        rate = m.getRating();

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addToCart) {
            model k = new model();
            k.addToCart(bookid);
            Cart c = new Cart();
            dispose();

        }
        for (int i = 0; i < 6; i++) {
            if (e.getSource() == button[i]) {

                model m = new model();
                m.assignBookId(j[i]);
                new View(m, m.getSuggestion(), j[i]);
                dispose();
                break;

            }
        }

    }
}
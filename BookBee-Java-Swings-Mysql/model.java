

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.PreparedStatement;

public class model {
    private Connection con;
    private ResultSet rs;
    private String bookName;
    private InputStream imageName;
    private int price;
    private String author;
    private String category;
    private String description;
    private float rating;
    private int noSold;
    private int id;
    public static int userid;

    public model() {
        // Creating connection to mysql database
        try {
            Class.forName("com.mysql.jdbc.Driver"); // loading and registering the driver
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookbee", "root", "Mysql@78298365");

        } catch (Exception e) {
            System.out.println("Database connection failed\n");
            e.printStackTrace();
        }
    }

    public void assignBookId(int id) {
        this.id = id;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM books WHERE id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            bookName = rs.getString("name");
            price = rs.getInt("price");
            author = rs.getString("author");
            category = rs.getString("category");
            description = rs.getString("description");
            imageName = rs.getBinaryStream("imagefile");
            rating = rs.getFloat("rating");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getBookName() {
        return bookName;
    }

    public InputStream getImageName() {
        try {
            // InputStream is = imageName.getBinaryStream(0, imageName.length());
            return imageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public float getRating() {
        return rating;
    }

    public int getTotalSold() {
        return noSold;
    }

    public int[] getSuggestion() {
        int[] res = new int[6]; // Array to store the top 6 suggestions
        int index = 0; // To keep track of the array index

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM books ORDER BY rating DESC");
            ResultSet ss = ps.executeQuery();

            while (ss.next() && index < res.length) {
                int bookId = ss.getInt("id");
                
                // Skip if the book ID matches the current user/book ID
                if (bookId == id) {
                    continue;
                }

                // Add the book ID to the result array
                res[index++] = bookId;
            }

        } catch (Exception e) {
            System.err.println("Error fetching book suggestions:");
            e.printStackTrace();
        }

        return res;
    }


    public ResultSet getUserDetails(int user) {
        ResultSet k = null;
        try {
            PreparedStatement ps = con.prepareStatement("select * FROM users WHERE id=?");
            ps.setInt(1, user);
            k = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;
    }

    public void addToCart(int bookid) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO `cart` ( `user_id`, `book_id`) VALUES ( ?, ?);");
            ps.setInt(1, userid);
            ps.setInt(2, bookid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ResultSet getCartDetails() {
        ResultSet k = null;
        try {
            PreparedStatement ps = con.prepareStatement(
                    "select books.name,books.price FROM books inner join cart on cart.book_id=books.id where cart.user_id=?");
            System.out.println(userid);
            ps.setInt(1, userid);
            k = ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;
    }

    public void deleteincart(String bookname) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE from cart  where cart.user_id=? AND book_id=(Select id from books where name=?)");
            ps.setInt(1, userid);
            ps.setString(2, bookname);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addtoOrder(String book_name) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "Insert into orders (user_id,book_id) VALUES (?,(Select id from books where name=?))");
            ps.setInt(1, userid);
            ps.setString(2, book_name);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ResultSet getOrderDetails() {
        ResultSet k = null;
        try {
            PreparedStatement ps = con.prepareStatement(
                    "select books.name,books.price FROM books inner join orders on orders.book_id=books.id where orders.user_id=?");
            System.out.println(userid);
            ps.setInt(1, userid);
            k = ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;
    }

    public ResultSet getBookDetails() {

        ResultSet k = null;
        try {
            PreparedStatement ps = con.prepareStatement("select * from books");
            k = ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;

    }

    public void setRating(int rate, int id) {
        try {
            PreparedStatement ps = con.prepareStatement("select rating,totalratings from books where id=?");

            ps.setInt(1, id);
            ResultSet k = ps.executeQuery();
            k.next();
            int Oldrating = k.getInt("rating");
            int totalratings = k.getInt("totalratings");
            int newrating = Oldrating + (rate - Oldrating) / (totalratings + 1);

            PreparedStatement ks = con.prepareStatement("Update books set rating=?,totalratings=? where id=?");
            ks.setInt(1, newrating);
            ks.setInt(2, totalratings + 1);
            ks.setInt(3, id);
            ks.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
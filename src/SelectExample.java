import java.sql.*;

public class SelectExample {
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";
    static final String USER = "guest";
    static final String PASS = "guest";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT id, first, last, age From Employees";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                //Retrieve by column name
                int id = rs.getInt("id"); int age = rs.getInt("age");
                String first = rs.getString("first"); String last = rs.getString("last");
                //Display values
                System.out.print("ID: " + id); System.out.print(", Age: " + age);
                System.out.print(", First: " + first); System.out.println(", Last: " + last);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch ( SQLException se) { se.printStackTrace(); // Handle errors for JDBC
        } catch ( Exception e ) { e.printStackTrace(); // Handle errors for Class.forName
        } finally { //finally block used to close resources

            try {
                if ( stmt!=null ) stmt.close();
            } catch ( SQLException se2 ) { /* nothing we can do */ }
            try { if ( conn!=null ) conn.close(); }
            catch ( SQLException se ) { se.printStackTrace(); }
        }
        System.out.println("Goodbye!");

    }
}

package heck_db_conn;

// import java.sql.*;
//from the yt video
// public class Principal {
//     public static void main(String[] args) {
//        try {
//         Class.forName("com.mysql.cj.jdbc.Driver");
//         Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/reuniao","root", "");
//         Statement stmt=con.createStatement();
//         ResultSet rs=stmt.executeQuery("SELECT * FROM pessoa");
//         while(rs.next())
//         {
//             System.out.println(rs.getString(1));
//         }
//         con.close();
//        }     
//        catch(Exception e){
//         System.out.println(e);
//        }
//     }
// }
import java.sql.*;

public class Principal {
    public static void main(String[] args) {
        final String db_url = "jdbc:mysql://localhost:3306/reuniao";
        final String db_query = "SELECT * FROM pessoa";
        final String db_user = "root";
        final String db_password = "";
        // Pessoa[]p;
        // int reultSetRows = 0;
        System.out.println("Iniciando conexão com o DB.");
        try (Connection c = DriverManager.getConnection(db_url, db_user, db_password);
                Statement statement = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet resultSet = statement.executeQuery(db_query)) {
            System.out.println("Conectado ao MySQL");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " \t"
                        + resultSet.getString(3) + " \t" + resultSet.getString(4));
                System.out.println();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
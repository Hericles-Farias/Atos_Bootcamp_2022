// package heck_db_conn;
// import java.sql.*;

// public class InsertionP {
    
//     public static void main(String[] args) {
//         final String db_url = "jdbc:mysql://localhost:3306/reuniao";
//         final String db_query = "INSERT INTO pessoa (nome,email,cargo) VALUES ('Alberto Santos','santos@yahoo.com','Professor')";
//         final String db_query2 = "UPDATE pessoa SET nome='Paulo Henrique', email='paulo.henrique@gmail.com', cargo='Aluno' WHERE nome='Alberto Santos'";
//         final String db_user = "root";
//         final String db_password = "";
//         // Pessoa[]p;
//         // int reultSetRows = 0;
//         System.out.println("Iniciando conexão com o DB.");
//         try {
//             Connection c = DriverManager.getConnection(db_url, db_user, db_password);
//             Statement statement = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//             //ResultSet resultSet = statement.executeQuery(db_query))
//             System.out.println("Conectado ao MySQL");
//             int linhas  = statement.executeUpdate(db_query2);
//             System.out.println("A query afetou: "+linhas);

//         } catch (SQLException sqlException) {
//             sqlException.printStackTrace();
//         }
//     }
// }

package heck_db_conn;

import java.sql.SQLException;

public class PrincipalDb {
    public static void main(String[] args) {
        // //testing
        // try {
        //     Db database = new Db("reuniao","root","");
        // }catch(SQLException e){
        //         System.out.println("Error: "+e.getMessage());
        // }
        //Connecting to Database
        try {
            Db database = new Db("reuniao","root","");
                    //Writing Operation
                    Object[] firstParams = {"HericlesNo2vo", "hericleslannister@gmail.com", "Aluno"};
                    int success1 = database.insert("pessoa", firstParams);
                    System.out.println("Inserted entries = " + success1);

                    
                    //Deletin Operation
                    // Object[] params2 = {"pauloVargas@ufn.edu.br"};
                    // int success2 = database.delete("pessoas", "email = ?", params2);
                    // System.out.println("Deleted entries= "+success2);
                    // //


        } catch (SQLException ex) {
                 System.out.println("error - "+ex.getMessage());
        }

    }    
}

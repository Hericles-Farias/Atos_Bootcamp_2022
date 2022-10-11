package com.hecktest;
import java.sql.ResultSet;
import java.sql.SQLException;
public class PrincipalDb {
    public static void main(String[] args) {
        try {
            Db database = new Db("reuniao","root","");
                    //Write Operation
                    // System.out.println("********WRITE OPERATION*******");
                    // Object[] p1 = {"H2ericlesNo2vods", "hericleslannister@gmail.com", "Aluno"};
                    // int s1 = database.insert("pessoa", p1);
                    // System.out.println("Inserted entries = " + s1);

                    
                    // //Delete Operation
                    // Object[] p2 = {"hericleslannister@gmail.com"};
                    // int s2 = database.delete("pessoa", "email = ?", p2);
                    // System.out.println("Deleted entries= "+s2);
                    // //

                    // //UPDATE OPERATION
                    // String[] columns = {"nome","email","cargo"};
                    // Object[] p = {"Hericles","hericlesfarias@outlook.com","DEV"};
                    // int s3 = database.update("pessoa", columns, "ID = 5", p);
                    // System.out.println("Updated Entries: "+s3);

                    //SELECT OPERATION
                    String[] columns = {"nome","email"};
                    Object[] p = {"Aluno"};
                    //Object[] p = {""};
                    ResultSet rs = database.select("pessoa",columns,"cargo = ?",p);
                    //ResultSet rs = database.select("pessoa",columns,"",p);
                    while (rs.next()){
                        System.out.println(rs.getString("nome")+" - "+rs.getString("email"));
                    }
                
        } catch (SQLException ex) {
                 System.out.println("error - "+ex.getMessage());
        }

    }    
}

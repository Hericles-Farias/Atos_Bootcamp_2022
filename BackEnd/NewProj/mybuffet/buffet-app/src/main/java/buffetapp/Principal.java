package buffetapp;

import com.br.database.*;

public class Principal {
    
    public static void main(String[] args) {
        dbOp dbk = new dbOp("heckjpa");
        try {
            dbk.Insert("Riven22222a223");
        }catch (Exception e){
            System.out.println("Error: "+e);
        }    

    }
}

package heck_sobrecarga;

import java.io.FileWriter;
import java.io.IOException;

public class UserInfo {
    String name;
    String email;

    public void saveInfo(String new_name,String new_email){
        name = new_name;
        email = new_email;
    }
    public void saveInfo(String new_name){
        name = new_name;
    }
    

    public void saveToTxt(){
        String filename = "./BackEnd/ExerciciosdeAula/Exerc_Aulas/src/heck_sobrecarga/" + name + ".txt";
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write("My name is: "+name);
            if (email !=null){
                myWriter.write("\nMy e-mail is: "+email);
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        }
}

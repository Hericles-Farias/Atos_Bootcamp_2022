package heck_construtores;

import java.util.Scanner;

public class Principal {
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.err.println("Choose a constructor type: \n (A) Passing name and age\n(B)Passing only age");
        String st = s.nextLine();
        
        if (st.equals("A")){
            System.err.println("Enter the name: ");
            String name = s.nextLine();
            System.err.println("Enter the age: ");
            Integer age = Integer.parseInt(s.nextLine());
            Pessoa p = new Pessoa(name,age);
            p.showInfo();
        }
        else{
            System.err.println("Enter the age: ");
            Integer age = Integer.parseInt(s.nextLine());
            Pessoa p = new Pessoa(age);
            p.showInfo();
        }
        s.close();
        
    

    }
}

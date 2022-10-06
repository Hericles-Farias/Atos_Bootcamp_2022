package heck_sobrecarga;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        UserInfo uf = new UserInfo();
        Scanner s = new Scanner(System.in);
        System.out.println("Entre com o nome do Usuario: ");
        String name = s.nextLine();
        System.out.println("Entre com o e-mail, ou aperte Enter para deixá-lo em branco: ");
        String email = s.nextLine();
        if (email.equals("")){
            uf.saveInfo(name);
        }else{
            uf.saveInfo(name,email);
        }
        uf.saveToTxt();
        s.close();
    }
}

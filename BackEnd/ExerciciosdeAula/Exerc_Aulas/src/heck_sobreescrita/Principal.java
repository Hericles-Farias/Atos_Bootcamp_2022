package heck_sobreescrita;
import java.util.Scanner;
public class Principal {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Entre com o Primeiro Numero: ");
        Integer n1 = Integer.parseInt(s.nextLine());
        System.out.println("Entre com o Segundo Numero: ");
        Integer n2 = Integer.parseInt(s.nextLine());
        s.close();
        System.out.println("----------------------------------");
        Soma sum = new Soma();
        sum.calcular(n1,n2);
        sum.exibeResultado();
        
        Div div = new Div();
        div.calcular(n1,n2);
        div.exibeResultado();
        
        Mult mult = new Mult();
        mult.calcular(n1,n2);
        mult.exibeResultado();
        
        Sub sub = new Sub();
        sub.calcular(n1,n2);
        sub.exibeResultado();
        
    }
}

package heck_heranca;

import java.util.Scanner;

public class Principal {
    
    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);
        System.out.println("Informe o nome: ");
        String nome = s.nextLine();
        System.out.println("Informe a idade: ");
        Integer idade = Integer.parseInt(s.nextLine());
        System.out.println("Informe o tipo: ");
        String tipo = s.nextLine();
        s.close();
        Pessoa p = new Pessoa(nome,idade,tipo);
        p.showInfo();
        p.falar();
        p.andar();
    }

}

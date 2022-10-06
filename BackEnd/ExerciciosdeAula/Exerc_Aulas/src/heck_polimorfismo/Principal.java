package heck_polimorfismo;
import java.util.Scanner;
public class Principal {
    public static void main(String[] args) {
        System.out.println("(1) Profissional ou\n(2) Pessoal?");
        Scanner s = new Scanner(System.in);
        Integer option = Integer.parseInt(s.nextLine());
        System.out.println("Entre com o Compromisso: ");
        String comp = s.nextLine();
        System.out.println("Entre com o dia: ");
        String data = s.nextLine();
        System.out.println("Entre com a hora: ");
        String hora = s.nextLine();
        s.close();
        if (option==1){
            Profissional p = new Profissional();
            p.nome=comp;
            p.data=data;
            p.hora=hora;
            p.exibeCompromissoProfissional();

        }else{
            Pessoal p = new Pessoal();
            p.nome=comp;
            p.data=data;
            p.hora=hora;
            p.exibeCompromissoPessoal();
        }
    }

}

package heck_interface;
import java.util.Scanner;

public class Principal extends Mensagem {
    //A classe que herdar a classe abstrata vai quer ter sobrescrever os métodos/atributos dela
    //Caso contratio ela nao vai funcionar, nessa sobrescrita os parametros de entrada
    //definidos la na classe abstrata devem ser respeitados tbm
    //Se esta classe tbm fosse do tipo abstrata a gente iria precisar sobrescrever, pois
    //classes abstratar devem ser implementadas somente pela primeira classe concreta que herdar ela
    //Todas as classes abstratas subsequentes que herdarem uma abstrata nao precisa sobrecrever o método
    @Override
    public void exibeMensagemA(){
        System.out.println("Cuide Bem dos Animais");
    }
    @Override//Is this really needed?
    public void exibeMensagemB(String msgFromUser){
        System.out.println(msgFromUser);
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Entre com uma mensagem para o meio ambiente:");
        String userMsg = s.nextLine();
        s.close();
        System.out.println("----------------------------");
        Principal msg = new Principal();
        msg.exibeMensagemA();
        msg.exibeMensagemB(userMsg);

    }

}

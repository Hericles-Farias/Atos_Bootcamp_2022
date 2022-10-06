package heck_polimorfismo;

public class Pessoal extends Compromisso implements InterfacePessoal {
    
    @Override
    public void exibeCompromissoPessoal(){
        System.out.println("Compromisso Pessoal\n");
        System.out.println("Compromisso: "+nome);
        System.out.println("Data: "+data);
        System.out.println("Hora: "+hora);
    }
}

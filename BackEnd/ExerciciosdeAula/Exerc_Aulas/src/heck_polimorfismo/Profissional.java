package heck_polimorfismo;

public class Profissional extends Compromisso implements InterfaceProfissional{


    @Override
    public void exibeCompromissoProfissional(){
        System.out.println("Compromisso Profussional\n");
        System.out.println("Compromisso: "+nome);
        System.out.println("Data: "+data);
        System.out.println("Hora: "+hora);
    }
}

package heck_heranca;

public class Pessoa extends SerHumano {
    boolean bipede=true;

    public void showInfo(){
        System.out.println("Nome: "+nome);    
        System.out.println("Idade: "+idade);    
        System.out.println("Tipo: "+tipo);    
        System.out.println("Bípede: "+bipede);    
    }
    public Pessoa(String nome, Integer idade, String tipo){
        this.nome = nome;
        this.idade= idade;
        this.tipo = tipo;
    }

}

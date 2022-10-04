package heck_construtores;

public class Pessoa {

    public String nome;
    public Integer idade;

    public Pessoa(String nome, Integer idade){
        this.nome = nome;
        this.idade = idade;
    }
    public Pessoa(Integer idade){
        this.idade = idade;
    }
    public void showInfo(){
        String s = String.format("Meu nome é %s \nMinha idade é %d",this.nome, this.idade);
        System.out.println(s);
    }

}

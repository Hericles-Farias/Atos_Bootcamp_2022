package heck_interface;

public abstract class Mensagem {
    //usamos uma classe abstrata quanto eu nao preciso isntanciar um objeto desta classe
    //Nao se pode instanciar classes abstratas
    //Um exemplo seria uma classe animal, tu pode ter varios animais do tipo cao, gato, etc (objetos instanciaveis)
    //Mas nao existe nenhum animal do tipo animal. No entanto a classe animal pode possuir metodos e 
    //atributos que estao presentes em todos as outras classes instanciaveis
    public abstract void exibeMensagemA();
    public abstract void exibeMensagemB(String msgFromUser);

}

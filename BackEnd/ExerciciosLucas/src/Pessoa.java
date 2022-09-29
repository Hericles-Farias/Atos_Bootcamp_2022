
public class Pessoa {
	private String nome="Fulano";
	private Integer idade;
	
	public Pessoa(String nome, Integer idade) {
		this.nome = nome;
		this.idade = idade;
	}
	public Pessoa(Integer idade) {
		this.idade=idade;
	}
	public void showInfo() {
		System.out.println("O nome desta pessoa é: "+this.nome);
		System.out.println("A idade dela é: "+this.idade);
	}
}

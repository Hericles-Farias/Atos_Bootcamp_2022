package exec2;

public class Aluno {
	private String nome="Fulano";
	private String matricula="SemMatricula";
	private String dataNascimento="SemNascimento";
	private String anoIngresso="SemAnodeIngresso";
	
	public Aluno(String nome, String matricula) {
		// TODO Auto-generated constructor stub
		this.nome = nome;
		this.matricula = matricula;
	}
	
//	public Aluno() {
//
//	}

	public Aluno(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Aluno(String nome, String dataNascimento, String anoIngresso){
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.anoIngresso = anoIngresso;
	}


	public void showInfo() {
		System.out.println("Nome= "+this.nome);
		System.out.println("Matricula= "+this.matricula);
		System.out.println("Data de Nascimento= "+this.dataNascimento);
		System.out.println("Ano de Ingresso na Faculdade= "+this.anoIngresso);
	
	}
	
}

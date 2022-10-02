package heck_heranca;

public class FuncionarioH extends PessoaH {
	public  String setor;
	public  String cargo;
	public  String funcao;
	
	public void showInfo() {
		System.out.println("Nome: "+nome);
		System.out.println("idade: "+idade);
		System.out.println("telefone: "+telefone);
		System.out.println("setor: "+setor);
		System.out.println("cargo: "+cargo);
		System.out.println("funcao: "+funcao);
	}
}

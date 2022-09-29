import java.util.Scanner;
public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	System.out.println("Escolha um construtor:");
	System.out.println("(A) - Nome e Idade");
	System.out.println("(B) - Idade");
	Scanner s = new Scanner(System.in);
	while (true){
		String choice = s.nextLine();
		if (choice.equals("A")){
			System.out.println("Entre com o nome: ");
			String nome = s.nextLine();
			System.out.println("Entre com a idade: ");
			Integer idade = s.nextInt();
			Pessoa pessoa = new Pessoa(nome,idade);
			pessoa.showInfo();
			break;
		}else if (choice.equals("B")) {
			System.out.println("Entre com o nome: ");
			Integer idade = s.nextInt();
			Pessoa pessoa = new Pessoa(idade);
			pessoa.showInfo();
			break;
		}else {
			System.out.println("Entrada Inválida, escolhe A ou B!");
			}
		}
	s.close();
	}
	
}

package heck_heranca;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalEmpresa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Scanner s = new Scanner(System.in);
		List<FuncionarioH> f = new ArrayList<FuncionarioH>();
		Integer n = 3;
		Scanner s = new Scanner(System.in);
		for(int i=0;i<n;i++) {			
			System.out.println("Novo Funcionario");
			System.out.println("-------------------------------");
			System.out.println("Entre com o nome do funcionario: ");
			String nomex=s.nextLine();
			System.out.println("Entre com a idade do funcionario: ");
			Integer idadex=Integer.parseInt(s.nextLine());
			System.out.println("Entre com o telefone do funcionario: ");
			Integer telefonex=Integer.parseInt(s.nextLine());
			System.out.println("Entre com o setor do funcionario: ");
			String setorx=s.nextLine();
			System.out.println("Entre com a cargo do funcionario: ");
			String cargox=s.nextLine();
			System.out.println("Entre com a funcao do funcionario: ");
			String funcaox=s.nextLine();
			
			FuncionarioH func1 = new FuncionarioH();
			func1.nome= nomex;
			func1.idade= idadex;
			func1.telefone= telefonex;
			func1.setor= setorx;
			func1.cargo= cargox;
			func1.funcao= funcaox;
			f.add(func1);
		}
		s.close();
		for(int i=0;i<n;i++) {
			f.get(i).showInfo();
	
		}

	}

}

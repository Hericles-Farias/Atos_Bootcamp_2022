package estudos_mod_acesso_b;
import estudos_mod_acesso_a.ClasseA;

public class ClasseC extends ClasseA{
    public static void main(String[] args){
        ClasseC cC = new ClasseC();
        System.out.println(cC.senha);//Cannot make a static reference to the non-static field
        //In this case we need to instanciate an object of this same class here
        //inside of itself. And this object will then have access to the parameter we need!
    }
}

package heck_sobreescrita;

public class Sub {
    double resultado;
    public void calcular(double v1, double v2){
        resultado = v1-v2;
    }
    public void exibeResultado(){
        System.out.println("Sub: "+ resultado);
    }
}
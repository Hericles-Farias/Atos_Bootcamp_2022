package heck_sobreescrita;

public class Div {
    
    double resultado;
    public void calcular(double v1, double v2){
        if (v2==0){
            System.out.println("Divisao por Zero Nao Permitida!");
        }else{
            resultado = v1/v2;
        }
    }
    public void exibeResultado(){
        System.out.println("Div: "+ resultado);
    }
}

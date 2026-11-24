package Project;

public class ErrNmaglia extends Exception{
    public ErrNmaglia(int numero, int i){
        i+=1;
        System.out.println(numero);
        System.out.println("nella squadra: "+i);
    }
}

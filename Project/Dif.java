package Project;

public class Dif extends Giocatore{
    
	private int def;

	public Dif(){}
	public Dif(int def){
		this.def = def;
	}

	public int getDef(){
		return(this.def);
	}


	public void setDef(int def){
		this.def = def;
	}


	public String toString(){
		return(this.def+ "");
	}

}
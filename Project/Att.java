package Project;

public class Att extends Giocatore{
    
	private int tiro;

	public Att(){}
	public Att(int tiro){
		this.tiro = tiro;
	}

	public int getTiro(){
		return(this.tiro);
	}


	public void setTiro(int tiro){
		this.tiro = tiro;
	}


	public String toString(){
		return(this.tiro+ "");
	}
}
package Project;

public class Cen extends Giocatore{
    
	private int pass;

	public Cen(){}
	public Cen(int pass){
		this.pass = pass;
	}

	public int getPass(){
		return(this.pass);
	}


	public void setPass(int pass){
		this.pass = pass;
	}


	public String toString(){
		return(this.pass+ "");
	}

}

package Project;

public class Por extends Giocatore{
    
	private int riflessi;

	public Por(){}
	public Por(int riflessi){
		this.riflessi = riflessi;
	}

	public int getDef(){
		return(this.riflessi);
	}


	public void setDef(int riflessi){
		this.riflessi = riflessi;
	}


	public String toString(){
		return(this.riflessi+ "");
	}
	public void setValutazione(){
		super.valutazione += 5;
	}
}

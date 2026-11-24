package Project;

public class Giocatore {
    

    
	private String name;
    private String cognome;
	private int nmaglia;
    protected int valutazione;
    

	public Giocatore(){}
	public Giocatore(String name, String cognome, int nmaglia, int valutazione){
		this.name = name;
        this.cognome = cognome;
		this.nmaglia = nmaglia;
        this.valutazione = valutazione;
	}

	public String getName(){
		return(this.name);
	}
	public String getCognome(){
		return(this.cognome);
	}
	public int getNmaglia(){
		return(this.nmaglia);
	}
    public int getValutazione(){
		return(this.valutazione);
	}
	public void setName(String name){
		this.name = name;
	}
	public void setCognome(String cognome){
		this.cognome = cognome;
	}
	public void setNmaglia(int nmaglia){
		this.nmaglia = nmaglia;
	}
	public void setNmaglia(String nmaglia){
		this.nmaglia = Integer.parseInt(nmaglia);
	}
    public void setValutazione(int valutazione){
		this.valutazione = valutazione;
	}

	public String toString(){
		return(this.name+" ("+this.cognome+")"+" ("+this.nmaglia+")"+" ("+this.valutazione+")");
	}

}

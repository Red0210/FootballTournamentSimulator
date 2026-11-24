package Project;

public class Squadra {
    private String name;
	private Att[] attaccanti = new Att[3];
    private Cen[] centrocampisti = new Cen[3];
    private Dif[] difensori = new Dif[4];
    private Por portiere;
	private int valutazione;
    private int valoff;
    private int valdif;
    
    public Squadra(String name, int valutazione){
		this.name = name;
        this.valutazione = valutazione;
	}
    public String getName(){
		return(this.name);
	}
    public void setName(String name){
		this.name = name;
	}
    public Att getAtt(int i){
		return(this.attaccanti[i]);
	}
    public void setAtt(Att attaccanti, int i){
		this.attaccanti[i] = attaccanti;
	}
    public Cen getCen(int i){
		return(this.centrocampisti[i]);
	}
    public void setCen(Cen centrocampisti, int i){
		this.centrocampisti[i] = centrocampisti;
	}
    public Dif getDif(int i){
		return(this.difensori[i]);
	}
    public void setDif(Dif difensori, int i){
		this.difensori[i] = difensori;
	}
    public Por getPor(){
		return(this.portiere);
	}
    public void setPor(Por portiere){
		this.portiere = portiere;
	}
    public int getValutazione(){
		return(this.valutazione);
	}
    public void setValutazione(){
		this.valutazione = this.portiere.getValutazione()+this.difensori[3].getValutazione();
        for(int i=0; i<3; i++){
            this.valutazione += this.attaccanti[i].getValutazione()+this.centrocampisti[i].getValutazione()+this.difensori[i].getValutazione();
        }
	}
    public int getValoff(){
		return(this.valoff);
	}
    public void setValoff(){
        for(int i=0; i<3; i++){
            this.valoff += this.attaccanti[i].getTiro()+this.centrocampisti[i].getPass();
        }
    }
    public int getValdif(){
		return(this.valdif);
	}
    public void setValdif(){
        this.valdif = this.portiere.getDef()*2;
        for(int i=0; i<4; i++){
            this.valdif += this.difensori[i].getDef();
        }
    }
}

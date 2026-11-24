package Project;

public class Torneo {
    
    private String vincitore;
    private String[] ordpartite;
	private Squadra[] squadre;
    private String[] risultati;

    public Torneo(int n){
		this.squadre = new Squadra[n];
        this.ordpartite = new String[n-1];
        this.risultati = new String[n-1];
	}

    public void setSquadre(Squadra[] squadre){
		this.squadre = squadre;
	}

    public String[] getOrdpartite(){
		return(this.ordpartite);
	}
    public String[] getRisultati(){
		return(this.risultati);
	}
    public Squadra[] getSquadre(){
		return(this.squadre);
	}

    public int calcolopunt(Squadra a, Squadra b){
        int x = 150 - a.getValutazione() - a.getValoff() +b.getValdif();     
        double y = Math.random() * x;
        int output = 0;
        if (y<=2){
            output = 4;
        }
        else if(y<=5){
            output = 3;
        }
        else if(y<=17){
            output = 2;
        }
        else if(y<=30){
            output = 1;
        }
        else{
            output = 0;
        }
        return(output);
    }
    public String torneo(){
        Squadra[] copia = new Squadra[this.squadre.length];
        for(int k=0; k<copia.length;k++ ){
            copia[k]=this.squadre[k];
        }
        int x = squadre.length;
        int o = 0;
        Squadra ricordaSquadra = squadre[0];
        for(int i=0; i<=(int)(Math.log(squadre.length) / Math.log(2)); i++){
            int[] ricorda = new int[x];
            Squadra[] vincenti = new Squadra[x/2];
            boolean s2 = false;
            int w = 0;
            for(int j = 0; j < x; j ++) ricorda[j] = -1;
            for(int j=0; j<x; j++){
                int c = (int)(Math.random()* 100);
                c %= x;
                for(int k=0; k<x; k++){
                    if(ricorda[k] ==  -1){
                        ricorda[k] = c;
                        if (s2){
                            int p = calcolopunt(copia[c], ricordaSquadra);
                            int g1 = calcolopunt( ricordaSquadra, copia[c]);
                            this.risultati[o]= g1+" "+p;
                            this.ordpartite[o]= ricordaSquadra.getName()+" - "+copia[c].getName();
                            if(g1>p){
                                vincenti[w] =  ricordaSquadra;
                            }
                            else if(g1<p){
                                vincenti[w] = copia[c];
                            }
                            else{
                                if(Math.random() < 0.5){
                                    vincenti[w] = ricordaSquadra;
                                    this.risultati[o] +=  " "+ricordaSquadra.getName();
                                }
                                else{
                                    vincenti[w] = copia[c];
                                    this.risultati[o] +=  " "+copia[c].getName();
                                }
                            }
                            w++;
                            s2 = false;
                            o++;
                        }
                        else{
                            ricordaSquadra = copia[c];
                            s2 = true;
                        }
                        break;
                    }
                    else if(c==ricorda[k]){
                        j--;
                        break;
                    }
                }
            }
            x /= 2;
            for(int j=0;j<x;j++){
                copia[j] = vincenti[j];
            }
        } 
        this.vincitore = copia[0].getName();
        return vincitore;
    }
}

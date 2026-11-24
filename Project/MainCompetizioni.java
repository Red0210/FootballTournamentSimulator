package Project;

import java.util.IllegalFormatException;
import java.util.Scanner;
import java.io.Console;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class MainCompetizioni{
	Scanner sc = new Scanner(System.in);
	Console console = System.console();

	public static void main(String[] args){
		new MainCompetizioni();
				
	}

	public MainCompetizioni(){
		System.out.println("Nel file in cui si creano le squadre da inserire:\n");
		System.out.println("insere una riga vuota tra le diverse squadre e inserire il nome della squadra e nelle righe sotto inserire i dati dei giocatori(11 per squadra) nel seguente ordine:\n");
		System.out.println("Ruolo(Att, Cen, Dif o Por) Nome Cognome Numero di maglia e due valutazioni, la prima da 1 a 10 e la seconda da 1 a 5.\n");
		





		int c = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/Hp/Desktop/Java/Project/squadre.txt"))){
			String line;
			while((line = reader.readLine()) != null){
				c++;
			}
			if((++c)%13==0){
				boolean a = false;
				for(int i=2;i<=8; i*=2){
					if((c/13)==i){
						a = true;
					}
				}
				if(!a){
					throw new ErrNumSq();
				}
			}
			else{
				throw new Incompleta();
			}
			reader.close();
		}
		catch(Incompleta e){
			System.out.println("Il file è stato creato male.");
		}
		catch(ErrNumSq e){
			System.out.println("Il numero di squadre non è compatibile per disputare un torneo.");
		}
		catch(IOException e){
			System.out.println("Il file non si apre.");
		}

		if((c)%13==0){
			Torneo torneo = new Torneo(c/13);
			Squadra[] array = new Squadra[c/13];
			String[]lettore = new String[c/13];
			for(int i=0; i<(c/13);i++){
				Squadra d = new Squadra("", i);
				array[i] = d;
				lettore[i] = "";
			}

			try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/Hp/Desktop/Java/Project/squadre.txt"))){
				String line;
				int natt=0;
				int ncen=0;
				int ndif=0;
				int i=0;
				boolean nome=true;
				boolean npor = false;
				int[] controllore = new int[11];
				for(int q=0;q<11;q++){
					controllore[q]=0;
				}
				while((line = reader.readLine()) != null){
					if(nome){
						for(int q=0; q<c/13; q++){
							if(line.equals(lettore[q])){
								throw new Errsquadreuguali();
							}
							else if(lettore[q].equals(null)){
								lettore[q]=line;
							}
						}
						array[i].setName(line);
						nome=false;
					}
					else{
						if(line.equals("")){
							i++;
							nome=true;
							natt=0;
							ncen=0;
							ndif=0;
							npor=false;
							for(int q=0;q<11;q++){
								controllore[q]=0;
							}
						}
						else{
							String[] gio= line.split(" ");
							if(gio[0].equals("Por")){
								if(!npor){
									if(Integer.parseInt(gio[5]) <0 || Integer.parseInt(gio[5]) >5){
										throw new NumberFormatException();
									}
									if(Integer.parseInt(gio[4]) <0 || Integer.parseInt(gio[4]) >10){
										throw new NumberFormatException();
									}
									if(Integer.parseInt(gio[3]) <0 || Integer.parseInt(gio[3]) >99){
										throw new NumberFormatException();
									}
									Por p = new Por(Integer.parseInt(gio[5]));
									p.setValutazione(Integer.parseInt(gio[4]));
									p.setValutazione();
									for(int t=0;t<11;t++){
										if(controllore[t]==0){
											break;
										}
										else if(controllore[t]==Integer.parseInt(gio[3])){
											throw new ErrNmaglia(Integer.parseInt(gio[3]),i);
										}
									}
									p.setNmaglia(gio[3]);
									controllore[natt+ncen+ndif] = p.getNmaglia();
									p.setCognome(gio[2]);
									p.setName(gio[1]);

									array[i].setPor(p);
									npor=true;
								}
								else{
									throw new ErrGio();
								}
							}
							else if(gio[0].equals("Dif")){
								if(ndif<4){
									if(Integer.parseInt(gio[5]) <0 || Integer.parseInt(gio[5]) >5){
										throw new NumberFormatException();
									}
									if(Integer.parseInt(gio[4]) <0 || Integer.parseInt(gio[4]) >10){
										throw new NumberFormatException();
									}
									if(Integer.parseInt(gio[3]) <0 || Integer.parseInt(gio[3]) >99){
										throw new NumberFormatException();
									}
									Dif d = new Dif(Integer.parseInt(gio[5]));
									d.setValutazione(Integer.parseInt(gio[4]));
									for(int t=0;t<11;t++){
										if(controllore[t]==0){
											break;
										}
										else if(controllore[t]==Integer.parseInt(gio[3])){
											throw new ErrNmaglia(Integer.parseInt(gio[3]),i);
										}
									}
									d.setNmaglia(Integer.parseInt(gio[3]));
									d.setCognome(gio[2]);
									d.setName(gio[1]);
									if(npor){
										controllore[natt+ncen+ndif+1] = d.getNmaglia();
									}
									else{
										controllore[natt+ncen+ndif] = d.getNmaglia();
									}
									array[i].setDif(d, ndif);
									ndif++;
								}
								else{
									throw new ErrGio();
								}
							}
							else if(gio[0].equals("Cen")){
								if(ncen<3){
									if(Integer.parseInt(gio[5]) <0 || Integer.parseInt(gio[5]) >5){
										throw new NumberFormatException();
									}
									if(Integer.parseInt(gio[4]) <0 || Integer.parseInt(gio[4]) >10){
										throw new NumberFormatException();
									}
									if(Integer.parseInt(gio[3]) <0 || Integer.parseInt(gio[3]) >99){
										throw new NumberFormatException();
									}
									Cen cn = new Cen(Integer.parseInt(gio[5]));
									cn.setValutazione(Integer.parseInt(gio[4]));
									for(int t=0;t<11;t++){
										if(controllore[t]==0){
											break;
										}
										else if(controllore[t]==Integer.parseInt(gio[3])){
											throw new ErrNmaglia(Integer.parseInt(gio[3]),i);
										}
									}
									cn.setNmaglia(Integer.parseInt(gio[3]));
									cn.setCognome(gio[2]);
									cn.setName(gio[1]);
									if(npor){
										controllore[natt+ncen+ndif+1] = cn.getNmaglia();
									}
									else{
										controllore[natt+ncen+ndif] = cn.getNmaglia();
									}
									array[i].setCen(cn, ncen);
									ncen++;
								}
								else{
									throw new ErrGio();
								}
							}
							else if(gio[0].equals("Att")){
								if(natt<3){
									if(Integer.parseInt(gio[5]) <0 || Integer.parseInt(gio[5]) >5){
										throw new NumberFormatException();
									}
									if(Integer.parseInt(gio[4]) <0 || Integer.parseInt(gio[4]) >10){
										throw new NumberFormatException();
									}
									if(Integer.parseInt(gio[3]) <0 || Integer.parseInt(gio[3]) >99){
										throw new NumberFormatException();
									}
									Att a = new Att(Integer.parseInt(gio[5]));
									a.setValutazione(Integer.parseInt(gio[4]));
									for(int t=0;t<11;t++){
										if(controllore[t]==0){
											break;
										}
										else if(controllore[t]==Integer.parseInt(gio[3])){
											throw new ErrNmaglia(Integer.parseInt(gio[3]),i);
										}
									}
									a.setNmaglia(Integer.parseInt(gio[3]));
									a.setCognome(gio[2]);
									a.setName(gio[1]);
									if(npor){
										controllore[natt+ncen+ndif+1] = a.getNmaglia();
									}
									else{
										controllore[natt+ncen+ndif] = a.getNmaglia();
									}
									array[i].setAtt(a, natt);
									natt++;
								}
								else{
									throw new ErrGio();
								}
							}
							else{
								throw new ErrRuolo();
							}
						}
					}
				}
				for(int j = 0; j < c/13; j ++){
					array[j].setValutazione();
				}
				torneo.setSquadre(array);
			}
			catch(IOException e){
				System.out.println("Il file non si apre.");
			}
			catch(ErrNmaglia e){
				System.out.println("Ci sono dei giocatori con lo stesso numero di maglia.");
			}
			catch(ErrGio e){
				System.out.println("Sono stati aggiunti troppi giocatori per ruolo.");
			}
			catch(Errsquadreuguali e){
				System.out.println("Delle squadre hanno lo stesso nome.");
			}
			catch(ErrRuolo e){
				System.out.println("Va definito un ruolo valido per il giocatore (Att, Cen, Dif, Por).");
			}
			catch(NumberFormatException e){
				System.out.println("Sono stati inseriti valori impossibili.");
			}



			




			char azione = ' ';
			while(azione!='U'){
				System.out.println("Qual è l'azione desiderata?");
				System.out.println("  [S] - Elencare le squadre");
				System.out.println("  [L] - Leggere squadre.txt");
				System.out.println("  [G] - Elencare i giocatori");
				System.out.println("  [M] - Modifica un giocatore");
				System.out.println("  [N] - Modifica il nome di una squadra");
				System.out.println("  [T] - Simulare il torneo");
				System.out.println("  [E] - Esci");
				azione = (char)sc.next().charAt(0);
				switch(azione){
					case 'T':
					simulare(torneo,c);
					break;
					case 'L':
					lettura(torneo);
					break;
					case 'M':
					modificare(torneo,c);
					break;
					case 'S':
					elencare(torneo);
					break;
					case 'N':
					modsquad(torneo,c);
					break;
					case 'G':
					elencogiocatori(torneo);
					break;
					case 'E':
					return;
					default:
					System.out.println("** azione sconosciuta?");
					azione = ' ';
				}
			}
		}
	}


	public void modsquad(Torneo torneo, int c){
		String[] salvatore = new String[c-1];
		System.out.println("Inserire la squadra  da modificare: ");
		String nome;
		int l=0;
		nome = console.readLine();
		System.out.println("Inserire il nuovo nome della squadra: ");
		String nuovo;
		nuovo = console.readLine();
		try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/Hp/Desktop/Java/Project/squadre.txt"))){
			String line;
			while((line = reader.readLine()) != null){
				if(l%13==0){
					if(line.equals(nuovo)){
						System.out.println("Non puoi cambiare il nome della squadra con un nome già esistente");
						return;
					}
				}
				salvatore[l]=line;
				l++;
			}
			reader.close();
		}
		catch(IOException e){
			System.out.println("Il file non si apre.");
		}
		try{
			BufferedWriter buffer = new BufferedWriter(new FileWriter("C:/Users/Hp/Desktop/Java/Project/squadre.txt"));
			for(int n=0; n<c-1; n++){
				if(nome.equals(salvatore[n])){
					buffer.write(nuovo+"\n");
				}
				else{
					buffer.write(salvatore[n]+"\n");
				}
			}
			buffer.close();
		}catch(Exception e){
		}
		lettura(torneo);

	}


	public void modificare(Torneo torneo, int c){
		String[] salvatore = new String[c-1];
		System.out.println("Inserire la squadra del giocatore da modificare: ");
		String nome;
		nome = console.readLine();
		System.out.println("Inserire il numero di maglia del giocatore da modificare: ");
		String numero;
		numero = console.readLine();
		String[] salvaNmaglia = new String[11];
		for(int u=0; u<11; u++){
			salvaNmaglia[u]="0";
		}
		int u = -1;
		try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/Hp/Desktop/Java/Project/squadre.txt"))){
			String line;
			int v=0;
			while((line = reader.readLine()) != null){
				if(u >= 0 && u < 11){
					String[] gio= line.split(" ");
					salvaNmaglia[u] = gio[3];
					u++;
				}
				if(line.equals(nome)){
					u = 0;
				}
				salvatore[v]=line;
				v++;
			}
			reader.close();
		}
		catch(IOException e){
			System.out.println("Il file non si apre.");
		}
		if(salvaNmaglia[0].equals("0")){
			System.out.println("La squadra inserita non esiste.");
			return;
		}
		for(int p=0; p<(c-1); p++){
			boolean osservatore = false;
			if(nome.equals(salvatore[p])){
				for(int y=0; y<11; y++){
					String[] gio= salvatore[p+y+1].split(" ");
					if(numero.equals(gio[3])){
						System.out.println("Inserire il nuovo nome del giocatore: ");
						gio[1]= sc.next();
						System.out.println("Inserire il nuovo cognome del giocatore: ");
						gio[2]= sc.next();
						do{
							osservatore = false;
							System.out.println("Inserire il nuovo numero di maglia del giocatore: ");
							gio[3]= sc.next();
							for(int s=0; s<11; s++){
								if(!numero.equals(salvaNmaglia[s])){
									if(gio[3].equals(salvaNmaglia[s])){
										osservatore = true;
										System.out.println("Il numero scelto è usato da un altro giocatore.");
									}
								}
							}
						}while(osservatore);
						do{
							osservatore = false;
							System.out.println("Inserire la valutazione generale (1-10) del giocatore: ");
							gio[4]= sc.next();
							if(Integer.parseInt(gio[4])<1 || Integer.parseInt(gio[4])>10){
								osservatore = true;
								System.out.println("Il numero inserito non è nel range previsto.");
							}
						}while(osservatore);
						do{
							osservatore = false;
							System.out.println("Inserire la valutazione specifica (1-5) del giocatore: ");
							gio[5]= sc.next();
							if(Integer.parseInt(gio[5])<1 || Integer.parseInt(gio[5])>5){
								osservatore = true;
								System.out.println("Il numero inserito non è nel range previsto.");
							}
						}while(osservatore);
						String gioc = gio[0]+" "+gio[1]+" "+gio[2]+" "+gio[3]+" "+gio[4]+" "+gio[5];
						salvatore[p+y+1]= gioc;
						osservatore = true;
						break;
					}
					if(y==10){
						System.out.println("Il giocatore cercato non esiste.");
						return;
					}
				}
				if(osservatore)	break;
			}
		}
		try{
			BufferedWriter buffer = new BufferedWriter(new FileWriter("C:/Users/Hp/Desktop/Java/Project/squadre.txt"));
			for(int n=0; n<c-1; n++){
				buffer.write(salvatore[n]+"\n");
			}
			buffer.close();
		}catch(Exception e){
		}
		lettura(torneo);
	}

	public void lettura(Torneo torneo){
		int c = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/Hp/Desktop/Java/Project/squadre.txt"))){
			String line;
			while((line = reader.readLine()) != null){
				c++;
			}
			if((++c)%13==0){
				boolean a = false;
				for(int i=2;i<=8; i*=2){
					if((c/13)==i){
						a = true;
					}
				}
				if(!a){
					throw new ErrNumSq();
				}
			}
			else{
				throw new Incompleta();
			}
			reader.close();
		}
		catch(Incompleta e){
			System.out.println("Il file è stato creato male.");
		}
		catch(ErrNumSq e){
			System.out.println("Il numero di squadre non è compatibile per disputare un torneo.");
		}
		catch(IOException e){
			System.out.println("Il file non si apre.");
		}

		if((c)%13==0){
			Squadra[] array = new Squadra[c/13];
			String[]lettore = new String[c/13];
			for(int i=0; i<(c/13);i++){
				Squadra d = new Squadra("", i);
				array[i] = d;
				lettore[i] = "";
			}

			try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/Hp/Desktop/Java/Project/squadre.txt"))){
				String line;
				int natt=0;
				int ncen=0;
				int ndif=0;
				int i=0;
				boolean nome=true;
				boolean npor = false;
				int[] controllore = new int[11];
				for(int q=0;q<11;q++){
					controllore[q]=0;
				}
				while((line = reader.readLine()) != null){
					if(nome){
						for(int q=0; q<c/13; q++){
							if(lettore[q].equals(line)){
								throw new Errsquadreuguali();
							}
							else if(lettore[q].equals(null)){
								lettore[q]=line;
							}
						}
						array[i].setName(line);
						nome=false;
					}
					else{
						if(line.equals("")){
							i++;
							nome=true;
							natt=0;
							ncen=0;
							ndif=0;
							npor=false;
							for(int q=0;q<11;q++){
								controllore[q]=0;
							}
						}
						else{
							String[] gio= line.split(" ");
							if(gio[0].equals("Por")){
								if(!npor){
									if(Integer.parseInt(gio[5]) <0 || Integer.parseInt(gio[5]) >5){
										throw new NumberFormatException();
									}
									if(Integer.parseInt(gio[4]) <0 || Integer.parseInt(gio[4]) >10){
										throw new NumberFormatException();
									}
									if(Integer.parseInt(gio[3]) <0 || Integer.parseInt(gio[3]) >99){
										throw new NumberFormatException();
									}
									Por p = new Por(Integer.parseInt(gio[5]));
									p.setValutazione(Integer.parseInt(gio[4]));
									p.setValutazione();
									for(int t=0;t<11;t++){
										if(controllore[t]==0){
											break;
										}
										else if(controllore[t]==Integer.parseInt(gio[3])){
											throw new ErrNmaglia(Integer.parseInt(gio[3]),i);
										}
									}
									p.setNmaglia(gio[3]);
									controllore[natt+ncen+ndif] = p.getNmaglia();
									p.setCognome(gio[2]);
									p.setName(gio[1]);

									array[i].setPor(p);
									npor=true;
								}
								else{
									throw new ErrGio();
								}
							}
							else if(gio[0].equals("Dif")){
								if(ndif<4){
									if(Integer.parseInt(gio[5]) <0 || Integer.parseInt(gio[5]) >5){
										throw new NumberFormatException();
									}
									if(Integer.parseInt(gio[4]) <0 || Integer.parseInt(gio[4]) >10){
										throw new NumberFormatException();
									}
									if(Integer.parseInt(gio[3]) <0 || Integer.parseInt(gio[3]) >99){
										throw new NumberFormatException();
									}
									Dif d = new Dif(Integer.parseInt(gio[5]));
									d.setValutazione(Integer.parseInt(gio[4]));
									for(int t=0;t<11;t++){
										if(controllore[t]==0){
											break;
										}
										else if(controllore[t]==Integer.parseInt(gio[3])){
											throw new ErrNmaglia(Integer.parseInt(gio[3]),i);
										}
									}
									d.setNmaglia(Integer.parseInt(gio[3]));
									d.setCognome(gio[2]);
									d.setName(gio[1]);
									if(npor){
										controllore[natt+ncen+ndif+1] = d.getNmaglia();
									}
									else{
										controllore[natt+ncen+ndif] = d.getNmaglia();
									}
									array[i].setDif(d, ndif);
									ndif++;
								}
								else{
									throw new ErrGio();
								}
							}
							else if(gio[0].equals("Cen")){
								if(ncen<3){
									if(Integer.parseInt(gio[5]) <0 || Integer.parseInt(gio[5]) >5){
										throw new NumberFormatException();
									}
									if(Integer.parseInt(gio[4]) <0 || Integer.parseInt(gio[4]) >10){
										throw new NumberFormatException();
									}
									if(Integer.parseInt(gio[3]) <0 || Integer.parseInt(gio[3]) >99){
										throw new NumberFormatException();
									}
									Cen cn = new Cen(Integer.parseInt(gio[5]));
									cn.setValutazione(Integer.parseInt(gio[4]));
									for(int t=0;t<11;t++){
										if(controllore[t]==0){
											break;
										}
										else if(controllore[t]==Integer.parseInt(gio[3])){
											throw new ErrNmaglia(Integer.parseInt(gio[3]),i);
										}
									}
									cn.setNmaglia(Integer.parseInt(gio[3]));
									cn.setCognome(gio[2]);
									cn.setName(gio[1]);
									if(npor){
										controllore[natt+ncen+ndif+1] = cn.getNmaglia();
									}
									else{
										controllore[natt+ncen+ndif] = cn.getNmaglia();
									}
									array[i].setCen(cn, ncen);
									ncen++;
								}
								else{
									throw new ErrGio();
								}
							}
							else if(gio[0].equals("Att")){
								if(natt<3){
									if(Integer.parseInt(gio[5]) <0 || Integer.parseInt(gio[5]) >5){
										throw new NumberFormatException();
									}
									if(Integer.parseInt(gio[4]) <0 || Integer.parseInt(gio[4]) >10){
										throw new NumberFormatException();
									}
									if(Integer.parseInt(gio[3]) <0 || Integer.parseInt(gio[3]) >99){
										throw new NumberFormatException();
									}
									Att a = new Att(Integer.parseInt(gio[5]));
									a.setValutazione(Integer.parseInt(gio[4]));
									for(int t=0;t<11;t++){
										if(controllore[t]==0){
											break;
										}
										else if(controllore[t]==Integer.parseInt(gio[3])){
											throw new ErrNmaglia(Integer.parseInt(gio[3]),i);
										}
									}
									a.setNmaglia(Integer.parseInt(gio[3]));
									a.setCognome(gio[2]);
									a.setName(gio[1]);
									if(npor){
										controllore[natt+ncen+ndif+1] = a.getNmaglia();
									}
									else{
										controllore[natt+ncen+ndif] = a.getNmaglia();
									}
									array[i].setAtt(a, natt);
									natt++;
								}
								else{
									throw new ErrGio();
								}
							}
							else{
								throw new ErrRuolo();
							}
						}
					}
				}
				for(int j = 0; j < c/13; j ++){
					array[j].setValutazione();
				}
				torneo.setSquadre(array);
			}
			catch(IOException e){
				System.out.println("Il file non si apre.");
			}
			catch(Errsquadreuguali e){
				System.out.println("Delle squadre hanno lo stesso nome.");
			}
			catch(ErrNmaglia e){
				System.out.println("Ci sono dei giocatori con lo stesso numero di maglia.");
			}
			catch(ErrGio e){
				System.out.println("Sono stati aggiunti troppi giocatori per ruolo.");
			}
			catch(ErrRuolo e){
				System.out.println("Va definito un ruolo valido per il giocatore (Att, Cen, Dif, Por).");
			}
			catch(NumberFormatException e){
				System.out.println("Sono stati inseriti valori impossibili.");
			}
		}
	}

	public void elencare(Torneo torneo){
		Squadra[] elenco = torneo.getSquadre();
		
		System.out.println("\nElenco delle squadre:");
		System.out.println("\n");
		for(int i = 0; i<elenco.length; i++ ){
			System.out.println(elenco[i].getName());
		}
		System.out.println("\n");
	}
	public void elencogiocatori(Torneo torneo){
		Squadra[] elenco = torneo.getSquadre();
		String nome;
		boolean controllo = true;
		System.out.println("\nDi quale squadra si vuole vedere i giocatori?");
		nome = console.readLine();
		System.out.println("\n");
		for(int i=0; i<elenco.length; i++){
			if(nome.equals(elenco[i].getName())){
				for(int j=0; j<11; j++){
					if(j<3){
						System.out.println("Att "+elenco[i].getAtt(j).getName()+" "+elenco[i].getAtt(j).getCognome()+" "+elenco[i].getAtt(j).getNmaglia()+" Valutazione globale:"+elenco[i].getAtt(j).getValutazione()+" Valutazione tiro:"+elenco[i].getAtt(j).getTiro());
					}
					else if(j<6){
						System.out.println("Cen "+elenco[i].getCen(j-3).getName()+" "+elenco[i].getCen(j-3).getCognome()+" "+elenco[i].getCen(j-3).getNmaglia()+" Valutazione globale:"+elenco[i].getCen(j-3).getValutazione()+" Valutazione passaggi:"+elenco[i].getCen(j-3).getPass());
					}
					else if(j<10){
						System.out.println("Dif "+elenco[i].getDif(j-6).getName()+" "+elenco[i].getDif(j-6).getCognome()+" "+elenco[i].getDif(j-6).getNmaglia()+" Valutazione globale:"+elenco[i].getDif(j-6).getValutazione()+" Valutazione difesa:"+elenco[i].getDif(j-6).getDef());
					}
					else{
						System.out.println("Por "+elenco[i].getPor().getName()+" "+elenco[i].getPor().getCognome()+" "+elenco[i].getPor().getNmaglia()+" Valutazione globale:"+elenco[i].getPor().getValutazione()+" Valutazione riflessi:"+elenco[i].getPor().getDef()+"\n");
					}
				}
				controllo = false;
			}
		}
		if(controllo == true){
			System.out.println("\nNon esiste una squadra con questo nome.");
		}
	}
	public void simulare(Torneo torneo, int c){
		String vincitore = torneo.torneo();
			System.out.println("\n-----------\nIl vincitore del torneo è: "+vincitore+"\nPer vedere i risultati completi consultare il file results.txt.\n-----------\n");
			try{
				BufferedWriter buffer = new BufferedWriter(new FileWriter("C:/Users/Hp/Desktop/Java/Project/results.txt"));
				String[] ordinePartite = torneo.getOrdpartite();
				String[] punteggio = torneo.getRisultati();
				for(int i = 0; i < (c/13)-1; i++){
					if(i==(c/13)-2)buffer.write("---\n> Finale\n");
					if(i==(c/13)-4)buffer.write("---\n> Semifinali\n");
					if(i==(c/13)-8)buffer.write("---\n> Quarti di finale\n");
					if(i==(c/13)-16)buffer.write("---\n> Ottavi di finale\n");
					String[] elenco = ordinePartite[i].split(" - ");
					String[] punt = punteggio[i].split(" ");
 					buffer.write(elenco[0]+" "+punt[0]+" - "+punt[1]+" "+elenco[1]);

					
					if(punt.length >=3){
						buffer.write(" /rig.(");
						for(int h = 0; h < punt.length - 2; h++){
							if(h==1)buffer.write(" ");
							buffer.write(punt[2 + h]);
						}
						buffer.write(") ");
					}


					
					buffer.newLine();

					
				}
				buffer.write("\n\nIl vincitore del torneo è: "+vincitore);
				buffer.close();
			}
			catch(Exception e){}
	}
}
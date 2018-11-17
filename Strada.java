import java.util.Random;

import javax.swing.plaf.synth.SynthSpinnerUI;
public class Strada {
	
	private EntitaStradale[][] strada;
	private int numCorsie=0; //righe
	private int numAttraversamenti=0; //colonne
	private int numPedoni=0;
	private int numAuto=0;
	private Random r=new Random();
	
	public Strada(int numCorsie, int numAttraversamenti, int numPedoni, int numAuto) {
		this.numCorsie=numCorsie;
		this.numAttraversamenti=numAttraversamenti;
		this.numPedoni=numPedoni;
		this.numAuto=numAuto;
		strada=new EntitaStradale[numCorsie][numAttraversamenti];
		aggiungiAuto(numAuto);
		aggiungiPedoni(numPedoni);
		aggiungiVuoto();
	}
	
	private void aggiungiAuto(int numAuto) {
		for(int i=0;i<numAuto;i++) {
			int riga=Math.abs(r.nextInt()%numCorsie);
			int colonna=Math.abs(r.nextInt()%numAttraversamenti);
			if(strada[riga][colonna] instanceof EntitaStradale )//c'è già qualcosa
				i--;
			else {
				strada[riga][colonna]=new Auto(riga,colonna);
			}
		}
	}
	
	private void aggiungiPedoni(int numPedoni) {
		for(int i=0;i<numPedoni;i++) {
			int riga=Math.abs(r.nextInt()%numCorsie);
			int colonna=Math.abs(r.nextInt()%numAttraversamenti);
			if(strada[riga][colonna] instanceof EntitaStradale) //c'è già un oggetto
				i--;
			else
				strada[riga][colonna]=new Pedone(riga,colonna); //aggiungi solo se c'è il null
		}
	}
	
	private void aggiungiVuoto() {
		for(int i=0;i<strada.length;i++) {
			for(int j=0;j<strada[i].length;j++) {
				if(!(strada[i][j] instanceof EntitaStradale))
					strada[i][j]=new Vuoto(i,j);
			}
		}
	}
	
	public void muoviPedoni() {
		for(int i=0;i<numCorsie;i++) {
			for(int j=0;j<numAttraversamenti;j++) {
				if(strada[i][j].getRiconoscitore()=='P') {//è un pedone?
					boolean ciglio=ciglioStrada(strada[i][j]);
					if(ciglio) {}//se è sul ciglio della strada diventa vuoto
					else if(!collisione(strada[i][j])) {
						EntitaStradale tmp=strada[i-1][j]; //per spostare il pedone scambio due
						strada[i-1][j]=strada[i][j];       //caselle
						strada[i][j]=tmp;
						strada[i-1][j].setRiga(i-1); //gli cambio la riga
					}
					else {
						System.out.println("Il pedone si è fermato per evitare una collisione");
					}
				}
			}
		}
	}
	
	public void muoviAuto() {
		for(int i=0;i<numCorsie;i++) {
			for(int j=numAttraversamenti-1;j>=0;j--) {
				if(strada[i][j].getRiconoscitore()=='A') {
					EntitaStradale auto=strada[i][j];//può succedere di spostare la stessa auto ma tutte saranno spostate
					for(int k=0;k<strada[i][j].getVelocita();k++) {
						boolean ciglio=ciglioStrada(auto);
						if(ciglio)
							break;
						else if(collisione(auto)) {
							System.out.println("L'auto si è fermata per evitare una collisione, cambia direzione");
							strada[i][j].setDirezione(); //cambia la direzione dell'auto così non resta "impantanata"
						}
						else{
							spostaAuto(auto);
			
						}
					}
				}
			}
		}
	}
	
	private void spostaAuto(EntitaStradale auto) {
		int riga=auto.getRiga();
		int colonna=auto.getColonna();
		switch(auto.getDirezione()) {
		case "up":{
			EntitaStradale tmp=strada[riga-1][colonna];
			strada[riga-1][colonna]=strada[riga][colonna];
			strada[riga][colonna]=tmp;
			auto.setRiga(riga-1);
			break;
		}
		case "down":{
			EntitaStradale tmp=strada[riga+1][colonna];
			strada[riga+1][colonna]=strada[riga][colonna];
			strada[riga][colonna]=tmp;
			auto.setRiga(riga+1);
			break;
		}
		case "straight":{
			EntitaStradale tmp=strada[riga][colonna+1];
			strada[riga][colonna+1]=strada[riga][colonna];
			strada[riga][colonna]=tmp;
			auto.setColonna(colonna+1);
			break;
		}
		}
	}
	
	private boolean collisione(EntitaStradale entita) {
		int riga=entita.getRiga();
		int colonna=entita.getColonna();
		switch(entita.getDirezione()) {
		case "up": {
				if(strada[riga-1][colonna].getRiconoscitore()!='V') {
					return true;
				}
			break;
		}
		case "down":{
			if(strada[riga+1][colonna].getRiconoscitore()!='V') {
				return true;
			}
			break;
		}
		case "straight":{
				if(strada[riga][colonna+1].getRiconoscitore()!='V') {
					return true;
				}
			break;
		}
		}
		return false;
	}
	
	private boolean ciglioStrada(EntitaStradale entita) {
		int riga=entita.getRiga();
		int colonna=entita.getColonna();
		switch(entita.getRiconoscitore()) {
		case 'P':{
			if(riga==0) {
				strada[riga][colonna]=new Vuoto(riga,colonna);
				return true;
			}
			break;
		}
		case 'A':{
			if(colonna==numAttraversamenti-1 && entita.getDirezione().equals("straight")) {
				strada[riga][colonna]=new Vuoto(riga,colonna);
				return true;
			}
			else if(entita.getDirezione().equals("up") && colonna==0) {
				strada[riga][colonna]=new Vuoto(riga,colonna);
				return true;
			}
			else if(entita.getDirezione().equals("down") && riga==numCorsie) {
				strada[riga][colonna]=new Vuoto(riga,colonna);
				return true;
			}
			break;
		}
		}
		return false;
	}
	
	public void visualizzaStrada() {
		for(int i=0;i<numCorsie;i++) {
			for(int j=0;j<numAttraversamenti;j++) {
				System.out.print(strada[i][j].getRiconoscitore()+"\t");
			}
			System.out.println();
		}
	}
	

}

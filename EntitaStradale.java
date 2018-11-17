import java.util.Random;

public class EntitaStradale {
	
	protected char riconoscitore=' ';
	protected int velocita=0;
	protected String direzione="";
	protected int riga=0;
	protected int colonna=0;
	private Random r=new Random();
	
	public EntitaStradale(int riga, int colonna) {
		this.riga=riga;
		this.colonna=colonna;
	}
	
	
	
	public int getRiga() {
		return riga;
	}



	public void setRiga(int riga) {
		this.riga = riga;
	}



	public int getColonna() {
		return colonna;
	}



	public void setColonna(int colonna) {
		this.colonna = colonna;
	}



	public char getRiconoscitore() {
		return riconoscitore;
	}

	public void setRiconoscitore(char riconoscitore) {
		this.riconoscitore = riconoscitore;
	}

	public int getVelocita() {
		return velocita;
	}

	public void setVelocita(int velocita) {
		this.velocita = velocita;
	}

	public String getDirezione() {
		return direzione;
	}

	public void setDirezione(String direzione) {
		this.direzione = direzione;
	}
	
	public void setDirezione() {
		int indice=Math.abs(r.nextInt()%Auto.direzioni.length);
		direzione=Auto.direzioni[indice];
	}
}

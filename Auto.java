import java.util.*;
public class Auto extends EntitaStradale {
	
	public static final int MASSIMA_VELOCITA=5;
	public static final String[] direzioni= new String[]{"up","down","straight"};
	private Random r=new Random();
	
	public Auto(int riga,int colonna) {
		super(riga,colonna);
		riconoscitore='A';
		velocita=Math.abs(r.nextInt()%MASSIMA_VELOCITA+1);
		direzione=direzioni[Math.abs(r.nextInt()%direzioni.length)]; //prende a caso una direzione
	}

}

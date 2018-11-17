
public class Pedone extends EntitaStradale {
	
	public static final int VELOCITA_PEDONE=1;
	
	public Pedone(int riga, int colonna) {
		super(riga,colonna);
		riconoscitore='P';
		velocita=VELOCITA_PEDONE;
		direzione="up";
	}

}

import it.unibs.fp.mylib.InputDati;

public class SimulaTraffico {
	
	public static Strada creaStrada() {
		int numCorsie=InputDati.leggiIntero("Inserisci quante corsie di marcia ci sono: ");
		int numAttraversamenti=InputDati.leggiIntero("Inserisci il numero di attraversamenti pedonali: ");
		int numPedoni=InputDati.leggiIntero("Quanti pedoni devono attraversare la strada? ");
		int numAuto=InputDati.leggiIntero("Quante auto ci sono sulla strada? ");
		return new Strada(numCorsie,numAttraversamenti,numPedoni,numAuto);
	}
	
	public static void main(String[] args) {
		String risposta="si";
		Strada strada=creaStrada();
		while(risposta.equals("si")) {
			strada.visualizzaStrada();
			strada.muoviPedoni();
			strada.muoviAuto();
			strada.visualizzaStrada();
			risposta=InputDati.leggiStringaNonVuota("Desideri continuare a far scorrere il traffico? ");
		}
	}

}

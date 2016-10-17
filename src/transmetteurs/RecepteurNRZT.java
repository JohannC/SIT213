package transmetteurs;

import destinations.DestinationInterface;
import excpetion.ArgumentsException;
import information.Information;
import information.InformationNonConforme;

public class RecepteurNRZT extends Transcepteur<Float, Boolean> {

	private final float seuilDeDetection = (max - min) / 2;
	
	/**
	 * Constructeur par défault sans paramètres 
	 * 
	 */
	public RecepteurNRZT() {
		super();
	}	
	
	/**
	 * Constructeur par défault avec deux paramètres (min et max) 
	 * 
	 * @param min : l'amplitude minimale du signal 
	 * @param max : l'amplitude maximale du signal 
	 * @throws InformationNonConforme : Exception levée dans le cas où on reçoit une information erronée
	 * @throws ArgumentsException 
	 * 
	 */
	public RecepteurNRZT(float min, float max) throws ArgumentsException {
		super(min, max);
	}
	
	/**
	 * Constructeur par défault avec un seul paramètre (nbEchantillon) 
	 * 
	 * @param nbEchantillon : Nombre d'échantillons 
	 * @throws InformationNonConforme : Exception levée dans le cas où on reçoit une information erronée
	 * @throws ArgumentsException 
	 * 
	 */
	public RecepteurNRZT(int nbEchantillon) throws ArgumentsException {
		super(nbEchantillon);
	}
	
	
	/**
	 * 
	 * Constructeur par défault avec trois paramètres (min, max, nbEchantillon)
	 * 
	 * @param min : l'amplitude minimale du signal 
	 * @param max : l'amplitude maximale du signal 
	 * @param nbEchantillon : Nombre d'échantillons 
	 * @throws InformationNonConforme : Exception levée dans le cas où on reçoit une information erronée
	 * @throws ArgumentsException 
	 * 
	 */
	public RecepteurNRZT(float min, float max, int nbEchantillon) throws ArgumentsException {
		super(min, max, nbEchantillon);
	}
	
	
	@Override
	public void recevoir(Information<Float> information) throws InformationNonConforme {
		informationRecue = information;
		emettre();
	}

	@Override
	public void emettre() throws InformationNonConforme {
		Information<Boolean> signalNRZT = this.decodageNRZT();
		for (DestinationInterface<Boolean> destinationConnectee : destinationsConnectees) {
			destinationConnectee.recevoir(signalNRZT);
		}
		this.informationEmise = signalNRZT;
	}

	private Information<Boolean> decodageNRZT() {
		Information<Boolean> signalNRZT = new Information<Boolean>();
		
		for(int i = 0; i < informationRecue.nbElements(); i += nbEchantillon) {
			float moyenne = 0.0f;
			float somme = 0.0f;
			int t1 = nbEchantillon / 3;
			int t3 = t1;
			int t2 = nbEchantillon - t1 - t3;

			for (int j = 0; j < t2; j++) {
				somme += informationRecue.iemeElement(i + t1 + j);
			}
			
			moyenne = somme / t2;
			signalNRZT.add(moyenne > seuilDeDetection);
		}
		return signalNRZT;
	}

}

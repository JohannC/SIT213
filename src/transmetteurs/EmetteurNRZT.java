package transmetteurs;

import destinations.DestinationInterface;
import excpetion.ArgumentsException;
import information.Information;
import information.InformationNonConforme;

/**
 * La classe EmetteurNRZT permet d'envoyer un signal num�rique NRZT
 * (Return-to-Zero trapezo�dale)
 */

public class EmetteurNRZT extends Transcepteur<Boolean, Float> {
	/**
	 * Constructeur par défault sans paramètres 
	 * 
	 */
	public EmetteurNRZT() {
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
	public EmetteurNRZT(float min, float max) throws ArgumentsException {
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
	public EmetteurNRZT(int nbEchantillon) throws ArgumentsException {
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
	public EmetteurNRZT(float min, float max, int nbEchantillon) throws ArgumentsException {
		super(min, max, nbEchantillon);
	}
	
	/**
	 * la variable informationRecue prend la valeur de l'argument de la methode.
	 * @param information message envoyé  au transmitteur
	 */
	@Override
	public void recevoir(Information<Boolean> information) throws InformationNonConforme {
		informationRecue = information;	
		emettre();
	}

	/**
	 * Méthode permettant d'�mettre une information <float> : le signal NRZT
	 */
	@Override
	public void emettre() throws InformationNonConforme {
		Information<Float> signalNRZT = this.encodageNRZT();
		for (DestinationInterface<Float> destinationConnectee : destinationsConnectees) {
			destinationConnectee.recevoir(signalNRZT);
		}
		this.informationEmise = signalNRZT;
	}

	/**
	 * Encode le signal NRZT
	 * 
	 * @return le signal NRZT
	 */
	private Information<Float> encodageNRZT() {
		Information<Float> signalNRZT = new Information<Float>();
		for (int i = 0; i < informationRecue.nbElements(); i++) {
			Boolean precedentBit;
			Boolean nextBit;
			if (i == 0) {
				precedentBit = false;
			} else {
				precedentBit = informationRecue.iemeElement(i - 1);
			}
			if (i == (informationRecue.nbElements()-1)) {
				nextBit = false;
			} else {
				nextBit = informationRecue.iemeElement(i + 1);
			}
			
			encodageBit(signalNRZT, informationRecue.iemeElement(i), precedentBit, nextBit);
		}
		return signalNRZT;
	}

	/**
	 * Encode chaque bit du signal NRZT, chaque bit est constitué de
	 * nbEchantillons
	 * 
	 * @param signalNRZT
	 *            le signal NRZT
	 * @param bit
	 *            Un bit du signal NRZT
	 */
	private void encodageBit(Information<Float> signalNRZT, Boolean bit, Boolean precedentBit, Boolean nextBit) {
		int t1 = nbEchantillon / 3;
		int t3 = t1;
		int t2 = nbEchantillon - t1 - t3;
		float deltaAmplitude = max - min;
		if (bit == true) {// bit � 1
			if (precedentBit == false) {//Evite d'avoir 
				for (int i = 0; i < t1; i++) {
					signalNRZT.add(this.min + (float)i / t1 * deltaAmplitude);
				}
			} else {
				for (int i = 0; i < t1; i++) {
					signalNRZT.add(this.max);
				}
			}
			for (int i = 0; i < t2; i++) {
				signalNRZT.add(this.max);
			}

			if(nextBit == true){
				for (int i = 1; i <= t3; i++) {
					signalNRZT.add(this.max);
				}
			} else {
				for (int i = 1; i <= t3; i++) {
					signalNRZT.add(this.max - (float)i / t3 * deltaAmplitude);
				}
			}
			

		} else {
			for (int i = 1; i <= nbEchantillon; i++) {
				signalNRZT.add(this.min);
			}
		}

	}

}

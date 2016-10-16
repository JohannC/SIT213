package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

/**
 * La classe EmetteurNRZT permet d'envoyer un signal numérique NRZT
 * (Return-to-Zero trapezoïdale)
 */

public class EmetteurNRZT extends Transcepteur<Boolean, Float> {

	/**
	 * Méthode permettant de reçevoir une information
	 * 
	 * @param information
	 *            l'information reçue
	 */
	@Override
	public void recevoir(Information<Boolean> information) throws InformationNonConforme {
		informationRecue = information;
		emettre();
	}

	/**
	 * Méthode permettant d'émettre une information <float> : le signal NRZT
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
			if (i == 0) {
				precedentBit = false;
			} else {
				precedentBit = informationRecue.iemeElement(i - 1);
			}
			encodageBitRZ(signalNRZT, informationRecue.iemeElement(i), precedentBit);
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
	private void encodageBitRZ(Information<Float> signalNRZT, Boolean bit, Boolean precedentBit) {
		int t1 = nbEchantillon / 3;
		int t3 = t1;
		int t2 = nbEchantillon - t1 - t3;
		float deltaAmplitude = max - min;
		if (bit == true) {// bit à 1
			if (precedentBit == false) {//Evite d'avoir 
				for (int i = 1; i <= t1; i++) {
					signalNRZT.add(this.min + i / t1 * deltaAmplitude);
				}
			} else {
				for (int i = 1; i <= t1; i++) {
					signalNRZT.add(this.max);
				}
			}
			for (int i = 1; i <= t2; i++) {
				signalNRZT.add(this.max);
			}

			for (int i = 1; i <= t3; i++) {
				signalNRZT.add(this.max - i / t3 * deltaAmplitude);
			}

		} else {
			for (int i = 1; i <= nbEchantillon; i++) {
				signalNRZT.add(this.min);
			}
		}

	}

}

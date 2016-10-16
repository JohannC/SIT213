package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

/**
 * La classe EmetteurRZ permet d'envoyer un signal numérique RZ (Return-to-Zero)
 */

public class EmetteurRZ extends Transcepteur<Boolean, Float> {
	/**
	 * Méthode permettant de reçevoir une information
	 * @param information l'information reçue
	 */
	@Override
	public void recevoir(Information<Boolean> information) throws InformationNonConforme {
		informationRecue = information;
		emettre();
	}
	/**
	 * Méthode permettant d'émettre une information <float> : le signal RZ
	 */
	@Override
	public void emettre() throws InformationNonConforme {
		Information<Float> signalRZ = this.encodageRZ();
		for (DestinationInterface<Float> destinationConnectee : destinationsConnectees) {
			destinationConnectee.recevoir(signalRZ);
		}
		this.informationEmise = signalRZ;
	}

	/**
	 * Encode le signal RZ
	 * @return le signal RZ
	 */
	private Information<Float> encodageRZ() {
		Information<Float> signalRZ = new Information<Float>();
		for (int i = 0; i < informationRecue.nbElements(); i++) {
			encodageBitRZ(signalRZ, informationRecue.iemeElement(i));
		}
		return signalRZ;
	}
	/**
	 * Encode chaque bit du signal RZ, chaque bit est constitué de nbEchantillons
	 * @param signalRZ le signal RZ
	 * @param bit Un bit du signal RZ
	 */
	private void encodageBitRZ(Information<Float> signalRZ, Boolean bit) {
		int t1 = nbEchantillon / 3;
		int t3 = t1;
		int t2 = nbEchantillon - t1 - t3;

		for (int i = 0; i < t1; i++) {
			signalRZ.add(this.min);
		}
		for (int i = 0; i < t2; i++) {
			signalRZ.add(bit ? this.max : this.min);
		}

		for (int i = 0; i < t3; i++) {
			signalRZ.add(this.min);
		}
	}

}

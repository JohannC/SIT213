package transmetteurs;

/**
 * Classe simulant un émetteur NRZ
 */

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

/**
 * 
 * @author M.LEMAUVIEL
 * @author LK.CHAO
 * @author L.AMHOUCHE
 * @author A.KANE
 * @author J.CORCUFF
 * 
 */

public class EmetteurNRZ extends Transcepteur<Boolean, Float> {

	/**
	 * Constructeur par défault sans paramètres 
	 * 
	 */
	
	public EmetteurNRZ() {
		super();
	}	
	
	/**
	 * Constructeur par défault avec deux paramètres (min et max) 
	 * 
	 * @param min : l'amplitude minimale du signal 
	 * @param max : l'amplitude maximale du signal 
	 * @throws InformationNonConforme : Exception levée dans le cas où on reçoit une information erronée
	 * 
	 */
	
	public EmetteurNRZ(float min, float max) throws InformationNonConforme {
		super(min, max);
	}
	
	/**
	 * Constructeur par défault avec un seul paramètre (nbEchantillon) 
	 * 
	 * @param nbEchantillon : Nombre d'échantillons 
	 * @throws InformationNonConforme : Exception levée dans le cas où on reçoit une information erronée
	 * 
	 */
	
	public EmetteurNRZ(int nbEchantillon) throws InformationNonConforme {
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
	 * 
	 */
	
	public EmetteurNRZ(float min, float max, int nbEchantillon) throws InformationNonConforme {
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
	 * Codage NRZ de l'information <Boolean> à une information <Float> (les echantillonsdu signal emis).
	 * Envoie informationRecue aux membres de la liste destinationConnectee
	 * la variable informationEmise prend la valeur de informationRecue
	 * 
	 */
	
	@Override
	public void emettre() throws InformationNonConforme {
		
		Information<Float> informationNRZ = new Information<Float>(); 
				
		for (int i = 0; i < informationRecue.nbElements(); i++) {
			if (informationRecue.iemeElement(i)) {
				for (int j = 0; j < nbEchantillon; j++)
					informationNRZ.add(max);
			} else {
				for (int j = 0; j < nbEchantillon; j++)
					informationNRZ.add(min);
			}
		}

		informationEmise = informationNRZ;
		
		for (DestinationInterface <Float> destinationConnectee : destinationsConnectees) {
			destinationConnectee.recevoir(informationNRZ);
		}
		this.informationEmise = informationNRZ;
		
	}

}

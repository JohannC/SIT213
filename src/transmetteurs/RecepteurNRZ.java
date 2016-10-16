package transmetteurs;

/**
 * Classe simulant un recepteur NRZ
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
public class RecepteurNRZ extends Transcepteur<Float, Boolean> {

	/**
	 * seuilDeDetection est le seuil qui nous permettra de decider si l'echantillon represente un max ou un min 
	 */
	private final float seuilDeDetection = (max - min)/2; 
	
	/**
	 * la variable informationRecue prend la valeur de l'argument de la methode.
	 * @param information message envoyé  au transmitteur
	 */
	
	@Override
	public void recevoir(Information<Float> information) throws InformationNonConforme {
		informationRecue = information;	
		emettre();
	}
	
	/**
	 * Decodage NRZ de l'information <Float> (les echantillonsdu signal emis) à une information <Boolean>
	 * Envoie informationRecue aux membres de la liste destinationConnectee
	 * la variable informationEmise prend la valeur de informationRecue
	 * 
	 */

	@Override
	public void emettre() throws InformationNonConforme {
		
		Information<Boolean> informationNRZ = new Information<Boolean>(); 
		
		for (int i = 0; i < (informationRecue.nbElements()/nbEchantillon); i++) {
			if (informationRecue.iemeElement(i*nbEchantillon) > seuilDeDetection) {
					informationNRZ.add(true);
			} else {
					informationNRZ.add(false);
			}
		}

		informationEmise = informationNRZ;
		
		for (DestinationInterface <Boolean> destinationConnectee : destinationsConnectees) {
			destinationConnectee.recevoir(informationNRZ);
		}
		this.informationEmise = informationNRZ;
		
	}

}

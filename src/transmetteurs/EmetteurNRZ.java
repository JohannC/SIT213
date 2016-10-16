package transmetteurs;

/**
 * Classe simulant un émetteur NRZ
 */

import destinations.DestinationInterface;
import excpetion.ArgumentsException;
import information.Information;
import information.InformationNonConforme;

public class EmetteurNRZ extends Transcepteur<Boolean, Float> {
	

	@Override
	public void recevoir(Information<Boolean> information) throws InformationNonConforme {
		informationRecue = information;	
		emettre();
	}

	@Override
	public void emettre() throws InformationNonConforme {
		
		Information<Float> informationNRZ = new Information<Float>(); 
		
		// Float[] tabFloat = new Float[informationRecue.nbElements() * nbEch];
		
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

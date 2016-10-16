package transmetteurs;

/**
 * Classe simulant un émetteur NRZ
 */

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

public class EmetteurNRZ extends Transcepteur<Boolean, Float> {
	
	public EmetteurNRZ() {
		super();
	}	
	
	public EmetteurNRZ(float min, float max) throws InformationNonConforme {
		super(min, max);
	}
	
	public EmetteurNRZ(int nbEchantillon) throws InformationNonConforme {
		super(nbEchantillon);
	}
	
	public EmetteurNRZ(float min, float max, int nbEchantillon) throws InformationNonConforme {
		super(min, max, nbEchantillon);
	}

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

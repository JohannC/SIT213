package transducteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;
import transmetteurs.Transmetteur;

public class TransducteurEmission extends Transmetteur<Boolean, Boolean> {

	public TransducteurEmission(){
		super();
	}
	
	@Override
	public void recevoir(Information<Boolean> information) throws InformationNonConforme {
		informationRecue = information;
		codageDeSource();
		emettre();
	}

	@Override
	public void emettre() throws InformationNonConforme {
		for (DestinationInterface<Boolean> destinationConnectee : destinationsConnectees) {
			destinationConnectee.recevoir(informationEmise);
		}
	}
	
	private void codageDeSource () {
		
		Information<Boolean> info = new Information<Boolean>();
		
		for (Boolean bit : informationRecue) {
			if (bit) {
				info.add(true);
				info.add(false);
				info.add(true);
			}
			else {
				info.add(false);
				info.add(true);
				info.add(false);
			}
		}
		
		informationEmise = info;
	}

}

package transducteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;
import transmetteurs.Transmetteur;

public class TransducteurReception extends Transmetteur<Boolean, Boolean> {

	@Override
	public void recevoir(Information<Boolean> information) throws InformationNonConforme {
		informationRecue = information;
		codageDeCanal();
		emettre();		
	}

	@Override
	public void emettre() throws InformationNonConforme {
		for (DestinationInterface<Boolean> destinationConnectee : destinationsConnectees) {
			destinationConnectee.recevoir(informationEmise);
		}
	}

	private void codageDeCanal() {
		Information<Boolean> info = new Information<Boolean>();
		for(int i=0 ; i <= informationRecue.nbElements(); i+=3) {
			if(informationRecue.iemeElement(i) == false && informationRecue.iemeElement(i+1)== false && informationRecue.iemeElement(i+1)== false ) {
				info.add(false);
			}
			if(informationRecue.iemeElement(i) == false && informationRecue.iemeElement(i+1)== false && informationRecue.iemeElement(i+1)== true ) {
				info.add(true);
			}
			if(informationRecue.iemeElement(i) == false && informationRecue.iemeElement(i+1)== true && informationRecue.iemeElement(i+1)== false ) {
				info.add(false);
			}
			if(informationRecue.iemeElement(i) == false && informationRecue.iemeElement(i+1)== true && informationRecue.iemeElement(i+1)== true ) {
				info.add(false);
			}
			if(informationRecue.iemeElement(i) == true && informationRecue.iemeElement(i+1)== false && informationRecue.iemeElement(i+1)== false ) {
				info.add(true);
			}
			if(informationRecue.iemeElement(i) == true && informationRecue.iemeElement(i+1)== false && informationRecue.iemeElement(i+1)== true ) {
				info.add(true);
			}
			if(informationRecue.iemeElement(i) == true && informationRecue.iemeElement(i+1)== true && informationRecue.iemeElement(i+1)== false ) {
				info.add(false);
			}
			if(informationRecue.iemeElement(i) == true && informationRecue.iemeElement(i+1)== true && informationRecue.iemeElement(i+1)== true ) {
				info.add(true);
			}
		}
		informationEmise = info;
	}
}

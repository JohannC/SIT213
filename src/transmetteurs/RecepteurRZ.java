package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

public class RecepteurRZ extends Transcepteur<Float, Boolean> {
	
	private final float seuilDeDetection = (max - min)/2;

	@Override
	public void recevoir(Information<Float> information) throws InformationNonConforme {
		informationRecue = information;
		emettre();
	}

	@Override
	public void emettre() throws InformationNonConforme {
		Information<Boolean> signalRZ = this.decodageRZ();
		for (DestinationInterface<Boolean> destinationConnectee : destinationsConnectees) {
			destinationConnectee.recevoir(signalRZ);
		}
		this.informationEmise = signalRZ;
	}
	
	private Information<Boolean> decodageRZ() {
		Information<Boolean> signalRZ = new Information<Boolean>();
		int i = 0;
		while (i < informationRecue.nbElements()) {
			float moyenne = 0;
			int debut = nbEchantillon/3;
			int fin = debut * 2;
		}
		return signalRZ;
	}
}

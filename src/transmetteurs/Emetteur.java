package transmetteurs;

import coders.Encoder;
import destinations.DestinationInterface;
import excpetion.ArgumentsException;
import information.Information;
import information.InformationNonConforme;

public class Emetteur extends Transcepteur<Boolean, Float>{
	
	private Encoder encoder; 
	
	public Emetteur(Encoder encoder) {
		super();
		this.encoder = encoder;
	}

	public Emetteur(Encoder encoder,int nbEchantillon) throws ArgumentsException {
		super(nbEchantillon);
		this.encoder = encoder;
	}

	public Emetteur(Encoder encoder, float min, float max) throws ArgumentsException {
		super(min, max);
		this.encoder = encoder;
	}

	public Emetteur(Encoder encoder, float min, float max, int nbEchantillon) throws ArgumentsException {
		super(min, max, nbEchantillon);
		this.encoder = encoder;
	}
	
	@Override
	public void recevoir(Information<Boolean> information) throws InformationNonConforme {
		informationRecue = information;	
		emettre();
	}
	
	@Override
	public void emettre() throws InformationNonConforme {
		Information<Float> signal = encoder.encode(informationRecue);
		for (DestinationInterface<Float> destinationConnectee : destinationsConnectees) {
			destinationConnectee.recevoir(signal);
		}
		this.informationEmise = signal;
	}
}

package transmetteurs;

import coders.Decoder;
import destinations.DestinationInterface;
import excpetion.ArgumentsException;
import information.Information;
import information.InformationNonConforme;

public abstract class Recepteur extends Transcepteur<Float, Boolean>{
	
	private Decoder decoder; 
	
	public Recepteur(Decoder decoder) {
		super();
		this.decoder = decoder;
	}

	public Recepteur(Decoder decoder,int nbEchantillon) throws ArgumentsException {
		super(nbEchantillon);
		this.decoder = decoder;
	}

	public Recepteur(Decoder decoder, float min, float max) throws ArgumentsException {
		super(min, max);
		this.decoder = decoder;
	}

	public Recepteur(Decoder decoder, float min, float max, int nbEchantillon) throws ArgumentsException {
		super(min, max, nbEchantillon);
		this.decoder = decoder;
	}
	
	@Override
	public void recevoir(Information<Float> information) throws InformationNonConforme {
		informationRecue = information;	
		emettre();
	}
	
	@Override
	public void emettre() throws InformationNonConforme {
		Information<Boolean> signal = decoder.decode(informationRecue);
		for (DestinationInterface<Boolean> destinationConnectee : destinationsConnectees) {
			destinationConnectee.recevoir(signal);
		}
		this.informationEmise = signal;
	}

}

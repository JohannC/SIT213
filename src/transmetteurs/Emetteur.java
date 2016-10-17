package transmetteurs;

import coders.Encoder;
import coders.EncoderNRZ;
import coders.EncoderNRZT;
import coders.EncoderRZ;
import destinations.DestinationInterface;
import excpetion.ArgumentsException;
import information.Information;
import information.InformationNonConforme;

public class Emetteur extends Transcepteur<Boolean, Float>{
	
	private Encoder encoder; 


	public Emetteur(String forme, float min, float max, int nbEchantillon) throws ArgumentsException {
		super(min, max, nbEchantillon);
		switch (forme) {
		case "NRZ":
			encoder = new EncoderNRZ(this.min, this.max, this.nbEchantillon);
			break;
		case "NRZT":
			encoder = new EncoderNRZT(this.min, this.max, this.nbEchantillon);
			break;
		case "RZ":
			encoder = new EncoderRZ(this.min, this.max, this.nbEchantillon);
			break;	
		default:
			break;
		}
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

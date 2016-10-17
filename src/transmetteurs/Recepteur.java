package transmetteurs;

import coders.Decoder;
import coders.DecoderNRZ;
import coders.DecoderNRZT;
import coders.DecoderRZ;
import destinations.DestinationInterface;
import excpetion.ArgumentsException;
import information.Information;
import information.InformationNonConforme;

/**
 * Classe Recepteur : Reçois un signal encodé sous forme NRZ ou RZ ou NZRT
 *
 */
public class Recepteur extends Transcepteur<Float, Boolean>{
	
	private Decoder decoder; 
	
	/**
	 * Initialise le Recepteur et un Decodeur NRZ ou RZ ou NRZT en fonction de la forme souhaitée 
	 * @param forme la forme du signal : NRZ ou RZ ou NZRT
	 * @param min amplitude minimale du signal
	 * @param max amplitude maximale du signal
	 * @param nbEchantillon le nombre d'échantillons par bit
	 * @throws ArgumentsException
	 */
	public Recepteur(String forme, float min, float max, int nbEchantillon) throws ArgumentsException {
		super(min, max, nbEchantillon);
		switch (forme) {
		case "NRZ":
			decoder = new DecoderNRZ(this.min, this.max, this.nbEchantillon);
			break;
		case "NRZT":
			decoder = new DecoderNRZT(this.min, this.max, this.nbEchantillon);
			break;
		case "RZ":
			decoder = new DecoderRZ(this.min, this.max, this.nbEchantillon);
			break;	
		default:
			break;
		}
	}
	/**
	 * Méthode pour reçevoir une information (un signal)
	 */
	@Override
	public void recevoir(Information<Float> information) throws InformationNonConforme {
		informationRecue = information;	
		emettre();
	}
	
	/**
	 * Méthode pour emettre une information (un signal)
	 */
	@Override
	public void emettre() throws InformationNonConforme {
		Information<Boolean> signal = decoder.decode(informationRecue);
		for (DestinationInterface<Boolean> destinationConnectee : destinationsConnectees) {
			destinationConnectee.recevoir(signal);
		}
		this.informationEmise = signal;
	}

}

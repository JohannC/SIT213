package transmetteurs;

import coders.Encoder;
import coders.EncoderNRZ;
import coders.EncoderNRZT;
import coders.EncoderRZ;
import destinations.DestinationInterface;
import excpetion.ArgumentsException;
import information.Information;
import information.InformationNonConforme;
/**
 * Classe Emetteur : Emet un signal encodé sous forme NRZ ou RZ ou NZRT
 *
 */
public class Emetteur extends Transcepteur<Boolean, Float>{
	
	private Encoder encoder; 

	/**
	 * Initialise l'Emetteur et un encodeur NRZ ou RZ ou NRZT en fonction de la forme souhaitée 
	 * @param forme la forme du signal : NRZ ou RZ ou NZRT
	 * @param min amplitude minimale du signal
	 * @param max amplitude maximale du signal
	 * @param nbEchantillon le nombre d'échantillons par bit
	 * @throws ArgumentsException
	 */
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
	/**
	 * Méthode pour reçevoir une information (un signal)
	 */
	@Override
	public void recevoir(Information<Boolean> information) throws InformationNonConforme {
		informationRecue = information;	
		emettre();
	}
	
	/**
	 * Méthode pour emettre une information (un signal)
	 */
	@Override
	public void emettre() throws InformationNonConforme {
		Information<Float> signal = encoder.encode(informationRecue);
		for (DestinationInterface<Float> destinationConnectee : destinationsConnectees) {
			destinationConnectee.recevoir(signal);
		}
		this.informationEmise = signal;
	}
}

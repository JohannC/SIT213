package transmetteurs;

import canaux.CanalTrajetsMultiples;
import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

/**
 * Classe du Transmetteur analogique bruite
 *
 */
public class TransmetteurAnalogiqueBruite extends Transmetteur<Float, Float> {

	private float snrDB;
	private int seed = 0;
	
	/** 
	 * Constructeur de la classe
	 * @param snrDB snr en DB demande par l'utilisateur
	 */
	public TransmetteurAnalogiqueBruite(float snrDB){
		this.snrDB = snrDB; 
	}
	
	/**
	 * Constructeur de la classe si une graine a ete specifie par l'utilisateur
	 * @param snrDB snr en DB demande par l'utilisateur
	 * @param seed graine specifie par l'utilisateur
	 */
	public TransmetteurAnalogiqueBruite(float snrDB, int seed){
		this.snrDB = snrDB; 
		this.seed = seed;
	}
	
	/**
	 * M�thode pour recevoir l'information
	 */
	@Override
	public void recevoir(Information<Float> information) throws InformationNonConforme {
		// TODO Auto-generated method stub
		informationRecue = information;
		emettre();
	}

	/**
	 * Methode pour emettre le signal
	 * S'occupe d'appeler les methodes permettant de g�n�rer un bruit
	 * et de l'additionner au signal en entree
	 */
	@Override
	public void emettre() throws InformationNonConforme {
		// TODO Auto-generated method stub
		
		float puissanceBruit = calculPuissanceBruit(informationRecue, snrDB);
		BruitBlancGaussien generateurBruit; 
		
		if (seed == 0)
			generateurBruit = new BruitBlancGaussien(puissanceBruit);
		else 
			generateurBruit = new BruitBlancGaussien(puissanceBruit, seed);
		
		Information<Float> bruit = generateurBruit.generateurBruitBG(informationRecue.nbElements());
		
		////////////////////////////////////////////////////////////////////////////////:
//		Information <Float> infoRetardee = new Information<Float>();
//		Information <Float> infoRetardeeEtBruitee = new Information<Float>();
//		
//		CanalTrajetsMultiples canal = new CanalTrajetsMultiples(informationRecue, retard, attenuation);
//		
//		infoRetardee = canal.monInfoRetardee();
//		
//		infoRetardeeEtBruitee = signalBruite(informationRecue, infoRetardee);
//		
//		informationEmise = signalBruite(infoRetardeeEtBruitee, bruit);
		/////////////////////////////////////////////////////////////////////////////////:
		
		informationEmise = signalBruite(informationRecue, bruit);
		
		for (DestinationInterface<Float> destinationConnectee : destinationsConnectees) {
			destinationConnectee.recevoir(informationEmise);
		}
	}
	
	/**
	 * M�thode pour calculer la puissance de bruit necessaire
	 * a partir du SNR demande par l'utilisateur
	 * @param informationRecue
	 * @param snrDB 
	 * @return puissance bruit
	 */
	private float calculPuissanceBruit(Information<Float> informationRecue, float snrDB){
		float pBruit;
		float sommeCarreSignal = 0f;
		for (float info : informationRecue)
			sommeCarreSignal += Math.pow(info,2); 
		float pSignal= sommeCarreSignal / informationRecue.nbElements();
		float snr = (float) Math.pow(10,(snrDB/10));
		pBruit = (float) (pSignal/snr);
		return pBruit;
	}
	
	/**
	 * Methode pour additionner l'information et le bruit
	 * Les deux doivent etre de type float
	 * @param information
	 * @param bruit
	 * @return information bruite
	 */
	private Information<Float> signalBruite(Information<Float> information, Information<Float> bruit) {
		Information<Float> signal = new Information<Float> ();
		for (int i = 0 ; i < information.nbElements() ; i++)
			signal.add(information.iemeElement(i) + bruit.iemeElement(i));
		return signal;
	}
}

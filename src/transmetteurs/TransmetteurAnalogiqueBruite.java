package transmetteurs;

import javax.rmi.CORBA.Util;

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
	private int retard;
	private float attenuation;
	private boolean hasdelay = false;
	
	
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
	
	public TransmetteurAnalogiqueBruite(float snrDB, int seed, int retard, float attenuation) {
		this.snrDB = snrDB;
		this.seed = seed;
		this.retard = retard;
		this.attenuation = attenuation;
		hasdelay = true;
		
	}
	public TransmetteurAnalogiqueBruite(float snr, int decalageTemporel, float amplitudeRelative) {
		// TODO Auto-generated constructor stub
		snrDB = snr;
		retard = decalageTemporel;
		attenuation = amplitudeRelative;
		hasdelay = true;
	}

	/**
	 * Méthode pour recevoir l'information
	 */
	@Override
	public void recevoir(Information<Float> information) throws InformationNonConforme {
		// TODO Auto-generated method stub
		informationRecue = information;
		emettre();
	}

	/**
	 * Methode pour emettre le signal
	 * S'occupe d'appeler les methodes permettant de générer un bruit
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
		
		if(hasdelay) {
			Information<Float> signalRetarde;
			signalRetarde = Utils.enableTrajetMultiple(informationRecue, retard, attenuation);
			informationEmise = Utils.additionnerSignaux(signalRetarde, bruit); 
		}
		else {
		informationEmise = Utils.additionnerSignaux(informationRecue, bruit);
		}
		
		for (DestinationInterface<Float> destinationConnectee : destinationsConnectees) {
			destinationConnectee.recevoir(informationEmise);
		}
	}
	
	/**
	 * Méthode pour calculer la puissance de bruit necessaire
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
		float pSignal= sommeCarreSignal / (informationRecue.nbElements()*10);
		float snr = (float) Math.pow(10,(snrDB/10));
		pBruit = (float) (pSignal/snr);
		return pBruit;
	}
}

package transmetteurs;

import utils.AdditionneurSignaux;
import utils.RetardateurSignal;
import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

/**
 * Un transmetteur analogique parfait reçoit une information<Float> et le
 * retransmet telle qu'elle
 *
 */
public class TransmetteurAnalogiqueParfait extends Transmetteur<Float, Float> {
	private int retard;
	private float attenuation;
	private boolean hasdelay = false;

	public TransmetteurAnalogiqueParfait() {
		super();
	}

	public TransmetteurAnalogiqueParfait(int retard, float attenuation) {
		super();
		this.retard = retard;
		this.attenuation = attenuation;
		hasdelay = true;
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
		if (hasdelay) {
			Information<Float> signalRetarde = RetardateurSignal.getSignalRetardee(informationRecue, retard, attenuation);
			informationEmise = AdditionneurSignaux.additionnerSignaux(informationRecue, signalRetarde);			
		} else {
			this.informationEmise = informationRecue;
		}
		for (DestinationInterface<Float> destinationConnectee : destinationsConnectees) {
			destinationConnectee.recevoir(informationEmise);
		}
	}

}

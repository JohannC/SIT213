package transmetteurs;

import java.util.ArrayList;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;
import utils.AdditionneurSignaux;
import utils.RetardateurSignal;

/**
 * Un transmetteur analogique parfait reçoit une information<Float> et le
 * retransmet telle qu'elle
 *
 */
public class TransmetteurAnalogiqueParfait extends Transmetteur<Float, Float> {
	private ArrayList<Integer> retard;
	private ArrayList<Float> attenuation;
	private boolean hasdelay = false;

	public TransmetteurAnalogiqueParfait() {
		super();
	}

	public TransmetteurAnalogiqueParfait(ArrayList<Integer> retard, ArrayList<Float> attenuation) {
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
			Information<Float> signalPlusRetard = null;
			for (int i = 0; i < retard.size(); i++) {
				Information<Float> signalRetarde = RetardateurSignal.getSignalRetardee(informationRecue, retard.get(i),
						attenuation.get(i));
				if (i == 0) {
					signalPlusRetard = AdditionneurSignaux.additionnerSignaux(informationRecue, signalRetarde);
				} else {
					signalPlusRetard = AdditionneurSignaux.additionnerSignaux(signalPlusRetard, signalRetarde);
				}

			}
			informationEmise = AdditionneurSignaux.additionnerSignaux(informationRecue, signalPlusRetard);
		} else {
			this.informationEmise = informationRecue;
		}
		for (DestinationInterface<Float> destinationConnectee : destinationsConnectees) {
			destinationConnectee.recevoir(informationEmise);
		}
	}

}

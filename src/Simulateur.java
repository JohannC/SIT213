import destinations.Destination;
import destinations.DestinationFinale;
import exception.ArgumentsException;
import information.Information;
import sources.Source;
import sources.SourceAleatoire;
import sources.SourceFixe;
import transmetteurs.Emetteur;
import transmetteurs.Recepteur;
import transmetteurs.Transmetteur;
import transmetteurs.TransmetteurAnalogiqueBruite;
import transmetteurs.TransmetteurAnalogiqueParfait;
import transmetteurs.TransmetteurParfait;
import visualisations.SondeAnalogique;
import visualisations.SondeLogique;

/**
 * La classe Simulateur permet de construire et simuler une chaîne de
 * transmission composee d'une Source, d'un nombre variable de Transmetteur(s)
 * et d'une Destination.
 * 
 * @author cousin
 * @author prou
 *
 */
public class Simulateur {

	private static final String nomSondeSource = "Source";
	private static final String nomSondeTransmetteur = "Destination";
	private static final String nomSondeEmetteurAnalogique = "emetteur";
	private static final int nbPixelSondeSource = 150;
	private static final int nbPixelSondeTransmetteur = 150;
	private static final String nomSondeRecepteur = "recepteur";
	private static final int nbPixelSondeRecepteur = 50;
	private static final String nomSondeTransmetteurAnalogique = "Transmetteur Analogique";
	private static int DEFAULT_NB_ECHANTILLON = 30;
	private static float DEFAULT_MIN = 0.0f;
	private static float DEFAULT_MAX = 1.0f;

	/** indique si le Simulateur utilise des sondes d'affichage */
	private boolean affichage = false;
	/**
	 * indique si le Simulateur utilise un message genere de manière aleatoire
	 */
	private boolean messageAleatoire = true;
	/**
	 * indique si le Simulateur utilise un germe pour initialiser les
	 * generateurs aleatoires
	 */
	private boolean aleatoireAvecGerme = false;
	/** la valeur de la semence utilisee pour les generateurs aleatoires */
	private Integer seed = null;
	/**
	 * la longueur du message aleatoire a transmettre si un message n'est pas
	 * impose
	 */
	private int nbBitsMess = 100;
	/** la chaîne de caractères correspondant a m dans l'argument -mess m */
	private String messageString = "100";

	/** le composant Source de la chaine de transmission */
	private Source<Boolean> source = null;
	/** Le composant emetteur analogique de la chaine de transmission **/
	private Emetteur emetteur;
	/** Lecomposant transmetteur parfait analogique **/
	private Transmetteur<Float, Float> transmetteurAnalogique;
	/** Le composant recepeteur analogique de la chaine de transmission **/
	private Recepteur recepteur;
	/**
	 * le composant Transmetteur parfait logique de la chaine de transmission
	 */
	private Transmetteur<Boolean, Boolean> transmetteurLogique = null;
	/** le composant Destination de la chaine de transmission */
	private Destination<Boolean> destination = null;
	/** le message a transmettre si celui-ci est booleen **/
	private Information<Boolean> information = null;

	/** Si le signal emis an transmetteur est analogique **/
	private Boolean signalAnalogique = false;

	/** Forme du signal analogique **/
	private String formeSignal = "RZ";

	/** Nombre d'echantillons par bit **/
	private int nombreEchantillon = DEFAULT_NB_ECHANTILLON;

	/** Amplitude minimale **/
	private float amplitudeMinimale = DEFAULT_MIN;

	/** Amplitude maximale **/
	private float amplitudeMaximale = DEFAULT_MAX;

	/** Utilisation d'un transmetteur bruite **/
	private boolean hasSNR = false;

	/** Rapport signal sur bruit en Db **/
	private float snr;

	/**
	 * Decalage temporel (en nombre d'echantillons entre le trajet indirect et
	 * le trajet direct
	 **/
	private int decalageTemporel = 0;

	/**
	 * Amplitude relative du signal du trajet indirect par rapport a celle du
	 * signal du trajet direct
	 **/
	private float amplitudeRelative = 0.0f;

	/** Indique si on a un trajet multiple **/
	private boolean trajetsMultiples = false;

	/**
	 * Le constructeur de Simulateur construit une chaîne de transmission
	 * composee d'une Source <Boolean>, d'une Destination <Boolean> et de
	 * Transmetteur(s) [voir la methode analyseArguments]... <br>
	 * Les differents composants de la chaîne de transmission (Source,
	 * Transmetteur(s), Destination, Sonde(s) de visualisation) sont crees et
	 * connectes.
	 * 
	 * @param args
	 *            le tableau des differents arguments.
	 *
	 * @throws ArgumentsException
	 *             si un des arguments est incorrect
	 *
	 */
	public Simulateur(String[] args) throws ArgumentsException {
		// analyser et recuperer les arguments
		analyseArguments(args);
		preparerSimulateur();
	}

	/**
	 * La methode preparerSimulateur sert a initialiser les attributs de l'objet
	 * Simulateur en fonction des paramètres passes par l'utilisateur
	 * 
	 * @throws ArgumentsException
	 *             si un des arguments est incorrect
	 */
	private void preparerSimulateur() throws ArgumentsException {
		if (hasSNR && !signalAnalogique) {
			signalAnalogique = true;
		}
		if (messageAleatoire) {
			// preparation d'une transmission d'un message aleatoire de longueur
			// messageString
			if (aleatoireAvecGerme)
				source = new SourceAleatoire(nbBitsMess, (long) seed);
			else
				source = new SourceAleatoire(nbBitsMess);
			destination = new DestinationFinale();
		} else {
			// prepareration d'une transmission de messageString
			source = new SourceFixe(messageString);
			destination = new DestinationFinale();
		}
		if (signalAnalogique) {
			if (!trajetsMultiples) {
				if (hasSNR) {
					if (aleatoireAvecGerme) {
						transmetteurAnalogique = new TransmetteurAnalogiqueBruite(snr, seed);
					} else {
						transmetteurAnalogique = new TransmetteurAnalogiqueBruite(snr);
					}
				} else {
					transmetteurAnalogique = new TransmetteurAnalogiqueParfait();
				}
			}
			if (trajetsMultiples) {
				if (hasSNR) {
					if (aleatoireAvecGerme) {
						transmetteurAnalogique = new TransmetteurAnalogiqueBruite(snr, seed, decalageTemporel, amplitudeRelative);
					} else {
						transmetteurAnalogique = new TransmetteurAnalogiqueBruite(snr, decalageTemporel, amplitudeRelative);
					}
				} else {
					transmetteurAnalogique = new TransmetteurAnalogiqueParfait(decalageTemporel, amplitudeRelative);
				}
			}
			emetteur = new Emetteur(formeSignal, amplitudeMinimale, amplitudeMaximale, nombreEchantillon);
			recepteur = new Recepteur(formeSignal, amplitudeMinimale, amplitudeMaximale, nombreEchantillon);
			source.connecter(emetteur);
			emetteur.connecter(transmetteurAnalogique);
			transmetteurAnalogique.connecter(recepteur);
			recepteur.connecter(destination);
		} else {
			transmetteurLogique = new TransmetteurParfait();
			source.connecter(transmetteurLogique);
			transmetteurLogique.connecter(destination);
		}
		if (affichage) {
			if (signalAnalogique) {
				source.connecter(new SondeLogique(nomSondeSource, nbPixelSondeSource));
				emetteur.connecter(new SondeAnalogique(nomSondeEmetteurAnalogique));
				transmetteurAnalogique.connecter(new SondeAnalogique(nomSondeTransmetteurAnalogique));
				recepteur.connecter(new SondeLogique(nomSondeRecepteur, nbPixelSondeRecepteur));
			} else {
				source.connecter(new SondeLogique(nomSondeSource, nbPixelSondeSource));
				transmetteurLogique.connecter(new SondeLogique(nomSondeTransmetteur, nbPixelSondeTransmetteur));
			}
		}
	}

	/**
	 * La methode analyseArguments extrait d'un tableau de chaînes de caractères
	 * les differentes options de la simulation. Elle met a jour les attributs
	 * du Simulateur.
	 *
	 * @param args
	 *            le tableau des differents arguments. <br>
	 *            <br>
	 *            Les arguments autorises sont : <br>
	 *            <dl>
	 *            <dt>-mess m</dt>
	 *            <dd>m (String) constitue de 7 ou plus digits a 0 | 1, le
	 *            message a transmettre</dd>
	 *            <dt>-mess m</dt>
	 *            <dd>m (int) constitue de 1 a 6 digits, le nombre de bits du
	 *            message "aleatoire" a  transmettre</dd>
	 *            <dt>-s</dt>
	 *            <dd>utilisation des sondes d'affichage</dd>
	 *            <dt>-seed v</dt>
	 *            <dd>v (int) d'initialisation pour les generateurs aleatoires
	 *            </dd>
	 *            </dl>
	 *
	 * @throws ArgumentsException
	 *             si un des arguments est incorrect.
	 *
	 */
	public void analyseArguments(String[] args) throws ArgumentsException {

		for (int i = 0; i < args.length; i++) {

			if (args[i].matches("-s")) {
				affichage = true;
			} else if (args[i].matches("-seed")) {
				aleatoireAvecGerme = true;
				i++;
				// traiter la valeur associee
				try {
					seed = new Integer(args[i]);
				} catch (Exception e) {
					throw new ArgumentsException("Valeur du parametre -seed  invalide :" + args[i]);
				}
			} else if (args[i].matches("-mess")) {
				i++;
				// traiter la valeur associee
				messageString = args[i];
				if (args[i].matches("[0,1]{7,}")) {
					messageAleatoire = false;
					nbBitsMess = args[i].length();
				} else if (args[i].matches("[0-9]{1,6}")) {
					messageAleatoire = true;
					nbBitsMess = new Integer(args[i]);
					if (nbBitsMess < 1)
						throw new ArgumentsException("Valeur du parametre -mess invalide : " + nbBitsMess);
				} else
					throw new ArgumentsException("Valeur du parametre -mess invalide : " + args[i]);

			} else if (args[i].matches("-nbEch")) {
				i++;
				if (args[i].matches("^[1-9]\\d*")) {
					nombreEchantillon = new Integer(args[i]);
					signalAnalogique = true;
				} else
					throw new ArgumentsException("Le nombre d'echantillons doit être une valeur entière positive");
			} else if (args[i].matches("-form")) {
				i++;
				if (args[i].toUpperCase().matches("RZ|NRZ|NRZT")) {
					signalAnalogique = true;
					formeSignal = args[i].toUpperCase();
				} else
					throw new ArgumentsException("Valeur du parametre -form invalide : " + args[i]);
			} else if (args[i].matches("-ampl")) {
				float min;
				float max;
				if (args[i + 1].matches("^([+-]?\\d*\\.?\\d*)$") && args[i + 2].matches("^([+-]?\\d*\\.?\\d*)$")) {
					min = new Float(args[i + 1]).floatValue();
					max = new Float(args[i + 2]).floatValue();
					if (min < max) {
						amplitudeMaximale = max;
						amplitudeMinimale = min;
						signalAnalogique = true;
						i += 2;
					} else
						throw new ArgumentsException("La valeur minimale doit être inferieure a la valeur maximale");
				} else
					throw new ArgumentsException(
							"Valeur(s) parametre(s) -ampl invalide(s) : " + args[i + 1] + " " + args[i + 2]);
			} else if (args[i].matches("-snr")) {
				if (args[i + 1].matches("^([+-]?\\d*\\.?\\d*)$")) {
					signalAnalogique = true;
					snr = new Float(args[i + 1]).floatValue();
					hasSNR = true;
					i++;
				} else
					throw new ArgumentsException("Valeur(s) parametre(s) -snr invalide(s) : " + args[i + 1]);
			} else if (args[i].matches("-ti")) {
				if (args[i + 1].matches("^\\s*-?[0-9]{1,10}\\s*$") && args[i + 2].matches("^([+-]?\\d*\\.?\\d*)$")) {
					decalageTemporel = new Integer(args[i + 1]).intValue();
					amplitudeRelative = new Float(args[i + 2]).floatValue();
					if(amplitudeRelative < 0.0 || amplitudeRelative>1.0){
						throw new ArgumentsException("Valeur parametre -ti invalide, l'attenuation du retard doit �tre compris entre 0 et 1");
					}
					signalAnalogique = true;
					trajetsMultiples = true;
					i += 2;
				}
				else
					throw new ArgumentsException("Valeur(s) parametre(s) -ti invalide(s) : " + args[i + 1] + " " + args[i + 2]);
			}

			else
				throw new ArgumentsException("Option invalide :" + args[i]);
		}
	}

	/**
	 * La methode execute effectue un envoi de message par la source de la
	 * chaîne de transmission du Simulateur.
	 *
	 * @throws Exception
	 *             si un problème survient lors de l'execution
	 *
	 */
	public void execute() throws Exception {
		source.emettre();
	}

	/**
	 * La methode qui calcule le taux d'erreur binaire en comparant les bits du
	 * message emis avec ceux du message reçu.
	 *
	 * @return La valeur du Taux dErreur Binaire.
	 */
	public float calculTauxErreurBinaire() {
		Information<Boolean> infoSource = source.getInformationEmise();
		Information<Boolean> infoDestination = destination.getInformationRecue();
		int nbErreur = 0;
		for (int i = 0; i < infoSource.nbElements() && i < infoDestination.nbElements(); i++) {
			if (infoSource.iemeElement(i) != infoDestination.iemeElement(i))
				nbErreur++;
		}
		float tauxErreurBinaire = (float) nbErreur / (float) infoSource.nbElements();

		if (tauxErreurBinaire > 0.5 && tauxErreurBinaire <= 1)
			tauxErreurBinaire = 1 - tauxErreurBinaire;
		return tauxErreurBinaire;
	}

	/**
	 * La fonction main instancie un Simulateur a l'aide des arguments
	 * paramètres et affiche le resultat de l'execution d'une transmission.
	 * 
	 * @param args
	 *            les differents arguments qui serviront a l'instanciation du
	 *            Simulateur.
	 */
	public static void main(String[] args) {

		Simulateur simulateur = null;

		try {
			simulateur = new Simulateur(args);
		} catch (Exception e) {
			System.out.println(e);
			System.exit(-1);
		}

		try {
			simulateur.execute();
			float tauxErreurBinaire = simulateur.calculTauxErreurBinaire();
			String s = "java  Simulateur  ";
			for (int i = 0; i < args.length; i++) {
				s += args[i] + "  ";
			}
			System.out.println(s + "  =>   TEB : " + tauxErreurBinaire);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			System.exit(-2);
		}
	}
}

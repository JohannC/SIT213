import coders.DecoderNRZ;
import coders.DecoderNRZT;
import coders.DecoderRZ;
import coders.EncoderNRZ;
import coders.EncoderNRZT;
import coders.EncoderRZ;
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
import transmetteurs.TransmetteurAnalogiqueParfait;
import transmetteurs.TransmetteurParfait;
import visualisations.SondeAnalogique;
import visualisations.SondeLogique;

/**
 * La classe Simulateur permet de construire et simuler une chaîne de
 * transmission composée d'une Source, d'un nombre variable de Transmetteur(s)
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
	 * indique si le Simulateur utilise un message généré de manière aléatoire
	 */
	private boolean messageAleatoire = true;
	/**
	 * indique si le Simulateur utilise un germe pour initialiser les
	 * générateurs aléatoires
	 */
	private boolean aleatoireAvecGerme = false;
	/** la valeur de la semence utilisée pour les générateurs aléatoires */
	private Integer seed = null;
	/**
	 * la longueur du message aléatoire à transmettre si un message n'est pas
	 * impose
	 */
	private int nbBitsMess = 100;
	/** la chaîne de caractères correspondant à m dans l'argument -mess m */
	private String messageString = "100";

	/** le composant Source de la chaine de transmission */
	private Source<Boolean> source = null;
	/** Le composant emetteur analogique de la chaine de transmission **/
	private Emetteur emetteur;
	/** Lecomposant transmetteur parfait analogique **/
	private Transmetteur<Float, Float> transmetteurAnalogiqueParfait;
	/** Le composant recepeteur analogique de la chaine de transmission **/
	private Recepteur recepteur;
	/**
	 * le composant Transmetteur parfait logique de la chaine de transmission
	 */
	private Transmetteur<Boolean, Boolean> transmetteurLogique = null;
	/** le composant Destination de la chaine de transmission */
	private Destination<Boolean> destination = null;
	/** le message à transmettre si celui-ci est booléen **/
	private Information<Boolean> information = null;

	/** Si le signal emis an transmetteur est analogique **/
	private Boolean signalAnalogique = false;

	/** Forme du signal analogique **/
	private String formeSignal = "RZ";

	/** Nombre d'échantillons par bit **/
	private int nombreEchantillon = DEFAULT_NB_ECHANTILLON;

	/** Amplitude minimale **/
	private float amplitudeMinimale = DEFAULT_MIN;

	/** Amplitude maximale **/
	private float amplitudeMaximale = DEFAULT_MAX;

	/**
	 * Le constructeur de Simulateur construit une chaîne de transmission
	 * composée d'une Source <Boolean>, d'une Destination <Boolean> et de
	 * Transmetteur(s) [voir la méthode analyseArguments]... <br>
	 * Les différents composants de la chaîne de transmission (Source,
	 * Transmetteur(s), Destination, Sonde(s) de visualisation) sont créés et
	 * connectés.
	 * 
	 * @param args
	 *            le tableau des différents arguments.
	 *
	 * @throws ArgumentsException
	 *             si un des arguments est incorrect
	 *
	 */
	public Simulateur(String[] args) throws ArgumentsException {
		// analyser et récupérer les arguments
		analyseArguments(args);
		preparerSimulateur();
	}

	/**
	 * La méthode preparerSimulateur sert à initialiser les attributs de l'objet
	 * Simulateur en fonction des paramètres passés par l'utilisateur
	 * 
	 * @throws ArgumentsException
	 *             si un des arguments est incorrect
	 */
	private void preparerSimulateur() throws ArgumentsException {
		if (messageAleatoire) {
			// preparation d'une transmission d'un message aléatoire de longueur
			// messageString
			if (aleatoireAvecGerme)
				source = new SourceAleatoire(nbBitsMess, (long) seed);
			else
				source = new SourceAleatoire(nbBitsMess);
			destination = new DestinationFinale();
		} else {
			// prépareration d'une transmission de messageString
			source = new SourceFixe(messageString);
			destination = new DestinationFinale();
		}
		if (signalAnalogique) {
			transmetteurAnalogiqueParfait = new TransmetteurAnalogiqueParfait();
			emetteur = new Emetteur(formeSignal, amplitudeMinimale, amplitudeMaximale, nombreEchantillon);
			recepteur = new Recepteur(formeSignal, amplitudeMinimale, amplitudeMaximale, nombreEchantillon);
			source.connecter(emetteur);
			emetteur.connecter(transmetteurAnalogiqueParfait);
			transmetteurAnalogiqueParfait.connecter(recepteur);
			recepteur.connecter(destination);
		} else {
			transmetteurLogique = new TransmetteurParfait();
			source.connecter(transmetteurLogique);
			transmetteurLogique.connecter(destination);
		}
		// fdfdsfdsfdsfds
		if (affichage) {
			if (signalAnalogique) {
				source.connecter(new SondeLogique(nomSondeSource, nbPixelSondeSource));
				emetteur.connecter(new SondeAnalogique(nomSondeEmetteurAnalogique));
				transmetteurAnalogiqueParfait.connecter(new SondeAnalogique(nomSondeTransmetteurAnalogique));
				recepteur.connecter(new SondeLogique(nomSondeRecepteur, nbPixelSondeRecepteur));
			} else {
				source.connecter(new SondeLogique(nomSondeSource, nbPixelSondeSource));
				transmetteurLogique.connecter(new SondeLogique(nomSondeTransmetteur, nbPixelSondeTransmetteur));
			}
		}
	}

	/**
	 * La méthode analyseArguments extrait d'un tableau de chaînes de caractères
	 * les différentes options de la simulation. Elle met à jour les attributs
	 * du Simulateur.
	 *
	 * @param args
	 *            le tableau des différents arguments. <br>
	 *            <br>
	 *            Les arguments autorisés sont : <br>
	 *            <dl>
	 *            <dt>-mess m</dt>
	 *            <dd>m (String) constitué de 7 ou plus digits à 0 | 1, le
	 *            message à transmettre</dd>
	 *            <dt>-mess m</dt>
	 *            <dd>m (int) constitué de 1 à 6 digits, le nombre de bits du
	 *            message "aléatoire" à  transmettre</dd>
	 *            <dt>-s</dt>
	 *            <dd>utilisation des sondes d'affichage</dd>
	 *            <dt>-seed v</dt>
	 *            <dd>v (int) d'initialisation pour les générateurs aléatoires
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
					throw new ArgumentsException("Le nombre d'échantillons doit être une valeur entière positive");
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
				if (args[++i].matches("^([+-]?\\d*\\.?\\d*)$") && args[++i].matches("^([+-]?\\d*\\.?\\d*)$")) {
					min = new Float(args[i - 1]).floatValue();
					max = new Float(args[i]).floatValue();
					if (min < max) {
						amplitudeMaximale = max;
						amplitudeMinimale = min;
						signalAnalogique = true;
					} else
						throw new ArgumentsException("La valeur minimale doit être inférieure à la valeur maximale");
				} else
					throw new ArgumentsException(
							"Valeur(s) parametre(s) -ampl invalide(s) : " + args[i] + " " + args[i + 1]);
			} else
				throw new ArgumentsException("Option invalide :" + args[i]);
		}
	}

	/**
	 * La méthode execute effectue un envoi de message par la source de la
	 * chaîne de transmission du Simulateur.
	 *
	 * @throws Exception
	 *             si un problème survient lors de l'exécution
	 *
	 */
	public void execute() throws Exception {
		source.emettre();
	}

	/**
	 * La méthode qui calcule le taux d'erreur binaire en comparant les bits du
	 * message émis avec ceux du message reçu.
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
		float tauxErreurBinaire = nbErreur / infoSource.nbElements();
		return tauxErreurBinaire;
	}

	/**
	 * La fonction main instancie un Simulateur à l'aide des arguments
	 * paramètres et affiche le résultat de l'exécution d'une transmission.
	 * 
	 * @param args
	 *            les différents arguments qui serviront à l'instanciation du
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

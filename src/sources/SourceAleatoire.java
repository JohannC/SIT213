package sources;

import java.util.Random;

import information.Information;

/**
 * La classe SourceAleatoire permet de construire une source envoyant un message
 * aléatoire de taille définie et possiblement en fonction d'une seed.
 * 
 */
public class SourceAleatoire extends Source<Boolean> {

	/** Génarateur de valeurs aléatoires */
	private Random r;

	/**
	 * Constructeur de la classe SourceAléatoire construisant un message de
	 * taille nbBitsMess en fonction d'une seed
	 * 
	 * @param nbBitsMess
	 * @param seed
	 */
	public SourceAleatoire(int nbBitsMess, long seed) {
		super();
		r = new Random(seed);
		genererInformation(nbBitsMess);
	}

	/**
	 * Constructeur de la classe SourceAléatoire construisant un message de
	 * taille nbBitsMess
	 * 
	 * @param nbBitsMess
	 */
	public SourceAleatoire(int nbBitsMess) {
		super();
		r = new Random();
		genererInformation(nbBitsMess);
	}

	/**
	 * Methode générant une message de aléatoire de nbBitMess. Créé pour cela un
	 * objet de type Information<Boolean>, contenant la suite de booléans
	 * représentant la suite binaire
	 * 
	 * @param nbBitsMess
	 */
	private void genererInformation(int nbBitsMess) {
		informationGeneree = new Information<Boolean>();
		for (int i = 0; i < nbBitsMess; i++) {
			Boolean value = r.nextBoolean();
			informationGeneree.add(value);
		}
	}
}

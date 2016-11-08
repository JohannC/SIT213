package Utils;

import information.Information;

/**
 * Classe FiltreAdapte
 * Encapsule la methode statique qui realise le calcul du produit de convolution de deux signaux.
 */
public class FiltreAdapte {
	/**
	 * Cette methode realise le calcul du produit de convolution de deux signaux.
	 * Les deux signaux a convoluer doivent avoir le meme pas de discretisation.
	 * 
	 * @param informationRecu
	 * 		Le signal en entree de type Information<Float>.
	 *            
	 * @param reponseImpulsionnelle
	 * 		Reponse impulsionnelle du filtre de type Information<Float>.
	 * 
	 * @return reponseDuFiltre
	 * 		Le produit de convolution des deux signaux de type Information<Float>
	 * 
	 * @throws BadStepsException
	 *      Erreur renvoyee si les deux signaux a convoluer ne presentent pas
	 *      le meme pas de discretisation.
	 */
	public static Information<Float> convolution(
			Information<Float> informationRecu,
			Information<Float> reponseImpulsionnelle) // throws BadStepsException
	{
		float sum = 0f;
		float p = 0f;

		int nbEchX = informationRecu.nbElements();
		int nbEchH = reponseImpulsionnelle.nbElements();
		int nbEchY = nbEchX + nbEchH;

		Information<Float> reponseDuFiltre = new Information<Float>();

		for (int n = 0; n < nbEchY; n++) {
			for (int m = 0; m < nbEchX; m++) {
				if (n - m >= 0 && n - m < nbEchH) {
					p = produit(informationRecu.iemeElement(m),reponseImpulsionnelle.iemeElement(n - m));
					sum = somme(sum, p);
				}
			}
			reponseDuFiltre.add(sum);
			sum = 0f;
		}

		return reponseDuFiltre;
	}
	
	/**
	 * Methode de classe qui s'occupe de calculer la somme de deux nombres de type float.
	 * 
	 * @param nombre1
	 * 		Premier nombre de type float.
	 * @param nombre2
	 * 		Seconde nombre de type float.
	 * @return somme
	 * 		Somme des deux nombres de type float.
	 */
	private static float somme(float nombre1, float nombre2) {
		return (nombre1 + nombre2);
	}
	
	/**
	 * Methode de classe qui s'occupe de calculer le produit de deux nombres de type float.
	 * 
	 * @param nombre1
	 * 		Premier nombre de type float.
	 * @param nombre2
	 * 		Seconde nombre de type float.
	 * @return somme
	 * 		Somme des deux nombres de type float.
	 */
	private static float produit(float nombre1, float nombre2) {

		return (nombre1 * nombre2);
	}
}

package calcul;

import information.Information;

/**
 * Cette classe encapsule la méthode statique qui réalise le calcul du produit
 * de convolution de deux signaux.
 * 
 * @author Lahoucine AMHOUCHE
 */
public class Convolution {
	/**
	 * Cette méthode réalise le calcul du produit de convolution de deux
	 * signaux. Pour que le calcul puisse être effectué, les deux signaux à
	 * convoluer doivent avoir le même pas de discrétisation.
	 * 
	 * @param informationRecu
	 *            Le premier Information<Float> à convoluer.
	 * @param reponseImpulsionnelle
	 *            Le deuxième Information<Float> à convoluer.
	 * 
	 * @return Le Information<Float> représentant le produit de convolution des
	 *         deux signaux.
	 * 
	 * @throws BadStepsException
	 *             Si les deux signaux à convoluer ne présentent pas le même pas
	 *             de discrétisation.
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

	public static float somme(float nombre1, float nombre2) {

		return (nombre1 + nombre2);
	}

	public static float produit(float nombre1, float nombre2) {

		return (nombre1 * nombre2);
	}
}

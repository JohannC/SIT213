/**
 * 
 */
package transmetteurs;

import java.util.Random;

import information.Information;

/**
 * Classe BruitBlancGaussien
 * Genere a partir d'une puissance donnee un bruit blanc gaussien
 * Peut prendre en parametre une graine 
 */
public class BruitBlancGaussien {

	private double puissanceBruitSqrt;
	private double echBruitBG;
	private Integer seed = null;
	Information<Float> informationBuitBG;
	
	/**
	 * Constructeur de la classe BruitBlancGaussien
	 * Affecte la racine de la puissance du bruit a son attribut associe 
	 * @param puissanceBruit 
	 */
	public BruitBlancGaussien (double puissanceBruit){
		
		this.puissanceBruitSqrt =  Math.sqrt(puissanceBruit);
		informationBuitBG = new Information<Float>();
	}
	
	/**
	 * Constructeur de la classe BruitBlancGaussien
	 * Affecte la racine de la puissance du bruit a son attribut associe
	 * ainsi que la valeur de la graine 
	 * @param puissanceBruit
	 * @param seed graine 
	 */
	public BruitBlancGaussien (double puissanceBruit, Integer seed){
		
		this.puissanceBruitSqrt = Math.sqrt(puissanceBruit);
		this.seed = seed; 
		informationBuitBG = new Information<Float>();
	}
	/**
	 * Méthode publique qui qenere un bruit Blanc Gaussien de type Information<Float>
	 * @param tailleBruitage taille de l'information
	 * @return bruit blanc gaussien
	 */
	public Information<Float> generateurBruitBG (int tailleBruitage){
		Random a;
		
		if(seed !=  null)
			a = new Random(seed);
		else
			a = new Random();
		
		for(int i = 0; i < tailleBruitage; i++) {
			
			double a1 = a.nextDouble();
			double a2 = a.nextDouble();
			echBruitBG = puissanceBruitSqrt*Math.sqrt(-2*Math.log(1-a1))*Math.cos(2*Math.PI*a2);
			informationBuitBG.add((float) echBruitBG);
		}

		return informationBuitBG;	
	}
}

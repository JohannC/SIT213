/**
 * 
 */
package transmetteurs;

import java.util.Random;

import information.Information;

/**
 * @author user
 *
 */
public class BruitBlancGaussien {

	private double puissanceBruitSqrt;
	private double echBruitBG;
	private Integer seed = null;
	Information<Float> informationBuitBG;
	
	public BruitBlancGaussien (double puissanceBruit){
		
		this.puissanceBruitSqrt =  Math.sqrt(puissanceBruit);
		informationBuitBG = new Information<Float>();
	}
	
	public BruitBlancGaussien (double puissanceBruit, Integer seed){
		
		this.puissanceBruitSqrt = Math.sqrt(puissanceBruit);
		this.seed = seed; 
		informationBuitBG = new Information<Float>();
	}
	/**
	 * Genere un bruit Blanc Gaussien 
	 * @param tailleBruitage
	 * @return
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

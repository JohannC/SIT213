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

	private double puissanceBruit;
	private double echBruitBG;
	private Integer seed = null;
	Information<Float> informationBuitBG;
	
	public BruitBlancGaussien (double puissanceBruit){
		
		this.puissanceBruit = puissanceBruit;
		informationBuitBG = new Information<Float>();
	}
	
	public BruitBlancGaussien (double puissanceBruit, Integer seed){
		
		this.puissanceBruit = puissanceBruit;
		this.seed = seed; 
		informationBuitBG = new Information<Float>();
	}
	
	public Information<Float> generateurBruitBG (int tailleBruitage){
		
		Random a1 = new Random();
		Random a2 = new Random();
		
		if(seed !=  null){
			a1.setSeed(seed);
			a2.setSeed(seed);
		}
			
		for(int i = 0; i < tailleBruitage; i++) {
			// Génére un bruit Blanc Gaussien 
			echBruitBG = puissanceBruit*Math.sqrt(-2*Math.log(1-a1.nextDouble()))*Math.cos(2*Math.PI*a2.nextDouble());
			informationBuitBG.add((float) echBruitBG);
		}
		
		return informationBuitBG;	
	}
}

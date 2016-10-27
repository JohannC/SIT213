package transmetteur;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import information.Information;

import java.applet.Applet;
import java.awt.Graphics;
import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import calculs.NbOccurrence;
import calculs.TriParTas;

import transmetteurs.BruitBlancGaussien;

public class BruitBlancGaussienTest {
	
	private static final int SEED = 2;

	private static final int PUISSANCE_BRUIT = 1;

	private static final int NB_ECH_BRUIT = 10;
	
	/////////////////////////////////////////////////////
	//private float [] tableau = new float[NB_ECH_BRUIT];
	//private ArrayList<Integer> tabOcc;
	/////////////////////////////////////////////////////

	Information <Float> infoTestBruitBG;
	
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Test
	public void nombreElementsTest() {
		infoTestBruitBG = new Information<Float>();
		BruitBlancGaussien bruitBG = new BruitBlancGaussien(PUISSANCE_BRUIT, SEED);
		
		infoTestBruitBG = bruitBG.generateurBruitBG(NB_ECH_BRUIT);
		
		collector.checkThat(infoTestBruitBG.nbElements(), is(NB_ECH_BRUIT));
		
		for(int i= 0; i < infoTestBruitBG.nbElements(); i++)
			System.out.println(infoTestBruitBG.iemeElement(i));
		
		/////////////////////////////////////////////////////
		
//		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//		
//		NbOccurrence nb = new NbOccurrence();
//		TriParTas tr = new TriParTas();
//		
//		
//		for(int i= 0; i < infoTestBruitBG.nbElements(); i++)
//			tableau[i] = (float) infoTestBruitBG.iemeElement(i);
//		
//		tr.tri(tableau, infoTestBruitBG.nbElements());
//		tabOcc = nb.chercherNbOcc(tableau, infoTestBruitBG.nbElements());
//		
//		for(int i= 0; i < infoTestBruitBG.nbElements(); i++)
//			System.out.println(tableau[i]);
//		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//		for(int i= 0; i < tabOcc.size(); i++)
//			System.out.println(tabOcc.get(i));
		
		/////////////////////////////////////////////////////
	}

}

package utils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Random;

import information.Information;
import utils.FiltreAdapte;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class FiltreAdapteTest {

	private static final int NBECH = 30;

	Information<Float> informationAConvoluer;

	Information<Float> reponseImpulsionnelle;

	Information<Float> reponseDuFiltre;

	// Convolution conv = new Convolution();

	boolean[] valeur = { true, false, false, true, true, false };

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Before
	public void setUp() throws Exception {

		informationAConvoluer = new Information<Float>();
		reponseImpulsionnelle = new Information<Float>();

		for (int j = 0; j < NBECH; j++) {
			reponseImpulsionnelle.add(1f);
		}

		for (int i = 0; i < 180; i++) {
			if (valeur[i / 30]) {
				informationAConvoluer.add(1f);
			} else {
				informationAConvoluer.add(0f);
			}
		}
	}

	@Test
	public void test() {

		reponseDuFiltre = new Information<Float>();

		float[] reponseDuFiltreMatlab = {1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f,
				10f, 11f, 12f, 13f, 14f, 15f, 16f, 17f, 18f, 19f, 20f, 21f,
				22f, 23f, 24f, 25f, 26f, 27f, 28f, 29f, 30f, 29f, 28f, 27f,
				26f, 25f, 24f, 23f, 22f, 21f, 20f, 19f, 18f, 17f, 16f, 15f,
				14f, 13f, 12f, 11f, 10f, 9f, 8f, 7f, 6f, 5f, 4f, 3f, 2f, 1f,
				0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
				0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 1f,
				2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f, 12f, 13f, 14f, 15f,
				16f, 17f, 18f, 19f, 20f, 21f, 22f, 23f, 24f, 25f, 26f, 27f,
				28f, 29f, 30f, 30f, 30f, 30f, 30f, 30f, 30f, 30f, 30f, 30f,
				30f, 30f, 30f, 30f, 30f, 30f, 30f, 30f, 30f, 30f, 30f, 30f,
				30f, 30f, 30f, 30f, 30f, 30f, 30f, 30f, 30f, 29f, 28f, 27f,
				26f, 25f, 24f, 23f, 22f, 21f, 20f, 19f, 18f, 17f, 16f, 15f,
				14f, 13f, 12f, 11f, 10f, 9f, 8f, 7f, 6f, 5f, 4f, 3f, 2f, 1f,
				0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
				0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f};

		for (int i = 0; i < reponseDuFiltreMatlab.length; i++)
			reponseDuFiltre.add(reponseDuFiltreMatlab[i]);

		Information<Float> information = FiltreAdapte.convolution(informationAConvoluer, reponseImpulsionnelle);

//		for (int i = 0; i < reponseDuFiltreMatlab.length; i++)
//			System.out.println(reponseDuFiltre.iemeElement(i)
//					- information.iemeElement(i));
		
		for (int i = 0; i < reponseDuFiltreMatlab.length; i++)
			collector.checkThat(FiltreAdapte.convolution(informationAConvoluer,reponseImpulsionnelle).iemeElement(i), is(reponseDuFiltre.iemeElement(i)));

	}
}

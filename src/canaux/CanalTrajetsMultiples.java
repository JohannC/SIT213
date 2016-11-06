package canaux;

import information.Information;

public class CanalTrajetsMultiples {
	
	private Information <Float> informationRecue;
	private Information <Float> informationRetardee = null;
	private int retard;
	private float attenuation;
	
	public CanalTrajetsMultiples(Information<Float> information, int retard, float attenuation){
		informationRecue = information;
		this.retard = retard;
		this.attenuation = attenuation;
	}

	public Information<Float> monInfoRetardee(){
		informationRetardee = new Information<Float>();
		for (int i = 0 ; i < retard ; i++)
			informationRetardee.add(0f);
		
		for (float e : informationRecue)
			informationRetardee.add(e*attenuation);
		
		return informationRetardee;
	}



}

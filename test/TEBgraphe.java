import java.io.File;
import java.io.FileWriter;

import exception.ArgumentsException;

public class TEBgraphe {

	private static final int nbEch = 30;

	public static void main(String[] args) throws ArgumentsException {
		File file = new File("simuTEB.csv");
		FileWriter fw;
		try {
			file.createNewFile();
			fw = new FileWriter(file);
			
			for (float snr = -30f; snr <= 20f; snr = snr + 0.1f) {
				float snr1 = snr + (float) (10*Math.log10(nbEch/2)); 
				fw.write(""+snr1);
				for (int i = 0; i < 100; i++){
					String[] args1 = { "-mess", "1000", "-form", "NRZT", "-snr", "" + Math.round(snr * 100.0) / 100.0 };
					Simulateur simu = new Simulateur(args1);
					float teb;
					simu.execute();
					teb = simu.calculTauxErreurBinaire();
					fw.write(","+teb);
				}
				fw.write("\n");
			}
			fw.flush();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

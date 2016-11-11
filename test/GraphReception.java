import java.io.File;
import java.io.FileWriter;

import exception.ArgumentsException;

public class GraphReception {	

	public static void main(String[] args) throws ArgumentsException {
		File file = new File("RZ-10000-5000-0_7-snr-10.csv");
		FileWriter fw;
		try {
			file.createNewFile();
			fw = new FileWriter(file);
					
			String[] args1 = { "-mess", "10000", "-form", "RZ", "-ti", "5000", "0.7", "-snr", "-10"};
			Simulateur simu = new Simulateur(args1);
			simu.execute();
			
			for(int i=0 ; i<simu.getSignalRecepteur().nbElements() ; i++ ){
				fw.write(""+simu.getSignalRecepteur().iemeElement(i));
				fw.write("\n");
			}
			fw.flush();
			fw.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
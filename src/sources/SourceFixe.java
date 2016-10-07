package sources;

import excpetion.ArgumentsException;
import information.Information;

/**
 * La classe SourceFixe permet de construire une source envoyant 
 * le message passé en paramètre 
 * 
 */
public class SourceFixe extends Source<Boolean>  {
	
	/**
	 * Constructeur de la classe SourceFixe construisant le message passé en paramètre.
	 * @param message une chaine de caractère contenant une suite de 0 et de 1
	 * @throws ArgumentsException lancée si le message passé en paramètre contient autre chose que des 0 et des 1
	 */
	public SourceFixe(String message) throws ArgumentsException{
		super();
		genererInformationMessage(message);
	}
	
	/**
	 * Methode faisant la converstion entre la chaine de caractères contenant des 0 et des 1 et un objet Information<Boolean> contenant la suite de bits correspondante
	 * @param message une chaine de caractère contenant une suite de 0 et de 1
	 * @throws ArgumentsException lancée si le message passé en paramètre contient autre chose que des 0 et des 1
	 */
	private void genererInformationMessage(String message) throws ArgumentsException {
		informationGeneree = new Information<Boolean>();
		for(int i = 0; i < message.length(); i++){
			if(message.charAt(i) == '0'){
				informationGeneree.add(false);
			} else if (message.charAt(i) == '1') {
				informationGeneree.add(true);
			}
			else
				throw new ArgumentsException("Le message ne doit contenir que des 0 et des 1 !");			
		}
	}
}

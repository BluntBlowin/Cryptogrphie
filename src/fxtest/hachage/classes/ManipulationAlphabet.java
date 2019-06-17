package fxtest.hachage.classes;

public class ManipulationAlphabet {
	
	private static ManipulationAlphabet instance = new ManipulationAlphabet();
	
	private ManipulationAlphabet(){
		super();
	}
	
	private final String alphabet = 
		"abcdefghijklmnopqrstuvwxyz ";
	
	/**
	 * Retourne l'indice de la lettre dans l'alphabet
	 * @param lettre
	 * @return
	 */
	public int getIndice(char lettre){
		int res=0;
		for(int i=0; i<alphabet.length(); i++){
			if(lettre==alphabet.charAt(i)){
				res=i;
			}
		}
		return res;
	}
	
	/**
	 * Retourne la lettre correspodant � l'indice
	 * @param indice
	 * @return
	 */
	public char getLettre(int indice){
		return alphabet.charAt(indice);
	}

	public String getAlphabet() {
		return alphabet;
	}

	public static ManipulationAlphabet getInstance() {
		return instance;
	}
	
	
	

}

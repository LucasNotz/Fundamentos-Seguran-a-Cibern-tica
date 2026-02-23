package Negocio;

public class Criptografia {
	
	//Método de criptografar
	public static String encriptar(String texto, String chave) {
	
		//String a ser retornado
		String cifra = "";
		
		//transformar o texto em cifra
		for (int i = 0; i < texto.length(); i++) {
			
			//letras tratadas como ints para recorrer ao ascii
			int letraOriginal = texto.charAt(i);
			
			//resto do modulo dita qual letra da chave a ser usada
			int letraChave = chave.charAt(i % chave.length());
			
			int letraCifra = (letraChave ^ letraOriginal);
			
			cifra += ((char) letraCifra);
		}
		
		return cifra;
	}
	
	//Método de decriptografar
	public static String desencriptar(String cifra, String chave) {
	
		//String a ser retornado
		String texto = "";
		
		//transformar o texto em cifra
		for (int i = 0; i < cifra.length(); i++) {
			
			//letras tratadas como ints para recorrer ao ascii
			int letraCifra = cifra.charAt(i);
			
			//resto do modulo dita qual letra da chave a ser usada
			int letraChave = chave.charAt(i % chave.length());
			
			int letraOriginal = (letraChave ^ letraCifra);
			
			texto += ((char) letraOriginal);
		}
		
		return texto;
	}
}

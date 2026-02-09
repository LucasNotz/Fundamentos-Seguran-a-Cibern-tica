import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CriptografiaROT13 {
	private static String encrypt(String text) {
		String criptograma = "";
		for (int i = 0; i < text.length(); i++) {
			int caracterOriginal = text.charAt(i);
			int caracterCifrado = 0;
			//a - z
			if ((caracterOriginal >= 97) && (caracterOriginal <= 122)) {
				caracterCifrado = caracterOriginal + 13;
				if (caracterCifrado > 122) {
					caracterCifrado -= 26;
				}
			}
			criptograma += ((char) caracterCifrado);
		}
		return criptograma;
	}
	
	public static void main(String[] args) {
		//Declaracao
		BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
		
		String texto = "";
		String criptograma = "";
		
		//Entrada
		try {
			System.out.print("Digite um texto: ");
			texto = leitor.readLine();
		} catch (Exception e) {}
		
		//Processamento
		criptograma = CriptografiaROT13.encrypt(texto);
		
		//Saida
		System.out.println("Texto: " + texto);
		System.out.println("Criptograma: " + criptograma);
	}
}

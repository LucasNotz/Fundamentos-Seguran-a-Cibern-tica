import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CriptografiaVigenere {
	/* Lógica da criptografia de vigenere
	 * 
	 * Mensagem original -> Numero tabela ascii -> binário (8 bits)
	 * Chave -> Numero da tabela ascii -> binário (8 bits)
	 * 
	 * Verifica quantas vezes a chave "cabe na original"
	 * Faz a operacao de XOR (^) de cada valor binário respectivo
	 * 
	 */
	
	private static String ecrypt(String texto, String chave) {
		String cifra = "";
		
		for (int i = 0; i < texto.length(); i++) {
			int letraOriginal = texto.charAt(i);
			//resto da divisao indica qual os valores respectivos
			int letraChave = chave.charAt(i % chave.length());
			int letraCifra = (letraOriginal ^ letraChave);
			
			//alinhamento = 11111111 and qualquer coisa
			String temp = Integer.toHexString(0XFF & letraCifra);
			//Pode sair resultado com so um valor, ao inves de dois, 
			//isso acerta a possivel diferenca em comprimento
			if (temp.length() == 1) temp = ("0" + temp);
			
			cifra += temp;
		}
		
		//Resultado é hexadecimal
		return cifra;
	}
	
	private static String decrypt(String cifra, String chave) {
		String texto = "";
		
		//hexadecimal é lido de 2 a 2 
		for (int i = 0; i < cifra.length(); i+=2) {
			int letraCifra = Integer.parseInt(cifra.substring(i, i + 2), 16);
			//resto da divisao indica qual os valores respectivos, 
			//dividido por dois por ser hexadecimal
			int letraChave = chave.charAt((i/2) % chave.length());
			int letraOriginal = (letraCifra ^ letraChave);
			
			texto += ((char) letraOriginal);
		}
		
		
		return texto;
	}
	
	public static void main(String[] args) {
		BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
		
		String check = "";
		String texto = "";
		String chave = "";
		String cifra = "";
		
		try {
			
			System.out.println("1: encriptar, 2: decriptar");
			check = leitor.readLine();
			
			if (check.equals("1")) {
				System.out.println("Insira o seu texto: ");
				texto = leitor.readLine();
				
				System.out.println("Insira sua chave: ");
				chave = leitor.readLine();
				
				cifra = CriptografiaVigenere.ecrypt(texto, chave);
				
				System.out.println("Seu criptograma é: " + cifra);
				
			} else {
				System.out.println("Insira sua cifra: ");
				cifra = leitor.readLine();
				
				System.out.println("Insira sua chave: ");
				chave = leitor.readLine();
				
				texto = CriptografiaVigenere.decrypt(cifra, chave);
				
				System.out.println("Seu texto decriptado é: " + texto);
			}
			

			
			

			
		} catch (Exception erro) {
			System.out.println(erro);
		}
		
	}
	
}

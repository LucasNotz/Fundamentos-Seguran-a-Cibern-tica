import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class TripleDES {
	private static String encriptar(String texto, String chave) throws Exception {
		//inicializacao da cifra
		Cipher objCifra = Cipher.getInstance("TripleDES");
		
		//preparar chave (especificacao da chave
		SecretKey objChave = new SecretKeySpec(chave.getBytes("UTF-8"), "TripleDES");
		
		//inicializacao da cifra
		objCifra.init(Cipher.ENCRYPT_MODE, objChave);
		
		//encriptar
		byte[] criptograma = objCifra.doFinal(texto.getBytes("UTF-8"));
		
		//codificacao do resultado
		return Base64.getEncoder().encodeToString(criptograma);
	}
	
	private static String decriptar(String criptograma, String chave) throws Exception {
		//inicializacao da cifra
		Cipher objCifra = Cipher.getInstance("TripleDES");
		
		//preparar chave (especificacao da chave
		SecretKey objChave = new SecretKeySpec(chave.getBytes("UTF-8"), "TripleDES");
		
		//inicializacao da cifra
		objCifra.init(Cipher.DECRYPT_MODE, objChave);
		
		//decodificar base64 e decriptar
		byte[] texto = objCifra.doFinal(Base64.getDecoder().decode(criptograma));
		
		//devolver como string
		return new String(texto, "UTF-8");
	}
	
	public static void main(String[] args) {
		BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.println("Digite o texto (3DES): ");
			String texto = leitor.readLine();
			
			System.out.println("Digite uma chave: ");
			String chave = leitor.readLine();
			
			String criptograma = encriptar(texto, chave);
			System.out.println(criptograma);
			
			System.out.println(decriptar(criptograma, chave));
			
		} catch (Exception erro) {
			System.out.println(erro);
		}
	}
}

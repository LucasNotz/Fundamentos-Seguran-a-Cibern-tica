import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	private static String encriptar(String texto, String chave) throws Exception {
		
		//inicializar  cifra
		Cipher objCifra = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		//especificar a chave
		SecretKey objChave = new SecretKeySpec(chave.getBytes("UTF-8"), "AES");
		
		//especificacao do vetor de inicializacao
		IvParameterSpec objIv = new IvParameterSpec("0123456789123456".getBytes());
		
		//inicializar a cifra
		objCifra.init(Cipher.ENCRYPT_MODE, objChave, objIv);
		
		//encriptar
		byte[] criptograma = objCifra.doFinal(texto.getBytes("UTF-8"));
		
		//devolver na base 64
		return Base64.getEncoder().encodeToString(criptograma);
	}
	
	private static String decriptar(String criptograma, String chave) throws Exception {
		
		//inicializar  cifra
		Cipher objCifra = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		//especificar a chave
		SecretKey objChave = new SecretKeySpec(chave.getBytes("UTF-8"), "AES");
		
		//especificacao do vetor de inicializacao
		IvParameterSpec objIv = new IvParameterSpec("0123456789123456".getBytes());
		
		//inicializar a cifra
		objCifra.init(Cipher.DECRYPT_MODE, objChave, objIv);

		//decritpar 
		byte[] texto = objCifra.doFinal(Base64.getDecoder().decode(criptograma));
		
		//
		return new String(texto, "UTF-8");
	}
	
	public static void main(String[] args) {
		BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.println("Digite o texto (AES): ");
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

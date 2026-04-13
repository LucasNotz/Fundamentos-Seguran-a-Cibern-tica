import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class RSA {
	private static KeyPair gerarParDeChaves() throws Exception {
		KeyPairGenerator objGerador = KeyPairGenerator.getInstance("RSA");
		objGerador.initialize(4096); //qtd bits da chave
		return KeyPairGenerator.getInstance("RSA").genKeyPair();
	}
	
	private static String encriptarRSA(byte[] chave, KeyPair parDeChaves) throws Exception {
		Cipher objCifra = Cipher.getInstance("RSA");
		objCifra.init(Cipher.ENCRYPT_MODE, parDeChaves.getPublic());
		byte[] cifra = objCifra.doFinal(chave);
		return Base64.getEncoder().encodeToString(cifra);
	}
	
	private static byte[] decriptarRSA(String cifra, KeyPair parDeChaves) throws Exception {
		Cipher objCifra = Cipher.getInstance("RSA");
		objCifra.init(Cipher.DECRYPT_MODE, parDeChaves.getPrivate());
		return objCifra.doFinal(Base64.getDecoder().decode(cifra));
	}
	
	private static String encriptarAES(String texto, byte[] chave) throws Exception {
		Cipher objCifra = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKey objChave = new SecretKeySpec(chave, "AES");
		IvParameterSpec objIv = new IvParameterSpec("0123456789101112".getBytes());
		objCifra.init(Cipher.ENCRYPT_MODE, objChave, objIv);
		byte[] cifra = objCifra.doFinal(texto.getBytes("UTF-8"));
		return Base64.getEncoder().encodeToString(cifra);
	}
	
	private static String decriptarAES(String cifra, byte[] chave) throws Exception {
		Cipher objCifra = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKey objChave = new SecretKeySpec(chave, "AES");
		IvParameterSpec objIv = new IvParameterSpec("0123456789101112".getBytes());
		objCifra.init(Cipher.DECRYPT_MODE, objChave, objIv);
		byte[] texto = objCifra.doFinal(Base64.getDecoder().decode(cifra));
		return new String(texto, "UTF-8");
	}
	
	private static byte[] calcularSHA256(byte[] chave) throws Exception {
		return MessageDigest.getInstance("SHA-256").digest();
	}
	
	private static byte[] randomizarChaveSimetrica() throws Exception {
		byte[] chave = new byte[100];
		for (int i = 0; i < chave.length; i++) {
			chave[i] = ((byte) (256 * Math.random()));
		}
		return chave;
	}
	
	public static void main(String[] args) {
		BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.println("Digite um texto: ");
			String texto = leitor.readLine();
			
			//Gerador do par de chaves do RSA
			KeyPair parDeChavesRSA = gerarParDeChaves();
				
			//Construção da chave AES
			byte[] chaveSimetricaAES = randomizarChaveSimetrica();
			
			//Encriptar chave aes com rsa
			System.out.println(encriptarRSA(chaveSimetricaAES, parDeChavesRSA));
			
			//Criptograma
			System.out.println(encriptarAES(texto, calcularSHA256(chaveSimetricaAES)));
			
			System.out.println("Digite a cifra da chave: ");
			chaveSimetricaAES = decriptarRSA(leitor.readLine(), parDeChavesRSA);
			
			System.out.println("Digite a cifra do texto: ");
			String cifra = leitor.readLine();
			
			texto = decriptarAES(cifra, calcularSHA256(chaveSimetricaAES));
			System.out.println(texto);
			
		} catch (Exception erro) {
			System.out.println(erro);
		}
	}
 }

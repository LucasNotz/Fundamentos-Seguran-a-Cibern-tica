import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class DiffieHellman {
	private static BigInteger primo = new BigInteger("102031405123416071809152453627382938465749676859789");
	private static BigInteger base = new BigInteger("1234567890123456789012345");
	
	public static void main(String[] args) {
		BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
		BigInteger minhaChaveSecreta = null;
		BigInteger minhaChavePública = null;
		BigInteger chavePublicaDoParceiro = null;
		BigInteger nossaChaveCompartilhada = null;
		
		try {
			System.out.println("Escolha sua chave secreta ");
			minhaChaveSecreta = new BigInteger(leitor.readLine());
			minhaChavePública = base.modPow(minhaChaveSecreta, primo);
			System.out.println(minhaChavePública);
			System.out.println("Digite a chave públic do parceiro ");
			chavePublicaDoParceiro = new BigInteger(leitor.readLine());
			nossaChaveCompartilhada = chavePublicaDoParceiro.modPow(minhaChaveSecreta, primo);
			System.out.println(nossaChaveCompartilhada);
					
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

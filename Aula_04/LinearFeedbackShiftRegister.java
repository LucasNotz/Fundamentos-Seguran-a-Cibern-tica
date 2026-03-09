import java.io.BufferedReader;
import java.io.InputStreamReader;

/* para aplicar essa criptografia basta usar esse processo para a chave
 * e usar o vigenere
 */


public class LinearFeedbackShiftRegister {
	// boolean[] registrador vai ter 32 bits
	private static void inicializar(boolean[] registrador) {
		BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
		
		String chave = "";
		
		try {
			System.out.println("Digite uma senha de quatro letras: ");
			chave = leitor.readLine();
			
			for (int i = 0; i < 4; i++) { //pegar uma letra de cada vez (se for menos => erro)
				
				int letraASCII = chave.charAt(i); //convertido para valor decimal da tabela ascii
				
				String letraBinario = Integer.toBinaryString(letraASCII); //transforma em binario
				
				//Ajustar tamanho de binarios convertidos menores (00110011 -> 110011 (conversao do decimal menor remove os zeros) -> 00110011)
				for (int j = 0; j < (8 - letraBinario.length()); j++) {
					letraBinario = ("0" + letraBinario);
				}
				
				//colocar dentro do registrador
				for (int j = 0; j < 8; j++) {
					registrador[j + (8 * i)] = (letraBinario.charAt(j) == '1'); // se ==1 -> 1 se !=1 -> 0, transformar char em boolean
				}
			}
		} catch (Exception erro) {
			System.out.println(erro);
		}
	}
	
	//deslocar tipo 1 ou tipo 2 do OTP
	private static boolean realocar(boolean[] registrador, boolean tipo) { //passagem de parametro como referencia -> muda o valor do registrador
		boolean retorno = registrador[0];
		
		boolean novoBit = retorno;
		
		if(!tipo) { //tipo = 0 
			//tipo 1
			novoBit = (novoBit ^ registrador[31] ^ registrador[6] ^ registrador[4] ^ registrador[2] ^ registrador[1] ^ registrador[0]);
		} else {
			//tipo 2
			novoBit = (novoBit ^ registrador[31] ^ registrador[6] ^ registrador[5] ^ registrador[1]);
		}
		
		// loop de girar
		for (int i = 0; i < 31; i++) { // deslocamento somente ate o penultimo
			registrador[i] = registrador[i + 1];
		}
		registrador[31] = novoBit; //inserir novo bit
		
		return retorno;
	}
	
	public static void main(String[] args) {
		boolean[] cabeca = new boolean[32];
		boolean[] registrador0 = new boolean[32];
		boolean[] registrador1 = new boolean[32];
		
		//senha de 12 digitos (4 de cada)
		inicializar(cabeca);
		inicializar(registrador0);
		inicializar(registrador1);
		
		String letra = "";
		
		while (true) {
			boolean bit0 = registrador0[0];
			boolean bit1 = registrador1[1];
			
			if (! realocar(cabeca, true)) {
				bit0 = realocar(registrador0, bit0);
			} else {
				bit1 = realocar(registrador1, bit1);
			}
			
			letra += ((bit0 ^ bit1) ? "1" : "0");
			
			if (letra.length() == 8 ) {
				System.out.print((char) Integer.parseInt(letra, 2)); //bit (int) -> char
				letra = "";
			}
		}
	}
}

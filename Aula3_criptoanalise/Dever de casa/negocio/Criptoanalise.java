package negocio;

import java.lang.reflect.Array;
import java.util.List;

public class Criptoanalise {
	
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
	public static String decriptar(String cifra, String chave) {
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

	public static String[] analisar(String criptograma) {

		String chave[] = new String[30];
		
		//separar em grupos baseado no tamanho da chave
		for (int tamanhoChave = 1; tamanhoChave <= 20; tamanhoChave++) {
			
			String tempChave = "";
			System.out.println("=============================");
			System.out.println("testando senha de tamanho " + tamanhoChave );
			
			
			
			//separar em conjuntos
			for (int numeroConjunto = 0; numeroConjunto < tamanhoChave; numeroConjunto++) {
				
				//declarar variaveis
				int[] vetorOcorrencia = new int[256]; //256 = tabela ascii e array para contar quantas ocorrencias de cada
				int maiorQtd = 0; //quantas vezes a maior ocorre
				int letraMaior = 0; //qual aparece mais vezes
				
				//ler por hexadecimal
				for (int i = 0; i < criptograma.length(); i+=2) {
					
					//separar em uma letra e transformando de hexadecimal para int (ascii)
					int letra =Integer.parseInt(criptograma.substring(i, i+2), 16);
					//System.out.println(letra);
					
					// i/ 2 porque é hexadecimal, resto pelo tamanho da chave -> escolhendo o caracter do conjunto correto
					// com base no resto da divisao
					if (((i / 2) % tamanhoChave) == numeroConjunto) {
						
						//incluir letra utilizado dentro dos array dos possiveis utilizados
						vetorOcorrencia[letra]++;
						
						//verificar se existem mais ocorrencias no utilizado (array do ascii) e verificar se é a maior
						if (vetorOcorrencia[letra] > maiorQtd) {
							maiorQtd = vetorOcorrencia[letra];
							letraMaior = letra;
						}
					}
				}
				//ascii 32 -> caracter mais commum
				int tempLetra = (letraMaior ^ 32);
				
				System.out.print((char) tempLetra);
				System.out.print("(" + tempLetra + ")");
				if (tempLetra < 32 || tempLetra == 127 || tempLetra > 140) {
					tempChave += '*';
				} else {
					tempChave += ((char) tempLetra);					
				}
	
				
			}
			System.out.println();
			//lembra que falta corrigir as letras fora do "alcance"
			//System.out.println(Criptoanalise.desencriptar(criptograma, tempChave));
			chave[tamanhoChave] = tempChave;
			
		}
		System.out.println("end");
		return chave;
	}

}

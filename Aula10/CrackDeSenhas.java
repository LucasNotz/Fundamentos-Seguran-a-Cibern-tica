import java.security.MessageDigest;
import java.util.HashMap;

public class CrackDeSenhas {
	private static HashMap<String, String> mapaDeSenhas = new HashMap<String, String>();
	
	private static void inicializar() {
		mapaDeSenhas.put("fc5669b52ce4e283ad1d5d182de88ff9faec6672bace84ac2ce4c083f54fe2bc", "kali");
		mapaDeSenhas.put("353b31cbc5fe9caf53063936395072f9369076a7d0c8ee534f834cb2693dd6e2", "junior");
		mapaDeSenhas.put("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92", "mane");
		mapaDeSenhas.put("d58d736c7a967fb5f307951932734f8b0594725faa5011dbb66a8c538e635fb6", "fulano");
		mapaDeSenhas.put("b7e94be513e96e8c45cd23d162275e5a12ebde9100a425c4ebcdd7fa4dcd897c", "beltrano");
		mapaDeSenhas.put("280d44ab1e9f79b5cce2dd4f58f5fe91f0fbacdac9f7447dffc318ceb79f2d02", "cicrano");
		mapaDeSenhas.put("0c08a9536b5dd78713f440acb930872fd69f7a71ad0cf9cdedc9628ddf9ac3d7", "gabriel");
		mapaDeSenhas.put("65e84be33532fb784c48129675f9eff3a682b27168c0ea744b2cf58ee02337c5", "joao");
		mapaDeSenhas.put("26df939ee38cc162bb98f4eb5a111fdb270db6bd1dc645e98871ac2d8449bd6c", "humberto");
		mapaDeSenhas.put("d04a0747e946c6233ab5a91ceb3a59624cdf14d7fd05e9386d22580ec980455e", "maria");
		mapaDeSenhas.put("756356fbfa52ca1d11812575fcb9238edb0cecd44785f2c73d4604c56954d0af", "fernanda");
		mapaDeSenhas.put("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", "mario");
		mapaDeSenhas.put("d75d2785d90cab90245dc9e22a82c1a048673c4a2c54fa1754e9085f4f01d687", "sunda");
		mapaDeSenhas.put("e79c15d596b9b9c1334150622ce1ecb016c61e2bf05b7864296a29f9e62ed863", "zulu");
	}

	public static String calcularHash(String texto) throws Exception {
		MessageDigest objHash = MessageDigest.getInstance("SHA-256");
		byte[] resumo = objHash.digest(texto.getBytes("UTF-8"));
		String hexadecimal = "";
		for (int i = 0 ; i < resumo.length ; i++) {
			String temp = Integer.toHexString(0xFF & resumo[i]);
			if (temp.length() == 1) temp = ("0" + temp);
			hexadecimal += temp;
		}
		return hexadecimal;
	}
	
	
	private static void ataqueNumerico() throws Exception {
		for (int i = 0 ; i < 1000000000 ; i++) {
			if (mapaDeSenhas.containsKey(calcularHash("" + i))) {
				System.out.println("Crackei PORRA !!!!" +
								   mapaDeSenhas.get(calcularHash("" + i)) + "\t" + i);
			}
		}
	}
	
	private static void ataqueAlfabeticoMinusculo() throws Exception {
		for (int i = 97 ; i < 123 ; i++) {
			for (int j = 97 ; j < 123 ; j++) {
				for (int k = 97 ; k < 123 ; k++) {
					for (int l = 97 ; l < 123 ; l++) {
						String senha = "" + ((char) i) +
											((char) j) + 
											((char) k) +
											((char) l);
						if (mapaDeSenhas.containsKey(calcularHash(senha))) {
							System.out.println("Crackei PORRA !!!!" +
									   mapaDeSenhas.get(calcularHash(senha)) + "\t" + senha);
							
						}
					}
				}
			}
		}
	}
	
	private static void ataqueAlfabeticoMaiusculo() throws Exception {
		for (int i = 65 ; i < 91 ; i++) {
			for (int j = 65 ; j < 91 ; j++) {
				for (int k = 65 ; k < 91 ; k++) {
					for (int l = 65 ; l < 91 ; l++) {
						for (int m = 65 ; m < 91 ; m++) {
							for (int n = 65 ; n < 91 ; n++) {
								String senha = "" + ((char) i) +
													((char) j) + 
													((char) k) +
													((char) l) +
													((char) m) +
													((char) n);
								if (mapaDeSenhas.containsKey(calcularHash(senha))) {
									System.out.println("Crackei PORRA !!!!" +
											mapaDeSenhas.get(calcularHash(senha)) + "\t" + senha);
									
								}
							}
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			inicializar();
			ataqueAlfabeticoMinusculo();
			//ataqueAlfabeticoMaiusculo();
			ataqueNumerico();
		} catch (Exception e) {}
	}
}

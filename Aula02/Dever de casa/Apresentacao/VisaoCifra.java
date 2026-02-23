package Apresentacao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Negocio.Criptografia;

public class VisaoCifra extends JFrame{
	
	//Second
	private JLabel encriptar = new JLabel("Encriptar");
	private JLabel decriptar = new JLabel("Decriptar");
	
	//Third
	private JLabel texto = new JLabel("Texto de entrada:");
	private JLabel cifra = new JLabel("Cifra de entrada:");
	private JTextArea textoField = new JTextArea();
	private JTextArea cifraField = new JTextArea();
	
	//Forth
	private JLabel chaveTexto = new JLabel("Chave de encriptação:");
	private JLabel chaveCifra = new JLabel("Chave de desencriptação:");
	private JTextArea chaveTextoField = new JTextArea();
	private JTextArea chaveCifraField = new JTextArea();
	
	//Fifth
	
		//Left
	private JLabel resultadoTexto = new JLabel("Cifra resultante");
	private JLabel resultadoCifra = new JLabel("Texto resultante");
	
		//Middle 
	private JTextArea resultadoTextoArea = new JTextArea();
	private JTextArea resultadoCifraArea = new JTextArea();
	
		//Right
	private JButton encriptarButton = new JButton("Executar");
	private JButton desencriptarButton = new JButton("Executar");
	
	public static void main(String[] args) {
		new VisaoCifra().setVisible(true);
	}
	
	public VisaoCifra() {
		setTitle("Cifra de Vigerne");
		setSize(700,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		
			//Lado encriptar
		encriptar.setBounds(100,20,300,40);
		encriptar.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 20));
		add(encriptar);
		
		//Inserir texto
		texto.setBounds(100,60,300,40);
		texto.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
		add(texto);
		
		textoField.setBounds(100,100,200, 80);
		textoField.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 12));
		textoField.setLineWrap(true);
		add(textoField);
		
		//Inserir chave
		chaveTexto.setBounds(100,200,300,30);
		chaveTexto.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
		add(chaveTexto);
		
		chaveTextoField.setBounds(100,230,200,30);
		chaveTextoField.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 12));
		chaveTextoField.setLineWrap(true);
		add(chaveTextoField);
		
		//Resultado 
		resultadoTexto.setBounds(100, 280, 300, 20);
		resultadoTexto.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
		add(resultadoTexto);
		
		encriptarButton.setBounds(100, 310, 150, 20);
		encriptarButton.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
		add(encriptarButton);
		encriptarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resultadoTextoArea.setText(Criptografia.desencriptar(textoField.getText(), chaveTextoField.getText()));
				
			}
		});
		
		resultadoTextoArea.setBounds(100, 340, 200, 80);
		resultadoTextoArea.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
		add(resultadoTextoArea);
		
			//Lado decriptar
		decriptar.setBounds(400,20,300,40);
		decriptar.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 20));
		add(decriptar);
		
		//Inserir cifra
		cifra.setBounds(400,60,300,40);
		cifra.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
		add(cifra);
		
		cifraField.setBounds(400,100,200,80);
		cifraField.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 12));
		cifraField.setLineWrap(true);
		add(cifraField);
		
		//Inserir chave
		chaveCifra.setBounds(400,200,300,30);
		chaveCifra.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
		add(chaveCifra);
		
		chaveCifraField.setBounds(400,230,200,30);
		chaveCifraField.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 12));
		chaveCifraField.setLineWrap(true);
		add(chaveCifraField);
		
		//Resultado 
		resultadoCifra.setBounds(400, 280, 300, 20);
		resultadoCifra.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
		add(resultadoCifra);
		
		desencriptarButton.setBounds(400, 310, 150, 20);
		desencriptarButton.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
		add(desencriptarButton);
		desencriptarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resultadoCifraArea.setText(Criptografia.desencriptar(cifraField.getText(), chaveCifraField.getText()));
				
			}
		});
		
		resultadoCifraArea.setBounds(400, 340, 200, 80);
		resultadoCifraArea.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
		add(resultadoCifraArea);
	}
}

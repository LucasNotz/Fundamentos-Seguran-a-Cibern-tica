package visual;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import negocio.Criptoanalise;

public class VisualCriptoanalise extends JFrame{
	
	//Second
	private JLabel encriptar = new JLabel("Criptoanalise");
	private JLabel decriptar = new JLabel("Decriptar");
	
	//Third
	private JLabel analiseLabel = new JLabel("Cifra a ser analisada:");
	private JLabel cifra = new JLabel("Cifra de entrada:");
	private JTextArea analiseField = new JTextArea();
	private JTextArea cifraField = new JTextArea();
	
	//Forth
	private JLabel chaveCifra = new JLabel("Chave de desencriptação:");
	private JTextArea chaveCifraField = new JTextArea();
	
	//Fifth
	
		//Left
	private JLabel resultadoAnaliseLabel = new JLabel("Chaves possiveis");
	private JLabel resultadoCifra = new JLabel("Texto resultante");
	private JLabel consideracao = new JLabel("* significa que o ascii codigo era > 140 (JList não imprime)");
	
		//Middle 
	private DefaultListModel<String> dlm = new DefaultListModel<String>();
	private JList<String> resultadoAnaliseArea = new JList<String>();
	//private JTextArea resultadoAnaliseAreaPrioridade = new JTextArea();
	private JScrollPane resultadoAnaliseAreaScroll = new JScrollPane(resultadoAnaliseArea);
	//private JScrollPane resultadoAnaliseAreaPrioridadeScroll = new JScrollPane(resultadoAnaliseAreaPrioridade);

	private JTextArea resultadoCifraArea = new JTextArea();
	private JScrollPane resultadoCifraAreaScroll = new JScrollPane(resultadoCifraArea);
	
		//Right
	private JButton analisarButton = new JButton("Analisar");
	private JButton desencriptarButton = new JButton("Executar");
	
	public static void main(String[] args) {
		new VisualCriptoanalise().setVisible(true);
	}
	
	public VisualCriptoanalise() {
		setTitle("Analise de Vigerne");
		setSize(1200,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		
			//Lado analisar
		encriptar.setBounds(100,20,300,40);
		encriptar.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 20));
		add(encriptar);
		
		//Inserir texto
		analiseLabel.setBounds(100,60,300,40);
		analiseLabel.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
		add(analiseLabel);
		
		analiseField.setBounds(100,100,200, 80);
		analiseField.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 12));
		analiseField.setLineWrap(true);
		add(analiseField);
		

		
		//Resultado 
		consideracao.setBounds(50,530,400,20);
		add(consideracao);
		resultadoAnaliseLabel.setBounds(420,40,300,40);
		resultadoAnaliseLabel.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
		add(resultadoAnaliseLabel);
		
		analisarButton.setBounds(120, 200, 150, 20);
		analisarButton.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
		add(analisarButton);
		analisarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] temp = Criptoanalise.analisar(analiseField.getText());
				for (int times = 0; times < 30; times ++) { 
					dlm.addElement(temp[times]);
				}
				resultadoAnaliseArea.setModel(dlm);	
				cifraField.setText(analiseField.getText());
			}
		});
		
		resultadoAnaliseAreaScroll.setBounds(350, 100, 300, 400);
		add(resultadoAnaliseAreaScroll);
		resultadoAnaliseArea.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int selected = resultadoAnaliseArea.getSelectedIndex();
				chaveCifraField.setText(dlm.getElementAt(selected));
			}
		});
			//Lado decriptar
		decriptar.setBounds(700,20,300,40);
		decriptar.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 20));
		add(decriptar);
		
		//Inserir cifra
		cifra.setBounds(700,60,300,40);
		cifra.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
		add(cifra);
		
		cifraField.setBounds(700,100,200,80);
		cifraField.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 12));
		cifraField.setLineWrap(true);
		add(cifraField);
		
		//Inserir chave
		chaveCifra.setBounds(700,200,300,30);
		chaveCifra.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
		add(chaveCifra);
		
		chaveCifraField.setBounds(700,230,200,30);
		chaveCifraField.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 12));
		chaveCifraField.setLineWrap(true);
		add(chaveCifraField);
		
		//Resultado 
		resultadoCifra.setBounds(700, 280, 300, 20);
		resultadoCifra.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
		add(resultadoCifra);
		
		desencriptarButton.setBounds(700, 310, 150, 20);
		desencriptarButton.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
		add(desencriptarButton);
		desencriptarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resultadoCifraArea.setText(Criptoanalise.decriptar(cifraField.getText(), chaveCifraField.getText()));
				
			}
		});
		
		resultadoCifraAreaScroll.setBounds(700, 340, 400, 150);
		//resultadoCifraAreaScoll.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
		resultadoCifraArea.setLineWrap(true);
		add(resultadoCifraAreaScroll);
	}
}

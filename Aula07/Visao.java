import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Visao extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JTextArea txtTexto = new JTextArea();
	private JScrollPane jspTexto = new JScrollPane(txtTexto);
	
	private JTextField txtResumo = new JTextField();
	
	private JButton btnCalcular = new JButton("Calcular");

	public static void main(String[] args) {
		new Visao().setVisible(true);
	}
	
	public Visao() {
		setTitle("Cálculo do Resumo Unidirecional SHA-256");
		setSize(500,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		
		jspTexto.setBounds(10,10, 465, 280);
		add(jspTexto);
		txtTexto.setLineWrap(true);
		
		txtResumo.setBounds(10,300,465,20);
		add(txtResumo);
		
		btnCalcular.setBounds(10,330,465,20);
		add(btnCalcular);
		
		btnCalcular.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					txtResumo.setText(SHA256.calcularHash(txtTexto.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}

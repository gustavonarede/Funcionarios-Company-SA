package sistema.telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import sistema.Navegador;

@SuppressWarnings("serial")
public class Login extends JPanel {

	private static final String ADMIN = "admin";
	
	JLabel labelUsuario;
	JTextField campoUsuario;
	JLabel labelSenha;
	JPasswordField campoSenha;
	JButton botaoEntrar;

	public Login() {

		criarComponentes();
		criarEventos();
	}

	private void criarComponentes() {
		setLayout(null);

		JLabel labelTitulo = new JLabel("Seja bem vindo ao sistema da companhia Sa", JLabel.CENTER);
		labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 18));

		labelUsuario = new JLabel("Usuario", JLabel.LEFT);
		campoUsuario = new JTextField();
		labelSenha = new JLabel("Senha", JLabel.LEFT);
		campoSenha = new JPasswordField();
		botaoEntrar = new JButton("Entrar");

		labelTitulo.setBounds(20, 100, 660, 40);
		labelUsuario.setBounds(250, 220, 200, 20);
		campoUsuario.setBounds(250, 240, 200, 40);
		labelSenha.setBounds(250, 300, 200, 40);
		campoSenha.setBounds(250, 300, 200, 40);
		botaoEntrar.setBounds(250, 350, 200, 40);

		add(labelTitulo);
		add(labelUsuario);
		add(campoUsuario);
		add(labelSenha);
		add(campoSenha);
		add(botaoEntrar);
		setVisible(true);

	}

	private void criarEventos() {
		botaoEntrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String usuario = campoUsuario.getText();
				String senha = String.valueOf(campoSenha.getPassword());
				System.out.println("senha:"  + senha);
				if (ADMIN.equals(usuario) && ADMIN.equals(senha)) {
					Navegador.inicio();
				} else {
					JOptionPane.showMessageDialog(null, "Acesso não permitido");
				}

			}
		});
	}

}
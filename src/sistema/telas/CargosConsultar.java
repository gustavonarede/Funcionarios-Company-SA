package sistema.telas;

import static sistema.Navegador.cargosEditar;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sistema.BancoDeDados;
import sistema.Navegador;
import sistema.entidades.Cargos;

public class CargosConsultar extends JPanel {

	Cargos cargoAtual;
	JLabel labelTitulo, labelCargo;
	JTextField campoCargo;
	JButton botaoPesquisar, botaoEditar, botaoExcluir;
	DefaultListModel<Cargos>listasCargosModelo = new DefaultListModel();
	JList<Cargos>listaCargos;
	public CargosConsultar() {

		criarComponentes();
		criarEventos();
	}
	private void criarComponentes() {
		setLayout(null);
		
		labelTitulo = new JLabel("Consulta de Cargos", JLabel.CENTER);
		labelTitulo.setFont(new Font( labelTitulo.getFont().getName(), Font.PLAIN, 20));
		labelCargo =  new JLabel("Nome do Cargo",JLabel.LEFT);
		campoCargo = new JTextField();
		botaoPesquisar = new JButton("Pesquisar Cargo");
		botaoEditar = new JButton("Editar Cargo");
		botaoEditar.setEnabled(false);
		botaoExcluir = new JButton("Excluir Cargo");
		botaoExcluir.setEnabled(false);
		listasCargosModelo = new DefaultListModel();
		listaCargos = new JList();
		listaCargos.setModel(listasCargosModelo);
		listaCargos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		
		labelTitulo.setBounds(20, 20, 660, 40);
		labelCargo.setBounds(150, 120, 400, 20);
		campoCargo.setBounds(150, 140, 400 ,40);
		botaoPesquisar.setBounds(560, 140, 130, 40);
		listaCargos.setBounds(150,200,400,240);
		botaoEditar.setBounds(560, 360, 130, 40);
		botaoExcluir.setBounds(560, 400, 130, 40);
		
		add(labelTitulo);
		add(labelCargo);
		add(campoCargo);
		add(listaCargos);
		add(botaoPesquisar);
		add(botaoEditar);
		add(botaoExcluir);
		setVisible(true);
		
	}
private void criarEventos() {
	botaoPesquisar.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			sqlPesquisarCargos(campoCargo.getText());
		}
		
		
	});
	botaoEditar.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Navegador.cargosEditar(cargoAtual);
			
		}
	});
	botaoExcluir.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			sqlDeletarCargo();
			
		}
	});
	listaCargos.addListSelectionListener(new ListSelectionListener() {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			cargoAtual = listaCargos.getSelectedValue();
			if(cargoAtual == null) {
				botaoEditar.setEnabled(false);
				botaoExcluir.setEnabled(false);
			}else {
				botaoEditar.setEnabled(true);
				botaoExcluir.setEnabled(true);;
			}
		}
	});
}
private void sqlPesquisarCargos(String nome) {
	//conexao
	Connection conexao;
	//Instrução SQL
	Statement instrucaoSQL;
	//resultados
	ResultSet resultados;
	
	try {
		//conectando ao banco
		conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao,BancoDeDados.usuario,BancoDeDados.senha);
		//criando a instruçãoSQL
		instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		resultados = instrucaoSQL.executeQuery("SELECT * FROM cargos WHERE nome like '%"+nome+"%'");
		listasCargosModelo.clear();
		while(resultados.next()) {
			Cargos cargo = new Cargos();
			cargo.setId(resultados.getInt("id"));
			cargo.setNome(resultados.getString("nome"));
			
			listasCargosModelo.addElement(cargo);
		}
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar os cargos");
		Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE,null, e);
	}
	
}
private void sqlDeletarCargo() {
	int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o cargo "+cargoAtual.getNome()+"?","Excluir",JOptionPane.YES_NO_OPTION);
	if(confirmacao == JOptionPane.YES_NO_OPTION) {
		//conexao
		Connection conexao;
		//instrucao sQL
		Statement instrucaoSQL;
		//resultados
		ResultSet resultados;
		 try {
			//conectando ao banco de dados
			 conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao,BancoDeDados.usuario,BancoDeDados.senha);
			 //criando a instrução SQL
			 instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			 instrucaoSQL.executeUpdate("DELETE cargos WHERE id="+cargoAtual.getId()+"");
			 JOptionPane.showMessageDialog(null, "Cargo deletado com sucesso.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir um cargo");
			Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE,null,e);
		}
		
	}
}
}

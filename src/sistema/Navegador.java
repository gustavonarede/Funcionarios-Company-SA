package sistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import sistema.entidades.Cargos;
import sistema.entidades.Funcionarios;
import sistema.telas.CargosConsultar;
import sistema.telas.CargosEditar;
import sistema.telas.CargosInserir;
import sistema.telas.FuncionariosEditar;
import sistema.telas.Inicio;
import sistema.telas.Login;

public class Navegador {
//menu
	private static boolean menuConstruido;
	private static boolean menuHabilitado;
	private static JMenuBar menuBar;
	private static JMenu menuArquivo, menuFuncionarios, menuCargos, menuRelatorios;
	private static JMenuItem miSair, miFuncionariosConsultar,miFuncionariosCadastrar,
	miCargosConsultar;
	private static JMenuItem miCargosCadastrar, miRelatoriosCargos, miRelatoriosSalarios;
	
	public static void login() {
		Sistema.tela = new Login();
		Sistema.frame.setTitle("Funcionarios Company SA");
		
		Navegador.atualizarTela();
		
				
	}
	public static void inicio() {
		Sistema.tela = new Inicio();
		Sistema.frame.setTitle("Funcionarios Company SA");
		Navegador.atualizarTela();
		
	}
	public static void cargosCadastrar() {
		Sistema.tela = new CargosInserir();
		Sistema.frame.setTitle("Funcionarios Company SA - cadastar cargos");
		Navegador.atualizarTela();
	}
	public static void cargosConsultar() {
		Sistema.tela = new CargosConsultar();
		Sistema.frame.setTitle("Funcionarios Company SA - consultar cargos");
		Navegador.atualizarTela();
	}
	public static void cargosEditar(Cargos cargo) {
		Sistema.tela = new CargosEditar(cargo);
		Sistema.frame.setTitle("Funcionarios Company SA - editar cargos");
	}
	public static void funcionariosEditar(Funcionarios funcionario) {
		Sistema.tela = new FuncionariosEditar(funcionario);
		Sistema.frame.setTitle("Funcionarios Company SA - editar cargos");
	}
	//metodo que remove a tela atual e adiciona a proxima tela
	private static void atualizarTela() {
		Sistema.frame.getContentPane().removeAll();
		Sistema.tela.setVisible(true);
		Sistema.frame.add(Sistema.tela);
	}
	private static void ConstruirMenu() {
		if(!menuConstruido) {
			menuConstruido = true;
			
			menuBar = new JMenuBar();
			//menu arquivo
			menuArquivo = new JMenu("Arquivo");
			menuBar.add(menuArquivo);
			miSair = new JMenuItem("Sair");
			menuArquivo.add(miSair);
			
			//menu Funcionarios
			menuFuncionarios = new JMenu("Funcionairos");
			menuBar.add(menuFuncionarios);
			miFuncionariosCadastrar = new JMenuItem("Cadastrar");
			menuFuncionarios.add(miFuncionariosCadastrar);
			miFuncionariosConsultar = new JMenuItem("Consultar");
			menuFuncionarios.add(miFuncionariosConsultar);
			//menu Cargos
			menuCargos = new JMenu("Cargos");
			menuBar.add(menuCargos);
			miCargosCadastrar = new JMenuItem("Cadastrar");
			menuCargos.add(miCargosCadastrar);
			miCargosConsultar = new JMenuItem("Consultar");
			menuCargos.add(miCargosConsultar);
			
			//menu Relatorios
			menuRelatorios = new JMenu("Relatorios");
			menuBar.add(menuRelatorios);
			miRelatoriosCargos = new JMenuItem("Funcionarios por cargos");
			menuRelatorios.add(miRelatoriosCargos);
			miRelatoriosSalarios = new JMenuItem("Salarios dos Funcionarios");
			menuRelatorios.add(miRelatoriosSalarios);
			
			criarEventosMenu();
		}
	}
	public static void habilitaMenu() {
		if(!menuConstruido) ConstruirMenu();
		if(!menuHabilitado) {
			menuHabilitado = true;
			Sistema.frame.setJMenuBar(menuBar);
		}
	}

	public static void desabilitaMenu() {
		if(menuHabilitado) {
			menuHabilitado = false;
			Sistema.frame.setJMenuBar(null);
		}
	}
	private static void criarEventosMenu() {
		miSair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		//funcionario
		miFuncionariosCadastrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		miFuncionariosConsultar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		miCargosCadastrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cargosCadastrar();
				
			}
		});
		miCargosConsultar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cargosConsultar();
				
			}
		});
		miRelatoriosCargos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		miRelatoriosSalarios.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
}

package view;

import java.io.IOException;

import javax.swing.JOptionPane;

import controller.ArquivosController;
import controller.IArquivosController;

public class Principal {
	public static void main(String args[]) {
		IArquivosController arqCont = new ArquivosController();
		String arquivo = "cadastro.csv";
			
		try {
			
			arqCont.verificaDirTemp();
			int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite um codigo para buscar"));
			arqCont.imprimeCadastro(arquivo, codigo);
			while(arqCont.verificaRegistro(arquivo, codigo)) {
				codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite um codigo para inserir"));
			}
			String nome = JOptionPane.showInputDialog("Digite um nome para inserir");
			String email = JOptionPane.showInputDialog("Digite um email para inserir");
			arqCont.insereCadastro(arquivo, codigo, nome, email);
			arqCont.imprimeArquivo(arquivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class ArquivosController implements IArquivosController{

	@Override
	public File verificaDirTemp() throws IOException {
		File dir = new File("C:\\TEMP");
		if(dir.exists() && dir.isDirectory()) {
			dir.mkdir();
		}
		return dir;
	}

	@Override
	public boolean verificaRegistro(String arquivo, int codigo) throws IOException {
		File arq = new File(verificaDirTemp(), arquivo);
		if(arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) {
				String cod = codigo+"";
				if(linha.contains(cod)) {

					buffer.close();
					leitor.close();
					fluxo.close();
					return true;
				}
		
				linha = buffer.readLine();
				
			}
			buffer.close();
			leitor.close();
			fluxo.close();
			return false;
		}
		else {
			throw new IOException("Arquivo inválido");
		}
	}

	@Override
	public void imprimeCadastro(String arquivo, int codigo) throws IOException {
		if(verificaRegistro(arquivo, codigo)){
			File arq = new File(verificaDirTemp(), arquivo);
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			String cod = codigo+"";
			String [] split = linha.split(";");
			while(linha != null) {
				if(linha.contains(cod)) {
					split = linha.split(";");
					JOptionPane.showMessageDialog(null,"Codigo: "+split[0]+"\nNome: "+split[1]+"\nEmail: "+split[2]);
					break;
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		}
		else {
			JOptionPane.showMessageDialog(null, "Codigo não encontrado!");
		}
			
	}

	@Override
	public void insereCadastro(String arquivo, int codigo, String nome, String email) throws IOException {
		StringBuffer buffer = new StringBuffer();		
		if(!verificaRegistro(arquivo, codigo)) {
			String linha = codigo+";"+nome+";"+email+";";
			buffer.append(linha);
			File arq = new File(verificaDirTemp(), arquivo);
			FileWriter fileWriter = new FileWriter(arq, true);
			PrintWriter print = new PrintWriter(fileWriter);
			print.write(linha);
			print.flush();
			print.close();
			fileWriter.close();
		}
		else {
			JOptionPane.showMessageDialog(null, "Codigo já existente!");
		}
	}
	public void imprimeArquivo(String arquivo) throws IOException {
		File arq = new File(verificaDirTemp(), arquivo);
		if(arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			linha = buffer.readLine();
			String [] split = linha.split(";");
			String mostra = "";
			while(linha != null) {
				split = linha.split(";");
				mostra += "\nCodigo: "+split[0]+" - Nome: "+split[1]+" - Email: "+split[2];
				linha = buffer.readLine();
			}
			JOptionPane.showMessageDialog(null, mostra);
			buffer.close();
			leitor.close();
			fluxo.close();
		}
		else {
			throw new IOException("Arquivo inválido");
		}
	}
}


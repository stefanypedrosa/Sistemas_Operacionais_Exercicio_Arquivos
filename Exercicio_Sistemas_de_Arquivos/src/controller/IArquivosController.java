package controller;

import java.io.File;
import java.io.IOException;

public interface IArquivosController {
	public File verificaDirTemp() throws IOException;
	public boolean verificaRegistro(String arquivo, int codigo) throws IOException;
	public void imprimeCadastro(String arquivo, int codigo) throws IOException;
	public void insereCadastro(String arquivo, int codigo, String nome, String email) throws IOException;
	public void imprimeArquivo(String arquivo) throws IOException;
}

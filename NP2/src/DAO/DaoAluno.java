package DAO;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import Dados.Dados;
import Entities.Aluno;

public class DaoAluno {

	private String filePath;
	private Dados dados;
	
	public DaoAluno(String aFilePath, Dados dados) {
		this.dados = dados;
		this.filePath = aFilePath;
	}

	public void loadAlunos() {
		try (InputStream is = new FileInputStream(filePath);
				InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(isr);) {

			String linha;

			while ((linha = br.readLine()) != null) {
				String[] palavras = linha.split(",");

				String id = palavras[0];
				String nome = palavras[1];

				Aluno aluno = new Aluno(id, nome);
				dados.addAluno(aluno);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveAlunos() {
		try (OutputStream os = new FileOutputStream(filePath);
				OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
				PrintWriter pw = new PrintWriter(osw, true);) {
			for (Aluno a : dados.getAluno()) {
				pw.println(a.getId() + "," + a.getNome());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

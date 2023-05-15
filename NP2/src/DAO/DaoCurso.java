package DAO;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import Dados.Dados;
import Dados.DadosCurso;
import Entities.Curso;
import Entities.Rendimento;
import Entities.rendimentoGraduacao;
import Entities.rendimentoPosGraduacao;

public class DaoCurso {

	private String filePath;
	private DadosCurso dados;
	private Dados dadosAluno;

	public DaoCurso(String aFilePath, DadosCurso dados, Dados dadosAluno) {
		this.dados = dados;
		this.filePath = aFilePath;
		this.dadosAluno = dadosAluno;
	}

	
	public void load() {
		loadCursos();
		loadRendimentos();
	}
	
	private void loadCursos() {
		try (InputStream is = new FileInputStream(filePath + "cursos.csv");
				InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(isr);) {

			String linha;

			while ((linha = br.readLine()) != null) {
				String[] palavras = linha.split(",");

				String nome = palavras[0];
				Curso.NIVEL nivel = null;

				if (palavras[1].equals("GRADUACAO")) {
					nivel = Curso.NIVEL.GRADUACAO;
				} else {
					nivel = Curso.NIVEL.POS_GRADUACAO;
				}

				int ano = Integer.parseInt(palavras[2]);

				Curso curso = new Curso(ano, nome, nivel);
				dados.addCursos(curso);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveCursos() {
		try (OutputStream os = new FileOutputStream(filePath + "cursos.csv");
				OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
				PrintWriter pw = new PrintWriter(osw, true);) {
			for (Curso c : dados.getCursos()) {
				pw.println(c.getNome() + "," + c.getNivel() + "," + c.getAno());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveRendimento() {
		for (Curso c : dados.getCursos()) {
			try (OutputStream os = new FileOutputStream(
					filePath + c.getNome() + "_" + c.getNivel() + "_" + c.getAno() + ".csv");
					OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
					PrintWriter pw = new PrintWriter(osw, true);) {
				for (Rendimento r : dados.getRendimento(c))
					pw.println(r.getAluno().getId() + "," + r.getNP1() + "," + r.getNP2() + "," + r.getReposicao()
					+ "," + r.getExame());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void loadRendimentos() {
		for (Curso c : dados.getCursos()) {
			try (InputStream is = new FileInputStream(
					filePath + c.getNome() + "_" + c.getNivel() + "_" + c.getAno() + ".csv");
					InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
					BufferedReader br = new BufferedReader(isr);) {

				String linha;

				while ((linha = br.readLine()) != null) {
					String[] palavras = linha.split(",");

					String id = palavras[0];
					double np1 = Double.parseDouble(palavras[1]);
					double np2 = Double.parseDouble(palavras[2]);
					double rep = Double.parseDouble(palavras[3]);
					double exa = Double.parseDouble(palavras[4]);
					
					Rendimento rendimento = null;
					
					if(c.getNivel() == Curso.NIVEL.GRADUACAO) {
						rendimento = new rendimentoGraduacao(np1, np2, rep, exa, c, dadosAluno.getAlunosById(id));
					}else if(c.getNivel() == Curso.NIVEL.POS_GRADUACAO) {
						rendimento = new rendimentoPosGraduacao(np1, np2, rep, exa, c, dadosAluno.getAlunosById(id));	
					}
					
					dados.addRendimento(c, rendimento);
					dadosAluno.addRendimento(dadosAluno.getAlunosById(id),  rendimento);
				}

			}catch(FileNotFoundException e) {
				System.out.println("O arquivo: " + c.getNome() + "_" + c.getNivel() + "_" + c.getAno() + ".csv" + 
									" ainda não foi criado, utilize a opção 3 para criar um arquivo novo.");
			}catch (IOException e) {
				e.printStackTrace();

			}
		}
	}
}

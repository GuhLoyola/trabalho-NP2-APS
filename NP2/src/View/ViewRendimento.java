package View;

import java.util.Scanner;

import Entities.Aluno;
import Entities.Curso;
import Entities.Curso.NIVEL;
import Entities.Rendimento;
import Entities.rendimentoGraduacao;
import Entities.rendimentoPosGraduacao;

public class ViewRendimento {

	viewAluno va;
	viewCurso vc;

	public ViewRendimento(viewAluno aVa, viewCurso aVc) {
		this.va = aVa;
		this.vc = aVc;
	}

	public void controller() {

		int opcao = 0;
		do {
			opcao = getOpcao();
			switch (opcao) {
			case 1:
				adicionarRendimento();
				break;
			case 0:
				voltar();
				break;
			}
		} while (opcao != 0);
		System.exit(0);

	}

	public int getOpcao() {

		System.out.println();
		System.out.println("--------------------------------");
		System.out.println("Escolha a opcao:");
		System.out.println("1 - Cadastrar rendimentos");
		System.out.println("0 - Voltar ao menu anterior");
		System.out.println("--------------------------------");
		System.out.println();

		Scanner in = new Scanner(System.in);
		String linha = in.nextLine();

		try {
			return Integer.parseInt(linha);
		} catch (NumberFormatException e) {
			System.out.println("O valor entrado : " + linha + " nao é valido");
			System.out.println("   a opcao deve ser um numero inteiro\n");
			return getOpcao();
		}

	}

	public void adicionarRendimento() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Informe um aluno:");
		Aluno aluno = va.encontraAlunoById();
		if (aluno == null) {
			System.out.println("Aluno não encontrado");
			return;
		}
		int ano = vc.entraAno();
		String nome = vc.entraNomeCurso();
		NIVEL nivel = vc.entraNivel();
		Curso curso = new Curso(ano, nome, nivel);
		if (!vc.dadosCurso.contains(curso)) {
			System.out.println("Não existe este curso");
			return;
		}
		double np1 = entraNota("NP1");
		double np2 = entraNota("NP2");
		double rep = entraNota("Reposicao");
		double exa = entraNota("Exame");
		Rendimento ren = null;
		if (nivel == NIVEL.POS_GRADUACAO) {
			ren = new rendimentoPosGraduacao(np1, np2, rep, exa, curso, aluno);
		} else if (nivel == NIVEL.GRADUACAO) {
			ren = new rendimentoGraduacao(np1, np2, rep, exa, curso, aluno);
		}
		va.dados.addRendimento(aluno, ren);
		vc.dadosCurso.addRendimento(curso, ren);
	}

	public double entraNota(String nomeNota) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Informe a nota da " + nomeNota + " :");
		double nota = sc.nextDouble();

		if (nota < 0 || nota > 10) {
			System.out.println("O número tem que ser entre 0 e 10");
			System.out.println("Tente novamente");
			System.out.println();
			return entraNota(nomeNota);
		} else {
			return nota;
		}

	}

	public void voltar() {
		vc.daoCur.saveRendimento();
		new View().init();
	}

}

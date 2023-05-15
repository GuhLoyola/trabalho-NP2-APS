package View;

import java.util.Scanner;

import DAO.DaoCurso;
import Dados.Dados;
import Dados.DadosCurso;
import Entities.Curso;
import Entities.Curso.NIVEL;

public class viewCurso {

	DaoCurso daoCur;
	DadosCurso dadosCurso;
	Dados dadosAluno;

	public viewCurso(Dados dadosAluno) {
		super();
		dadosCurso = new DadosCurso();
		daoCur = new DaoCurso("files/", dadosCurso, dadosAluno);
		
	}

	public void controller() {

		int opcao = 0;
		do {
			opcao = getOpcao();
			switch (opcao) {
			case 1:
				adicionaCurso();
				break;
			case 2:
				listaTodosCursos();
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
		System.out.println("1 - Cadastrar cursos");
		System.out.println("2 - Listar todos os cursos");
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

	public void voltar() {
		daoCur.saveCursos();
		new View().init();
	}

	public void adicionaCurso() {
		Curso curso = entraCurso();
		if (curso == null) {
			return;
		}
		if (dadosCurso.addCursos(curso)) {
			System.out.println("Adicionando curso " + curso);
		} else {
			System.out.println("Falha ao adicionar curso " + curso);
		}

	}

	public Curso entraCurso() {
		int ano = entraAno();
		String nomeCur = entraNomeCurso();
		Curso.NIVEL nivel = entraNivel();

		Curso temCurso = new Curso(ano, nomeCur, nivel);
		
		if (dadosCurso.contains(temCurso)) {
			System.out.println("Já temos um curso com essas credenciais");
			System.out.println("Em " + dadosCurso.getCursos());
			System.out.println(temCurso);
			return null;
		}
		return new Curso(ano,nomeCur,nivel);
	}

	public int entraAno() {
		System.out.println("Entre com o ano do curso");
		Scanner sc = new Scanner(System.in);

		int ano = sc.nextInt();
		return ano;
	}

	public String entraNomeCurso() {
		System.out.println("Entre com o nome do curso");
		Scanner in = new Scanner(System.in);

		String nome = in.nextLine();

		return nome.trim();
	}

	public NIVEL entraNivel() {
		System.out.println("Entre com o nivel do curso");
		Scanner in = new Scanner(System.in);

		String nivel = in.nextLine();

		if (nivel.toLowerCase().equals("GRADUACAO".toLowerCase())) {
			return Curso.NIVEL.GRADUACAO;
		} else if (nivel.toLowerCase().equals("POS_GRADUACAO".toLowerCase())){
			return Curso.NIVEL.POS_GRADUACAO;
		} else {
			System.out.println("Entrada não reconhecida");
			return entraNivel();
		}
	}

	public void listaTodosCursos() {
		System.out.println("Listando todos os cursos");
		for (Curso c : dadosCurso.getCursos()) {
			System.out.println(c);
		}
	}

}
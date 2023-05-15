package View;

import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

import DAO.DaoAluno;
import Dados.Dados;
import Entities.Aluno;
import Entities.Rendimento;

public class viewAluno {

	DaoAluno dao;
	Dados dados;

	public viewAluno() {
		dados = new Dados();
		dao = new DaoAluno("files/alunos.csv", dados);
	}

	public void controller() {
		int opcao = 0;
		do {
			opcao = getOpcao();
			switch (opcao) {
			case 1:
				adicionaAluno();
				break;
			case 2:
				listaTodosAlunos();
				break;
			case 3:
				listaAlunosByNome();
				break;
			case 4:
				encontraAlunoById();
				break;
			case 5:
				relatorioAluno();
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
		System.out.println("1 - Cadastrar alunos");
		System.out.println("2 - Listar todos os alunos");
		System.out.println("3 - Listar alunos pelo nome");
		System.out.println("4 - Listar alunos pelo ID");
		System.out.println("5 - Fazer relatório do aluno");
		System.out.println("0 - Voltar para o menu anterior");
		System.out.println("--------------------------------");
		System.out.println();

		Scanner in = new Scanner(System.in);
		String linha = in.nextLine();

		try {
			return Integer.parseInt(linha);
		} catch (NumberFormatException e) {
			System.out.println("O valor entrado : " + linha + " não é valido");
			System.out.println("   a opção deve ser um numero inteiro\n");
			return getOpcao();
		}

	}

	public void voltar() {
		dao.saveAlunos();
		new View().init();
	}

	public Dados getDados() {
		return dados;
	}
	
	public void adicionaAluno() {
		Aluno aluno = entraAluno();
		if (aluno == null) {
			return;
		}
		if (dados.addAluno(aluno)) {
			System.out.println("Adicionando aluno " + aluno);
		} else {
			System.out.println("Falha ao adicionar aluno " + aluno);
		}

	}
	
	public void relatorioAluno() {
		Aluno a = encontraAlunoById();
		for (Rendimento r:dados.getRendimento(a)) {
			System.out.println();
			System.out.println(r);
		}
	}
	
	public void listaTodosAlunos() {
		System.out.println("Listando todos os alunos");
		for (Aluno a : dados.getAluno()) {
			System.out.println(a);
		}
	}

	public void listaAlunosByNome() {
		String nome = entraNome();
		listaAlunosByNome(nome);
	}

	public void listaAlunosByNome(String keyNome) {
		System.out.println("Listando todos os aluno.nome contém \"" + keyNome + "\"");
		Collection<Aluno> alunos = dados.getAlunosByName(keyNome);
		if (alunos.size() == 0) {
			System.out.println("Nenhum aluno em que nome contém " + keyNome);
		}
		System.out.println("   " + alunos.size() + " alunos encontrados");
		for (Aluno a : alunos) {
			System.out.println(a);
		}
		System.out.println();
	}

	public Aluno encontraAlunoById() {
		String id = entraId();
		return encontraAlunoById(id);
	}

	public Aluno encontraAlunoById(String keyId) {
		System.out.println("Procurando aluno com id \"" + keyId + "\"");
		Aluno a = dados.getAlunosById(keyId);
		if (a == null) {
			System.out.println("Aluno não encontrado");
			return null;
		} else {
			System.out.println("Aluno encontrado:");
			System.out.println(a);
			return a;
		}
	}

	public Aluno entraAluno() {
		String id = entraId();

		Aluno temAluno = dados.getAlunosById(id);
		if (temAluno != null) {
			System.out.println("Já temos um aluno com este id:");
			System.out.println(temAluno);
			return null;
		}

		String nome = entraNome();
		return new Aluno(id, nome);
	}

	public String entraId() {
		System.out.println("Entre com o Id do aluno");
		Scanner in = new Scanner(System.in);

		String id = in.nextLine();

		return id.trim();
	}

	public String entraNome() {
		System.out.println("Entre com o nome do aluno");
		Scanner in = new Scanner(System.in);
		return in.nextLine().trim();
	}

}
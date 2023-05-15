package View;

import java.util.Scanner;

public class View {
	
	viewAluno va = new viewAluno();
	viewCurso vc = new viewCurso(va.getDados());
	ViewRendimento vr = new ViewRendimento(va, vc);

	public View() {

	}

	public void init() {

		va.dao.loadAlunos();
		vc.daoCur.load();
		controller();
	}

	public void controller() {
		int opcao = 0;
		do {
			opcao = getOpcao();
			switch (opcao) {
			case 1:
				va.controller();
				break;
			case 2:
				vc.controller();
				break;
			case 3:
				vr.controller();
				break;
			case 0:
				sair();
				break;
			}
		} while (opcao != 0);
		System.exit(0);
	}

	public int getOpcao() {

		System.out.println();
		System.out.println("--------------------------------");
		System.out.println("Escolha a opção:");
		System.out.println("1 - Opções referentes aos alunos");
		System.out.println("2 - Opções referentes aos cursos");
		System.out.println("3 - Opções referentes aos rendimentos");
		System.out.println("0 - Sair do programa");
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

	public void sair() {
		System.out.println("saindo do programa");
		System.out.println("Salvando Alunos e Cursos...");
		va.dao.saveAlunos();
		vc.daoCur.saveCursos();
	}

}
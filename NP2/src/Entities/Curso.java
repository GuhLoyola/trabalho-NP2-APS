package Entities;

import java.util.Objects;

public class Curso implements Comparable<Curso> {

	public enum NIVEL {
		GRADUACAO, POS_GRADUACAO
	};

	private String nome;
	private int ano;
	private NIVEL nivel;

	public Curso(int aAno, String aNome, NIVEL aNivel) {
		this.nome = aNome;
		this.ano = aAno;
		this.nivel = aNivel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public NIVEL getNivel() {
		return nivel;
	}

	public void setNivel(NIVEL nivel) {
		this.nivel = nivel;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (this.getClass() != o.getClass()) {
			return false;
		}
		Curso oCurso = (Curso) o;
		return this.nome.equals(oCurso.nome) && Objects.equals(oCurso.ano, this.ano)
				&& Objects.equals(oCurso.nivel, this.nivel);
	}

	@Override
	public int compareTo(Curso o) {
		if (!this.nome.equals(o.nome)) {
			return this.nome.compareTo(o.nome);
		}
		if (this.ano != o.ano) {
			return Integer.compare(this.ano, o.ano);
		}
		return this.nivel.compareTo(o.nivel);
	}

	@Override
	public String toString() {
		return "Curso: " + nome + ", Ano: " + ano + ", Nivel: " + nivel;
	}

}

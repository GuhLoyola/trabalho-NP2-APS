package Entities;

abstract public class Rendimento {
	
	Aluno aluno;
	Curso curso;
	private double np1;
	private double np2;
	private double reposicao;
	private double exame;
	private boolean aprovado;

	public Rendimento(double aNp1, double aNp2, double aReposicao, double aExame,Curso curso, Aluno aluno) {
		this.np1 = aNp1;
		this.np2 = aNp2;
		this.reposicao = aReposicao;
		this.exame = aExame;
		this.aluno = aluno;
		this.curso = curso;
		mediaFinal();
	}
	
	public double mediaInicial() {
		
		double media;
		
		if(reposicao > np1) {
			media = (reposicao + np2) / 2;
		}else if(reposicao > np2) {
			media = (np1 + reposicao) / 2;
		}else {
			media = (np1 + np2) / 2;
		}
		return media;
	}
	
	abstract public double mediaFinal ();

	public Aluno getAluno() {
		return aluno;
	}
	
	public Curso getCuso() {
		return curso;
	}
	
	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public double getNP1() {
		return np1;
	}

	public double getNP2() {
		return np2;
	}

	public double getReposicao() {
		return reposicao;
	}

	public double getExame() {
		return exame;
	}

	public void setNP1(double nP1) {
		np1 = nP1;
	}

	public void setNP2(double nP2) {
		np2 = nP2;
	}

	public void setReposicao(double reposicao) {
		this.reposicao = reposicao;
	}

	public void setExame(double exame) {
		this.exame = exame;
	}

	@Override
	public String toString() {
		return  aluno + ", " + curso + ", Np1: " + np1 + ", Np2: " + np2 + ", Reposição: "
				+ reposicao + ", Exame: " + exame + ", Aprovado: " + aprovado;
	}
	

}
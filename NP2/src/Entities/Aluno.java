package Entities;

public class Aluno {
	
	private String id;
	private String nome;	
	
	public Aluno(String aId, String aNome) {
		this.id = aId;
		this.nome = aNome;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return  "Nome: " + nome + ", Id: " + id;
	}
	
}

package Dados;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Entities.Aluno;
import Entities.Rendimento;

public class Dados {

	private Map<String, Aluno> alunos = new TreeMap<>();
	
	private Map<String, List<Rendimento>> rendimentosByAluno = new TreeMap<>();

	public Map<String, Aluno> getAlunosById() {
		return this.alunos;
	}

	public boolean addAluno(Aluno a) {
		if (alunos.containsKey(a.getId())) {
			return false;
		}
		this.alunos.put(a.getId(), a);
		
		this.rendimentosByAluno.put(a.getId(), new ArrayList<>());

		return true;
	}
	
	public void addRendimento(Aluno a , Rendimento r) {
		 this.rendimentosByAluno.get(a.getId()).add(r);
	}
	
	public List<Rendimento> getRendimento(Aluno a) {
		return rendimentosByAluno.get(a.getId());
	}

	public Collection<Aluno> getAluno() {
		return this.alunos.values();
	}

	public Aluno getAlunosById(String id) {
		return alunos.get(id);
	}

	public List<Aluno> getAlunosByName(String keyName) {

		List<Aluno> alunosByName = new ArrayList<>();

		for (Aluno a : alunos.values()) {
			if (a.getNome().toLowerCase().contains(keyName.toLowerCase())) {
				alunosByName.add(a);
			}
		}
		return alunosByName;
	}

}
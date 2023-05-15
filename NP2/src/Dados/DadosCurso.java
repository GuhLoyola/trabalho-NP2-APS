package Dados;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import Entities.Curso;
import Entities.Rendimento;

public class DadosCurso {

	Set<Curso> cursos = new TreeSet<>();
	
	Map<Curso, List<Rendimento>>  rendimentosByCurso = new TreeMap<>();

	public boolean addCursos(Curso c) {
		this.rendimentosByCurso.put(c, new ArrayList<>());
		return cursos.add(c);
	}
	
	public Set <Curso> getCursos(){
		return this.cursos;
	}
	
	public void addRendimento(Curso c , Rendimento r) {
		 this.rendimentosByCurso.get(c).add(r);
	}
	
	public List<Rendimento> getRendimento(Curso c) {
		return rendimentosByCurso.get(c);
	}
	
	
	public boolean contains(Curso c) {
		return cursos.contains(c);
	}
	
}
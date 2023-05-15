package Entities;


public class rendimentoGraduacao extends Rendimento{
	

	public rendimentoGraduacao(double aNp1, double aNp2, double aReposicao, double aExame,Curso curso, Aluno aluno) {
		super(aNp1, aNp2, aReposicao, aExame, curso, aluno);
	}
	
	@Override
	public double mediaFinal () {

		double mediaFinal;
		
		if(mediaInicial() >= 7.0) {
			mediaFinal = mediaInicial();
			setAprovado(true);
			return mediaFinal;
		}else {
			getExame();
			mediaFinal = (getExame() + mediaInicial()) / 2;
			if(mediaFinal >= 5.0) {
				setAprovado(true);
				return mediaFinal;
			}else {
				setAprovado(false);
				return mediaFinal;
			}

		}
	}

}
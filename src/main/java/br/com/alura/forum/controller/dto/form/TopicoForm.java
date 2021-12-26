package br.com.alura.forum.controller.dto.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

public class TopicoForm {
	
	@NotNull @NotEmpty @Size(min = 5)
	private String titulo;
	@NotNull @NotEmpty @Size(min = 10)
	private String mensagem;
	@NotNull @NotEmpty
	private String nomeCurso;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	
	public Topico convert(CursoRepository repository) {
		
		Curso curso = repository.findByNome(nomeCurso);
		
		return new Topico(titulo, mensagem, curso);
	}
	
	@Override
	public String toString() {
		
		return 
				"titulo: " + titulo + " /n " +
				"mensagem: " + mensagem + " /n " +
				"nomeCurso: " + nomeCurso + " /n ";
				
	}
	public Topico update(Topico topico, CursoRepository cursoRepository) {
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		topico.setCurso(cursoRepository.findByNome(this.nomeCurso));
		return topico;
	}

}

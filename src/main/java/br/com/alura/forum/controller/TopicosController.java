package br.com.alura.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.TopicoDetalhadoDto;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.dto.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	public List<TopicoDto> list(String curso){
		
		if(curso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDto.convert(topicos);
		} else {
			List<Topico> topicos = topicoRepository.findByCursoNome(curso);
			return TopicoDto.convert(topicos);
		}
		
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<TopicoDto> create(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder){
		
		System.out.println(form.toString());
		
		Topico topico = form.convert(cursoRepository);
		topicoRepository.save(topico);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TopicoDetalhadoDto> get(@PathVariable Long id){
		Optional<Topico> topico = topicoRepository.findById(id);
		
		if (!topico.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(new TopicoDetalhadoDto(topico.get()));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDto> update(@PathVariable Long id, @RequestBody @Valid TopicoForm form) {
		Optional<Topico> optional = topicoRepository.findById(id);
		
		if (!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Topico topico = form.update(optional.get() , cursoRepository);
		
		return ResponseEntity.ok(new TopicoDto(topico)); 
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Topico> optional = topicoRepository.findById(id);
		
		if (!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		topicoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}

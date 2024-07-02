package br.com.AppContatos.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.AppContatos.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import br.com.AppContatos.dto.ContatoDTO;
import br.com.AppContatos.model.Contato;

@RestController
@RequestMapping("/api/contatos")
public class ContatoResource {
	
	@Autowired
	private ContatoService contatoService;
	
	@Operation(summary = "Lista todos os registros de contatos")
	@GetMapping
	public ResponseEntity<List<Contato>> listarContatos() {
		List<Contato> contatos = contatoService.listarContatos();
		if (contatos == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(contatos);
	}
	
	@Operation(summary = "Busca registro de contato por ID")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Contato>> buscarPorId(@PathVariable Long id) {
		Optional<Contato> findContato = contatoService.buscarPorId(id);
		
		if(findContato == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findContato);
	}

	@Operation(summary = "Cria um novo registro de contato")
	@PostMapping
	public ResponseEntity<Contato> criar(@RequestBody Contato contato) {
		Contato novoContato = contatoService.criar(contato);
		if (novoContato == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(novoContato);
	}
	
	@Operation(summary = "Atualiza um registro de contato")
	@PutMapping
	public ResponseEntity<Contato> atualizar(@RequestBody Contato contato) {
		Contato updContato = contatoService.atualizar(contato);
		
		if (updContato == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(contato);		
	}
	
	@Operation(summary = "Exclui um registro de contato")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		contatoService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@Operation(summary = "Pesquisa de todos os contatos relacionados à uma pessoa")
	@GetMapping("/pessoa/{id}")
	public ResponseEntity<List<ContatoDTO>> buscarContatosPorIdPessoa(@PathVariable Long id) {
		
		List<ContatoDTO> contatoDto = contatoService.buscarContatosPorIdPessoa(id);	
		if(contatoDto == null) {
			return ResponseEntity.notFound().build();
		}
		if(contatoDto.size() == 0) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(contatoDto);		
	}
	
}

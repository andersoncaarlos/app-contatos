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

import br.com.AppContatos.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import br.com.AppContatos.model.Pessoa;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaService pessoaService;
	
	@Operation(summary = "Busca por todos os registros de pessoas")
	@GetMapping
	public ResponseEntity<List<Pessoa>> buscarPessoas() {
		List<Pessoa> pessoas = pessoaService.listarPessoas();
		
		if(pessoas == null) {
			return ResponseEntity.notFound().build();
		}
		if(pessoas.size() == 0) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pessoas);		
	}

	@Operation(summary = "Busca registro de pessoas por ID")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Pessoa>> findById(@PathVariable Long id) {
		Optional<Pessoa> pessoa = pessoaService.buscarPorId(id);
		if (pessoa.isEmpty()) {
			return ResponseEntity.notFound().build();
			}
		return ResponseEntity.ok(pessoa);
	}
	
	@Operation(summary = "Salva registro de pessoa")
	@PostMapping
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa) {
		Pessoa newPessoa = pessoaService.criar(pessoa);
		
		if (newPessoa == null) {
			return ResponseEntity.notFound().build();
		}		
		return ResponseEntity.ok(newPessoa);
	}
	
	@Operation(summary = "Atualiza registro de pessoa")
	@PutMapping
	public ResponseEntity<Pessoa> atualizar(Pessoa pessoa) {
		Pessoa updPessoa = pessoaService.atualizar(pessoa);
		if (updPessoa == null) {
			return ResponseEntity.notFound().build();
		}		
		return ResponseEntity.ok(updPessoa);		
	}	
	
	@Operation(summary = "Exclui registro de pessoa")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(Long id) {
		pessoaService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}	
}

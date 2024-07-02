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
import br.com.AppContatos.dto.PessoaDTO;
import br.com.AppContatos.model.Pessoa;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaService pessoaService;
	
	@Operation(summary = "Lista todos os registros de pessoas")
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

	@Operation(summary = "Busca registro de pessoa por ID")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Pessoa>> buscarPorId(@PathVariable Long id) {
		Optional<Pessoa> pessoa = pessoaService.buscarPorId(id);
		if (pessoa.isEmpty()) {
			return ResponseEntity.notFound().build();
			}
		return ResponseEntity.ok(pessoa);
	}
	
	@Operation(summary = "Cria um novo registro de pessoa")
	@PostMapping
	public ResponseEntity<Pessoa> criar(@RequestBody Pessoa pessoa) {
		Pessoa newPessoa = pessoaService.criar(pessoa);
		
		if (newPessoa == null) {
			return ResponseEntity.notFound().build();
		}		
		return ResponseEntity.ok(newPessoa);
	}
	
	@Operation(summary = "Atualiza um registro de pessoa")
	@PutMapping
	public ResponseEntity<Pessoa> atualizar(@RequestBody Pessoa pessoa) {
		Pessoa updPessoa = pessoaService.atualizar(pessoa);
		if (updPessoa == null) {
			return ResponseEntity.notFound().build();
		}		
		return ResponseEntity.ok(updPessoa);		
	}	
	
	@Operation(summary = "Exclui um registro de pessoa")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		pessoaService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}	
	
	@Operation(summary = "Pesquisa de uma lista contendo os dados de Pessoa com seu endereço completo em um único registro")
	@GetMapping("/malaDireta/{id}")
	public ResponseEntity<List<PessoaDTO>> malaDireta(@PathVariable Long id) {
		
		List<PessoaDTO> pessoaDto = pessoaService.malaDireta(id);	
		if(pessoaDto == null) {
			return ResponseEntity.notFound().build();
		}
		if(pessoaDto.size() == 0) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pessoaDto);		
	}
}

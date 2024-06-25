package br.com.AppContatos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.AppContatos.model.Pessoa;
import br.com.AppContatos.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa criar(Pessoa pessoa) {
		if (pessoa.getNome() == null) {
			System.out.println("Nome da Pessoa está vazio");
		}
		try {
			return pessoaRepository.save(pessoa);
		} catch(Exception e) {
			System.out.println("Erro ao adicionar a pessoa" + pessoa.toString() + ": " + e.getMessage());
			return null;
		}
		
	}
	
	public List<Pessoa> listarPessoas() {
		return pessoaRepository.findAll();
	}
	
	public Optional<Pessoa> buscarPorId(Long id) {
		return pessoaRepository.findById(id);
	}
	
	public Pessoa atualizar(Pessoa pessoa) {
		Optional<Pessoa> findPessoa = pessoaRepository.findById(pessoa.getId());
		if (findPessoa.isPresent()) {
			Pessoa updPessoa = findPessoa.get();
			updPessoa.setNome(pessoa.getNome());
			updPessoa.setEndereço(pessoa.getEndereço());
			updPessoa.setCep(pessoa.getCep());
			updPessoa.setCidade(pessoa.getCidade());
			updPessoa.setUf(pessoa.getUf());
			return pessoaRepository.save(updPessoa);
		}		
		return pessoaRepository.save(pessoa);		
	}
	
	public void deletar(Long id) {
		pessoaRepository.deleteById(id);		
	}	
}

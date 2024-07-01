package br.com.AppContatos.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.AppContatos.dto.PessoaDTO;
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
			updPessoa.setEndereco(pessoa.getEndereco());
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
	
	/**
	 * Consulta em banco de dados, onde retorna o registro de Pessoa com endereço completo em um único registro
	 * @param id de pessoa
	 * @return lista de PessoaDTO
	 */
	public List<PessoaDTO> malaDireta(Long id) {
		
		List<Object[]> listResult = pessoaRepository.malaDireta(id);
		List<PessoaDTO> listPessoaDTO = new ArrayList<PessoaDTO>();
		
		//Conversão de objeto de banco de dados para PessoaDTO
		for (Object[] obj : listResult) {
			PessoaDTO pDTO = returnDBPessoaDTO(obj);
			listPessoaDTO.add(pDTO);
		}
		return listPessoaDTO;		
	}
	
	/**
	 * Conversão de objeto recebido do banco de dados para DTO de Pessoa
	 * @param resultado objeto do banco de dados
	 * @return Objeto PessoaDTO
	 */
	private PessoaDTO returnDBPessoaDTO(Object[] resultado) {
		if (resultado != null) {
			PessoaDTO pessoaDTO = new PessoaDTO(
					(((Long) resultado[0]).longValue()),
					((String)resultado[1]),
					((String)resultado[2]));
			return pessoaDTO;
		} 
		else {
			return null;
		}
	}
}

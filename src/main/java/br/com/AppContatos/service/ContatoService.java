package br.com.AppContatos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.AppContatos.model.Contato;
import br.com.AppContatos.repository.ContatoRepository;

@Service
public class ContatoService {
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	public Contato criar(Contato contato) {
		if (contato.getContato() == null) {
			System.out.println("Campo de contato está vazio.");
		}
		if (contato.getPessoa() == null) {
			System.out.println("O contato precisa estar relacionado à uma pessoa");
		}
		if (contato.getTipo() == null) {
			System.out.println("O campo tipo não pode ser nulo");
		}
		try {
			return contatoRepository.save(contato);
		} catch (Exception e) {
			System.out.println("Erro ao inserir o contato " + contato.toString() + ": " + e.getMessage());
			return null;
		}		
	}
	
	public List<Contato> listarContatos() {
		return contatoRepository.findAll();
	}
	
	public Optional<Contato> buscarPorId(Long id) {
		return contatoRepository.findById(id);
	}
	
	public Contato atualizar(Contato contato) {
		Optional<Contato> findContato = contatoRepository.findById(contato.getId());
		if (findContato.isPresent()) {
			Contato updContato = findContato.get();
			updContato.setTipo(contato.getTipo());
			updContato.setContato(contato.getContato());
			updContato.setPessoa(contato.getPessoa());
			return contatoRepository.save(updContato);			
		}
		return contatoRepository.save(contato);
	}
	
	public void deletar(Long id) {
		contatoRepository.deleteById(id);
	}
	
	

}

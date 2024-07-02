package br.com.AppContatos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.AppContatos.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
	
	@Query (value = "select tb_contato.pessoa_id, tb_contato.contato AS Contatos from tb_contato where tb_contato.pessoa_id = ?1", 
			nativeQuery = true)
	List<Object[]> buscarContatosPorIdPessoa (Long id);

}

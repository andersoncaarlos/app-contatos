package br.com.AppContatos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.AppContatos.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	@Query(value = "select tb_pessoa.id, tb_pessoa.nome, concat(tb_pessoa.endereco, ' - ', tb_pessoa.cep, ' - ', tb_pessoa.cidade, '/', tb_pessoa.uf) AS malaDireta from tb_pessoa where tb_pessoa.id = ?1",
			nativeQuery = true)
	List<Object[]> malaDireta(Long id);
}

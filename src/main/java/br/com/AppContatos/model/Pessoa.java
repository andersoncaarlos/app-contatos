package br.com.AppContatos.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = "tb_pessoa")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column
	private String endereço;
	
	@Column
	@Size(min = 8, message = "CEP deve conter 8 digitos")
	private String cep;
	
	@Column
	private String cidade;
	
	@Column
	private String uf;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Contato> contatos;
	
	public Pessoa(Long id, String nome, String endereço, String cep, String cidade, String uf) {
		this.id = id;
		this.nome = nome;
		this.endereço = endereço;
		this.cep = cep;
		this.cidade = cidade;
		this.uf = uf;
	}
	
	public Pessoa() {		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereço() {
		return endereço;
	}

	public void setEndereço(String endereço) {
		this.endereço = endereço;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", endereço=" + endereço + ", cep=" + cep + ", cidade=" + cidade
				+ ", uf=" + uf + "]";
	}	
}

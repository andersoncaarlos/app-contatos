package br.com.AppContatos.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table (name = "tb_contato")
public class Contato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private tipoTelefone tipo;
	
	@Column(nullable = false)
	private String contato;
	
	@NotNull(message = "Campo Pessoa não pode está vazio")
	@ManyToOne
	@JoinColumn(name = "pessoa_id", referencedColumnName = "id")
	private Pessoa pessoa;
		
	public Contato(Long id, tipoTelefone tipo, String contato, Pessoa pessoa) {
		this.id = id;
		this.tipo = tipo;
		this.contato = contato;
		this.pessoa = pessoa;
	}
	
	public Contato() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public tipoTelefone getTipo() {
		return tipo;
	}

	public void setTipo(tipoTelefone tipo) {
		this.tipo = tipo;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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
		Contato other = (Contato) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Contato [id=" + id + ", tipo=" + tipo + ", contato=" + contato + ", pessoa=" + pessoa + "]";
	}

	public enum tipoTelefone {
        TELEFONE(0),
        CELULAR(1);

        private final int codigo;

        tipoTelefone(int codigo) {
            this.codigo = codigo;
        }

        public int getCodigo() {
            return codigo;
        }

        public static tipoTelefone fromCodigo(int codigo) {
            for (tipoTelefone tipo : tipoTelefone.values()) {
                if (tipo.getCodigo() == codigo) {
                    return tipo;
                }
            }
            throw new IllegalArgumentException("Número inválido para o tipo de telefone: " + codigo);
        }
    }
}

package negocio.entidade;

import java.io.Serializable;

/* Programa de vendedores por departamentos
 *  
 * Departamento de entidade de departamento
 * Classe Departamento
 * 
 * Um vendedor pode ter um departamento
 * Um departamento pode ter varios vendedores 
 * 
 * Lista de verificação de classe de entidade:
 * Atributos
 * Contrutores
 * Getters/Setters
 * hashCode and Equal
 * toString
 * Implements Serializable*/

// implementando Serializável para converter os objetos sequencia de bytes
// na linguagem java tem que ser feito, caso precise que o objeto seja
// gravado em arquivos, disco, trafegado pela rede...
public class Departamento implements Serializable {

	private static final long serialVersionUID = 1L;

	// Atributos da classe departamento
	private Integer id;
	private String nome;
	
	//construtores
	// Criação construtor padrão
	public Departamento() {
	}

	// Criação construtor com argumentos
	public Departamento(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	// Metodos Getters e Setter
	// Metodo getters Id
	public Integer getId() {
		return id;
	}
	
	// Metodo Setters ID
	public void setId(Integer id) {
		this.id = id;
	}

	// Metodo Getter Nome
	public String getNome() {
		return nome;
	}
	// Metodo Setters nome
	public void setNome(String nome) {
		this.nome = nome;
	}

	// hashCode and Equal
	// hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	// and Equal
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	// toString
	// Melhorar a forma de imprimir os objetos nos testes.
	@Override
	public String toString() {
		return "Departamento [id=" + id + ", Nome=" + nome + "]";
	}
}
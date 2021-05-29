package negocio.entidade;

import java.io.Serializable;
import java.util.Date;

/* Programa de vendedores por departamentos
 *  
 * Vendedor entidade de departamento
 * Interfaces DepartmentDao e SellerDao
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

// implementando Serializável, para permitir a serialização para o objeto que você quer serializar. 
// (tornar o objeto serializável) criar um objeto serializável para escrever o arquivo. 
// sequencia de bytes na linguagem java tem que ser feito, caso precise que o objeto seja
// gravado em arquivos, disco, trafegado pela rede...
public class Vendedor implements Serializable{
	
	//Numero de serie do serializable
	private static final long serialVersionUID = 1L;
	
	// Atributos da classe
	private Integer id;
	private String nome;
	private String email;
	private Date dataNascimento;
	private Double baseSalarial;
	
	// composição dos objetos / Associação dos Objetos;
	// Um Venderor faz parte de um Departamento
	private Departamento departamento;
	
	// Construtores
	// Criação construtor padrão
	public Vendedor() {
	}
	
	// Criação construtor com argumentos	
	public Vendedor(Integer id, String nome, String email, Date dataNascimento, Double baseSalarial,
			Departamento departamento) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.baseSalarial = baseSalarial;
		this.departamento = departamento;
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
	
	// Metodo getters Nome
	public String getNome() {
		return nome;
	}
	
	// Metodo Setters Nome
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	// Metodo getters Email
	public String getEmail() {
		return email;
	}

	// Metodo Setters Email
	public void setEmail(String email) {
		this.email = email;
	}

	// Metodo getters Data do aniversario
	public Date getdataNascimento() {
		return dataNascimento;
	}

	// Metodo Setters Data do Aniversario
	public void setdataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	// Metodo getters Base Salarial
	public Double getBaseSalarial() {
		return baseSalarial;
	}

	// Metodo Setters Base salarial
	public void setBaseSalarial(Double baseSalarial) {
		this.baseSalarial = baseSalarial;
	}

	// Metodo getters Departamento composição
	public Departamento getDepartamento() {
		return departamento;
	}

	// Metodo Setters Departamento composição
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
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
		Vendedor other = (Vendedor) obj;
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
		return "Vendedor [id=" + id + ", nome=" + nome + ", email=" + email + ", dataNascimento=" + dataNascimento
				+ ", baseSalarial=" + baseSalarial + ", departamento=" + departamento + "]";
	}
}
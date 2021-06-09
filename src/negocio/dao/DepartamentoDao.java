package negocio.dao;

import java.util.List;

import negocio.entidade.Departamento;

// Interface Departamento DAO
public interface DepartamentoDao {
	
	// Operações do Objeto de acesso a dados(DAO) 
	// Para manipular os atributos do Departamento.
	void inserir(Departamento obj);
	void atualizar(Departamento obj);
	void apagarPorId(Integer id);
	Departamento buscaPorId(Integer id);
	List<Departamento> buscaTodos();
}
package negocio.dao;

import java.util.List;

import negocio.entidade.Vendedor;

// Interface Vendedor DAO
public interface VendedorDao {

	// Operações do Objeto de acesso a dados(DAO) 
	// Para manipular os Atributos dos Vendedores.
	void inserir(Vendedor obj);
	void atualizar(Vendedor obj);
	void apagarPorId(Vendedor id);
	Vendedor buscaPorId(Integer id);
	List<Vendedor> buscaTodos();	
}
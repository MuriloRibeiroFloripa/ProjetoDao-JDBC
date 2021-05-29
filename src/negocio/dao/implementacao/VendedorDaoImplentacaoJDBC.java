package negocio.dao.implementacao;

import java.util.List;

import negocio.dao.VendedorDao;
import negocio.entidade.Vendedor;


/*
 *  Estruturação do DAO implementando a classe Vendedor DAO JDBC
 * Criando o DAO Factory, classe responsavel por instanciar os DAOs.
 * 
 */

// Classe que implementa Vendedor DAO
public class VendedorDaoImplentacaoJDBC implements VendedorDao {

	// Implementação dos metodos
	@Override
	public void inserir(Vendedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizar(Vendedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apagarPorId(Vendedor id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vendedor buscaPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vendedor> buscaTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}

package negocio.dao;

import db.DB;
import negocio.dao.implementacao.DepartamentoDaoImplementacaoJDBC;
import negocio.dao.implementacao.VendedorDaoImplentacaoJDBC;

/*
 * Implementa��o da fabrica, classe auxiliar respons�vel por instanciar os DAOs
 * Opera�oes estaticas para instanciar os DAOs
 * 
 */

public class FabricaDao {
	
	// classe expoem um mettodo que retorna o tipo da interface
	// porem internamente ela vai instanciar uma  implementa��o.
	// Massete para n�o precisa expor a implementa��o, deixar  somente a interface
	public static VendedorDao criarVendedorDao() {
		return  new VendedorDaoImplentacaoJDBC(DB.getConnection());
	}

	public static DepartamentoDao criarDepartamentoDao() {		
		return new DepartamentoDaoImplementacaoJDBC(DB.getConnection());
	}
}

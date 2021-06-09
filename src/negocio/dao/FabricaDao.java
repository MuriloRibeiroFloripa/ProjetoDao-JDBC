package negocio.dao;

import db.DB;
import negocio.dao.implementacao.DepartamentoDaoImplementacaoJDBC;
import negocio.dao.implementacao.VendedorDaoImplentacaoJDBC;

/*
 * Implementação da fabrica, classe auxiliar responsável por instanciar os DAOs
 * Operaçoes estaticas para instanciar os DAOs
 * 
 */

public class FabricaDao {
	
	// classe expoem um mettodo que retorna o tipo da interface
	// porem internamente ela vai instanciar uma  implementação.
	// Massete para não precisa expor a implementação, deixar  somente a interface
	public static VendedorDao criarVendedorDao() {
		return  new VendedorDaoImplentacaoJDBC(DB.getConnection());
	}

	public static DepartamentoDao criarDepartamentoDao() {		
		return new DepartamentoDaoImplementacaoJDBC(DB.getConnection());
	}
}

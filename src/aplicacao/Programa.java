package aplicacao;

import negocio.dao.FabricaDao;
import negocio.dao.VendedorDao;
import negocio.entidade.Vendedor;

public class Programa {

	// Metodo Principal
	public static void main(String[] args) {
		
		// instanciado a fabricaDao
		VendedorDao vendedorDao = FabricaDao.criarVendedorDao();
		
		// busca vendedor pelo id
		System.out.println("======== Teste 1: Vendedor Busca Por ID ========");
		Vendedor vendedor = vendedorDao.buscaPorId(2);		
		System.out.println(vendedor);
		}
}
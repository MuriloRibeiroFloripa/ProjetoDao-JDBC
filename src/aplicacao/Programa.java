package aplicacao;

import negocio.dao.FabricaDao;
import negocio.dao.VendedorDao;
import negocio.entidade.Vendedor;

/*
 * Incremento de teste do projeto
 */

public class Programa {

	// Metodo Principal
	public static void main(String[] args) {
		
		VendedorDao vendedorDao = FabricaDao.criarVendedorDao();
		
		// busca vendedor pelo id
		Vendedor vendedor = vendedorDao.buscaPorId(2);
		
		System.out.println(vendedor);
		}
}
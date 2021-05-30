package aplicacao;

import java.util.List;

import negocio.dao.FabricaDao;
import negocio.dao.VendedorDao;
import negocio.entidade.Departamento;
import negocio.entidade.Vendedor;

public class ProgramaVendedor {

	// Metodo Principal
	public static void main(String[] args) {

		// instanciado a fabricaDao
		VendedorDao vendedorDao = FabricaDao.criarVendedorDao();

		// busca pelo id
		System.out.println("======== Teste 1: Busca Por ID ========");
		Vendedor vendedor = vendedorDao.buscaPorId(2);
		System.out.println(vendedor);

		// busca por Id do departamento
		System.out.println("\n======== Teste 2: Busca Por ID do Departamento ========");
		Departamento departamento = new Departamento(2, null);
		List<Vendedor> list = vendedorDao.buscaTodosDepartamentos(departamento);
		// para testar preciso for para percorrer a lista
		for (Vendedor obj : list) {
			System.out.println(obj);
		}
	}
}
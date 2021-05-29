package aplicacao;

import java.util.Date;

import negocio.dao.FabricaDao;
import negocio.dao.VendedorDao;
import negocio.entidade.Departamento;
import negocio.entidade.Vendedor;

/*
 * Incremento de teste do projeto
 */

public class Programa {

	// Metodo Principal
	public static void main(String[] args) {
		
		// criação do objeto Departamento
		Departamento  obj = new Departamento (1, "Livros");
		
		// criação do Objeto Vendedor
		Vendedor vendedor = new Vendedor(21, "Bob", "bob@gmail.com", new Date(), 3000.0, obj);
		
		// Acrescentando uma instanciação VendedorDao chamando a fabrica de DAO.
		// Assim o programa não conhece a implementação, conhecendo apenas a interface.
		// Sendo também uma injeção de dependencia sem explicitar a implementacao.
		VendedorDao vendedorDao = FabricaDao.criarVendedorDao();
		
		System.out.println(vendedor); //Imprime o Vendedor Inserido
		}
}
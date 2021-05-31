package aplicacao;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import negocio.dao.FabricaDao;
import negocio.dao.VendedorDao;
import negocio.entidade.Departamento;
import negocio.entidade.Vendedor;

public class ProgramaVendedor {

	// Metodo Principal
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		// instanciado a fabricaDao
		VendedorDao vendedorDao = FabricaDao.criarVendedorDao();

		// busca pelo id		
		System.out.println("======== Teste 1: Busca Por ID ========");
		Vendedor vendedor = vendedorDao.buscaPorId(11);
		System.out.println(vendedor);
		
		// busca por Id do departamento
		System.out.println("\n======== Teste 2: Busca Por ID do Departamento ========");
		Departamento departamento = new Departamento(2, null);
		List<Vendedor> list = vendedorDao.buscaTodosDepartamentos(departamento);
		// para testar preciso for para percorrer a lista
		for (Vendedor obj : list) {
			System.out.println(obj);
		}

		// busca por todos os vendedores da Lista
		System.out.println("\n======== Teste 3: Busca Todos os vendedores da Lista ========");
		// como estou aproveitando a variavel da lista não precisa de outra variavel para armazenar essa nova lista
		list = vendedorDao.buscaTodos();
		// para testar preciso for para percorrer a lista
		for (Vendedor obj : list) {
			System.out.println(obj);
		}
		
		// Inserindo Vendedor
		System.out.println("\n======== Teste 4: Inserindo Vendedor ========");
		Vendedor novoVendedor = new Vendedor(null, "Gregorio", "gregorio@gmail.com", new Date(), 4000.0, departamento);
		vendedorDao.inserir(novoVendedor);
		System.out.println("Iserido! novo id = "+ novoVendedor.getId());	
		
		// Atualiza o Vendedor pelo seu Id
		System.out.println("\n======== Teste 5: Atualiza o Vendedor ========");
		// utilizando a variavel vendedor
		vendedor = vendedorDao.buscaPorId(1); // busca o vendedor com Id definido
		vendedor.setNome("joao Batista da Silva"); // definindo para alterar
		vendedorDao.atualizar(vendedor);
		System.out.println("Atualizado com Sucesso!");
	
		// Apaga o vendedor pelo id informado
		System.out.println("\n======== Teste 6: Apaga o Vendedor ========");
		System.out.println("Entre com o Id do vendedor a ser apagado: ");
		int id = sc.nextInt();
		vendedorDao.apagarPorId(id);		
		sc.close();		
	}
}
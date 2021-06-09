package aplicacao;

import java.util.List;
import java.util.Scanner;

import negocio.dao.DepartamentoDao;
import negocio.dao.FabricaDao;
import negocio.entidade.Departamento;

public class ProgramaDepartamento {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		DepartamentoDao departamentoDao = FabricaDao.criarDepartamentoDao();

		System.out.println("=== TESTE 1: Busca por Id =======");
		Departamento dep = departamentoDao.buscaPorId(1);
		System.out.println(dep);
		
		System.out.println("\n=== TESTe 2: Busca Todos os Departamentos =======");
		List<Departamento> list = departamentoDao.buscaTodos();
		for (Departamento d : list) {
			System.out.println(d);
		}
		
		System.out.println("\n=== TESTE 3: inserir Departamento =======");
		Departamento newDepartamento = new Departamento(null, "Musicas");
		departamentoDao.inserir(newDepartamento);
		System.out.println("Inserido! Novo Id: " + newDepartamento.getId());
		
		System.out.println("\n=== TESTE 4: Atualiza Departamento pelo Id =======");
		Departamento dep2 = departamentoDao.buscaPorId(1);
		dep2.setNome("Futebol");
		departamentoDao.atualizar(dep2);
		System.out.println("Atualizado com Sucesso!");
		
		System.out.println("\n=== TESTE 5: apaga Departamento pelo Id =======");
		System.out.print("Insira O Id para excluir o Departamento ");
		int id = sc.nextInt();
		departamentoDao.apagarPorId(id);
		System.out.println("Apagado Com Sucesso!");
		
		sc.close();
	}
}
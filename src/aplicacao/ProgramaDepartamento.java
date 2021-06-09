package aplicacao;

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
	}

}

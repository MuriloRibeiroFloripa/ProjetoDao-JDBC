package aplicacao;

import java.util.Date;

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
		
		// System.out.println(obj); // Imprime Departamento Criado
		// System.out.println("");
		System.out.println(vendedor); //Imprime o Vendedor Inserido
		}
}
package negocio.dao.implementacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import negocio.dao.VendedorDao;
import negocio.entidade.Departamento;
import negocio.entidade.Vendedor;


/*
 *  Estrutura��o do DAO implementando a classe Vendedor DAO JDBC
 * Criando o DAO Factory, classe responsavel por instanciar os DAOs.
 * 
 */

// Classe que implementa Vendedor DAO
public class VendedorDaoImplentacaoJDBC implements VendedorDao {

	// Implementa��o da dependecia do DAO com a conex�o
	// Para ter a cone��o a disposi��o nesta classe.
	private Connection conn;
	public VendedorDaoImplentacaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	// Implementa��o dos metodos
	@Override
	public void inserir(Vendedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizar(Vendedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apagarPorId(Vendedor id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vendedor buscaPorId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null; // Tras os dados em formato tabela
		try {
			st = conn.prepareStatement(
					"SELECT vendedor.*,departamento.Nome as DepNome "
					+ "FROM vendedor INNER JOIN departamento "
					+ "ON vendedor.DepartamentoId = departamento.Id "
					+ "WHERE vendedor.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			// associa��o dos objetos, rs aponta para posi��o zero da tabela
			if(rs.next()) { // testa se veio algum resultado
				//Implementa��o do metodo para retornar o vendedor por Id.
				Departamento dep = new Departamento();
				dep.setId(rs.getInt("DepartamentoId"));
				dep.setNome(rs.getString("DepNome"));
				Vendedor obj = new Vendedor();
				obj.setId(rs.getInt("Id"));
				obj.setNome(rs.getString("Nome"));
				obj.setEmail(rs.getString("Email"));
				obj.setBaseSalarial(rs.getDouble("BaseSalario"));
				obj.setdataNascimento(rs.getDate("DataNascimento"));
				obj.setDepartamento(dep);
				return obj;				
			}
			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage()); //lan�a exce��o personalizada
		} 
		// fechando os recursos st, rs
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Vendedor> buscaTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}

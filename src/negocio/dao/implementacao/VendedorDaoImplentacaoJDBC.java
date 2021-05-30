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
 *  Estruturação do DAO implementando a classe Vendedor DAO JDBC
 * Criando o DAO Factory, classe responsavel por instanciar os DAOs.
 * 
 */

// Classe que implementa Vendedor DAO
public class VendedorDaoImplentacaoJDBC implements VendedorDao {

	// Implementação da dependecia do DAO com a conexão
	// Para ter a coneção a disposição nesta classe.
	private Connection conn;

	public VendedorDaoImplentacaoJDBC(Connection conn) {
		this.conn = conn;
	}

	// Implementação dos metodos
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
					"SELECT vendedor.*,departamento.Nome as DepNome " + "FROM vendedor INNER JOIN departamento "
							+ "ON vendedor.DepartamentoId = departamento.Id " + "WHERE vendedor.Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();
			// associação dos objetos, rs aponta para posição zero da tabela
			if (rs.next()) { // testa se veio algum resultado

				// chamando a função de Instanciar Departamento
				Departamento dep = instanciaDepartamento(rs);

				// Chama a função de instanciar o Vendedor passando
				// rs do PreparedStatement=rs e o dep da instanciaDepartamento ja instanciado
				// acima.
				Vendedor obj = instanciaVendedor(rs, dep);

				// retorna os objetos instanciados pela chamada de função.
				return obj;
			}
			return null;

			// Capturando SQLexception, lanca exception personalizada.
		} catch (SQLException e) {
			throw new DbException(e.getMessage()); // lança exceção personalizada
		}
		// fechando os recursos st, rs
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	// Instanciar Vendedor com tratamento personalizado, propagando a SQLException
	private Vendedor instanciaVendedor(ResultSet rs, Departamento dep) throws SQLException {

		Vendedor obj = new Vendedor();
		obj.setId(rs.getInt("Id"));
		obj.setNome(rs.getString("Nome"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalarial(rs.getDouble("BaseSalario"));
		obj.setdataNascimento(rs.getDate("DataNascimento"));
		obj.setDepartamento(dep);
		return obj;
	}

	// Instanciar Departamento com tratamento personalizado, propagando a
	// SQLException
	private Departamento instanciaDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("DepartamentoId"));
		dep.setNome(rs.getString("DepNome"));
		return dep;
	}

	@Override
	public List<Vendedor> buscaTodos() {
		// TODO Auto-generated method stub
		return null;
	}
}
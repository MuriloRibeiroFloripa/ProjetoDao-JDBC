package negocio.dao.implementacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	// Inserir Vendedor
	@Override
	public void inserir(Vendedor obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO vendedor "
					+ "(Nome, Email, DataNascimento, BaseSalario, DepartamentoId) "
					+ "VALUES " + "(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);// Coloca o Id do novo vendedor inserido.
			
			// Configuração dos ? (interrogações);
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getdataNascimento().getTime()));
			st.setDouble(4, obj.getBaseSalarial());
			st.setInt(5, obj.getDepartamento().getId()); // apartir do departamento que eu acesso o Id, navegando Obj

			// executar
			int linhasAfetadas = st.executeUpdate();

			// testando
			if (linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				// Fechando o recurso ResultSet
				DB.closeResultSet(rs);
			}
			// lancando exceção caso ocorra.
			else {
				throw new DbException("Erro Inesperado! Nenhuma Linha foi Afetada!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		// Fechando o recurso Startement
		finally {
			DB.closeStatement(st);
		}
	}

	// Metodo atualiza Vendedor pelo Id
	@Override
	public void atualizar(Vendedor obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE vendedor "
					+ "SET Nome = ?, Email = ?, DataNascimento = ?, BaseSalario = ?, DepartamentoId = ? "
					+ "WHERE Id = ?");

			// Configuração dos ? (interrogações);
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getdataNascimento().getTime()));
			st.setDouble(4, obj.getBaseSalarial());
			st.setInt(5, obj.getDepartamento().getId()); // apartir do departamento que eu acesso o Id, navegando Obj
			st.setInt(6, obj.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		// Fechando o recurso Startement
		finally {
			DB.closeStatement(st);
		}
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

	// Busca todos os vendedores da Lista
	@Override
	public List<Vendedor> buscaTodos() {
		PreparedStatement st = null;
		ResultSet rs = null; // Tras os dados em formato tabela
		try {
			st = conn.prepareStatement(
					"SELECT vendedor.*,departamento.Nome as DepNome " 
					+ "FROM vendedor INNER JOIN departamento "
					+ "ON vendedor.DepartamentoId = departamento.Id " 
					+ "ORDER BY Nome;");

			// executa minha Query;
			rs = st.executeQuery();

			// Declarando a lista para receber os resultados da execução da Query
			List<Vendedor> list = new ArrayList<>();

			// função map para controlar a referencia para departamento para não se repetir,
			// Sem multiplicar os departamentos, e sim referenciar o objeto Certo na
			// memoria.
			// estrutura map vazia com chave Integer do Id e Valor Departamento
			Map<Integer, Departamento> map = new HashMap<>();

			// Percorrer os resultados enquanto tiver um proximo
			while (rs.next()) {

				// Controle para não repetir o departamento testando se ele ja existe
				// Guardando na estrutura Map, qualquer departamento que for instanciado.
				// Pela estrutura while e com get ele verifica o id do departamento.
				// reaproveita o departamento se ele ja existe.
				Departamento dep = map.get(rs.getInt("DepartamentoId"));

				// se o retorno da instanciação do objeto Departamento dep for null
				// porem se ele ja existir o map, vai pegar ele.
				if (dep == null) {
					// instancia o departamento
					dep = instanciaDepartamento(rs);
					// guarda dentro do Map o departamento
					map.put(rs.getInt("DepartamentoId"), dep);
				}

				// Intancia o vendedor apontando para dep
				// seja ele existente ou novo departamento criado
				Vendedor obj = instanciaVendedor(rs, dep);

				// adciona o vendedor na lista.
				list.add(obj);
			}
			// Depois de adicionar todos na lista
			// Retorna a lista;
			return list;

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

	// Busca todos departamentos pelo id
	@Override
	public List<Vendedor> buscaTodosDepartamentos(Departamento departamento) {
		PreparedStatement st = null;
		ResultSet rs = null; // Tras os dados em formato tabela
		try {
			st = conn.prepareStatement("SELECT vendedor.*,departamento.Nome as DepNome "
					+ "FROM vendedor INNER JOIN departamento " + "ON vendedor.DepartamentoId = departamento.Id "
					+ "WHERE DepartamentoId = ? " + "ORDER BY Nome;");
			st.setInt(1, departamento.getId());
			// executa minha Query;
			rs = st.executeQuery();

			// Declarando a lista para receber os resultados da execução da Query
			List<Vendedor> list = new ArrayList<>();

			// função map para controlar a referencia para departamento para não se repetir,
			// Sem multiplicar os departamentos, e sim referenciar o objeto Certo na
			// memoria.
			// estrutura map vazia com chave Integer do Id e Valor Departamento
			Map<Integer, Departamento> map = new HashMap<>();

			// Percorrer os resultados enquanto tiver um proximo
			while (rs.next()) {

				// Controle para não repetir o departamento testando se ele ja existe
				// Guardando na estrutura Map, qualquer departamento que for instanciado.
				// Pela estrutura while e com get ele verifica o id do departamento.
				// reaproveita o departamento se ele ja existe.
				Departamento dep = map.get(rs.getInt("DepartamentoId"));

				// se o retorno da instanciação do objeto Departamento dep for null
				// porem se ele ja existir o map, vai pegar ele.
				if (dep == null) {
					// instancia o departamento
					dep = instanciaDepartamento(rs);
					// guarda dentro do Map o departamento
					map.put(rs.getInt("DepartamentoId"), dep);
				}

				// Intancia o vendedor apontando para dep
				// seja ele existente ou novo departamento criado
				Vendedor obj = instanciaVendedor(rs, dep);

				// adciona o vendedor na lista.
				list.add(obj);
			}
			// Depois de adicionar todos na lista
			// Retorna a lista;
			return list;

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
}
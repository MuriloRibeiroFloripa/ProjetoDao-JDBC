package negocio.dao.implementacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import negocio.dao.DepartamentoDao;
import negocio.entidade.Departamento;

public class DepartamentoDaoImplementacaoJDBC implements DepartamentoDao{
	
	// Implementação da dependecia do DAO com a conexão
	// Para ter a coneção a disposição nesta classe.
	private Connection conn;
	
	public DepartamentoDaoImplementacaoJDBC(Connection conn) {
		this.conn = conn;
	}

	// Implementação dos metodos
	
	//inserir Departamento
	@Override
	public void inserir(Departamento obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO departamento " +
				"(Nome) " +
				"VALUES " +
				"(?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());

			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}		
	}

	//Atualiza Dados do Departamento pelo Id
	@Override
	public void atualizar(Departamento obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE departamento " +
				"SET Nome = ? " +
				"WHERE Id = ?");

			st.setString(1, obj.getNome());
			st.setInt(2, obj.getId());

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}
	
	
	@Override
	public void apagarPorId(Departamento id) {
		// TODO Auto-generated method stub
		
	}

	//Busca departamento por id
	@Override
	public Departamento buscaPorId(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM departamento WHERE Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Departamento obj = new Departamento();
				obj.setId(rs.getInt("Id"));
				obj.setNome(rs.getString("Nome"));
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	//Busca Todos os Departamentos 
	@Override
	public List<Departamento> buscaTodos() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM departamento ORDER BY Nome");
			rs = st.executeQuery();

			List<Departamento> list = new ArrayList<>();

			while (rs.next()) {
				Departamento obj = new Departamento();
				obj.setId(rs.getInt("Id"));
				obj.setNome(rs.getString("Nome"));
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
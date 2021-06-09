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
	
	// Implementa��o da dependecia do DAO com a conex�o
	// Para ter a cone��o a disposi��o nesta classe.
	private Connection conn;
	
	public DepartamentoDaoImplementacaoJDBC(Connection conn) {
		this.conn = conn;
	}

	// Implementa��o dos metodos
	@Override
	public void inserir(Departamento obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizar(Departamento obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apagarPorId(Departamento id) {
		// TODO Auto-generated method stub
		
	}

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
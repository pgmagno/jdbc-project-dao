package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SalesmanDAO;
import model.entities.Department;
import model.entities.Salesman;

public class SalesmanDAOforJDBC implements SalesmanDAO {
	
	private Connection conn;
	
	public SalesmanDAOforJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Salesman obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Salesman obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Salesman findById(Integer id) {

		Salesman s = null;
		ResultSet rs = null;
		PreparedStatement st  = null;
		
		try {
			st = conn.prepareStatement(
					"""
						SELECT Seller.*, Department.name FROM Seller
						INNER JOIN Department on Department.Id = Seller.DepartmentId
						WHERE Seller.Id = ? 
					"""
					);
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				s = instantiateSalesman(rs);				
			}
			return s;								
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			
		}
		
	}

	@Override
	public List<Salesman> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Salesman instantiateSalesman(ResultSet rs) throws SQLException {
		return new Salesman(
				rs.getInt("Id"),
				rs.getString("Name"),
				rs.getString("Email"),
				rs.getDate("BirthDate"),
				rs.getDouble("BaseSalary"),
				instantiateDepartment(rs)				
				);		 
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		
		Department d = new Department(
				rs.getInt("DepartmentId"),
				rs.getString("Department.name")
				);		
		return d;
	}

}

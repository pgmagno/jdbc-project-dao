package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		Department d = null;
		Salesman s = null;
		ResultSet rs = null;
		PreparedStatement st  = null;
		
		try {
			st = conn.prepareStatement(
					"""
						SELECT Seller.*, Department.name DepartmentName FROM Seller
						INNER JOIN Department on Department.Id = Seller.DepartmentId
						WHERE Seller.Id = ? 
					"""
					);
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				d = instantiateDepartment(rs);
				s = instantiateSalesman(rs, d);				
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
	public List<Salesman> findByDepartmentId(Integer id) {

		ResultSet rs = null;
		PreparedStatement st  = null;
		
		try {
			st = conn.prepareStatement(
					"""
						SELECT Seller.*, Dept.Name DepartmentName FROM Seller
						inner join Department Dept
						on Dept.Id = Seller.DepartmentId
						where departmentId = ?
						order by Name
					"""
					);
			st.setInt(1, id);
			rs = st.executeQuery();			
			
			List<Salesman> listOfSalesmen = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				Department dept = map.get(rs.getInt("DepartmentId"));
				if(dept == null) {
					dept = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dept);
				}
					Salesman s = instantiateSalesman(rs, dept);
					listOfSalesmen.add(s);
				
			}
			return listOfSalesmen;
			
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
	
	private Salesman instantiateSalesman(ResultSet rs, Department dept) throws SQLException {
		return new Salesman(
				rs.getInt("Id"),
				rs.getString("Name"),
				rs.getString("Email"),
				rs.getDate("BirthDate"),
				rs.getDouble("BaseSalary"),
				dept				
				);		 
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		
		Department d = new Department(
				rs.getInt("DepartmentId"),
				rs.getString("DepartmentName")
				);		
		return d;
	}

}

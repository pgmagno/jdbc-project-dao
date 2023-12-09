package model.dao;

import model.dao.impl.SalesmanDAOforJDBC;

public class DAOfactory {
	public static SalesmanDAO createSalesmanDAO() {
		return new SalesmanDAOforJDBC();
	}
}

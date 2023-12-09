package model.dao;

import db.DB;
import model.dao.impl.SalesmanDAOforJDBC;

public class DAOfactory {
	public static SalesmanDAO createSalesmanDAO() {
		return new SalesmanDAOforJDBC(DB.getConnection());
	}
}

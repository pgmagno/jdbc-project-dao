package application;

import model.dao.DAOfactory;
import model.dao.SalesmanDAO;
import model.entities.Salesman;

public class Program {

	public static void main(String[] args) {		
		
		SalesmanDAO salesmanDAO = DAOfactory.createSalesmanDAO();
		
		System.out.println("::: TEST 01 - FindByID :::");
		System.out.println("ID: 01");
		Salesman s = salesmanDAO.findById(1);		
		System.out.println(s);
	}

}

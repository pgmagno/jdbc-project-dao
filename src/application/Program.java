package application;

import java.util.List;

import model.dao.DAOfactory;
import model.dao.SalesmanDAO;
import model.entities.Salesman;

public class Program {

	public static void main(String[] args) {		
		
		SalesmanDAO salesmanDAO = DAOfactory.createSalesmanDAO();
		
		System.out.println("::: TEST 01 - FindByID :::");
		System.out.println("ID: 1");
		Salesman sm = salesmanDAO.findById(1);		
		System.out.println(sm);
		
		System.out.println("::: TEST 02 - FindByDepartmentID :::");
		System.out.println("ID: 1");
		List<Salesman> listOfSalesmen = salesmanDAO.findByDepartmentId(1);
		for (Salesman s : listOfSalesmen) {			
			System.out.println(s);
		}
	}

}

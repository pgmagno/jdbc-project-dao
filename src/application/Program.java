package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.dao.DAOfactory;
import model.dao.SalesmanDAO;
import model.entities.Department;
import model.entities.Salesman;

public class Program {

	public static void main(String[] args) {		
		
		SalesmanDAO salesmanDAO = DAOfactory.createSalesmanDAO();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("::: TEST 01 - FindByID :::");
		System.out.println("ID: 1");
		Salesman sm = salesmanDAO.findById(1);		
		System.out.println(sm);
		
		System.out.println("\n::: TEST 02 - FindByDepartmentID :::");
		System.out.println("ID: 1");
		List<Salesman> listOfSalesmen = salesmanDAO.findByDepartmentId(1);
		for (Salesman s : listOfSalesmen) {			
			System.out.println(s);
		}
		
		System.out.println("\n::: TEST 03 - FindAll :::");
		List<Salesman> listOfAllSalesmen = salesmanDAO.findAll();
		for (Salesman s : listOfAllSalesmen) {			
			System.out.println(s);
		}
		
		System.out.println("\n::: TEST 04 - Insert :::");
		
		Date date = null;
		try {
			date = sdf.parse("08/05/1987");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Salesman mySalesman = new Salesman(
				null,
				"Chandler",
				"chandler.bing@gmail.com",
				date,
				7000.0,
				new Department(2, null)
				);
		
		salesmanDAO.insert(mySalesman);
		System.out.println("New Salesman inserted! ID: " + mySalesman.getId());
		
		System.out.println("\n::: TEST 05 - Update :::");
		
		Date dateUpdate = null;
		try {
			dateUpdate = sdf.parse("08/05/1987");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Salesman mySalesmanUpdate = new Salesman(
				13,
				"Kayo",
				"kayo.magno@gmail.com",
				dateUpdate,
				7000.0,
				new Department(2, null)
				);
		
		salesmanDAO.update(mySalesmanUpdate);
		System.out.println("Salesman updated! ID: " + mySalesmanUpdate.getId());
		
		System.out.println("\n::: TEST 06 - Delete :::");
		salesmanDAO.deleteById(24);
		salesmanDAO.deleteById(14);
		salesmanDAO.deleteById(25);
		salesmanDAO.deleteById(26);
		
	}

}

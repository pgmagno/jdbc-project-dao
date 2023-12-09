package model.dao;

import java.util.List;

import model.entities.Salesman;

public interface SalesmanDAO {
	void insert(Salesman obj);
	void update(Salesman obj);
	void deleteById(Integer id);
	Salesman findById(Integer id);
	List<Salesman> findByDepartmentId(Integer id);
	List<Salesman> findAll();
}

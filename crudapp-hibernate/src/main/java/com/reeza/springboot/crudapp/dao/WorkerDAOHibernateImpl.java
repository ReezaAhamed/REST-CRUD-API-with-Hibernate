package com.reeza.springboot.crudapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.reeza.springboot.crudapp.entity.Worker;

@Repository
public class WorkerDAOHibernateImpl implements WorkerDAO {
	
	//define field for entity manager 
	@Autowired
	private EntityManager entityManager; //Spring automatically creates for us, so just inject

	@Override
	//@Transactional //handles transaction management, so we don't have to manually start and commit transaction
		//removed this annotation from DAO, because, it's a work that should be done by the Service
	public List<Worker> findAll() {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class); //current session is given as the argument
		
		//create the query
		Query<Worker> query = currentSession.createQuery("from Worker", Worker.class);
		
		//execute the query and get the result list
		List<Worker> workers = query.getResultList();
		
		//return the result list
		return workers;
	}

	@Override
	public Worker findById(int id) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class); 
		
		//get the worker
		Worker worker = currentSession.get(Worker.class, id);
		
		//return the result list
		return worker;
	}

	@Override
	public void save(Worker worker) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class); 
		
		//save the worker
		currentSession.saveOrUpdate(worker);
	}

	@Override
	public void deleteById(int wId) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class); 
		
		// create a query to delete the object with primary key
		Query query = currentSession.createQuery("delete from Worker where id=:workerId"); //Worker, capital W, cuz that's the entity in Java
		
		//set the parameter for the query
		query.setParameter("workerId", wId);
		
		//execute the query
		query.executeUpdate();	
	}

}

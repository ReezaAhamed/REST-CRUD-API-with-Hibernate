package com.reeza.springboot.crudapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reeza.springboot.crudapp.dao.WorkerDAO;
import com.reeza.springboot.crudapp.entity.Worker;

@Service
public class WorkerServiceImpl implements WorkerService {
	
	@Autowired
	//@Qualifier("WorkerDAOHibernateIJPAmpl")
	private WorkerDAO workerDAO;

	@Override
	@Transactional
	public List<Worker> findAll() {
		
		return workerDAO.findAll(); //just delegate the call to the DAO
	}

	@Override
	@Transactional
	public Worker findById(int id) {
		
		return workerDAO.findById(id); //just delegate the call to the DAO
	}

	@Override
	@Transactional
	public void save(Worker worker) {
		
		workerDAO.save(worker); //just delegate the call to the DAO
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		
		workerDAO.deleteById(id); //just delegate the call to the DAO
	}

}

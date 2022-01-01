package com.reeza.springboot.crudapp.dao;

import java.util.List;

import com.reeza.springboot.crudapp.entity.Worker;

public interface WorkerDAO {
	public List<Worker> findAll();
	public Worker findById(int id);
	public void save(Worker worker);
	public void deleteById(int id);
}

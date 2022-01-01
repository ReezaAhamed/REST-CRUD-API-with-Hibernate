package com.reeza.springboot.crudapp.service;

import java.util.List;

import com.reeza.springboot.crudapp.entity.Worker;

public interface WorkerService {
	
	public List<Worker> findAll();
	public Worker findById(int id);
	public void save(Worker worker);
	public void deleteById(int id);
	
}

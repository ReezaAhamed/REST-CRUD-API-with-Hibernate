package com.reeza.springboot.crudapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reeza.springboot.crudapp.entity.Worker;
import com.reeza.springboot.crudapp.service.WorkerService;

@RestController
@RequestMapping("/api")
public class WorkerRestController {
	
	@Autowired
	//private WorkerDAO workerDAO; => Removed, because, In the correct project structure controller should use the service,
										// sevice delegates the calls to dao and dao communicates with the database
	private WorkerService workerService; 
	
	// expose "/workers" and return list of workers
	@GetMapping("/workers")
	public List<Worker> findAll(){
		
		//delegate the call to workerService
		return workerService.findAll();
	}
	
	// add mapping for GET /workers/{id}
	@GetMapping("/workers/{wId}")
	public Worker findWorkerById(@PathVariable int wId) { //PathVariable name should match the name of the variable in mapping
														//As you know method name doesn't matter at all! 
		//delegate the call to workerService
		Worker worker = workerService.findById(wId);	
		
		//IMPORTANT!!! : Always if the worker is there or not should be checked
		if (worker == null) {
			throw new RuntimeException("Worker id = " + wId + " is not found.");
		}
		
		return worker;
	} 
	
	// add a new worker : add mapping for POST /workers, (whenever JSON body is sent via @RequestBody, 
							//endpoint doesn't contain individual id like workers/{id})
	@PostMapping("/workers")
	public Worker addWorker(@RequestBody Worker worker) { //@RequestBody is the main thing when adding
																//otherwise everything will be saved null (except for id)
		
		//IMPORTANT!!! : Remember, the id is automatically generated (auto increment in our database).
							//Incase, if someone passes an id in JSON => set the id to 0,
							// this is to force a save of new item... instead of update
		worker.setId(0);
		
		//delegate the call to workerService
		workerService.save(worker);
		
		return worker;
	}
	
	// update an existing worker : add mapping for PUT /workers, , (whenever JSON body is sent @RequestBody, 
									//endpoint doesn't contain individual id like workers/{id})
	@PutMapping("/workers")
	public Worker updateWorker(@RequestBody Worker worker) {
		
		// This time RequestBody JSON is sent with the id as we're updating an existing worker;
		
		// IMPORTANT : Check the worker is already existing
		int id = worker.getId();
		Worker tempWorker = workerService.findById(id); //Remember, this is a hibernate query, 
														 //don't confuse with @GetMapping ("workers/{wId}"}
		
		if(tempWorker == null ) {
			throw new RuntimeException("Worker id = " + id + " is not found.");
		} //else { // else statement is not needed, because, program terminates here incase of an exception 
			
		workerService.save(worker);	

		return worker; 
	}
	
	// delete an existing employee : add mapping for DELETE /workers/{wId} 
	@DeleteMapping("/workers/{wId}")
	public String deleteWorker(@PathVariable int wId) {
		
		Worker tempWorker = workerService.findById(wId); //Remember, this is a hibernate query, 
														//don't confuse with @GetMapping ("workers/{wId}"}
		if(tempWorker == null ) {
			throw new RuntimeException("Worker id = " + wId + " is not found.");
		} 
			
		workerService.deleteById(wId);
			
		return "Deleted the employee id : " + wId;
	}

}

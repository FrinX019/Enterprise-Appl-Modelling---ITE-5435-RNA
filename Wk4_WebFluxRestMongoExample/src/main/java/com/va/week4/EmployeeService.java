package com.va.week4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	public Flux<Employee> getAll() {

        return empRepo.findAll().switchIfEmpty(Flux.empty());

  }
	
	public Mono<Employee> getById(final String id) {

        return empRepo.findById(id);

  }	
	
	
	public Mono update(final String id, final Employee employee) {

        return empRepo.save(employee);

  }
	
	public Mono save(final Employee employee) {

        return empRepo.save(employee);

  }


}

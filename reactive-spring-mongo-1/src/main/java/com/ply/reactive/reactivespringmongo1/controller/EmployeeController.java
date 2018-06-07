package com.ply.reactive.reactivespringmongo1.controller;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ply.reactive.reactivespringmongo1.model.Employee;
import com.ply.reactive.reactivespringmongo1.model.EmployeeEvent;
import com.ply.reactive.reactivespringmongo1.repository.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("rest/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public EmployeeController(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@GetMapping("/all")
	public Flux<Employee> getAll(){
		return employeeRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<Employee> getById(@PathVariable final String id){
		return employeeRepository.findById(id);
	}
	
	@GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<EmployeeEvent> getEvents(@PathVariable final String id){
		System.out.println(Thread.getAllStackTraces());
		return employeeRepository.findById(id).flatMapMany(employee -> {
			Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
			Flux<EmployeeEvent> employeeEventFlux = 
					Flux.fromStream(Stream.generate(() -> new EmployeeEvent(employee, new Date()))
							
					);
			return Flux.zip(interval, employeeEventFlux)
					.map(object -> {
						return object.getT2();
					});
		});
	}

}

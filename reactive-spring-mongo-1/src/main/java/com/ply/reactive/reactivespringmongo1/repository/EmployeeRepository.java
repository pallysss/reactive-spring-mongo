package com.ply.reactive.reactivespringmongo1.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.ply.reactive.reactivespringmongo1.model.Employee;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {

}

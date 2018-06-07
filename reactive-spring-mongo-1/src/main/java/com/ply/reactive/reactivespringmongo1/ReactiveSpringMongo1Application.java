package com.ply.reactive.reactivespringmongo1;

import java.util.UUID;
import java.util.stream.Stream;

import org.mockito.internal.progress.ArgumentMatcherStorage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ply.reactive.reactivespringmongo1.model.Employee;
import com.ply.reactive.reactivespringmongo1.repository.EmployeeRepository;

@SpringBootApplication
public class ReactiveSpringMongo1Application {
	
	@Bean
	CommandLineRunner employees(EmployeeRepository employeeRepository) {
		return args -> {
			employeeRepository.deleteAll()
			.subscribe(null,null,() -> {
				Stream.of(new Employee(UUID.randomUUID().toString(), "Peter", 23000L),
						new Employee(UUID.randomUUID().toString(), "John", 21000L),
						new Employee(UUID.randomUUID().toString(), "Adam", 20000L),
						new Employee(UUID.randomUUID().toString(), "Chris", 19000L))
				.forEach(employee -> {
					employeeRepository.save(employee)
					.subscribe(emp -> System.out.print(emp));
				});
				//
			})
			;
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactiveSpringMongo1Application.class, args);
	}
}

package com.ply.reactive.reactivespringmongo1.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data 
@Document
public class EmployeeEvent {
	private Employee employee;
	private Date date;
	
	

}

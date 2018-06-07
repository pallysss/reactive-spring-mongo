package com.ply.reactive.reactivespringmongo1.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@EqualsAndHashCode
@ToString
@Getter
@Setter
@AllArgsConstructor
@Data
@Document
public class Employee {
	
	@Id
	private String id;
	private String name;
	private Long salary;
	

}

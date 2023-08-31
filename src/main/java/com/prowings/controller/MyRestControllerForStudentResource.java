package com.prowings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prowings.model.Student;
import com.prowings.service.VotingApiService;

@RestController
public class MyRestControllerForStudentResource {
	
	@Autowired
	VotingApiService apiService;
	
	
	@GetMapping("/get_all_students")
	public ResponseEntity<List<Student>> getAllStudents()
	{
		System.out.println("inside controller...");
		return apiService.getAllStudents();
	}

	@GetMapping("/get_all_students/{subject}")
	public ResponseEntity<List<Student>> getAllStudentsWithPerticularSubject(@PathVariable("subject") String subject)
	{
		System.out.println("inside controller...");
		return apiService.getAllStudentsWithPerticularSubject(subject);
	}

	@PostMapping("/create_student")
	public ResponseEntity<Student> createNewStudent(@RequestBody Student std)
	{
		System.out.println("inside create student controller...");
		return apiService.createStudent(std);
	}
	

	@PostMapping("/update_student")
	public ResponseEntity<String> updateStudent(@RequestBody Student std)
	{
		System.out.println("inside create student controller update method...");
		return apiService.updateStudent(std);
	}

	@GetMapping("/delete_student/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") int id)
	{
		System.out.println("inside delete student controller update method...");
		return apiService.deleteStudent(id);
	}
	
}

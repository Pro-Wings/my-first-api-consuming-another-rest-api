package com.prowings.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.prowings.model.Person;
import com.prowings.model.Student;

@Service
public class VotingApiService {

	@Autowired
	RestTemplate restTemplate;

	public ResponseEntity<List<Student>> getAllStudents() {

		String url = "http://localhost:8080/my-first-rest-api-using-spring-mvc/students";

		List<Student> list = restTemplate.getForObject(url, List.class);

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("xyz", "123");
		resHeaders.add("pqr", "aaaa");
		
//		ResponseEntity<List<Student>> res = new ResponseEntity<List<Student>>(list, resHeasders, HttpStatus.OK);
//		return res;
		
		return ResponseEntity.ok().headers(resHeaders).body(list);
	}

	public ResponseEntity<Student> createStudent(Student std) {
		
		String url = "http://localhost:8080/my-first-rest-api-using-spring-mvc/students";
		
		Student retrivedStd = restTemplate.postForObject(url, std, Student.class);
		
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("xyz", "123");
		resHeaders.add("pqr", "aaaa");
		
		return ResponseEntity.ok().headers(resHeaders).body(retrivedStd);
	}

	public ResponseEntity<String> doVoting() {

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("xyz", "123");
		resHeaders.add("myheader", "aaaa");

		Person person = new Person(14, "QQQ", 20, "India");

		String body = restTemplate.postForObject("http://localhost:8080/my-first-rest-api-using-spring-mvc/vote",
				person, String.class);

		ResponseEntity result = new ResponseEntity(body, resHeaders, HttpStatus.ALREADY_REPORTED);

		return result;

	}

}

package com.prowings.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.prowings.interceptors.MyRequestLoggerInterceptor;
import com.prowings.model.Person;
import com.prowings.model.Student;
import com.prowings.model.StudentList;
import com.prowings.model.Subject;

@Service
public class VotingApiService {

	@Autowired
	RestTemplate restTemplate;

	public ResponseEntity<List<Student>> getAllStudents() {

		String url = "http://localhost:8080/my-first-rest-api-using-spring-mvc/students";

		List<ClientHttpRequestInterceptor> myInterceptorList = new ArrayList<>();
		myInterceptorList.add(new MyRequestLoggerInterceptor());
//		myInterceptorList.add(new MyValidationInterceptor());
		
		restTemplate.setInterceptors(myInterceptorList);
		
//		restTemplate.setInterceptors(Collections.singletonList(new MyRequestLoggerInterceptor()));
		
		List<Student> list = restTemplate.getForObject(url, List.class);

//		ResponseEntity<Student[]> response = restTemplate.exchange(url, HttpMethod.GET, null, Student[].class);

//		Student[] stds = response.getBody();

//		RequestCallback requestCallback = request -> {
//		    request.getHeaders().set("abc", "123");
//		};
//		
//		ResponseExtractor<String> responseExtractor = response -> {
//			
//			
//		    BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()));
//		    StringBuilder responseBody = new StringBuilder();
//		    String line;
//		    while ((line = reader.readLine()) != null) {
//		        responseBody.append(line);
//		    }
//		    return responseBody.toString();
//		};

//		String resp = restTemplate.execute(url, HttpMethod.GET, requestCallback, responseExtractor);

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("xyz", "123");
		resHeaders.add("pqr", "aaaa");

//		ResponseEntity<List<Student>> res = new ResponseEntity<List<Student>>(list, resHeasders, HttpStatus.OK);
//		return res;

		return ResponseEntity.ok().headers(resHeaders).body(list);
	}

	public ResponseEntity<List<Student>> getAllStudentsWithPerticularSubject(String sub) {

		String url = "http://localhost:8080/my-first-rest-api-using-spring-mvc/students";

		Student[] studentsArr = restTemplate.getForObject(url, Student[].class);

		List<Student> filteredStudents = new ArrayList();

		for (Student s : studentsArr) {
			for (Subject subject : s.getSubjects()) {
				if (subject.getName().equalsIgnoreCase(sub))
					filteredStudents.add(s);
			}
		}

//		List<Student> filteredStudents = filterStudentsBySubject(sub, list);

		return ResponseEntity.ok().body(filteredStudents);
	}

	public ResponseEntity<Student> createStudent(Student std) {

		String url = "http://localhost:8080/my-first-rest-api-using-spring-mvc/students";

		restTemplate.setInterceptors(Collections.singletonList(new MyRequestLoggerInterceptor()));
		
		Student retrivedStd = restTemplate.postForObject(url, std, Student.class);

		UUID txnId = UUID.randomUUID();
		
		String transactionId = txnId.toString();
		
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("xyz", "123");
		resHeaders.add("pqr", "aaaa");
		resHeaders.add("transactionId", transactionId);

		return ResponseEntity.ok().headers(resHeaders).body(retrivedStd);
	}

	public ResponseEntity<String> updateStudent(Student std) {

		String url = "http://localhost:8080/my-first-rest-api-using-spring-mvc/students";

		try {
			restTemplate.put(url, std);
		} catch (RestClientException e) {
			System.out.println("Exception while calling update api!!" + e.getMessage());
			return new ResponseEntity<String>("Error while updating the student!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("xyz", "123");
		resHeaders.add("pqr", "aaaa");

		return ResponseEntity.ok().headers(resHeaders).body("Updated successfully!!");
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

	public ResponseEntity<String> deleteStudent(int id) {
		String url = "http://localhost:8080/my-first-rest-api-using-spring-mvc/students/"+id;
		System.out.println(">>>> delete url : "+url);
		try {
			restTemplate.delete(url);
		} catch (RestClientException e) {
			System.out.println("Exception while calling delete api!!" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<String>("Error while deleting the student!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("xyz", "123");
		resHeaders.add("pqr", "aaaa");

		return ResponseEntity.ok().headers(resHeaders).body("Deleted successfully!!");

	}

}

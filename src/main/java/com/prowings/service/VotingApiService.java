package com.prowings.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.prowings.model.Person;

@Service
public class VotingApiService {
	
	@Autowired
	RestTemplate restTemplate;
	
	
	public ResponseEntity<String> doVoting()
	{
		
		Person person = new Person(12, "XYZ", 23, "India");
		
		ResponseEntity<String> result = new ResponseEntity<String>(HttpStatus.CREATED);
		//	public <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request,		Class<T> responseType, Object... uriVariables)
		result = restTemplate.postForEntity("http://localhost:8080/my-first-rest-api-using-spring-mvc/vote", person, String.class );
		
		return result;
		
	}

}

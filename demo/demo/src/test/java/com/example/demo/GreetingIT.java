package com.example.demo;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class GreetingIT {

	@LocalServerPort
	private int port;

	@Autowired
	private CalcController controller;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() throws Exception{
		assertNotNull(controller);
	}

	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception{
		assertTrue(this.restTemplate.getForObject("http://localhost:"+port+"/",String.class).contains("Hello World"));
	}

	@Test
	public void greetingShouldIncludeNameOfUser() throws Exception{
		String name="Ryan";
		//System.out.println(this.restTemplate.getForObject("http://localhost:"+port+"/?name="+name,String.class));
		assertTrue(this.restTemplate.getForObject("http://localhost:"+port+"/?name="+name,String.class).contains(name));
	}

}

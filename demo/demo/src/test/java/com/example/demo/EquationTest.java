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
public class EquationTest {

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
  public void makingTwoEquationsWithSameValuesShouldBeEqualObjects() throws Exception{
    Equation e1=new Equation("1", "2", "3");
    Equation e2=new Equation("1", "2", "3");
    assertTrue(e1.equals(e2));
  }

  @Test
  public void makingTwoEquationsWithSameValuesShouldHaveSameToString() throws Exception{
    Equation e1=new Equation("1", "2", "3");
    Equation e2=new Equation("1", "2", "3");
    assertEquals(e1.toString(), e2.toString());
  }

  @Test
  public void hashOfEquationShouldNotBeNull() throws Exception{
    Equation e=new Equation("0", "0", "0");
    assertNotNull(e.hashCode());
  }

}

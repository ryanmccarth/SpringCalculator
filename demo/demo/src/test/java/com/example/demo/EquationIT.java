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
public class EquationIT {

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

  //----------------------------------ADDITION TESTS---------------------------------------
  @Test
  public void additionWithTwoParametersShouldReturnDouble() throws Exception{
    String x="12";
    String y="3";
    String sum="15";
    String results=this.restTemplate.getForObject("http://localhost:"+port+"/add?x="+x+"&"+"y="+y, String.class);
    assertTrue(results.contains(sum));
  }

  @Test
  public void additionWithOnlyXShouldReturnXAndDefaultY() throws Exception{
    String x="7";
    String results=this.restTemplate.getForObject("http://localhost:"+port+"/add?x="+x, String.class);
    assertTrue(results.contains(x));
    assertTrue(results.contains("0"));
  }

  @Test
  public void additionWithOnlyYShouldReturnY() throws Exception{
    String y="11";
    String results=this.restTemplate.getForObject("http://localhost:"+port+"/add?y="+y, String.class);
    assertTrue(results.contains(y));
    assertTrue(results.contains("0"));
  }

  @Test
  public void additionWithNoParamatersShouldDefaultToZeros() throws Exception{
    String results=this.restTemplate.getForObject("http://localhost:"+port+"/add", String.class);
    int count=0;
    for(int i=0; i<results.length(); i++){
      if(results.charAt(i)=='0')count++;
    }
    //4 zeros in answer, one for x, one for y, and two for result: 0.0
    assertEquals(count, 4);
  }



  //----------------------------------SUBTRACTION TESTS---------------------------------------
  @Test
  public void subtractionWithTwoParametersShouldReturnDouble() throws Exception{
    String x="14";
    String y="6";
    String dif="8";
    String results=this.restTemplate.getForObject("http://localhost:"+port+"/sub?x="+x+"&"+"y="+y, String.class);
    assertTrue(results.contains(dif));
  }

  @Test
  public void subtractionWithOnlyXShouldReturnXAndDefaultY() throws Exception{
    String x="9";
    String results=this.restTemplate.getForObject("http://localhost:"+port+"/sub?x="+x, String.class);
    assertTrue(results.contains(x));
    assertTrue(results.contains("0"));
  }

  @Test
  public void subtractionWithOnlyYShouldReturnNegativeY() throws Exception{
    String y="11";
    String results=this.restTemplate.getForObject("http://localhost:"+port+"/sub?y="+y, String.class);
    assertTrue(results.contains(y));
    assertTrue(results.contains("-"+y));
    assertTrue(results.contains("0"));
  }

  @Test
  public void subtractionWithNoParamatersShouldDefaultToZeros() throws Exception{
    String results=this.restTemplate.getForObject("http://localhost:"+port+"/sub", String.class);
    int count=0;
    for(int i=0; i<results.length(); i++){
      if(results.charAt(i)=='0')count++;
    }
    //4 zeros in answer, one for x, one for y, and two for result: 0.0
    assertEquals(count, 4);
  }

  //----------------------------------MULTIPLICATION TESTS---------------------------------------
  @Test
  public void multiplicationWithTwoParametersShouldReturnDouble() throws Exception{
    String x="7";
    String y="4";
    String product="28";
    String results=this.restTemplate.getForObject("http://localhost:"+port+"/mult?x="+x+"&"+"y="+y, String.class);
    assertTrue(results.contains(product));
  }

  @Test
  public void multiplicationWithOnlyXShouldReturn0AndDefaultY() throws Exception{
		int count=0;
    String x="9";
    String results=this.restTemplate.getForObject("http://localhost:"+port+"/mult?x="+x, String.class);
    assertTrue(results.contains(x));
		for(int i=0; i<results.length(); i++){
      if(results.charAt(i)=='0')count++;
    }
		assertEquals(count, 3);
  }

  @Test
  public void multiplicationWithOnlyYShouldReturn0AndDefaultX() throws Exception{
		int count=0;
    String y="11";
    String results=this.restTemplate.getForObject("http://localhost:"+port+"/mult?y="+y, String.class);
    assertTrue(results.contains(y));
		for(int i=0; i<results.length(); i++){
      if(results.charAt(i)=='0')count++;
    }
		assertEquals(count, 3);
  }

  @Test
  public void multiplicationWithNoParamatersShouldDefaultToZeros() throws Exception{
    String results=this.restTemplate.getForObject("http://localhost:"+port+"/mult", String.class);
    int count=0;
    for(int i=0; i<results.length(); i++){
      if(results.charAt(i)=='0')count++;
    }
    //4 zeros in answer, one for x, one for y, and two for result: 0.0
    assertEquals(count, 4);
  }


  //----------------------------------DIVISION TESTS---------------------------------------
	@Test
	public void divisionWithTwoParametersShouldReturnDouble() throws Exception{
		String x="20";
		String y="4";
		String quotient="5";
		String results=this.restTemplate.getForObject("http://localhost:"+port+"/div?x="+x+"&"+"y="+y, String.class);
		assertTrue(results.contains(quotient));
	}

	@Test
	public void divisionWithOnlyXShouldReturnXAndDefaultY() throws Exception{
		char x='7';
		int count=0;
		String results=this.restTemplate.getForObject("http://localhost:"+port+"/div?x="+x, String.class);
		assertTrue(results.contains("1"));
		for(int i=0; i<results.length(); i++){
			if(results.charAt(i)==x)count++;
		}
		assertEquals(count, 2);
	}

	@Test
	public void divisionWithOnlyYShouldReturn0AndDefaultX() throws Exception{
		int count=0;
		String y="6";
		String results=this.restTemplate.getForObject("http://localhost:"+port+"/div?y="+y, String.class);
		assertTrue(results.contains(y));
		for(int i=0; i<results.length(); i++){
			if(results.charAt(i)=='0')count++;
		}
		assertEquals(count, 3);
	}

	@Test
	public void divisionWithNoParamatersShouldDefaultToZeroAndOne() throws Exception{
		String results=this.restTemplate.getForObject("http://localhost:"+port+"/div", String.class);
		int count=0;
		for(int i=0; i<results.length(); i++){
			if(results.charAt(i)=='0')count++;
		}
		//3 zeros in answer, one for x and two for result: 0.0
		assertEquals(count, 3);
		assertTrue(results.contains("1"));
	}

}

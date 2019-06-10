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
  // @Test
  // public void subtractionWithTwoParametersShouldReturnDouble() throws Exception{
  //   String x="14";
  //   String y="6";
  //   String dif="8";
  //   String results=this.restTemplate.getForObject("http://localhost:"+port+"/sub?x="+x+"&"+"y="+y, String.class);
  //   assertTrue(results.contains(dif));
  // }
  //
  // @Test
  // public void subtractionWithOnlyXShouldReturnXAndDefaultY() throws Exception{
  //   String x="9";
  //   String results=this.restTemplate.getForObject("http://localhost:"+port+"/sub?x="+x, String.class);
  //   assertTrue(results.contains(x));
  //   assertTrue(results.contains("0"));
  // }
  //
  // @Test
  // public void subtractionWithOnlyYShouldReturnNegativeY() throws Exception{
  //   String y="11";
  //   String results=this.restTemplate.getForObject("http://localhost:"+port+"/sub?y="+y, String.class);
  //   assertTrue(results.contains(y));
  //   assertTrue(results.contains("-"+y));
  //   assertTrue(results.contains("0"));
  // }
  //
  // @Test
  // public void subtractionWithNoParamatersShouldDefaultToZeros() throws Exception{
  //   String results=this.restTemplate.getForObject("http://localhost:"+port+"/sub", String.class);
  //   int count=0;
  //   for(int i=0; i<results.length(); i++){
  //     if(results.charAt(i)=='0')count++;
  //   }
  //   //4 zeros in answer, one for x, one for y, and two for result: 0.0
  //   assertEquals(count, 4);
  // }


  //----------------------------------DIVISION TESTS---------------------------------------
  // @Test
  // public void subtractionWithTwoParametersShouldReturnDouble() throws Exception{
  //   String x="14";
  //   String y="6";
  //   String dif="8";
  //   String results=this.restTemplate.getForObject("http://localhost:"+port+"/sub?x="+x+"&"+"y="+y, String.class);
  //   assertTrue(results.contains(dif));
  // }
  //
  // @Test
  // public void subtractionWithOnlyXShouldReturnXAndDefaultY() throws Exception{
  //   String x="9";
  //   String results=this.restTemplate.getForObject("http://localhost:"+port+"/sub?x="+x, String.class);
  //   assertTrue(results.contains(x));
  //   assertTrue(results.contains("0"));
  // }
  //
  // @Test
  // public void subtractionWithOnlyYShouldReturnNegativeY() throws Exception{
  //   String y="11";
  //   String results=this.restTemplate.getForObject("http://localhost:"+port+"/sub?y="+y, String.class);
  //   assertTrue(results.contains(y));
  //   assertTrue(results.contains("-"+y));
  //   assertTrue(results.contains("0"));
  // }
  //
  // @Test
  // public void subtractionWithNoParamatersShouldDefaultToZeros() throws Exception{
  //   String results=this.restTemplate.getForObject("http://localhost:"+port+"/sub", String.class);
  //   int count=0;
  //   for(int i=0; i<results.length(); i++){
  //     if(results.charAt(i)=='0')count++;
  //   }
  //   //4 zeros in answer, one for x, one for y, and two for result: 0.0
  //   assertEquals(count, 4);
  // }

}

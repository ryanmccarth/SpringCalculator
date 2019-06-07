package com.example.demo;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTest {

	@Test
	public void alwaysTrue() {
		assertTrue(true);
	}

	@Test
	public void alwaysFalse() {
		assertFalse(false);
	}

	@Test
  public void equationGetters(){
		Equation e=new Equation(2,2,4);
	 	assertEquals(e.getX()+e.getY(), e.getResult(), 4);
	}

}

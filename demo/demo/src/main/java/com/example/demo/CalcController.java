package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalcController{

  private static final String template="Hello, %s!";
  private int counter=0;

  //greeting on startup
  @RequestMapping("/")
  public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name){
    counter++;
    return new Greeting(counter, String.format(template, name));
  }

  //addition
  @RequestMapping("/add")
  public Equation addition(@RequestParam(defaultValue="0")String x, @RequestParam(defaultValue="0")String y){
    double numX=Integer.parseInt(x);
    double numY=Integer.parseInt(y);
    double result=numX+numY;
    return new Equation(numX, numY, result);
  }

  //subtraction
  @RequestMapping("/sub")
  public Equation subtraction(@RequestParam(defaultValue="0")String x, @RequestParam(defaultValue="0")String y){
    double numX=Integer.parseInt(x);
    double numY=Integer.parseInt(y);
    double result=numX-numY;
    return new Equation(numX, numY, result);
  }

  //multiplication
  @RequestMapping("/mult")
  public Equation multiplication(@RequestParam(defaultValue="0")String x, @RequestParam(defaultValue="0")String y){
    double numX=Integer.parseInt(x);
    double numY=Integer.parseInt(y);
    double result=numX*numY;
    return new Equation(numX, numY, result);
  }

  //division
  @RequestMapping("/div")
  public Equation division(@RequestParam(defaultValue="0")String x, @RequestParam(defaultValue="1")String y){
    double numX=Integer.parseInt(x);
    double numY=Integer.parseInt(y);
    double result=numX/numY;
    return new Equation(numX, numY, result);
  }

  //error for all other requests
  @RequestMapping("/**")
  public String error(){
    return "Invalid address, ";
  }
}

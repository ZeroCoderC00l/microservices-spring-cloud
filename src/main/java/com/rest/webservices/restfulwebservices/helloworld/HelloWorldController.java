package com.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping(path="/hello-world")
	public String hello() {
		return "Holaa!";
	}
	
	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloBean() {
		return new HelloWorldBean("Hola, ese es mi mensaje");
	}
	
	@GetMapping("/hello-world-bean-path-variable/{message}")
	public HelloWorldBean helloBeanPathVariable(@PathVariable String message) {
		return new HelloWorldBean(String.format("Holaaaaa, %s", message));
	}
}

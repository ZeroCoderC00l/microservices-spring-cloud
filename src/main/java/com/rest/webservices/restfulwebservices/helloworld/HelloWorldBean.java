package com.rest.webservices.restfulwebservices.helloworld;

public class HelloWorldBean {

	private String mesage;

	public HelloWorldBean(String message) {
		this.mesage = message;
	}

	public String getMesage() {
		return mesage;
	}

	public void setMesage(String mesage) {
		this.mesage = mesage;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [mesage=" + mesage + "]";
	}
}

package com.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;

	@GetMapping("/users")
	public List<User> retreiveAllUsers() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		
		//HATEOAS (Hypermedia As The Engine Of Application State). 
		//Returning a Resource with Data and Links.
		Resource<User> resource = new Resource<User>(user);
		
		ControllerLinkBuilder linkAllUsers = linkTo(methodOn(this.getClass()).retreiveAllUsers());
		
		resource.add(linkAllUsers.withRel("all-users"));
		
		return resource;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> save(@Valid @RequestBody User user) {
		User savedUser = service.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);

		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
	}
}

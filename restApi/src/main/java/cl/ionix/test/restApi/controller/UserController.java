package cl.ionix.test.restApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import cl.ionix.test.restApi.model.User;
import cl.ionix.test.restApi.repository.UserRepository;

@RestController()
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	//Retorna todos los usuarios registrados
    @GetMapping("/users")
    List<User> findAll() {
        return userRepository.findAll();
    }

    //Guarda en BD nuevo usuario
    @PostMapping("/newuser")
    @ResponseStatus(HttpStatus.CREATED)
    User addNewUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    // Find
    @GetMapping("/users/{email}")
    List<User> findByEmail(@PathVariable String email) {
        return userRepository.findByEmail(email);
    }
	
}

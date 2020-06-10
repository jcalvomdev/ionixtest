package cl.ionix.test.restApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ionix.test.restApi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findByEmail(String email);
}

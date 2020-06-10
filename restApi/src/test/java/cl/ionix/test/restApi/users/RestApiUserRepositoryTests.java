package cl.ionix.test.restApi.users;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cl.ionix.test.restApi.model.User;
import cl.ionix.test.restApi.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
class RestApiUserRepositoryTests {

	@Autowired
	private UserRepository userRepository;
	

	@Test
	void saveUser() {
		assertNotNull(this.userRepository);
		
		User user = new User();
		user.setUserName("test");
		user.setEmail("test@test.cl");
		user.setName("testname");
		user.setPhone(123456789012345L);
		
		user = this.userRepository.save(user);
		assertNotNull(user.getUserId());
		
	}
	
	@Test
	void queryUserByID() {
		assertNotNull(this.userRepository);
		
		Optional<User> queryResult = this.userRepository.findById(1L);
		assertTrue(queryResult.isPresent());
		
		User user = queryResult.get();
		assertTrue(user.getUserId().equals(1L));
	}
	
	@Test
	void queryUserByMail() {
		assertNotNull(this.userRepository);
		
		List<User> users = this.userRepository.findByEmail("jcalvo@abc.cl");
		assertNotNull(users);
		assertTrue(!users.isEmpty());
		
		User firstUser =  users.get(0);
		assertNotNull(firstUser);
		assertTrue(firstUser.getEmail().equals("jcalvo@abc.cl"));
	}
	
	@Test
	void deleteUser() {
		assertNotNull(this.userRepository);
		
		User user = new User();
		user.setUserName("test");
		user.setEmail("test@test.cl");
		user.setName("testname");
		user.setPhone(123456789012345L);
		
		user = this.userRepository.save(user);
		
		assertNotNull(user.getUserId());
		
		this.userRepository.deleteById(user.getUserId());
		
		Optional<User> queryResult = this.userRepository.findById(user.getUserId());
		assertTrue(!queryResult.isPresent());
	}

}

package cl.ionix.test.restApi.users;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import cl.ionix.test.restApi.controller.UserController;
import cl.ionix.test.restApi.model.User;
import cl.ionix.test.restApi.repository.UserRepository;

@WebMvcTest(UserController.class)
class RestApiUserControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	void findAllUsers() throws Exception {
		
		User firstUser = new User();
		firstUser.setUserName("test");
		firstUser.setEmail("test@test.cl");
		firstUser.setName("testname");
		firstUser.setPhone(123456789012345L);
		
		when(userRepository.findAll())
        .thenReturn(Arrays.asList(firstUser));
		
		this.mockMvc.perform(get("/users") 
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isOk())
		           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		           .andExpect(jsonPath("$", hasSize(1)))
		           .andExpect(jsonPath("$[0].userName", is("test")));
	}
	
	@Test
	void saveUser() throws Exception {
		User firstUser = new User();
		firstUser.setUserId(1L);
		firstUser.setUserName("test");
		firstUser.setEmail("test@test.cl");
		firstUser.setName("testname");
		firstUser.setPhone(123456789012345L);
		
		when(userRepository.save(Mockito.any(User.class) ))
        .thenReturn(firstUser);
		
		this.mockMvc.perform(post("/newuser") 
					.content("{ \"userId\": 1, "
							+ "\"userName\": \"test\", "
							+ "\"email\": \"test@test.cl\", "
							+ "\"name\": \"test\", "
							+ "\"phone\": 123456789012345"
							+ "}") 
					.accept(MediaType.APPLICATION_JSON))
		           	.andExpect(status().isCreated());
	}
	
	@Test
	void findUserByMail() throws Exception {
		User firstUser = new User();
		firstUser.setUserName("test");
		firstUser.setEmail("test@test.cl");
		firstUser.setName("testname");
		firstUser.setPhone(123456789012345L);
		
		when(userRepository.findByEmail("test@test.cl"))
        .thenReturn(Arrays.asList(firstUser));
		
		this.mockMvc.perform(get("/users/{email}","test@test.cl") 
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isOk())
		           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		           .andExpect(jsonPath("$", hasSize(1)))
		           .andExpect(jsonPath("$[0].email", is("test@test.cl")));
	}

}

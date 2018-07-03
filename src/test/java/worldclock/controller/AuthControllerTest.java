package worldclock.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import worldclock.entity.User;
import worldclock.service.UserService;
import worldclock.utils.Message;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AuthController.class)
public class AuthControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService service;

	@Test
	public void testAddGuest() throws Exception {

		Mockito.when(service.addGuest(1)).thenReturn("8f14e45fceea167a5a36dedd4bea2543");

		mockMvc.perform(get("/addGuest"))
				.andExpect(status().isOk())
				.andExpect(content().string("8f14e45fceea167a5a36dedd4bea2543"));

	}
	
	@Test
	public void testSignUp() throws Exception {
		
		Message message = new Message(200, "Sign up success!");
		
		String userExample = "{\"sessionId\": \"8f14e45fceea167a5a36dedd4bea2543\",\"username\": \"thien\",\"password\": \"1\"}";
		
		String jsonExpected = "{code:200,message:Sign up success!}";
		
//		when(service.addUser(any(User.class))).thenReturn(message);
		
//		Mockito.doReturn(message)
//			.when(service)
//			.addUser(Mockito.any(User.class));
		
		
		
		mockMvc.perform(
				post("/signUp")
				.accept(MediaType.APPLICATION_JSON)
				.content(userExample)
				.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(content().json(jsonExpected));
		
	}



}

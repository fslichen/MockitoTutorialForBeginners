package evolution.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import evolution.controller.dto.PostRequest;
import evolution.service.AnyService;

public class AnyControllerTest {
	@Mock
	private AnyService anyService;
	
	@InjectMocks
	private AnyController anyController;
	
	private MockMvc mockMvc;
	
    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);// Initializes fields annotated with Mockito annotations.
        mockMvc = MockMvcBuilders// Builds a MockMvc instance by registering one or more @Controller instances and configuring Spring MVC infrastructure programmatically.
                .standaloneSetup(anyController)
                .build();
    }
    
    @Test
    public void testGetCallService() throws Exception {
    		when(anyService.anyString()).thenReturn("Hello");
    		when(anyService.anyInteger()).thenReturn(100);
    		mockMvc.perform(get("/get/call/service"))
    		.andExpect(jsonPath("$.name", is("Hello")))
    		.andExpect(jsonPath("$.gender", is("Hello")))
    		.andExpect(jsonPath("$.age", is(100)));
    }
    
    @Test
    public void testGetDto() throws Exception {
    		when(anyService.anyString()).thenReturn("Hello World");
        mockMvc.perform(get("/get/dto"))
        .andExpect(status().isOk())
		.andExpect(jsonPath("$.name", is("Chen")))// Verify that the name is "Chen".
        .andExpect(jsonPath("$.age", is(27)))
        .andExpect(jsonPath("$.gender", is("M")));
    }
    
    @Test
    public void testGetArray() throws Exception {
        when(anyService.anyString()).thenReturn("Hello World");
        mockMvc.perform(get("/get/array"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))// Verify that the array has size of 1.
		.andExpect(jsonPath("$[0].name", is("Chen")))// Verify that the name attribute of the first element of the array is "Chen".
        .andExpect(jsonPath("$[0].age", is(27)))
        .andExpect(jsonPath("$[0].gender", is("M")));
        verify(anyService, times(1)).anyString();// Verify that anyMethod() is invoked only once in anyService.
        verifyNoMoreInteractions(anyService);// Verify that after the response, no more interactions are made to the anyService.
    }
    
    @Test
    public void testGetResponse() throws Exception {
        MvcResult result = mockMvc.perform(get("/get/response"))
        .andExpect(status().isOk()).andReturn();
        MockHttpServletResponse response = result.getResponse();
        System.out.println(String.format("Content = %s", response.getContentAsString()));
        System.out.println(String.format("Character Encoding = %s", response.getCharacterEncoding()));
        System.out.println(String.format("Content Type = %s", response.getContentType()));
        System.out.println(String.format("Status = %s", response.getStatus()));
    }
    
    @Test
    public void testPostArray() throws Exception {
    		when(anyService.anyInteger()).thenReturn(0);
    		PostRequest request = new PostRequest();
    		request.setAge(27);
    		request.setGender("M");
    		request.setName("Chen");
    		mockMvc.perform(post("/post/array")
    		.contentType(MediaType.APPLICATION_JSON)
    		.content(asJsonString(request)))// Request Body
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$", hasSize(1)))
    		.andExpect(jsonPath("$[0].name", is("Chen")))// Verify that the name attribute of the first element of the array is "Chen".
        .andExpect(jsonPath("$[0].age", is(27)))
        .andExpect(jsonPath("$[0].gender", is("M")));
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

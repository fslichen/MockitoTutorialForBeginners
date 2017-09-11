package evolution.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import evolution.controller.dto.GetResponse;
import evolution.controller.dto.PostRequest;
import evolution.service.AnyService;

@RestController
public class AnyController {
	@Autowired
	private AnyService anyService;
	
	@PostMapping("/post/call/service")
	public GetResponse postCallService() {
		GetResponse response = new GetResponse();
		response.setAge(anyService.originalInteger(27));
		response.setName(anyService.originalString("Chen"));
		response.setGender(anyService.originalString("M"));
		return response;
	}
	
	@GetMapping("/get/path/variable/{name}")
	public GetResponse getPathVariable(@PathVariable("name") String name) {
		GetResponse response = new GetResponse();
		response.setName(name);
		return response;
	}
	
	@GetMapping("/get/request/parameter")
	public GetResponse getRequestParameter(@RequestParam("name") String name) {
		GetResponse response = new GetResponse();
		response.setName(name);
		return response;
	}
	
	@GetMapping("/get/call/service")
	public GetResponse getCallService() {
		GetResponse response = new GetResponse();
		response.setAge(anyService.anyInteger());
		response.setGender(anyService.anyString());
		response.setName(anyService.anyString());
		return response;
	}
	
	@GetMapping("/get/dto")
	public GetResponse getDto() {
		GetResponse response = new GetResponse();
		response.setName("Chen");
		response.setAge(27);
		response.setGender("M");
		return response;
	}
	
	@GetMapping("/get/response")
	public GetResponse getResponse() {
		GetResponse response = new GetResponse();
		response.setName("Chen");
		response.setAge(27);
		response.setGender("M");
		return response;
	}
	
	@GetMapping("/get/array")
	public List<GetResponse> getArray() {
		anyService.anyString();
		GetResponse response = new GetResponse();
		response.setName("Chen");
		response.setAge(27);
		response.setGender("M");
		return Arrays.asList(response);
	}
	
	@PostMapping("/post/array")
	public List<GetResponse> post(@RequestBody PostRequest request) {
		anyService.anyInteger();
		GetResponse response = new GetResponse();
		response.setName(request.getName());
		response.setAge(request.getAge());
		response.setGender(request.getGender());
		return Arrays.asList(response);
	}
}

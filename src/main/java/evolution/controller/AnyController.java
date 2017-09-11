package evolution.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import evolution.controller.dto.GetResponse;
import evolution.controller.dto.PostRequest;
import evolution.service.AnyService;

@RestController
public class AnyController {
	@Autowired
	private AnyService anyService;
	
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

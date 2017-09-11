package evolution.service;

import org.springframework.stereotype.Service;

@Service
public class AnyService {
	public String anyString() {
		return "anyString";
	}
	
	public Integer anyInteger() {
		return 0;
	}
	
	public String originalString(String string) {
		return string;
	}
	
	public Integer originalInteger(Integer integer) {
		return integer;
	}
	
	public void wasteTime() {
		
	}
}

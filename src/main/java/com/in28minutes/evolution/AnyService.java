package com.in28minutes.evolution;

public class AnyService {
	AnotherService anotherService;
	// The lucky values for name are "Chen" and "Ling"
	// The lucky values for gender are "M" and "F"
	// The lucky values for age can be randomly generated.
	// In order to find the lucky values for certain parameter, let the machine ready the code,
	// and pay special attention to the if statement, extract all the strings out, and make 
	// them as lucky values for parameters. 
	// But anyway, just randomly generate the values.
	// Human beings are also allowed to give luckly values when business logic is very complex.
	public boolean anyMethod(String name, String gender, int age) {
		if (!anotherService.anyMethod(age)) {// The age should be greater than or equal to 18.
			return false;
		}
		if ("Chen".equals(name)) {
			if ("M".equals(gender)) {
				return true;
			} else if ("F".equals(gender)) {
				return false;
			}
		} else if ("Ling".equals(name)) {
			if ("F".equals(gender)) {
				return true;
			} else if ("M".equals(gender)) {
				return false;
			}
		}
		return true;
	}
}

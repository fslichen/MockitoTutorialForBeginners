package com.in28minutes.evolution;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockitoInjectMocksTest {
	@InjectMocks// Inject all the mocked dependencies into the following bean.
	AnyService anyService;
	
	// This can be automatically generated when dependency is missing,
	// Just take a look at the error info and then you can see what dependencies are missing.
	@Mock 
	AnotherService anotherService;
	
	@Test
	public void usingMockito() {
		when(anotherService.anyMethod(27)).thenReturn(true);
		assertEquals(true, anyService.anyMethod("Chen", "M", 27));
	}
}

package com.qa.battleships;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.battleships.rest.RestLayer;
import com.qa.battleships.service.ServiceLayer;

@RunWith(MockitoJUnitRunner.class)
public class EndpointTest {

	private static final String MOCK_VALUE2 = "test_value_2";
	private static final String TRUE = "{\"response\":\"true\"}";
	private static final String MOCK_VALUE = "test_value";

	@InjectMocks
	private RestLayer endpoint;

	@Mock
	private ServiceLayer service;

	@Before
	public void setup() {
		endpoint.setLoginService(service);
	}

	@Test
	public void testaddUser() {
		Mockito.when(service.addUser(MOCK_VALUE2)).thenReturn(TRUE);
		assertEquals(TRUE, endpoint.addUser(MOCK_VALUE2));
		Mockito.verify(service).addUser(MOCK_VALUE2);
	}

	@Test
	public void testUpdatePassword() {
		Mockito.when(service.updatePassword(MOCK_VALUE)).thenReturn(TRUE);
		assertEquals(TRUE, endpoint.updatePassword(MOCK_VALUE));
		Mockito.verify(service).updatePassword(MOCK_VALUE);
	}
	
	@Test
	public void testCheckUsername() {
		Mockito.when(service.checkUsername(MOCK_VALUE)).thenReturn(TRUE);
		assertEquals(TRUE, endpoint.checkUsername(MOCK_VALUE));
		Mockito.verify(service).checkUsername(MOCK_VALUE);
	}
	
	@Test
	public void testCheckPassword() {
		Mockito.when(service.checkPassword(MOCK_VALUE)).thenReturn(TRUE);
		assertEquals(TRUE, endpoint.checkPassword(MOCK_VALUE));
		Mockito.verify(service).checkPassword(MOCK_VALUE);
	}
}

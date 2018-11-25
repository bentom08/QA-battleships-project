package com.qa.battleships.mockitoTests;

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
	 	endpoint.setGameService(service);
	}

	@Test
	public void testAddUser() {
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
	
	@Test
	public void testDeleteUser() {
		Mockito.when(service.deleteUser(MOCK_VALUE)).thenReturn(TRUE);
		assertEquals(TRUE, endpoint.deleteUser(MOCK_VALUE));
		Mockito.verify(service).deleteUser(MOCK_VALUE);
	}
	
	@Test
	public void testAddGame() {
		Mockito.when(service.addGame(MOCK_VALUE, MOCK_VALUE2)).thenReturn(TRUE);
		assertEquals(TRUE, endpoint.addGame(MOCK_VALUE, MOCK_VALUE2));
		Mockito.verify(service).addGame(MOCK_VALUE, MOCK_VALUE2);
	}
	
	@Test
	public void testGetUserGames() {
		Mockito.when(service.getUserGames(MOCK_VALUE)).thenReturn(TRUE);
		assertEquals(TRUE, endpoint.getUserGames(MOCK_VALUE));
		Mockito.verify(service).getUserGames(MOCK_VALUE);
	}
	
	@Test
	public void testGetAIGames() {
		Mockito.when(service.getAllGames()).thenReturn(TRUE);
		assertEquals(TRUE, endpoint.getAllGames());
		Mockito.verify(service).getAllGames();
	}
}

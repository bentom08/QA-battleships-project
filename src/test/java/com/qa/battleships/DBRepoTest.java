package com.qa.battleships;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.battleships.persistence.domain.User;
import com.qa.battleships.persistence.repository.DBRepositoryImpl;
import com.qa.battleships.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class DBRepoTest {
	
	@InjectMocks
	private DBRepositoryImpl repo;
	
	@Mock
	private EntityManager em;
	
	private JSONUtil util;
	
	private static final String MOCK_OBJECT = "{\"username\":\"user123\",\"password\":\"password123\"}";
	private static final String MOCK_INCORRECTOBJECT = "{\"username\":\"user123\",\"password\":\"wrong\"}";
	private static final String MOCK_USERNAME = "user123";
	private static final String MOCK_PASSWORD = "password123";
	private static final String MOCK_HASHED = BCrypt.hashpw(MOCK_PASSWORD, BCrypt.gensalt());
	private static final String TRUE = "{\"response\":\"true\"}";
	private static final String FALSE = "{\"response\":\"false\"}";
	
	@Before
	public void setup() {
		repo.setManager(em);
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void addUserTest() {
		assertEquals(TRUE , repo.addUser(MOCK_OBJECT));
	}
	
	@Test
	public void updatePasswordTest() {
		Mockito.when(em.find(User.class, MOCK_USERNAME)).thenReturn(new User(MOCK_USERNAME, MOCK_HASHED));
		assertEquals(TRUE , repo.updatePassword(MOCK_OBJECT));
	}
	
	@Test
	public void checkUserTest() {
		Mockito.when(em.find(User.class, MOCK_USERNAME)).thenReturn(new User(MOCK_USERNAME, MOCK_HASHED));
		Mockito.when(em.find(User.class, null)).thenReturn(null);
		
		assertEquals(TRUE, repo.checkUsername(MOCK_USERNAME));
		assertEquals(FALSE, repo.checkUsername(null));
	}
	
	@Test
	public void checkPasswordTest() {
		Mockito.when(em.find(User.class, MOCK_USERNAME)).thenReturn(new User(MOCK_USERNAME, MOCK_HASHED));
		
		assertEquals(TRUE, repo.checkPassword(MOCK_OBJECT));
		assertEquals(FALSE, repo.checkPassword(MOCK_INCORRECTOBJECT));
	}
	
	@Test
	public void deleteUserTest() {
		Mockito.when(em.find(User.class, MOCK_USERNAME)).thenReturn(new User(MOCK_USERNAME, MOCK_HASHED));
		Mockito.when(em.find(User.class, null)).thenReturn(null);
		
		assertEquals(TRUE, repo.deleteUser(MOCK_USERNAME));
		assertEquals(FALSE, repo.deleteUser(null));
	}

}

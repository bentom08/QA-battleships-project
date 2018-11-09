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

import com.qa.battleships.persistence.domain.Users;
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
	private static final String MOCK_USERNAME = "user123";
	private static final String MOCK_PASSWORD = "password123";
	private static final String MOCK_HASHED = BCrypt.hashpw(MOCK_PASSWORD, BCrypt.gensalt());
	
	@Before
	public void setup() {
		repo.setManager(em);
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void addUserTest() {
		assertEquals(true , repo.addUser(MOCK_OBJECT));
	}
	
	@Test
	public void updatePasswordTest() {
		assertEquals(true , repo.updatePassword(MOCK_PASSWORD, MOCK_USERNAME));
	}
	
	@Test
	public void checkUserTest() {
		Mockito.when(em.find(Users.class, MOCK_USERNAME)).thenReturn(new Users(MOCK_USERNAME, MOCK_HASHED));
		Mockito.when(em.find(Users.class, null)).thenReturn(null);
		
		assertEquals(true, repo.checkUsername(MOCK_USERNAME));
		assertEquals(false, repo.checkUsername(null));
	}
	
	@Test
	public void checkPasswordTest() {
		Mockito.when(em.find(Users.class, MOCK_USERNAME)).thenReturn(new Users(MOCK_USERNAME, MOCK_HASHED));
		
		assertEquals(true, repo.checkPassword(MOCK_PASSWORD, MOCK_USERNAME));
		assertEquals(false, repo.checkPassword("incorrectpassword", MOCK_USERNAME));
	}

}

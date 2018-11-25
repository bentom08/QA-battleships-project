package com.qa.battleships.mockitoTests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.battleships.persistence.domain.Game;
import com.qa.battleships.persistence.domain.User;
import com.qa.battleships.persistence.repository.GameDataRepoImpl;

@RunWith(MockitoJUnitRunner.class)
public class GameDataRepoTest {
	
	@InjectMocks
	private GameDataRepoImpl repo;
	
	@Mock
	private EntityManager em;
	
	@Mock
	private Query query;
	
	private static final String MOCK_USERNAME = "user123";
	private static final String MOCK_PASSWORD = "password123";
	private static final String MOCK_HASHED = BCrypt.hashpw(MOCK_PASSWORD, BCrypt.gensalt());
	private static final String TRUE = "{\"response\":\"true\"}";
	private static final List<Game> MOCK_GAMELIST = Arrays.asList(new Game(), new Game());
	
	@Before
	public void setup() {
		repo.setManager(em);
	}

	@Test
	public void testAddGame() {
		Mockito.when(em.find(User.class, MOCK_USERNAME)).thenReturn(new User(MOCK_USERNAME, MOCK_HASHED));
		assertEquals(TRUE, repo.addGame(MOCK_USERNAME, new Game()));
	}
	
	@Test
	public void testGetUserGames() {
		Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.setParameter(Mockito.anyString(), Mockito.eq(MOCK_USERNAME))).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(MOCK_GAMELIST);
		assertEquals(MOCK_GAMELIST, repo.getUserGames(MOCK_USERNAME));
	}
	
	@Test
	public void testGetAIGames() {
		Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(MOCK_GAMELIST);
		assertEquals(MOCK_GAMELIST, repo.getAllGames());
	}

}
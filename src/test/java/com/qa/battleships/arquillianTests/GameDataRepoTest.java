package com.qa.battleships.arquillianTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;

import com.qa.battleships.persistence.domain.Game;
import com.qa.battleships.persistence.domain.User;
import com.qa.battleships.persistence.repository.DBRepositoryImpl;
import com.qa.battleships.persistence.repository.GameDataRepo;
import com.qa.battleships.persistence.repository.GameDataRepoImpl;
import com.qa.battleships.persistence.repository.UserLogin;

@RunWith(Arquillian.class)
public class GameDataRepoTest {
	
	@Inject
	private GameDataRepoImpl repo;
	
	@Inject
	private DBRepositoryImpl userRepo;
	
	private User user = new User("user", "pass");
	private User user2 = new User("user2", "pass");
	private Game game1 = new Game(user, (byte) 2, 100L, (short)15, (short)15, (byte)10, true, (short)15, (short)15);
	private Game game2 = new Game(user2, (byte) 1, 100L, (short)15, (short)15, (byte)10, true, (short)15, (short)15);
	private static final String TRUE = "{\"response\":\"true\"}";
	
	@Deployment
    public static Archive<?> createDeploymentPackage() {

        return ShrinkWrap.create(JavaArchive.class)
                .addClass(User.class)
                .addClass(GameDataRepoImpl.class)
                .addClass(GameDataRepo.class)
                .addClass(Game.class)
                .addClass(DBRepositoryImpl.class)
                .addClass(BCrypt.class)
                .addClass(UserLogin.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
    }
	
	@Test
	@InSequence(1)
	public void deploymentTest() {
		userRepo.addUser(user);
		userRepo.addUser(user2);
		
		assertNotNull(repo);
	}
	
	@Test
	@InSequence(2)
	public void testAddGame() {
		assertEquals(TRUE, repo.addGame("user", game1));
		assertEquals(TRUE, repo.addGame("user2", game2));
	}
	
	@Test
	@InSequence(3)
	public void testGetUserGames() {
		assertEquals(Arrays.asList(game1).toString(), repo.getUserGames("user").toString());
		assertEquals(Arrays.asList(game2).toString(), repo.getUserGames("user2").toString());
	}
	
	@Test
	@InSequence(4)
	public void testGetAIGames() {
		assertEquals(Arrays.asList(game1, game2).toString(), repo.getAllGames().toString());
	}
	
}
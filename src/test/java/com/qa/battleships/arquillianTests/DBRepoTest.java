package com.qa.battleships.arquillianTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import com.qa.battleships.persistence.domain.User;
import com.qa.battleships.persistence.repository.DBRepositoryImpl;
import com.qa.battleships.persistence.repository.UserLogin;

@RunWith(Arquillian.class)
public class DBRepoTest {
	
	@Inject
	private DBRepositoryImpl repo;
	
	private static final String TRUE = "{\"response\":\"true\"}";
	private static final String FALSE = "{\"response\":\"false\"}";

	
	@Deployment
    public static Archive<?> createDeploymentPackage() {

        return ShrinkWrap.create(JavaArchive.class)
                .addClass(User.class)
                .addClass(DBRepositoryImpl.class)
                .addClass(UserLogin.class)
                .addClass(BCrypt.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
    }
	
	@Test
	@InSequence(1)
	public void deploymentTest() {
		assertNotNull(repo);
	}
	
	@Test
	@InSequence(2)
	public void addUserTest() {
		assertEquals(TRUE, repo.addUser(new User("user", "pass")));
		
		assertEquals(TRUE, repo.checkUsername("user"));
	}
	
	@Test
	@InSequence(3)
	public void checkUsernameTest() {
		assertEquals(TRUE, repo.checkUsername("user"));
		assertEquals(FALSE, repo.checkUsername("notAUser"));
	}
	
	@Test
	@InSequence(4)
	public void checkPasswordTest() {
		assertEquals(TRUE, repo.checkPassword(new User("user", "pass")));
		assertEquals(FALSE, repo.checkPassword(new User("user", "wrongPassword")));
	}
	
	@Test
	@InSequence(5)
	public void updatePasswordTest() {
		assertEquals(TRUE, repo.updatePassword(new User("user", "pass1")));
		assertEquals(TRUE, repo.checkPassword(new User("user", "pass1")));
	}
	
	@Test
	@InSequence(6)
	public void deleteUserTest() {
		assertEquals(FALSE, repo.deleteUser("notAUser"));
		assertEquals(TRUE, repo.deleteUser("user"));
		
		assertEquals(FALSE, repo.checkUsername("user"));
	}
	
}

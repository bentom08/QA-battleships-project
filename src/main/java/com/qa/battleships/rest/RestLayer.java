package com.qa.battleships.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.qa.battleships.service.BattleShipsService;
import com.qa.battleships.service.ServiceLayer;
import com.qa.battleships.service.UserLoginService;

@Path("/battleships")
public class RestLayer {
	
	@Inject
	UserLoginService loginService;
	
	@Inject
	BattleShipsService battleships;

	@POST
	@Path("/addUser")
	@Produces(MediaType.APPLICATION_JSON)
	public String addUser(String jsonUser) {
		return loginService.addUser(jsonUser);
	}
	
	@PUT
	@Path("/updatePassword")
	@Produces(MediaType.APPLICATION_JSON)
	public String updatePassword(String jsonUser) {
		return loginService.updatePassword(jsonUser);
	}
	
	@GET
	@Path("/checkUsername/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String checkUsername(@PathParam("username") String username) {
		return loginService.checkUsername(username);
	}

	@POST
	@Path("/checkPassword")
	@Produces(MediaType.APPLICATION_JSON)
	public String checkPassword(String jsonUser) {
		return loginService.checkPassword(jsonUser);
	}
	
	@DELETE
	@Path("/deleteUser/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteUser(@PathParam("username") String username) {
		return loginService.deleteUser(username);
	}

	@PUT
	@Path("/placeShips")
	@Produces(MediaType.APPLICATION_JSON)
	public String setHasShip(String ships) {
		return battleships.placeShips(ships);
	}
	
	public void setLoginService(ServiceLayer service) {
		this.loginService = service;
	}

	
}
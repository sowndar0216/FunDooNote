package com.bridgeit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.dao.UserDAO;
//import com.bridgeit.model.Note;
//import com.bridgeit.model.Team;
import com.bridgeit.model.User;
import com.bridgeit.model.UserOtp;



@Service
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserDAO userDAO;

	/*public void addTeam(Team team) {
		teamDAO.addTeam(team);		
	}

	public void updateTeam(Team team) {
		teamDAO.updateTeam(team);
	}

	public Team getTeam(int id) {
		return teamDAO.getTeam(id);
	}

	public void deleteTeam(int id) {
		teamDAO.deleteTeam(id);
	}

	public List<Team> getTeams() {
		return teamDAO.getTeams();
	}
*/
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDAO.addUser(user);
	}

	@Override
	public List<User> getUsers() {

		
		return userDAO.getUsers();
	}

	@Override
	public void updateUser(User user,Integer id,User userCon) {

		userDAO.updateUser(user,id,userCon);
	}

	@Override
	public User getUser(Integer id) {
		// TODO Auto-generated method stub
		return userDAO.getUser(id);
	}

	@Override
	public boolean sendOtp(User user) {

		
		return userDAO.sendOtp(user);
	}

	@Override
	public boolean verifyOtp(UserOtp otp) {
		
		return userDAO.verifyOtp(otp);
	}

	@Override
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		userDAO.deleteUser(id);
	}

	@Override
	public String logIn(User user) {
		// TODO Auto-generated method stub
	return userDAO.logIn(user);	
	}

	@Override
	public boolean checkEmail(User user) {
		return userDAO.checkEmail(user);

	}

}

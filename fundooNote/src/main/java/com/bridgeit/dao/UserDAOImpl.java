package com.bridgeit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.bridgeit.dto.UserDto;
import com.bridgeit.model.User;
import com.bridgeit.model.UserOtp;
import com.bridgeit.utility.UserToken;
import com.bridgeit.utility.Utility;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addUser(User user) {
		getCurrentSession().save(user);

	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from User").list();
	}

	public User getUser(Integer id) {
		User user = (User) getCurrentSession().get(User.class, id);
		return user;
	}

	@Override
	public void updateUser(User user, Integer id, User userCon) {

		User userUpdate = getUser(user.getId());
		userUpdate.setName(userCon.getName());
		userUpdate.setEmail(userCon.getEmail());
		getCurrentSession().update(userUpdate);
		// System.out.println("ok");

	}

	@Override
	public boolean sendOtp(User user) {
		String otp = Utility.getOtp();
		System.out.println("otp sent");
		Utility.emailOtp(user, otp);
		UserOtp otp2 = new UserOtp();
		otp2.setEmail(user.getEmail());
		otp2.setOtp(otp);
		otp2.setId(user.getId());
		System.out.println(otp2.getEmail() + "  " + otp2.getOtp() + "  " + otp2.getId());
		// getCurrentSession().update(otp2);
		System.out.println("update");
		getCurrentSession().save(otp2);

		return true;
	}

	@SuppressWarnings("unchecked")
	public List<UserOtp> getOtps() {

		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from UserOtp").list();
	}

	@Override
	public boolean verifyOtp(UserOtp otp) {
		System.out.println("verifyOtp" + otp.getOtp());
		List<UserOtp> otpList = getOtps();

		System.out.println(otpList.get(0).getOtp() + "  " + otp.getOtp());
		for (int i = 0; i < otpList.size(); i++) {
			if (otpList.get(i).getOtp().equals(otp.getOtp())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void deleteUser(Integer id) {

		User user = getUser(id);
		if (user != null) {
			getCurrentSession().delete(user);
		}

	}

	@Override
	public UserDto logIn(User user) {
		HttpHeaders header = new HttpHeaders();
		List<User> userList = getUsers();
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getEmail().equals(user.getEmail())
					&& userList.get(i).getPassword().equals(user.getPassword()))
			{
				System.out.println(userList.get(i).getId());
				ModelMapper mapper = new ModelMapper();

				UserDto dto = mapper.map(userList.get(i), UserDto.class);

				return dto;

			}
		}
		return null;

	}

	@Override
	public boolean checkEmail(User user) {
		System.out.println(user.getEmail());
		List<User> listUser = getUsers();

		if (listUser.size() == 0) {
			return true;
		} else {
			for (int i = 0; i < listUser.size(); i++) {
				System.out.println(listUser.get(i).getEmail());
				if (listUser.get(i).getEmail().equals(user.getEmail())) {
					System.out.println("a");
					return false;
				}
			}

			return true;
		}

	}

	@Override
	public void verifyToken(String token) {
		// TODO Auto-generated method stub
		try {
			UserToken.tokenVerify(token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void resetPassword(User tempUser) {
		// TODO Auto-generated method stub
		User user = new User();

		List<User> userList = getUsers();

		for (int i = 0; i < userList.size(); i++) {

			if (userList.get(i).getEmail().equals(tempUser.getEmail())) {

				user = userList.get(i);
			}
		}

		user.setPassword(tempUser.getPassword());

		getCurrentSession().update(user);

	}

	@Override
	public List<UserOtp> getOtp() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from UserOtp").list();
	}

	@Override
	public boolean resetOtp(User user, String otp) {
		// TODO Auto-generated method stub

		UserOtp otp2 = new UserOtp();
		List<UserOtp> userList = getOtp();
		UserOtp newUser = new UserOtp();
		for (int i = 0; i < userList.size(); i++) {

			if (userList.get(i).getEmail().equals(user.getEmail())) {

				newUser = userList.get(i);
			}
		}
		newUser.setEmail(user.getEmail());
		newUser.setOtp(otp);
		System.out.println(newUser.getEmail() + "  " + newUser.getOtp());
		System.out.println("update");
		getCurrentSession().update(newUser);
		System.out.println("updated");
		return true;

	}

}

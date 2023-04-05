package com.harh.contract.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.harh.contract.commons.User;

import lombok.Getter;


@Service
@Getter
public class UserServiceImpl {
	
	private static List<User> users;
	
	
	public UserServiceImpl() {
		if (users == null) {
			users = new ArrayList<User>();
		}
	}
	
	public List<User> getAllUsers(){
		return UserServiceImpl.users;		
	}
	
	public User getUserById(String userId) {
		List<Object> filteredUser = UserServiceImpl.users.stream().filter(user -> user != null && user.getUserId() != null && user.getUserId().equals(userId))
		.collect(Collectors.toList());
		return (User) filteredUser;
	}
	
	
	public void resetUsers() {
		UserServiceImpl.users = new ArrayList<User>();
	}
	
	public boolean addUser(User user) {
		List<User> filteredUser = UserServiceImpl.users.stream().filter(u -> u.getUserId()!= null && u != null && u.getUserId().equals(user.getUserId()))
		.collect(Collectors.toList());
		
		if (CollectionUtils.isEmpty(filteredUser)) {
			users.add(user);
			return true;
		}
		
		return false;
	}
}

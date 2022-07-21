package com.adminservice.admin;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





@Service
public class UserService  {
	
	@Autowired
	UserRepo userRepo;
	
	public User addUser(User u) {
		User savedEntity =  userRepo.save(u);
		return savedEntity;
	}

	public List<User> getAllUsers() {
		
		return userRepo.findAll();
	}

	public User updateUsers(User u) {
		
		return userRepo.save(u);
	}
	
	@Transactional
	public void deleteUserByID(int userId) {
		userRepo.deleteById(userId);
		
		
	}
	
	

}

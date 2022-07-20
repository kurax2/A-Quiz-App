package com.adminservice.admin;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.adminservice.security.MyUserDetails;




@Service
public class UserService implements UserDetailsService {
	
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
	
	@Override
	public UserDetails loadUserByUsername(String username) throws 
	      UsernameNotFoundException {
		
		User user =  userRepo.getUsersByUsername(username);
		System.out.println(" ");
		System.out.println("--------Inside App User Service IMP ---------- ");
		System.out.println(" Arg :- "+username);
		System.out.println(" From Database "+user);
		
		return new MyUserDetails(user);
		

	}

}

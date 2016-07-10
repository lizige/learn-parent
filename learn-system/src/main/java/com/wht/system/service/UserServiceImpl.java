package com.wht.system.service;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wht.system.dao.UserDAO;
import com.wht.system.dao.UserRepository;
import com.wht.system.model.User;
import com.wht.system.security.UserDetailAdapter;


@Service("userService")
public class UserServiceImpl implements UserService {

	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource(name="userRepository")
	private UserDAO userDAO;
	
	@Resource(name="userRepository")
	private UserRepository userRepository;
	
	
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
		
		UserDetails userDetails = null;
		
		try {
			
			User user = this.userDAO.getUserByLoginName(loginName);
			if(user != null) {
				
				userDetails = new UserDetailAdapter(user);
			}
			
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		
		
		if(userDetails == null) throw new UsernameNotFoundException("�û�����ڻ��������");
		
		return userDetails;
	}


	@Override
	public List<User> listAllUsers() {
		
		return this.userRepository.findAll();
	}


	@Override
	public User createUser(User user) {
		return this.userRepository.save(user);
	}


	@Override
	public void updateUser(User user) {
		
		this.userRepository.save(user);
	}


	@Override
	public void deleteUserById(Long id) {
		
		this.userRepository.delete(id);
	}


	@Override
	public User getUserById(Long id) {
		
		return this.userRepository.findOne(id);
	}


	@Override
	public User getUserByLoginName(String loginName) {
		
		return this.userRepository.getByLoginName(loginName);
	}


	
	
}

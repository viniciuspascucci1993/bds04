package com.devsuperior.bds04.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devsuperior.bds04.entities.User;
import com.devsuperior.bds04.repositores.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(username);
		
		if (user == null) {
			logger.error("User Not Found " + username);
			throw new UsernameNotFoundException("E-mail not found");
		}
		
		logger.info("User Found ! " + username);
		return user;
	}

}

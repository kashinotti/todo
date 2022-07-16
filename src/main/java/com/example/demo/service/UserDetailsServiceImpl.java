package com.example.demo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

		var user = this.userService.findByUsername(username);
		
		if (user == null) {
            throw new UsernameNotFoundException("User [" + username + "] not found.");
        }
        return createUser(user);
	}
	
	public UserDetails createUser(com.example.demo.entity.User user) {

		Set<GrantedAuthority> auth = new HashSet(); 
		auth.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
		User userDetails = new User(user.getUsername(), user.getPassword(), auth);
		return userDetails;
	}
	
}

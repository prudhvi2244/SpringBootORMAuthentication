package com.example.demo.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;

@Service
public class UserServiceImpl implements IUserService ,UserDetailsService {

	@Autowired
	private UserRepository urepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public String saveUser(User user) {
		String password=user.getPassword();
		String encodedPassword=passwordEncoder.encode(password);
		user.setPassword(encodedPassword);
		User savedUser=urepo.save(user);
		String msg="User With ID :"+savedUser.getUid()+" Registered Successfully";
		return msg;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> opt=urepo.findByUsername(username);
		if(opt.isEmpty())
		{
			throw new UsernameNotFoundException("User Does Not Exist!");
		}
		else
		{
			User user=opt.get();
			return new org.springframework.security.core.userdetails
									.User(username,
											user.getPassword(),
											user.getUrole().stream().map(role->new SimpleGrantedAuthority(role))
											.collect(Collectors.toSet())
											);
		}
	}

}

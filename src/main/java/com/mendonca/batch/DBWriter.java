package com.mendonca.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mendonca.models.User;
import com.mendonca.repository.UserRepository;

@Component
public class DBWriter implements ItemWriter<User> {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public void write(List<? extends User> users) throws Exception {
		
		System.out.println(users);
		userRepository.saveAll(users);
	}

	
	
	
	
	
}

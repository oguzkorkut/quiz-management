package com.quiz.service;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.model.User;
import com.quiz.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements IUserService {

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findUserByUsername(String userName) {
		return userRepository.findByUsername(userName);
	}

	@Override
	public boolean deleteById(UUID id) {
		Long deletedCount = userRepository.deleteById(id);
		logger.trace(id);
		logger.trace(deletedCount);
		if (deletedCount > 0) {
			return true;
		} else {
			return false;
		}
	}

}

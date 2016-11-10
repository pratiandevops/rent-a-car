package com.ranko.rent_a_car.service.impl;

import com.ranko.rent_a_car.model.Role;
import com.ranko.rent_a_car.model.User;
import com.ranko.rent_a_car.repository.RoleRepository;
import com.ranko.rent_a_car.repository.UserRepository;
import com.ranko.rent_a_car.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUsername(userName);
	}

	@Override
	public User save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public void remove(Long id) throws IllegalArgumentException {
		User user = userRepository.findOne(id);
		if (user == null) {
			throw new IllegalArgumentException(String.format(
					"User with id=%d does not exist.", id));
		}
		userRepository.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public User findOneWithRoles(Long id) {
		return userRepository.findByUserIdWithRoles(id);
	}

	@Override
	@Transactional(readOnly = true)
	public User findByUserNameWithRoles(String username) {
		return userRepository.findByUserNameWithRoles(username);
	}

	@Override
	public boolean isUsernameUnique(Long id, String username) {
		User user = findByUserName(username);

		return (user == null || ((id != null) && (user.getId() == id)));
	}
}

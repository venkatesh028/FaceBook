package com.ideas2it.ideasbook.service.impl;

import com.ideas2it.ideasbook.repository.UserRepository;
import org.springframework.stereotype.Service;

import com.ideas2it.ideasbook.model.User;
import com.ideas2it.ideasbook.service.UserService;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public User readUser(Long id) {
        return userRepository.findById(id).get();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public User updateUser(Long id, User user) {
        User oldUser = readUser(id);

        if (Objects.nonNull(user.getName()) && !"".equals(user.getName())) {
            oldUser.setName(user.getName());
        }
        if (Objects.nonNull(user.getEmail()) && !"".equals(user.getEmail())) {
            oldUser.setEmail(user.getEmail());
        }
        if (Objects.nonNull(user.getPassword()) && !"".equals(user.getPassword())) {
            oldUser.setPassword(user.getPassword());
        }
        return userRepository.save(oldUser);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "Deleted";
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}


package com.example.iuliana.service;

import com.example.iuliana.dao.UserDao;
import com.example.iuliana.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User save(User user){
        return userDao.save(user);
    }

    public User getUserById(Long id){
        return userDao.findById(id).get();
    }

    public User delete(Long id){
        User user = getUserById(id);
        userDao.delete(user);
        return user;
    }

    public User update(Long id, User modifiedUser){
        User user = getUserById(id);
        mapUsers(user, modifiedUser);
        save(user);
        return modifiedUser;
    }

    public List<User> getAllUsers(){
        return userDao.findAll();
    }


    private void mapUsers(User user, User modifiedUser) {
        user.setFirstName(modifiedUser.getFirstName());
        user.setLastName(modifiedUser.getLastName());
        user.setPosts(modifiedUser.getPosts());
    }
}

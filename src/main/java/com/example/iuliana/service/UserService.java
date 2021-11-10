package com.example.iuliana.service;

import com.example.iuliana.dao.UserDao;
import com.example.iuliana.model.Role;
import com.example.iuliana.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

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

    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if(user == null){
            throw  new UsernameNotFoundException(String.format("User '%s' not found.", username));
        }
        return new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());
    }

    private void mapUsers(User user, User modifiedUser) {
        user.setUsername(modifiedUser.getUsername());
        user.setPosts(modifiedUser.getPosts());
    }
}

package com.example.iuliana.service;

import com.example.iuliana.dao.RoleDao;
import com.example.iuliana.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role save(Role role){
        return roleDao.save(role);
    }

    public Role getById(Long id){
        return roleDao.getById(id);
    }

    public Role delete(Long id){
        Role role = getById(id);
        roleDao.delete(role);
        return role;
    }

    public Role update(Long id, Role modifiedRole){
        Role role = roleDao.getById(id);
        map(role, modifiedRole);
        return role;
    }

    public List<Role> getAllRoles(){
        return roleDao.findAll();
    }

    private void map(Role role, Role modifiedRole) {
        role.setName(modifiedRole.getName());
    }
}

package com.example.demo.service;

import com.example.demo.dao.UserDAO;
import com.example.demo.dto.UserDTO;
import com.example.demo.exceptions.ResourceNotFound;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.constants.Constants.EMPTY;
import static com.example.demo.constants.Constants.INVALID;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public User addUser(User user){
        if(user==null){
            throw new ResourceNotFound(INVALID);
        }
        userDAO.addUser(user);

        return user;
    }
    public List<User> getAllUsers(){
        if(userDAO.userList.isEmpty()){
            throw new ResourceNotFound(EMPTY);
        }
        return userDAO.getAllUsers();
    }
    public User getUserByName(String name){
        if(userDAO.userList.isEmpty()){
            throw new ResourceNotFound(EMPTY);
        }
        return userDAO.getUserByName(name);
    }

    public User getUsername(String name){
        for(User p : userDAO.getAllUsers()){
            if(p.getUserName().equals(name)){
                return p;
            }
        }
        return null;
    }


}

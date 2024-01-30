package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class UserDAO {
   public List<User> userList;

    public UserDAO(){
        this.userList = new ArrayList<>();
    }

    public User addUser(User user){
        userList.add(user);
        return user;
    }

    public User getUserByName(String name){
        for(int i=0 ;i<userList.size();i++){
            if(userList.get(i).getUserName().equals(name)){
                return userList.get(i);
            }
        }
        return null;
    }

    public List<User> getAllUsers(){
        return userList;
    }





}

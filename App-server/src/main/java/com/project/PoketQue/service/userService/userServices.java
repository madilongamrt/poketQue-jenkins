package com.project.PoketQue.service.userService;

import com.project.PoketQue.Models.User;
import com.project.PoketQue.Repository.userRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class userServices {

    @Autowired
    public userRepo userepo;

    /**
     * Save user and read if it already exists
     */
    public Object save(User person) {
        Optional<User> user = Optional.ofNullable(userepo.getByUseremail(person.getEmail()));
        if(user.isPresent()){
            return user.get();
        }
        return userepo.save(person);
    }

    /**
     * get a specific user by id
     */
    public Object getById(Integer userid) {
        Optional<User> usr = userepo.findById(userid);
        if(usr.isPresent()) {
        	return usr.get();
        }
        return "user not found";
    }

}
package com.project.PoketQue.Controller;

import com.project.PoketQue.Models.User;
import com.project.PoketQue.service.userService.userServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("api/user")
public class UserController {

    @Autowired
    public userServices userserv;

    /**
     * Adds/saves a user
     */
    @PostMapping("/addUser")
    public Object createUser(@Valid @RequestBody User person) {
        return userserv.save(person);
    }
    /**
     * Get user by id
     */
    @GetMapping("/getUserid/{userid}")
    public ResponseEntity<Object> getUserById(@Valid @PathVariable(value = "userid") Integer userid) {
        Object user = userserv.getById(userid);

        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(user);
        }

    }



}
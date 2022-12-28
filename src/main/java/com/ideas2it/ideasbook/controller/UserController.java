package com.ideas2it.ideasbook.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.ideasbook.model.User;
import com.ideas2it.ideasbook.service.UserService;

/**
 * Controller for the user get the request  from the client
 * and provide the response to the client
 *
 * @author Venkatesh TM
 * @version 1.0
 * @since 28/12/2022
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
         this.userService = userService;
    }

    /**
     * Gets the user details from the request body
     * send the user details to the service
     * inorder to save user details
     *
     * @param user - Holds the details of the user
     * @return ResponseEntity - Holds the user object and HttpStatus code
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
         return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    /**
     * Calls the service inorder to get List of users
     *
     * @return ResponseEntity - Holds the List of user object and HttpStatus code
     */
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {

         return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    /**
     * Gets the id from the request then call the service
     * Inorder to get the user based on the id
     *
     * @return ResponseEntity - Holds the user object and HttpStatus code
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {

         return new ResponseEntity<>(userService.readUser(id), HttpStatus.FOUND);
    }

    /**
     * Gets the user details from the request
     * send the user details to service inorder to update
     * the user
     *
     * @param id  - id of the user
     * @param user - Holds the updated user details
     * @return ResponseEntity - Holds the user object and HttpStatus code
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
    }

    /**
     * Gets the id from the request
     * send the id to service inorder to delete the user
     *
     * @param id - id of the user
     * @return ResponseEntity - Holds the Message and HttpStatus code
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }
}

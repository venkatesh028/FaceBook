package com.ideas2it.ideasbook.service;

import java.util.List;

import com.ideas2it.ideasbook.model.User;

/**
 *  Contains the Method to perform the creation, reade , update
 *  delete and get operation for the user and return the result
 *  to the controller
 *
 * @author Venkatesh TM
 * @version 1.0 28/12/2022
 */
public interface UserService {

    /**
     * Calls the method from the jpa repository inorder to
     * save the user details then return the
     * user object to the controller
     *
     * @param user - Holds the user details
     * @return User - user object which saved in the database
     */
    public User createUser(User user);

    /**
     * Calls the method from the jpa repository inorder to
     * get the user details then return the
     * user object to the controller
     *
     * @param id - Holds the user details
     * @return User - user object which saved in the database
     */
    public User readUser(Long id);

    /**
     * Calls the method from the jpa repository inorder to
     * Update the user then return the
     * user object to the controller
     *
     * @param id - id of the user need to be updated
     * @param user - Holds the user details
     * @return User - Holds the user details
     */
    public User updateUser(Long id, User user);

    /**
     * Calls the method from the jpa repository inorder to
     * delete the user then return the message
     * to the controller
     *
     * @param id - id of the user
     * @return message - Holds the success message
     */
    public String deleteUser(Long id);

    /**
     * Calls the method from the jpa repository inorder to
     * get the list of user and return the List of users
     * to the controller
     *
     * @return listOfUsers - Holds the list of user
     */
    public List<User> getUsers();
}

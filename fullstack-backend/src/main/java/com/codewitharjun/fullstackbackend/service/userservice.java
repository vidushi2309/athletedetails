package com.codewitharjun.fullstackbackend.service;

import com.codewitharjun.fullstackbackend.exception.UserNotFoundException;
import com.codewitharjun.fullstackbackend.model.User;
import com.codewitharjun.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class userservice {

    @Autowired
    private UserRepository userRepository;

    public User  createeUser(User newwUser, MultipartFile imageFile)throws IOException
            {
        newwUser.setImageUrl(imageFile.getOriginalFilename());
        newwUser.setImageType(imageFile.getContentType());
        newwUser.setImageData(imageFile.getBytes());
       return userRepository.save(newwUser);
    }

    /**
     * Create a new user.
     * @param newUser the new user details.
     * @return the saved user.
     */

    /**
     * Get all users.
     * @return list of all users.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Get user by ID.
     * @param id the user ID.
     * @return the user details.
     * @throws UserNotFoundException if the user does not exist.
     */
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Update an existing user by ID.
     * @param newUser the updated user details.
     * @param id the user ID.
     * @return the updated user.
     * @throws UserNotFoundException if the user does not exist.
     */
    public User updateUser(User newUser, Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Delete a user by ID.
     * @param id the user ID.
     * @return a confirmation message.
     * @throws UserNotFoundException if the user does not exist.
     */
    public String deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id " + id + " has been deleted successfully.";
    }
}


package com.daymeijroos.iacspring.dao;

import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import com.daymeijroos.iacspring.model.User;
import com.daymeijroos.iacspring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class UserDAO implements DAO<User>{
    private final UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public User getByEmail(String email) throws ResourceNotFoundException {
        Optional<User> result = userRepository.findByEmail(email);
        if(result.isPresent()) {
            return result.get();
        }else {
            throw new ResourceNotFoundException("User", email);
        }
    }

    @Override
    public User getById(String id) throws ResourceNotFoundException {
        Optional<User> result = userRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("User", id);
        }
    }

    @Override
    public User saveToDatabase(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User update(String id, User userRequest) throws ResourceNotFoundException {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
        user.setEmail(userRequest.getEmail());
        return userRepository.save(user);
    }

    @Override
    public void delete(String id) throws ResourceNotFoundException {
        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
        this.userRepository.deleteById(id);
    }
}

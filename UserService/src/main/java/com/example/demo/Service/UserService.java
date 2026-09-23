package com.example.demo.Service;

import com.example.demo.Model.UserEntity;
import com.example.demo.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Böyle bir kullanıcı yok."));
    }
    public UserEntity createUser(UserEntity user){
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }
    public void deleteUser(Long id){
        if (!userRepository.existsById(id)){
            throw new RuntimeException("Böyle bir kullanıcı yok");
        }else
            userRepository.deleteById(id);
    }
    public UserEntity updateUser(Long id,UserEntity updateData){
        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Böyle bir kullanıcı yok"));
        existingUser.setFullName(updateData.getFullName());
        existingUser.setEmail(updateData.getEmail());

        return userRepository.save(existingUser);
    }
}

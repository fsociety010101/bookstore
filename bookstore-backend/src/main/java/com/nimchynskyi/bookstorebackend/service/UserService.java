package com.nimchynskyi.bookstorebackend.service;

import com.nimchynskyi.bookstorebackend.model.Order;
import com.nimchynskyi.bookstorebackend.model.User;
import com.nimchynskyi.bookstorebackend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addOrder(Order order, String email) {
        var user = userRepository.findByEmail(email);
        if (!user.isPresent()){
            throw new EntityNotFoundException();
        }
        user.get().addOrder(order);
        userRepository.save(user.get());

    }


    public void removeOrder(Order order) {
        var user = userRepository.findByOrdersContaining(order);
        if(!user.isPresent()){
            throw new EntityNotFoundException();
        }
        user.get().getOrders().remove(order);

    return;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow( () -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),user.getAuthorities());
    }
}

package com.nimchynskyi.bookstorebackend;

import com.nimchynskyi.bookstorebackend.model.Item;
import com.nimchynskyi.bookstorebackend.model.Order;
import com.nimchynskyi.bookstorebackend.model.User;
import com.nimchynskyi.bookstorebackend.repository.ItemRepository;
import com.nimchynskyi.bookstorebackend.repository.UserRepository;
import com.nimchynskyi.bookstorebackend.security.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DbInit implements CommandLineRunner {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Autowired
    public DbInit(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        itemRepository.saveAll(List.of(
                new Item("Harry Potter 1", new BigDecimal("118.45"), "https://m.media-amazon.com/images/I/51zZ3SAulVL._SX334_BO1,204,203,200_.jpg"),
                new Item("Ziemia Obiecana", new BigDecimal("77.79"), "https://m.media-amazon.com/images/I/41hm3xYuv3L._SX331_BO1,204,203,200_.jpg"),
                new Item("NIC MNIE NIE ZŁAMIE", new BigDecimal("36.46"), "https://m.media-amazon.com/images/I/41ZVR-cw7wL._SX333_BO1,204,203,200_.jpg")
        ));
        User user = new User("admin@gmail.com","admin",Role.ROLE_ADMIN,new ArrayList<Order>());
        User user1 = new User("user@gmail.com","user", Role.ROLE_USER,new ArrayList<Order>());
        userRepository.saveAll(List.of(user,user1));
    }
}


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
                new Item("The Art of Clean Code. Jak pisać czysty kod.", new BigDecimal("41.30"), "https://static01.helion.com.pl/global/okladki/vbig/theart.jpg"),
                new Item("JavaScript. Przewodnik. Poznaj język.", new BigDecimal("69.50"), "https://static01.helion.com.pl/global/okladki/vbig/jspp7v.jpg"),
                new Item("React w działaniu. Tworzenie aplikacji internetowych.", new BigDecimal("39.99"), "https://static01.helion.com.pl/global/okladki/vbig/reacw2.jpg"),
                new Item("Java. Kompendium programisty.", new BigDecimal("139.30"), "https://static01.helion.com.pl/global/okladki/vbig/javk12.jpg"),
                new Item("Kombinacje C++. 648 łamigłówek.", new BigDecimal("99.98"), "https://static01.helion.com.pl/global/okladki/vbig/komcpp.jpg"),
                new Item("Potoki danych. Leksykon kieszonkowy. ", new BigDecimal("34.49"), "https://static01.helion.com.pl/global/okladki/vbig/potdan.jpg"),
                new Item("Cyberbezpieczeństwo dla bystrzaków. Wydanie II", new BigDecimal("71.25"), "https://static01.helion.com.pl/global/okladki/vbig/cybeb2.jpg"),
                new Item("Kubernetes. Tworzenie systemów rozproszonych.", new BigDecimal("142.90"), "https://static01.helion.com.pl/global/okladki/vbig/kuber3.jpg")
        ));
        User user = new User("admin@gmail.com","admin",Role.ROLE_ADMIN,new ArrayList<Order>());
        User user1 = new User("user@gmail.com","user", Role.ROLE_USER,new ArrayList<Order>());
        userRepository.saveAll(List.of(user,user1));
    }
}


package com.danielprinz.udemy;

import com.danielprinz.udemy.auth.persistence.UserEntity;
import com.danielprinz.udemy.auth.persistence.UserRepository;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;

import javax.inject.Singleton;

@Singleton
public class TestDataProvider {

    private final UserRepository users;

    public TestDataProvider(final UserRepository users) {
        this.users = users;
    }

    @EventListener
    public void init(StartupEvent event) {
        final String email = "alice@example.com";
        if (users.findByEmail(email).isEmpty()) {
            final UserEntity alice = new UserEntity();
            alice.setEmail(email);
            alice.setPassword("secret");
            users.save(alice);
        }
    }
}

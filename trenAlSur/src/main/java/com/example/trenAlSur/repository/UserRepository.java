package com.example.trenAlSur.repository;

import com.example.trenAlSur.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {

    Optional<User> findByUsuario(String usuario);
}

package com.cleancodeaddict.springsecurtymultipleauthentication.repositories;

import com.cleancodeaddict.springsecurtymultipleauthentication.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {

    Optional<Authority> findByAuthName(String authName);
}

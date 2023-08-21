package com.cleancodeaddict.springsecurtymultipleauthentication.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Authority {
    @Id @GeneratedValue
    private Long id;
    private String authName;
    @ManyToMany(mappedBy = "authorities")
    private Set<AppUser> users;
}

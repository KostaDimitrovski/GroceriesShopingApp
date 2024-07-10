package com.example.groceriesShoping.model;


import com.example.groceriesShoping.enums.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    //, cascade = CascadeType.ALL, fetch = FetchType.LAZY
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonBackReference
    private Cart cart;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonBackReference
    private Wishlist wishlist;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id != null ? id.equals(user.id) : user.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}

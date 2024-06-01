package com.rhmaster.rhmaster.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
})
@Builder
public class User {
        @Id
        @GeneratedValue
        private UUID id;

        private String username;

        @Column(name = "nombres")
        private String name;

        @Column(name = "apellidos")
        private String lastname;

        private String email;
        private String password;

        @Column(name = "habilitado")
        private boolean enabled;

        @OneToOne(fetch = FetchType.LAZY)
        @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
        private Role rol = new Role(ERole.ROLE_CANDIDATO);
}

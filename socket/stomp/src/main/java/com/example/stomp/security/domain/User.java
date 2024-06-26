package com.example.stomp.security.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

@NoArgsConstructor
@Entity
@Table(name = "users")
@Getter
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * The email (unique)
     */
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    /**
     * The password (will be encoded)
     */
    @Column(name = "password")
    private String password;

    /**
     * The nickname with code (unique)
     */
    @Column(name = "nickname", unique = true)
    private String nickname;

    /**
     * The constructor of User
     *
     * @param email The email
     * @param password The password
     * @param nickname The nickname with code
     */
    @Builder
    public User(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    /**
     * Get your nickname without code
     *
     * @return Return nickname of nickname#0000 form
     */
    public String getNicknameWithoutCode() {
        return this.nickname.contains("#") ? new StringTokenizer(this.nickname, "#").nextToken() : this.nickname;
    }

    /**
     * Get your code in nickname
     *
     * @return The code of nickname
     */
    public String getNicknameCode() {
        return this.nickname.contains("#") ? this.nickname.split("#")[1] : null;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

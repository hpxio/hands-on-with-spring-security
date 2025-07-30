package io.dice28.codelabs.security.jwt.repository.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.dice28.codelabs.security.jwt.model.UserProfileType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_PROFILE")
public class UserProfileEntity implements UserDetails {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private boolean isActive;
    private boolean isFlagged;

    @Column(unique = true, length = 36, nullable = false)
    private String username;

    @Column(length = 36, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String flagReason;

    private UserProfileType profileType;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdTimeStamp;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedTimeStamp;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /* TODO: revisit to understand purpose */
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isActive && !this.isFlagged;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isActive && !this.isFlagged;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        /* TODO: revisit during refresh token implementation */
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive && !this.isFlagged;
    }
}

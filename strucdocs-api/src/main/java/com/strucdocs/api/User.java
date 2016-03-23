package com.strucdocs.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;
import java.util.Collection;

import static java.util.Arrays.asList;

/**
 * A implementation of {@link org.springframework.security.core.userdetails.UserDetails}. It represents a User in the
 * StrucDocs system.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "username"})
@ToString
public class User
    implements org.springframework.security.core.userdetails.UserDetails {

    private static final long serialVersionUID = -8404328451078811028L;

    private Long id;

    @NotNull
    private String username;

    @NotNull
    @JsonIgnore
    private String password;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return asList(() -> "ROLE_ADMIN", () -> "ROLE_USER");
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}

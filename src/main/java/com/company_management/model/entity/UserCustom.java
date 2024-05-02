package com.company_management.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USER_CUSTOM")
public class UserCustom implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "USER_NAME")
    private String username;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    @JsonIgnore
    private String password;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "ACTIVE")
    private Integer active;
    @Column(name = "ACTIVE_CODE")
    private String activeCode;
    @Column(name = "FORGOT_PASSWORD")
    private String forgotPassword;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @Column(name = "USER_DETAIL_ID")
    private Long userDetailId;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinTable(name = "USER_CUSTOM_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns =
    @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles = new HashSet<>() ;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthority =
                this.roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());

        return simpleGrantedAuthority;
    }
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
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
    public String getUser(){
        return this.username;
    }
}

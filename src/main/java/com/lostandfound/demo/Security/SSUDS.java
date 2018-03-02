package com.lostandfound.demo.Security;


import com.lostandfound.demo.Model.AppUser;
import com.lostandfound.demo.Model.Role;
import com.lostandfound.demo.Repository.AppUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class SSUDS implements UserDetailsService {
    private AppUserRepository userRepo;

    public SSUDS(AppUserRepository userRepository) {
        this.userRepo = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Set <GrantedAuthority> userAuthorities = new HashSet<>();
        AppUser thisUser = userRepo.findAppUserByUsername(username);
        return new User(thisUser.getUsername(),thisUser.getPassword(),grantedAuthorities(thisUser));
    }

    public Set <GrantedAuthority> grantedAuthorities(AppUser user)
    {
        Set <GrantedAuthority> userAuthorities = new HashSet<>();
        for(Role eachRole: user.getRoles())
        {
            userAuthorities.add(new SimpleGrantedAuthority(eachRole.getRoleName()));
        }
        return userAuthorities;
    }
}

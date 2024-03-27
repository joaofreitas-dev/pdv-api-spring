package com.joaofreitas.pdvapi.jwt;

import com.joaofreitas.pdvapi.entities.User;
import com.joaofreitas.pdvapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private  UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        return new JwtUserDetails(user);
    }

    public JwtToken getTokenAuthenticated (String username) {
        User.Role role = userService.findByRoleUsername(username);
        return JwtUtils.createToken(username, role.name().substring("ROLE_".length()));
    }
}

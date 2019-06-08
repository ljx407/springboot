package com.ljx.chapter13.security;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chapter13UserDetailServiceImpl implements UserDetailsService {

    final static Map<String, Map<String, Object>> userRepository = new HashMap<>();

    static {
        userRepository.put("jasonliu", ImmutableMap.of("password", "$2a$10$pWGWlw2CD6BHsU2yXn9OlOQDm7Dy1g1s6h409DkXIJY26i46OAhXq", "roles", Lists.newArrayList("ROLE_ADMIN")));
        userRepository.put("peggy", ImmutableMap.of("password", "$2a$10$pWGWlw2CD6BHsU2yXn9OlOQDm7Dy1g1s6h409DkXIJY26i46OAhXq", "roles", Lists.newArrayList("ROLE_ADMIN")));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!userRepository.containsKey(username)) {
            throw new UsernameNotFoundException(username);
        }
        Map<String, Object> userInfo = userRepository.get(username);
        List<String> roles = (List<String>) userInfo.get("roles");
        UserDetails userDetails = User.builder().username(username)
                .password((String) userInfo.get("password"))
                .authorities(roles.toArray(new String[roles.size()]))
                .build();
        return userDetails;
    }
}

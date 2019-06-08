package com.ljx.chapter12.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
public class UserAuthDetailService implements UserDetailsService {

    private final String USER_PREFIX = "user:";
    private final String ROLE_PREFIX = "role:";

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(!redisTemplate.hasKey(USER_PREFIX + username)) {
            log.info("账户：{} 不存在！", USER_PREFIX + username);
//            return User.builder().build();
            throw new RuntimeException("无效的账号！");
        }

        String pass = (String)redisTemplate.opsForValue().get(USER_PREFIX + username);
        List<String> roles = redisTemplate.opsForList().range(ROLE_PREFIX + username, 0, -1);

        if(CollectionUtils.isEmpty(roles)) {
            log.info("用不角色：{} 不存在！", ROLE_PREFIX + username);
            throw new RuntimeException("用户未授权！");
        }

        UserDetails details = User.withUsername(username)
                .password(pass)
                .authorities(CollectionUtils.isEmpty(roles) ? null : roles.toArray(new String[roles.size()]))
                .build();

        return details;
    }
}

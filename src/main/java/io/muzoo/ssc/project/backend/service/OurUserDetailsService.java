package io.muzoo.ssc.project.backend.service;

import io.muzoo.ssc.project.backend.entity.UserEntity;
import io.muzoo.ssc.project.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OurUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findFirstByUserName(username);

        if(user != null){
            return User.withUsername(user.getUserName())
                    .password(user.getPassword())
                    .authorities("dfasdfasd").build();
        }else{
            throw new UsernameNotFoundException("Invalid username or password!!!!");
        }
    }
}
